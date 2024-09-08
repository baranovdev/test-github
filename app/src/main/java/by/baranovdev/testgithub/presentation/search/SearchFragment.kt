package by.baranovdev.testgithub.presentation.search

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.baranovdev.testgithub.R
import by.baranovdev.testgithub.application.TestApplication
import by.baranovdev.testgithub.databinding.FragmentSearchBinding
import by.baranovdev.testgithub.di.ViewModelFactory
import by.baranovdev.testgithub.domain.entity.GithubRepositoryEntity
import by.baranovdev.testgithub.domain.entity.User
import by.baranovdev.testgithub.presentation.base.adapter.EmptyStateItem
import by.baranovdev.testgithub.presentation.base.adapter.ErrorItem
import by.baranovdev.testgithub.presentation.base.adapter.LoadingItem
import by.baranovdev.testgithub.presentation.base.adapter.NothingFoundItem
import by.baranovdev.testgithub.presentation.search.adapter.items.RepositoryItem
import by.baranovdev.testgithub.presentation.search.adapter.items.UserItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import javax.inject.Inject

class SearchFragment() : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private val adapter = GroupAdapter<GroupieViewHolder>()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as TestApplication).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[SearchViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvResults.layoutManager = LinearLayoutManager(this.context)

        if (viewModel.currentQuery.value.isNullOrEmpty()) setInitState()

        binding.tilSearch.editText?.doOnTextChanged { text, start, before, count ->
            text?.let {
                if (it.length > 2) {
                    setSearchButtonIconState(true)
                    binding.tilSearch.error = null
                } else {
                    setSearchButtonIconState(false)
                    binding.tilSearch.error = getString(R.string.input_length_error)
                }
            }
        }

        binding.tilSearch.editText?.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                onSearchClicked()
            }
            true
        }

        binding.btnSearch.setOnClickListener {
            onSearchClicked()
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.tilSearch.isEnabled = false
                binding.btnSearch.isEnabled = false
                setSearchButtonIconState(false)
                adapter.clear()
                adapter.add(LoadingItem())
                binding.rvResults.adapter = adapter
            } else {
                binding.tilSearch.isEnabled = true
                binding.btnSearch.isEnabled = true
                setSearchButtonIconState(true)
            }
        }

        viewModel.currentQuery.observe(viewLifecycleOwner) {
            binding.tilSearch.editText?.setText(it, TextView.BufferType.NORMAL)
            viewModel.search(it)
        }

        viewModel.searchResult.observe(viewLifecycleOwner) {
            adapter.clear()
            if (it.isEmpty()) adapter.add(NothingFoundItem())
            it.forEach { result ->
                when (result) {
                    is GithubRepositoryEntity -> {
                        adapter.add(RepositoryItem(result, ::navigateToBrowserFragment))
                    }

                    is User -> {
                        adapter.add(UserItem(result, ::onUserClick))
                    }
                }
            }
            binding.rvResults.adapter = adapter
        }

        viewModel.error.observe(viewLifecycleOwner) {
            if (it?.isNotEmpty() == true) {
                adapter.clear()
                adapter.add(ErrorItem(it, ::onRetryClick))
            }
        }

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun setSearchButtonIconState(isEnabled: Boolean) {
        if (isEnabled) {
            binding.btnSearch.setImageDrawable(context?.getDrawable(R.drawable.ic_search_enable))
        } else {
            binding.btnSearch.setImageDrawable(context?.getDrawable(R.drawable.ic_search_disabled))
        }
    }

    private fun navigateToBrowserFragment(url: String, name: String) {
        findNavController().navigate(
            SearchFragmentDirections.actionSearchFragmentToBrowserFragment(
                root = url, name = name
            )
        )
    }

    private fun validateInput(): Boolean = getInput().length > 2

    private fun getInput(): String = binding.tilSearch.editText?.text?.toString().orEmpty()

    private fun onUserClick(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    private fun setInitState() {
        adapter.add(EmptyStateItem())
        binding.rvResults.adapter = adapter
    }

    private fun onRetryClick() {
        val query = viewModel.currentQuery.value
        query?.let {
            viewModel.search(it)
        } ?: run {
            adapter.clear()
            adapter.add(EmptyStateItem())
        }
    }

    private fun onSearchClicked() {
        if (validateInput()) viewModel.setQuery(getInput())
        else binding.tilSearch.error = getString(R.string.input_length_error)
    }

}
package by.baranovdev.testgithub.presentation.browser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.baranovdev.testgithub.application.TestApplication
import by.baranovdev.testgithub.databinding.FragmentBrowserBinding
import by.baranovdev.testgithub.di.ViewModelFactory
import by.baranovdev.testgithub.domain.entity.PathEntityType
import by.baranovdev.testgithub.presentation.base.adapter.ErrorItem
import by.baranovdev.testgithub.presentation.base.adapter.LoadingItem
import by.baranovdev.testgithub.presentation.browser.adapter.items.BackItem
import by.baranovdev.testgithub.presentation.browser.adapter.items.DirectoryItem
import by.baranovdev.testgithub.presentation.browser.adapter.items.FileItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import javax.inject.Inject

class BrowserFragment : Fragment(){

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: BrowserViewModel

    val adapter = GroupAdapter<GroupieViewHolder>()

    val args: BrowserFragmentArgs by navArgs()

    private lateinit var binding: FragmentBrowserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as TestApplication).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[BrowserViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBrowserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvBrowser.adapter = adapter

        binding.tvPath.text = args.name

        if (!viewModel.currentPath.isInitialized) {
            viewModel.getPath(args.root)
        }

        viewModel.currentPath.observe(viewLifecycleOwner) {
            adapter.clear()
            viewModel.pathStack.value?.let { stack ->
                if (stack.size > 1) adapter.add(
                    BackItem(::backToParent)
                )
            }
            it.forEach { item ->
                when (item.type) {
                    PathEntityType.DIR -> {
                        adapter.add(DirectoryItem(item, ::onClickDirectory))
                    }
                    PathEntityType.FILE -> adapter.add(FileItem(item, ::onClickFile))
                    PathEntityType.UNKNOWN -> {
                        //skip
                    }
                }
            }
            binding.rvBrowser.adapter = adapter
        }

        viewModel.error.observe(viewLifecycleOwner){
            if(it?.isNotEmpty() == true) {
                adapter.clear()
                adapter.add(ErrorItem(it, ::onRetryClick))
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner){
            if(it){
                adapter.clear()
                adapter.add(LoadingItem())
            }
        }
    }

    private fun onClickDirectory(url: String) {
        viewModel.getPath(url)
    }


    private fun onClickFile(htmlUrl: String, name: String) {
        findNavController().navigate(BrowserFragmentDirections.actionBrowserFragmentToWebViewFragment(htmlUrl, name))
    }

    private fun backToParent(){
        viewModel.navigateBackLocally()
    }

    private fun onRetryClick(){
        val lastPath = viewModel.pathStack.value?.lastOrNull()
        viewModel.getPath(lastPath ?: args.root, false)
    }

}
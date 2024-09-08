package by.baranovdev.testgithub.presentation.search.adapter.items

import android.view.View
import by.baranovdev.testgithub.R
import by.baranovdev.testgithub.databinding.ItemRepositoryBinding
import by.baranovdev.testgithub.domain.entity.GithubRepositoryEntity
import com.xwray.groupie.viewbinding.BindableItem

class RepositoryItem(
    private val repository: GithubRepositoryEntity,
    private val onClick: (String, String) -> Unit
) :
    BindableItem<ItemRepositoryBinding>() {
    override fun bind(viewBinding: ItemRepositoryBinding, position: Int) {
        viewBinding.tvTitle.text = repository.name
        viewBinding.tvDescription.text = repository.description
        viewBinding.root.setOnClickListener {
            onClick.invoke(repository.contentsUrl, repository.name)
        }
        viewBinding.tvForksCount.text = repository.forksCount.toString()
    }

    override fun getLayout(): Int = R.layout.item_repository

    override fun initializeViewBinding(view: View): ItemRepositoryBinding =
        ItemRepositoryBinding.bind(view)

}
package by.baranovdev.testgithub.presentation.browser.adapter.items

import android.view.View
import by.baranovdev.testgithub.R
import by.baranovdev.testgithub.databinding.ItemDirectoryBinding
import by.baranovdev.testgithub.domain.entity.PathEntity
import com.xwray.groupie.viewbinding.BindableItem

class DirectoryItem(
    private val dir: PathEntity,
    private val onClick: (String) -> Unit
) : BindableItem<ItemDirectoryBinding>() {
    override fun bind(viewBinding: ItemDirectoryBinding, position: Int) {
        viewBinding.root.setOnClickListener{
            onClick.invoke(dir.url)
        }
        viewBinding.tvName.text = dir.name
    }

    override fun getLayout(): Int = R.layout.item_directory

    override fun initializeViewBinding(view: View): ItemDirectoryBinding = ItemDirectoryBinding.bind(view)

}
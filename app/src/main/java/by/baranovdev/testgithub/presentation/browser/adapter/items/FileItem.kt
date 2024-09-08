package by.baranovdev.testgithub.presentation.browser.adapter.items

import android.view.View
import by.baranovdev.testgithub.R
import by.baranovdev.testgithub.databinding.ItemFileBinding
import by.baranovdev.testgithub.domain.entity.PathEntity
import com.xwray.groupie.viewbinding.BindableItem

class FileItem(
    private val file: PathEntity,
    private val onClick: (String, String) -> Unit
): BindableItem<ItemFileBinding>() {
    override fun bind(viewBinding: ItemFileBinding, position: Int) {
        viewBinding.root.setOnClickListener{
            onClick.invoke(file.htmlUrl, file.name)
        }
        viewBinding.tvName.text = file.name
    }

    override fun getLayout(): Int = R.layout.item_file

    override fun initializeViewBinding(view: View): ItemFileBinding = ItemFileBinding.bind(view)

}
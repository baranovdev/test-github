package by.baranovdev.testgithub.presentation.browser.adapter.items

import android.view.View
import by.baranovdev.testgithub.R
import by.baranovdev.testgithub.databinding.ItemBackBinding
import com.xwray.groupie.viewbinding.BindableItem

class BackItem(
    private val onClick: () -> Unit
) : BindableItem<ItemBackBinding>() {
    override fun bind(viewBinding: ItemBackBinding, position: Int) {
        viewBinding.root.setOnClickListener{
            onClick.invoke()
        }
    }

    override fun getLayout(): Int = R.layout.item_back

    override fun initializeViewBinding(view: View): ItemBackBinding = ItemBackBinding.bind(view)

}
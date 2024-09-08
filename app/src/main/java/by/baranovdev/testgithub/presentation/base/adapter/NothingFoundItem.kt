package by.baranovdev.testgithub.presentation.base.adapter

import android.view.View
import by.baranovdev.testgithub.R
import by.baranovdev.testgithub.databinding.ItemNothingFoundBinding
import com.xwray.groupie.viewbinding.BindableItem

class NothingFoundItem: BindableItem<ItemNothingFoundBinding>() {
    override fun bind(viewBinding: ItemNothingFoundBinding, position: Int) {}

    override fun getLayout(): Int = R.layout.item_nothing_found

    override fun initializeViewBinding(view: View): ItemNothingFoundBinding = ItemNothingFoundBinding.bind(view)
}
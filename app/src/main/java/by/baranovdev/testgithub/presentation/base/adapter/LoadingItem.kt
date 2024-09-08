package by.baranovdev.testgithub.presentation.base.adapter

import android.view.View
import by.baranovdev.testgithub.R
import by.baranovdev.testgithub.databinding.ItemLoadingBinding
import com.xwray.groupie.viewbinding.BindableItem

class LoadingItem(): BindableItem<ItemLoadingBinding>() {
    override fun bind(viewBinding: ItemLoadingBinding, position: Int) {

    }

    override fun getLayout(): Int = R.layout.item_loading

    override fun initializeViewBinding(view: View): ItemLoadingBinding = ItemLoadingBinding.bind(view)
}
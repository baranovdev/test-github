package by.baranovdev.testgithub.presentation.base.adapter

import android.view.View
import by.baranovdev.testgithub.R
import by.baranovdev.testgithub.databinding.ItemEmptyStateBinding
import com.xwray.groupie.viewbinding.BindableItem

class EmptyStateItem: BindableItem<ItemEmptyStateBinding>() {
    override fun bind(viewBinding: ItemEmptyStateBinding, position: Int) {
        viewBinding.lavEmptyState.speed = 0.5f
    }

    override fun getLayout(): Int = R.layout.item_empty_state

    override fun initializeViewBinding(view: View): ItemEmptyStateBinding = ItemEmptyStateBinding.bind(view)
}
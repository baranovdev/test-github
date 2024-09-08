package by.baranovdev.testgithub.presentation.base.adapter

import android.view.View
import by.baranovdev.testgithub.R
import by.baranovdev.testgithub.databinding.ItemExceptionBinding
import com.xwray.groupie.viewbinding.BindableItem

class ErrorItem (private val exception: String, private val onRetryClick: () -> Unit) : BindableItem<ItemExceptionBinding>() {
    override fun bind(viewBinding: ItemExceptionBinding, position: Int) {
        viewBinding.tvException.text = exception
        viewBinding.btnRetry.setOnClickListener{
            onRetryClick.invoke()
        }
    }

    override fun getLayout(): Int = R.layout.item_exception

    override fun initializeViewBinding(view: View): ItemExceptionBinding = ItemExceptionBinding.bind(view)

}
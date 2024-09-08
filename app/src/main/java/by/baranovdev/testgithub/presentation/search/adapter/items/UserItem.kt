package by.baranovdev.testgithub.presentation.search.adapter.items

import android.view.View
import by.baranovdev.testgithub.R
import by.baranovdev.testgithub.databinding.ItemUserBinding
import by.baranovdev.testgithub.domain.entity.User
import com.squareup.picasso.Picasso
import com.xwray.groupie.viewbinding.BindableItem

class UserItem(private val user: User, private val onClick: (String) -> Unit): BindableItem<ItemUserBinding>() {
    override fun bind(viewBinding: ItemUserBinding, position: Int) {
        viewBinding.tvName.text = user.name
        Picasso.get()
            .load(user.imageUrl)
            .into(viewBinding.ivAvatar)
        viewBinding.tvScore.text = user.score.toString()
        viewBinding.root.setOnClickListener {
            onClick.invoke(user.profileUrl)
        }
    }

    override fun getLayout(): Int = R.layout.item_user

    override fun initializeViewBinding(view: View): ItemUserBinding = ItemUserBinding.bind(view)

}
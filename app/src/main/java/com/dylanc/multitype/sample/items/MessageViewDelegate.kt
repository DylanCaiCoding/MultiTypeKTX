package com.dylanc.multitype.sample.items

import android.content.Context
import android.view.ViewGroup
import com.drakeet.multitype.ItemViewDelegate
import com.dylanc.multitype.sample.databinding.ItemMessageBinding
import com.dylanc.viewbinding.BindingViewHolder

/**
 * @author Dylan Cai
 */
class MessageViewDelegate : ItemViewDelegate<Message, BindingViewHolder<ItemMessageBinding>>() {

  override fun onCreateViewHolder(context: Context, parent: ViewGroup) =
    BindingViewHolder<ItemMessageBinding>(parent)

  override fun onBindViewHolder(holder: BindingViewHolder<ItemMessageBinding>, item: Message) {
    with(holder.binding) {
      tvName.text = "${item.name} :"
      tvContent.text = item.content
    }
  }
}
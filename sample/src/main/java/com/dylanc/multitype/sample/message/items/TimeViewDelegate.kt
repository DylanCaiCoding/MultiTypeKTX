package com.dylanc.multitype.sample.message.items

import android.content.Context
import android.view.ViewGroup
import com.drakeet.multitype.ItemViewDelegate
import com.dylanc.multitype.sample.databinding.ItemTimeBinding
import com.dylanc.viewbinding.BindingViewHolder

/**
 * @author Dylan Cai
 */
class TimeViewDelegate : ItemViewDelegate<String, BindingViewHolder<ItemTimeBinding>>() {

  override fun onCreateViewHolder(context: Context, parent: ViewGroup) =
    BindingViewHolder<ItemTimeBinding>(parent)

  override fun onBindViewHolder(holder: BindingViewHolder<ItemTimeBinding>, item: String) {
    with(holder.binding) {
      tvText.text = item
    }
  }
}
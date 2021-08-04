package com.dylanc.multitype.sample.exam.items

import android.content.Context
import android.view.ViewGroup
import com.dylanc.multitype.CheckableItemViewDelegate
import com.dylanc.multitype.sample.databinding.ItemOptionBinding
import com.dylanc.viewbinding.BindingViewHolder
import com.dylanc.viewbinding.onItemClick

/**
 * @author Dylan Cai
 */
class MultipleOptionViewDelegate :
  CheckableItemViewDelegate<Option, BindingViewHolder<ItemOptionBinding>>(false) {

  override fun onCreateViewHolder(context: Context, parent: ViewGroup) =
    BindingViewHolder<ItemOptionBinding>(parent)
      .onItemClick {
        checkItem(it)
      }

  override fun onBindViewHolder(holder: BindingViewHolder<ItemOptionBinding>, item: Option) {
    with(holder.binding) {
      checkBox.text = item.name
      checkBox.isChecked = item.isChecked
    }
  }
}
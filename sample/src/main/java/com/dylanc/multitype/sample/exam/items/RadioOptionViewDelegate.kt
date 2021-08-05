package com.dylanc.multitype.sample.exam.items

import android.content.Context
import android.view.ViewGroup
import com.dylanc.multitype.CheckType
import com.dylanc.multitype.CheckableItemViewDelegate
import com.dylanc.multitype.sample.databinding.ItemOptionBinding
import com.dylanc.viewbinding.BindingViewHolder
import com.dylanc.viewbinding.onItemClick

/**
 * @author Dylan Cai
 */
class RadioOptionViewDelegate :
  CheckableItemViewDelegate<Option, BindingViewHolder<ItemOptionBinding>>(CheckType.SINGLE) {

  override fun onCreateViewHolder(context: Context, parent: ViewGroup) =
    BindingViewHolder<ItemOptionBinding>(parent)
      .onItemClick { position ->
        checkItem(position)
      }

  override fun onBindViewHolder(holder: BindingViewHolder<ItemOptionBinding>, item: Option) {
    with(holder.binding) {
      checkBox.text = item.name
      checkBox.isChecked = item.isChecked
    }
  }
}
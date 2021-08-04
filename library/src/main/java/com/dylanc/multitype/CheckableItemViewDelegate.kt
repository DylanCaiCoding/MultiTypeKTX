package com.dylanc.multitype

import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewDelegate

/**
 * @author Dylan Cai
 */
abstract class CheckableItemViewDelegate<T : ICheckable, VH : RecyclerView.ViewHolder>(
  private val singleChoice: Boolean = true
) : ItemViewDelegate<T, VH>() {

  private var selectedPosition = mutableMapOf<Int, Int>().withDefault { -1 }

  @Suppress("UNCHECKED_CAST")
  fun checkItem(position: Int) {
    val item = adapterItems[position] as T
    if (singleChoice) {
      val lastPosition = selectedPosition.getValue(item.groupId)
      val selectedItem = if (lastPosition >= 0) adapterItems[lastPosition] as T else null
      if (item != selectedItem) {
        item.isChecked = true
        selectedItem?.isChecked = false
        selectedPosition[item.groupId] = position
        if (lastPosition >= 0) {
          adapter.notifyItemChanged(lastPosition)
        }
      } else {
        item.isChecked = !item.isChecked
      }
    } else {
      item.isChecked = !item.isChecked
    }
    adapter.notifyItemChanged(position)
  }
}
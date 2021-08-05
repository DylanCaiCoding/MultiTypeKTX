@file:Suppress("unused")

package com.dylanc.multitype

import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewDelegate

/**
 * @author Dylan Cai
 */

interface ICheckable {
  val groupId: Int get() = -1
  var isChecked: Boolean
}

@JvmInline
value class CheckType private constructor(val value: Int) {
  companion object {
    val SINGLE = CheckType(-1)
    val MULTIPLE = CheckType(-2)
    fun limit(number: Int): CheckType {
      check(number >= 0) { "Limit the number of checked cannot be negative." }
      return CheckType(number)
    }
  }
}

abstract class CheckableItemViewDelegate<T : ICheckable, VH : RecyclerView.ViewHolder>(
  private val type: CheckType
) : ItemViewDelegate<T, VH>() {

  private val selectedPositions by lazy {
    mutableMapOf<Int, Int>().withDefault { -1 }
  }

  @Suppress("UNCHECKED_CAST")
  fun checkItem(position: Int) {
    val item = adapterItems[position] as T
    when (type) {
      CheckType.SINGLE -> {
        val lastPosition = selectedPositions.getValue(item.groupId)
        val selectedItem = if (lastPosition >= 0) adapterItems[lastPosition] as T else null
        if (item != selectedItem) {
          item.isChecked = true
          selectedItem?.isChecked = false
          selectedPositions[item.groupId] = position
          if (lastPosition >= 0) {
            adapter.notifyItemChanged(lastPosition)
          }
        } else {
          item.isChecked = !item.isChecked
        }
      }
      CheckType.MULTIPLE -> {
        item.isChecked = !item.isChecked
      }
      else -> {
        val checkedNumber = adapterItems.filter {
          it is ICheckable && it.isChecked && it.groupId == item.groupId
        }.size
        if (checkedNumber >= type.value && !item.isChecked) {
          return
        }
        item.isChecked = !item.isChecked
      }
    }
    adapter.notifyItemChanged(position)
  }
}
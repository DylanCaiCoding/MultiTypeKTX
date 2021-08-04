@file:Suppress("unused", "NOTHING_TO_INLINE")

package com.dylanc.multitype

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import com.drakeet.multitype.ItemViewDelegate
import com.drakeet.multitype.MultiTypeAdapter


typealias ItemsLiveData<T> = MutableLiveData<List<T>>

inline fun <reified T : Any> MultiTypeAdapter(delegate: ItemViewDelegate<T, *>) =
  MultiTypeAdapter { register(delegate) }

inline fun MultiTypeAdapter(block: MultiTypeAdapter.() -> Unit) =
  MultiTypeAdapter().apply(block)

inline fun <reified T : Any> MultiTypeAdapter.register(vararg delegate: ItemViewDelegate<T, *>) =
  register(T::class).to(*delegate)

inline fun <T : Any> MultiTypeAdapter.observeItemsChanged(
  owner: LifecycleOwner,
  items: LiveData<List<T>>,
  detectMoves: Boolean = true,
  noinline areItemsTheSame: (T, T) -> Boolean
) {
  items.observe(owner) {
    submitItems(it, detectMoves, areItemsTheSame)
  }
}

fun <T : Any> MultiTypeAdapter.submitItems(
  newItems: List<T>,
  detectMoves: Boolean = true,
  areItemsTheSame: (T, T) -> Boolean
) {
  val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
    @Suppress("UNCHECKED_CAST")
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
      areItemsTheSame(items[oldItemPosition] as T, newItems[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
      items[oldItemPosition] == newItems[newItemPosition]

    override fun getOldListSize() = items.size
    override fun getNewListSize() = newItems.size
  }, detectMoves)
  items = newItems
  result.dispatchUpdatesTo(this)
}
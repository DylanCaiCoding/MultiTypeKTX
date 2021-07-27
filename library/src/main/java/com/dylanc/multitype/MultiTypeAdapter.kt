package com.dylanc.multitype

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import com.drakeet.multitype.MultiTypeAdapter


typealias ItemsLiveData<T> = MutableLiveData<List<T>>

fun MultiTypeAdapter(block: MultiTypeAdapter.() -> Unit) =
  MultiTypeAdapter().apply(block)

fun <T : Any> MultiTypeAdapter.observeItems(
  owner: LifecycleOwner,
  items: LiveData<List<T>>,
  detectMoves: Boolean = true,
  areItemsTheSame: (T, T) -> Boolean
) {
  items.observe(owner) {
    notifyItemsChanged(it, detectMoves, areItemsTheSame)
  }
}

fun <T : Any> MultiTypeAdapter.notifyItemsChanged(
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
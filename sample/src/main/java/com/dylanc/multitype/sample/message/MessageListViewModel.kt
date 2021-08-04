package com.dylanc.multitype.sample.message

import androidx.lifecycle.ViewModel
import com.dylanc.multitype.ItemsLiveData
import com.dylanc.multitype.sample.message.items.Message

/**
 * @author Dylan Cai
 */
class MessageListViewModel : ViewModel() {

  private val data1 = listOf(
    "Today",
    Message(1, "Drakeet", "MultiType library that easier and more flexible to create multiple types for Android RecyclerView."),
    Message(2, "Dylan Cai", "Click the menu to switch data.")
  )

  private val data2 = listOf(
    "Yesterday",
    Message(3, "Xiaobai", "Where is Drakeet?"),
    Message(4, "Miwu", "I am hungry."),
    "2021/7/9",
    Message(2, "Dylan Cai", "This movie is very interesting."),
    Message(1, "Drakeet", "MultiType library that easier and more flexible to create multiple types for Android RecyclerView."),
  )

  private val data3 = listOf(
    "2021/7/9",
    Message(1, "Drakeet", "MultiType library that easier and more flexible to create multiple types for Android RecyclerView."),
    "2021/7/8",
    Message(2, "Dylan Cai", "Hi, nice to meet you."),
    "2021/7/7",
    Message(3, "Xiaobai", "Where is Drakeet?"),
  )

  val items = ItemsLiveData(data1)

  fun changeData1() {
    items.value = data1
  }

  fun changeData2() {
    items.value = data2
  }

  fun changeData3() {
    items.value = data3
  }
}
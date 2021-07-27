package com.dylanc.multitype.sample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dylanc.multitype.MultiTypeAdapter
import com.dylanc.multitype.observeItems
import com.dylanc.multitype.sample.databinding.ActivityMainBinding
import com.dylanc.multitype.sample.items.Message
import com.dylanc.multitype.sample.items.MessageViewDelegate
import com.dylanc.multitype.sample.items.TimeViewDelegate
import com.dylanc.viewbinding.binding

/**
 * @author Dylan Cai
 */
class MainActivity : AppCompatActivity() {

  private val binding: ActivityMainBinding by binding()
  private val viewModel: MainViewModel by viewModels()
  private val adapter = MultiTypeAdapter {
    register(TimeViewDelegate())
    register(MessageViewDelegate())
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding.recyclerView.adapter = adapter
    adapter.observeItems(this, viewModel.items) { oldItem, newItem ->
      (oldItem is String && newItem is String && oldItem == newItem) ||
          (oldItem is Message && newItem is Message && oldItem.id == newItem.id)
    }
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.data, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.data1 -> viewModel.changeData1()
      R.id.data2 -> viewModel.changeData2()
      R.id.data3 -> viewModel.changeData3()
    }
    return true
  }
}
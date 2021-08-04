package com.dylanc.multitype.sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dylanc.multitype.sample.databinding.ActivityMainBinding
import com.dylanc.multitype.sample.exam.ExamActivity
import com.dylanc.multitype.sample.message.MessageListActivity
import com.dylanc.viewbinding.binding

/**
 * @author Dylan Cai
 */
class MainActivity : AppCompatActivity() {
  private val binding: ActivityMainBinding by binding()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    with(binding) {
      btnMessageList.setOnClickListener {
        startActivity(Intent(this@MainActivity, MessageListActivity::class.java))
      }
      btnExam.setOnClickListener {
        startActivity(Intent(this@MainActivity, ExamActivity::class.java))
      }
    }
  }
}
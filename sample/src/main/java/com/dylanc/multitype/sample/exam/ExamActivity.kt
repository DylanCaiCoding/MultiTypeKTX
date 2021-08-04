package com.dylanc.multitype.sample.exam

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dylanc.multitype.ItemsLiveData
import com.dylanc.multitype.MultiTypeAdapter
import com.dylanc.multitype.observeItemsChanged
import com.dylanc.multitype.register
import com.dylanc.multitype.sample.databinding.ActivityListBinding
import com.dylanc.multitype.sample.exam.items.*
import com.dylanc.viewbinding.binding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author Dylan Cai
 */
class ExamActivity : AppCompatActivity() {

  private val binding: ActivityListBinding by binding()
  private val adapter = MultiTypeAdapter {
    register(QuestionViewDelegate())
    register(RadioOptionViewDelegate(), MultipleOptionViewDelegate())
      .withKotlinClassLinker { _, item ->
        if (item.singleChoice) RadioOptionViewDelegate::class else MultipleOptionViewDelegate::class
      }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    title = "Exam"
    adapter.items = listOf(
      Question("1. Interface is one of the implementation mechanisms of Java object-oriented. Which of the following statements is true?"),
      Option(1, "Java supports multiple inheritance, and a class can implement multiple interfaces", true),
      Option(1, "Java supports only single inheritance, and a class can implement only multiple interfaces", true),
      Option(1, "Java supports only single inheritance, and a class can implement only one interface", true),
      Option(1, "Java supports multiple inheritance, but a class can implement only one interface", true),
      Question("2. (multiple selection) Which of the following exception belongs to RuntimeException."),
      Option(2, "ArithmeticException", false),
      Option(2, "IllegalArgumentException", false),
      Option(2, "NullPointerException", false),
      Option(2, "BufferUnderflowException", false),
      Question("3. Math.round(11.5) equals ___. Math.round(-11.5) equals ___."),
      Option(3, "11, -11", true),
      Option(3, "11, -12", true),
      Option(3, "12, -11", true),
      Option(3, "12, -12", true),
    )
    binding.recyclerView.adapter = adapter
  }
}
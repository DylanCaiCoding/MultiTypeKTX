package com.dylanc.multitype.sample.exam.items

import android.content.Context
import android.view.ViewGroup
import com.drakeet.multitype.ItemViewDelegate
import com.dylanc.multitype.sample.databinding.ItemQuestionBinding
import com.dylanc.viewbinding.BindingViewHolder

/**
 * @author Dylan Cai
 */
class QuestionViewDelegate :
  ItemViewDelegate<Question, BindingViewHolder<ItemQuestionBinding>>() {

  override fun onCreateViewHolder(context: Context, parent: ViewGroup) =
    BindingViewHolder<ItemQuestionBinding>(parent)

  override fun onBindViewHolder(holder: BindingViewHolder<ItemQuestionBinding>, item: Question) {
    with(holder.binding) {
      tvQuestion.text = item.content
    }
  }
}
package com.dylanc.multitype.sample.exam.items

import com.dylanc.multitype.ICheckable

/**
 * @author Dylan Cai
 */
data class Option(
  override val groupId: Int,
  val name: String,
  val isSingleChoice: Boolean,
  override var isChecked: Boolean = false
) : ICheckable
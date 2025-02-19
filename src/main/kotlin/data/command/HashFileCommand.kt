package org.gigachad.data.command

import org.gigachad.data.Algorithm
import org.gigachad.data.param.InputParam
import org.gigachad.data.param.Param

class HashFileCommand @JvmOverloads constructor(
  private val inputPath: String,
  private val algorithm: Algorithm,
  private val outputPath: String? = null,
): Command() {

  override fun execute() {

  }
}

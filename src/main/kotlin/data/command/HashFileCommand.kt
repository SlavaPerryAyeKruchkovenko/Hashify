package org.gigachad.data.command

import org.gigachad.data.param.AlgorithmParam
import org.gigachad.data.param.InputParam
import org.gigachad.data.param.OutputParam

class HashFileCommand(
  private val input: InputParam,
  private val output: OutputParam,
  private val algorithm: AlgorithmParam,
): Command() {

  override fun execute() {

  }
}

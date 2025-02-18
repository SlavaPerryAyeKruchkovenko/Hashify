package org.gigachad.data.param

class InputParam: Param<String>() {

  override val alias: List<String> = listOf("--input", "-input", "-i", "--i")
}

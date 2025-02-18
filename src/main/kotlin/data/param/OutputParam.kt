package org.gigachad.data.param

class OutputParam: Param<String>() {

  override val alias: List<String> = listOf("--output", "-output", "-o", "--o")
}

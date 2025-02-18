package org.gigachad.data.param

class HelpParam: Param<String>() {

  override val alias: List<String> = listOf("--help", "-help", "-h", "--h")
}

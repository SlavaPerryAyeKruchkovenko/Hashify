package org.gigachad.data.param

import org.gigachad.data.util.Validator

class InputParam: Param<String>() {

  override val alias: List<String> = listOf("--input", "-input", "-i", "--i")
  override fun validate(options: Map<String, String>) = Validator.validateFileParam(
	  options, alias, this
  )
}

package org.gigachad.data.param

import org.gigachad.data.util.Validator

class OutputParam: Param<String>() {

  override val alias: List<String> = listOf("--output", "-output", "-o", "--o")
  override fun validate(options: Map<String, String>) = Validator.validateFileParam(
	  options, alias, this
  )
}

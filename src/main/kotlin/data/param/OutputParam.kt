package org.gigachad.data.param

import org.gigachad.data.exception.ParamException

class OutputParam: Param<String>() {

  override val alias: List<String> = listOf("--output", "-output", "-o", "--o")
  override fun validate(options: Map<String, String>) {
	options.keys.forEach { key ->
	  if (alias.contains(key.lowercase())) {
		return
	  }
	}
	throw ParamException.ParamNotFound(this)
  }
}

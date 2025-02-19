package org.gigachad.data.param

import org.gigachad.data.Algorithm
import org.gigachad.data.exception.ParamException

class AlgorithmParam: Param<Algorithm>() {

  override val alias: List<Algorithm> = Algorithm.entries.toList()
  override fun validate(options: Map<String, String>) {
	options.keys.forEach { key ->
	  if (alias.map { it.value }.contains(key.lowercase())) {
		return
	  }
	}
	throw ParamException.ParamNotFound(this)
  }
}

package org.gigachad.data.util

import org.gigachad.data.exception.ParamException
import org.gigachad.data.param.Param
import java.io.File

object Validator {

  fun validateFileParam(options: Map<String, String>, alias: List<String>, param: Param<String>) {
	options.keys.forEach { key ->
	  if (alias.contains(key.lowercase())) {
		val value = options[key.lowercase()]
		val file = value?.let { File(it) }
		if (file?.exists() == true) {
		  return
		} else {
		  throw ParamException.FileNotFoundException(value ?: "")
		}
	  }
	}
	throw ParamException.ParamNotFound(param)
  }
}

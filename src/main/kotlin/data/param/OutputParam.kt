package org.gigachad.data.param

import org.gigachad.data.exception.ParamException
import java.io.File

data object OutputParam: Param<String>() {

  override val alias: List<String> = listOf("--output", "-output", "-o", "--o")
  override fun getValue(options: Map<String, String>) = alias.firstNotNullOf {
	options[it]
  }

  override fun validate(options: Map<String, String>) {
	options.keys.forEach { key ->
	  if (alias.contains(key.lowercase())) {
		val value = options[key.lowercase()]
		val file = value?.let { File(it) }
		if (file?.exists() == true || file?.createNewFile() == true) {
		  return
		} else {
		  throw ParamException.CannotCreateFileException("Failed to create new file $key")
		}
	  }
	}
	throw ParamException.ParamNotFound(this)
  }
}

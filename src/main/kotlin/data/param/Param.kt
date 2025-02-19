package org.gigachad.data.param

sealed class Param<T> {

  abstract val alias: List<T>
  open fun getValue(options: Map<T, T>) = alias.firstNotNullOfOrNull {
	options[it]
  }

  abstract fun validate(options: Map<String, String>)
}

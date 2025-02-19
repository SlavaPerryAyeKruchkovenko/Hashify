package org.gigachad.data.param

sealed class Param<T> {

  abstract val alias: List<T>
  open fun getValue(options: Map<String, String>): T = alias.first {
	it == options[it.toString()]
  }

  abstract fun validate(options: Map<String, String>)
}

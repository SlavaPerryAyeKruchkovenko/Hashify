package org.gigachad.data.param

sealed class Param<T> {

  abstract val alias: List<T>
  fun getValue(options: Map<T, T>) = alias.mapNotNull {
	options[it]
  }
}

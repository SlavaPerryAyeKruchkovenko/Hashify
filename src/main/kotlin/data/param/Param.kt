package org.gigachad.data.param

sealed class Param<T> {

  abstract val alias: List<T>
  abstract fun getValue(options: Map<String, String>): T
  abstract fun validate(options: Map<String, String>)
}

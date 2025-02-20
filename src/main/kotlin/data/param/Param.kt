package org.gigachad.data.param

sealed class Param<T> {

    abstract val alias: List<T>
    abstract fun getValue(options: Map<String, String>): T
    abstract fun validate(options: Map<String, String>)
    open fun available(options: Map<String, String>): Boolean {
        return options.keys.any { key ->
            alias.map { it.toString() }.contains(key)
        }
    }
}

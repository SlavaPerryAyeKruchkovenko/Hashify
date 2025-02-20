package org.hashify.data.param.model

sealed class Param<T> {

    abstract val alias: Collection<String>
    abstract fun getValue(options: Map<String, String>): T
    abstract fun validate(options: Map<String, String>)
    fun available(options: Map<String, String>): Boolean {
        return options.keys.any { key ->
            alias.map { it.lowercase() }.contains(key.lowercase())
        }
    }
}

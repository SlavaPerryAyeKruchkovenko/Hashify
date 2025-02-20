package org.gigachad.data.param

import org.gigachad.data.Algorithm
import org.gigachad.data.exception.ParamException

data object AlgorithmParam : Param<Algorithm>() {
    private val algorithms = Algorithm.entries.toList()
    override val alias: List<String> = algorithms.map { it.value }
    override fun validate(options: Map<String, String>) {
        options.keys.forEach { key ->
            if (algorithms.map { it.value }.contains(key.lowercase())) {
                return
            }
        }
        throw ParamException.ParamNotFound(this)
    }

    override fun getValue(options: Map<String, String>) = algorithms.first {
        it.value == options[it.value]?.lowercase()
    }
}

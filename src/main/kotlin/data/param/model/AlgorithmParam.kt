package org.hashify.data.param.model

import org.hashify.data.Algorithm
import org.hashify.data.exception.ParamException
import org.hashify.data.param.Describable

data object AlgorithmParam : Param<Algorithm>(), Describable {
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

    override val info: String
        get() = "<алгоритм> Название алгоритма все алгоритмы ${algorithms.joinToString { it.value }}."
}

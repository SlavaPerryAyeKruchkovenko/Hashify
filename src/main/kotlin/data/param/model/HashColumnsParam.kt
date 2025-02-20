package org.hashify.data.param.model

import org.hashify.data.param.Describable
import org.hashify.data.util.Validator

data object HashColumnsParam : Param<Collection<Int>>(), Describable {
    override val alias: List<String> = listOf("--columns", "-columns", "-col", "--col")

    override fun getValue(options: Map<String, String>): List<Int> = alias.firstNotNullOf { key ->
        val value = options[key]
        value?.split(",")?.map { it.toInt() }
    }

    override fun validate(options: Map<String, String>) {
        Validator.validateArrayParam(options, this)
    }

    override val info: String
        get() = "${alias.joinToString()}='1,2,3' Указывает индексы колонок, которые нужно захэшировать алгоритмом."
}

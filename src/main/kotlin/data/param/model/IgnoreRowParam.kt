package org.hashify.data.param.model

import org.hashify.data.param.Describable
import org.hashify.data.util.Validator

data object IgnoreRowParam : Param<Collection<Int>>(), Describable {
    override val alias: List<String> = listOf("--ignore-rows", "-ignore-rows", "-ir", "--ir")

    override fun getValue(options: Map<String, String>): List<Int> = alias.firstNotNullOf { key ->
        val value = options[key]
        value?.split(",")?.map { it.toInt() }
    }

    override fun validate(options: Map<String, String>) {
        Validator.validateArrayParam(options, this)
    }

    override val info: String
        get() = "${alias.joinToString()}='1,2,3' Указывает индекс строк, которые нужно проигнорировать при хэишровании."
}

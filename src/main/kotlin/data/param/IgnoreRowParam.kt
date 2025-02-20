package org.gigachad.data.param

import org.gigachad.data.util.Validator

data object IgnoreRowParam : Param<Collection<Int>>() {
    override val alias: List<String> = listOf("--rows", "-rows", "-r", "--r")

    override fun getValue(options: Map<String, String>): List<Int> = alias.firstNotNullOf { key ->
        val value = options[key]
        value?.split(",")?.map { it.toInt() }
    }

    override fun validate(options: Map<String, String>) {
        Validator.validateArrayParam(options, this)
    }
}

package org.hashify.data.param.model

import org.hashify.data.param.Describable
import org.hashify.data.util.Validator

data object InputParam : Param<String>(), Describable {

    override val alias: List<String> = listOf("--input", "-input", "-i", "--i")
    override fun getValue(options: Map<String, String>) = alias.firstNotNullOf {
        options[it]
    }

    override fun validate(options: Map<String, String>) = Validator.validateFileParam(
        options, alias, this
    )

    override val info: String
        get() = "${alias.joinToString()}=<файл>.csv. Указывает путь к входному файлу."
}

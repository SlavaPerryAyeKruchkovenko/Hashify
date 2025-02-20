package org.gigachad.data.param

import org.gigachad.data.util.Validator

data object InputParam : Param<String>() {

    override val alias: List<String> = listOf("--input", "-input", "-i", "--i")
    override fun getValue(options: Map<String, String>) = alias.firstNotNullOf {
        options[it]
    }

    override fun validate(options: Map<String, String>) = Validator.validateFileParam(
        options, alias, this
    )
}

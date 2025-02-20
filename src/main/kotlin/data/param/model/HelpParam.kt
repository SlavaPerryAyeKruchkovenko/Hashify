package org.hashify.data.param.model

import org.hashify.data.exception.ParamException
import org.hashify.data.param.Describable

data object HelpParam : Param<String>(),Describable {

    override val alias: List<String> = listOf("--help", "-help", "-h", "--h")
    override fun getValue(options: Map<String, String>) = "-h"

    override fun validate(options: Map<String, String>) {
        if (options.size == 1) {
            if (alias.contains(options.keys.first().lowercase())) {
                return
            }else{
                throw ParamException.ParamNotFound(this)
            }
        } else {
            throw ParamException.IncorrectCountOfParams(
                expectedCount = 1, actualCount = options.size, this
            )
        }
    }

    override val info: String
        get() = "${alias.joinToString()} - Показать справку."
}

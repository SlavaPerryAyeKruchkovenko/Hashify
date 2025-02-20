package org.gigachad.data.param

import org.gigachad.data.exception.ParamException

data object HelpParam : Param<String>() {

    override val alias: List<String> = listOf("--help", "-help", "-h", "--h")
    override fun getValue(options: Map<String, String>) = "-h"

    override fun validate(options: Map<String, String>) {
        if (options.size == 1) {
            if (alias.contains(options.keys.first().lowercase())) {
                throw ParamException.ParamNotFound(this)
            }
        } else {
            throw ParamException.IncorrectCountOfParams(
                expectedCount = 1, actualCount = options.size, this
            )
        }
    }
}

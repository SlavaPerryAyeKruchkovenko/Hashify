package org.gigachad.data.util

import java.io.File
import org.gigachad.data.exception.ParamException
import org.gigachad.data.param.Param

object Validator {

    fun validateFileParam(options: Map<String, String>, alias: List<String>, param: Param<String>) {
        options.keys.forEach { key ->
            if (alias.contains(key.lowercase())) {
                val value = options[key.lowercase()]
                val file = value?.let { File(it) }
                if (file?.exists() == true) {
                    return
                } else {
                    throw ParamException.FileNotFoundException(value ?: "")
                }
            }
        }
        throw ParamException.ParamNotFound(param)
    }

    fun validateArrayParam(options: Map<String, String>, param: Param<Collection<Int>>) {
        options.keys.forEach { key ->
            val value = options[key]?.trim()
            val regex = Regex("^[0-9,]+$")
            if (value != null) {
                if (regex.matches(value)) {
                    return
                } else {
                    val forbiddenChars = Regex("[^0-9,]").findAll(value).map { it.value }.toSet()
                    throw ParamException.IncorrectCharactersParamValue(forbiddenChars)
                }
            }
        }
        throw ParamException.ParamNotFound(param)
    }
}

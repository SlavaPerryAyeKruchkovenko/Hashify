package org.hashify.data.util

import org.hashify.data.exception.ParamException
import org.hashify.data.param.model.Param
import java.io.File

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

    fun validateArrayParam(options: Map<String, String>, param: Param<Collection<Int>>, alias: List<String>) {
        val regex = Regex("^[0-9,]+$")
        options.keys.forEach { key ->
            val value = options[key]?.trim()
            if (alias.contains(key.lowercase()) && value != null) {
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

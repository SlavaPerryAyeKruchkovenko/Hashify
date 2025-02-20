package org.hashify.data.param.model

import java.io.File
import org.hashify.data.exception.ParamException
import org.hashify.data.param.Describable

data object OutputParam : Param<String>(), Describable {

    override val alias: List<String> = listOf("--output", "-output", "-o", "--o")
    override fun getValue(options: Map<String, String>) = alias.firstNotNullOf {
        options[it]
    }

    override fun validate(options: Map<String, String>) {
        options.keys.forEach { key ->
            if (alias.contains(key.lowercase())) {
                val value = options[key.lowercase()]
                val file = value?.let { File(it) }
                if (file?.exists() == true || file?.createNewFile() == true) {
                    return
                } else {
                    throw ParamException.CannotCreateFileException("Failed to create new file $key")
                }
            }
        }
        throw ParamException.ParamNotFound(this)
    }

    override val info: String
        get() = "${alias.joinToString()}=<файл>.csv. Указывает путь к выходному файлу."
}

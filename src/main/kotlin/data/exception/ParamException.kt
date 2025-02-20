package org.hashify.data.exception

import org.hashify.data.param.model.Param

sealed class ParamException @JvmOverloads constructor(
    override val message: String,
    override val cause: Throwable? = null,
) : Exception(message) {

    data class ParamNotFound(private val param: Param<*>) : ParamException(
        message = "parameter with alias ${param.alias.joinToString { it }} not found"
    )

    data class IncorrectCountOfParams(
        private val expectedCount: Int,
        private val actualCount: Int,
        private val param: Param<*>,
    ) : ParamException(
        message = "expected count of params: $expectedCount actual is $actualCount for param alias ${param.alias}"
    )

    data class FileNotFoundException(
        private val path: String,
    ) : ParamException(
        message = "file for path $path not found"
    )

    data class CannotCreateFileException(
        private val path: String,
    ) : ParamException(
        message = "cannot create fil for path $path"
    )

    data class IncorrectCharactersParamValue(val incorrectCharacters: Collection<String>) : ParamException(
        message = "Invalid characters ${incorrectCharacters.joinToString()}"
    )
}

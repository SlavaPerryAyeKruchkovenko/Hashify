package org.hashify

import org.hashify.data.command.Command
import org.hashify.data.command.HelpCommand
import org.hashify.data.command.UnprocessedCommand
import org.hashify.data.mapper.CommandMapper.getHashCommandByParams
import org.hashify.data.param.model.AlgorithmParam
import org.hashify.data.param.model.HashColumnsParam
import org.hashify.data.param.model.HelpParam
import org.hashify.data.param.model.InputParam
import org.hashify.data.param.model.OutputParam
import org.hashify.data.param.model.Param

fun main(args: Array<String>) {
    val options = getOptions(args)
    val params = getParams(options).onEach { param ->
        runCatching {
            param.validate(options)
        }.onFailure {
            println("Error: ${it.message}")
        }
    }
    val command = getCommandByParams(params, options)
    command.execute()
}

private fun getOptions(args: Array<String>) = args.mapNotNull {
    val parts = it.split("=")
    when (parts.size) {
        1 -> {
            parts[0] to parts[0]
        }

        2 -> {
            parts[0] to parts[1]
        }

        else -> {
            null
        }
    }
}.toMap()

fun getParams(options: Map<String, String>): List<Param<*>> {
    val params = listOf(AlgorithmParam, HelpParam, InputParam, OutputParam)
    return params.filter {
        try {
            it.available(options)
        } catch (e: Exception) {
            false
        }
    }
}

fun getCommandByParams(params: List<Param<*>>, options: Map<String, String>): Command {
    val hashParams = setOf(AlgorithmParam::class, InputParam::class, HashColumnsParam::class)
    val paramClasses = params.map { it::class }.toSet()
    return when {
        params.size == 1 && params[0] is HelpParam -> HelpCommand
        params.size in 3..5 && hashParams.all { it in paramClasses } -> getHashCommandByParams(
            params,
            options
        )

        else -> UnprocessedCommand
    }
}


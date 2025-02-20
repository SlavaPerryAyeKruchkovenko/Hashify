package org.gigachad

import org.gigachad.data.command.Command
import org.gigachad.data.command.HelpCommand
import org.gigachad.data.command.UnprocessedCommand
import org.gigachad.data.mapper.CommandMapper.getHashCommandByParams
import org.gigachad.data.param.AlgorithmParam
import org.gigachad.data.param.HelpParam
import org.gigachad.data.param.InputParam
import org.gigachad.data.param.OutputParam
import org.gigachad.data.param.Param

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
    val hashParams = setOf(AlgorithmParam::class, InputParam::class)
    val paramClasses = params.map { it::class }.toSet()
    return when {
        params.size == 1 && params[0] is HelpParam -> HelpCommand
        params.size in 2..3 && hashParams.all { it in paramClasses } -> getHashCommandByParams(
            params,
            options
        )

        else -> UnprocessedCommand
    }
}


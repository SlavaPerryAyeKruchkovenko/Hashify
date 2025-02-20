package org.gigachad

import org.gigachad.data.command.Command
import org.gigachad.data.command.HelpCommand
import org.gigachad.data.command.UnprocessedCommand
import org.gigachad.data.mapper.CommandMapper.getHashCommandByParams
import org.gigachad.data.param.*

fun main(args: Array<String>) {
    val options = getOptions(args)
    val params = getParams(options)
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
    return when {
        params.size == 1 && params[0] is HelpParam -> HelpCommand
        params.size in 2..3 -> getHashCommandByParams(params, options)
        else -> UnprocessedCommand
    }
}


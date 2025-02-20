package org.hashify.data.mapper

import org.hashify.data.command.HashFileCommand
import org.hashify.data.param.model.AlgorithmParam
import org.hashify.data.param.model.HashColumnsParam
import org.hashify.data.param.model.IgnoreRowParam
import org.hashify.data.param.model.InputParam
import org.hashify.data.param.model.OutputParam
import org.hashify.data.param.model.Param

object CommandMapper {

    fun getHashCommandByParams(params: List<Param<*>>, options: Map<String, String>): HashFileCommand {
        val input = params.first { it is InputParam } as InputParam
        val output = params.firstOrNull { it is OutputParam }
        val algorithm = params.first { it is AlgorithmParam } as AlgorithmParam
        val hashColumn = params.first { it is HashColumnsParam } as HashColumnsParam
        val ignoreRow = params.firstOrNull { it is IgnoreRowParam }
        return HashFileCommand(
            inputPath = input.getValue(options),
            algorithm = algorithm.getValue(options),
            outputPath = if (output != null) {
                (output as OutputParam).getValue(options)
            } else {
                null
            },
            hashColumn = hashColumn.getValue(options),
            ignoreRow = if (ignoreRow != null) {
                (ignoreRow as IgnoreRowParam).getValue(options)
            } else {
                null
            },
        )
    }
}

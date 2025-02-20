package org.gigachad.data.mapper

import org.gigachad.data.command.HashFileCommand
import org.gigachad.data.param.AlgorithmParam
import org.gigachad.data.param.InputParam
import org.gigachad.data.param.OutputParam
import org.gigachad.data.param.Param

object CommandMapper {

    fun getHashCommandByParams(params: List<Param<*>>, options: Map<String, String>): HashFileCommand {
        val input = params.first { it is InputParam } as InputParam
        val output = params.firstOrNull { it is OutputParam }
        val algorithm = params.first { it is AlgorithmParam } as AlgorithmParam
        return HashFileCommand(
            inputPath = input.getValue(options),
            algorithm = algorithm.getValue(options),
            outputPath = if (output != null) {
                (output as OutputParam).getValue(options)
            } else {
                null
            }
        )
    }
}

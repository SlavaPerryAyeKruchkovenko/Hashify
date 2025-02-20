package org.hashify.data.command

import org.hashify.data.param.Describable
import org.hashify.data.param.model.AlgorithmParam
import org.hashify.data.param.model.HashColumnsParam
import org.hashify.data.param.model.HelpParam
import org.hashify.data.param.model.IgnoreRowParam
import org.hashify.data.param.model.InputParam
import org.hashify.data.param.model.OutputParam

data object HelpCommand : Command() {
    private val params = listOf<Describable>(InputParam, OutputParam, AlgorithmParam, IgnoreRowParam, HashColumnsParam)
    private val projectName = System.getProperty("project.name", "Unknown Project")
    override fun execute() {
        print("""
Использование:
    $projectName.jar --input="<путь_к_файлу>.csv" --output="<путь_к_файлу>.csv" <алгоритм> --columns="1,2" --ignore-rows="0,12"

Описание:
    Читает строки из файла и применяет указанный алгоритм шифрования.

Аргументы:
    ${params.joinToString("\n\t\t") { it.info }}

Примеры:
  --input=~/test.csv md5 --columns="1,2"
  --i=~/test.csv sha256 --o=~/test2.scv --ir="0" --col="1,2"

Дополнительно:
  ${HelpParam.info}
""".trim()
        )
    }
}

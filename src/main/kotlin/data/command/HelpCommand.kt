package org.gigachad.data.command

import org.gigachad.data.Algorithm

data object HelpCommand : Command() {
    private val algorithms = Algorithm.entries.toList()
    override fun execute() {
        print(
            """
        Использование:
          hashify.jar --input="<путь_к_файлу>.csv" --output="<путь_к_файлу>.scv" <алгоритм>

        Описание:
          Читает строки из файла и применяет указанный алгоритм шифрования.

        Аргументы:
          -input, -i, --i, --input="<файл>.csv"   Указывает путь к входному файлу.
          -output, -o, --o, --output="<файл>.scv"  Указывает путь к выходному файлу.
          <алгоритм> Название алгоритма все алгоритмы ${algorithms.joinToString { it.value }}.

        Примеры:
          --input="~/test.csv" --output="~/test2.csv" md5
          --input="data.txt" --output="result.txt" sha256

        Дополнительно:
          -h, --help   Показать справку.
        """.trimIndent()
        )
    }
}

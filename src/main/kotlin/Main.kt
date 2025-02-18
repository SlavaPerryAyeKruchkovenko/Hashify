package org.gigachad

import org.gigachad.data.command.Command
import org.gigachad.data.param.Param
import kotlin.reflect.full.isSubclassOf

fun main(args: Array<String>) {
  val params = Command::class.nestedClasses.filter { it.isFinal && it.isSubclassOf(Command::class) }
  val commands = Param::class.nestedClasses.filter { it.isFinal && it.isSubclassOf(Param::class) }
  val options = args.mapNotNull {
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

}

package org.gigachad.data.command

sealed class Command {
  abstract fun execute()
}

package org.hashify.data.command

sealed class Command {
    abstract fun execute()
}

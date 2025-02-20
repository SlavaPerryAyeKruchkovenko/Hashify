package org.gigachad.data.command

data object UnprocessedCommand : Command() {

    override fun execute() {
        println("unprocessed command")
    }
}

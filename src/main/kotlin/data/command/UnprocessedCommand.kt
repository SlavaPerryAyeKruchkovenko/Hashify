package org.hashify.data.command

data object UnprocessedCommand : Command() {

    override fun execute() {
        println("unprocessed command. Use -h for show help command for more details")
    }
}

package codes.pedromanoel.orcamento.app

fun main(args: Array<String>) {
    val app = App.create()

    shutdownHook {
        app.stop()
    }

    app.start()
}

private fun shutdownHook(shutdownCallback: () -> Unit) {
    Runtime.getRuntime().addShutdownHook(Thread(shutdownCallback))
}
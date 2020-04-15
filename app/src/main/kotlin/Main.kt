package codes.pedromanoel.orcamento.app

import org.koin.core.context.startKoin

fun main(args: Array<String>) {
    val app = startKoin {
        printLogger()
        modules(appModule)
    }.koin.get<App>()

    shutdownHook {
        app.stop()
    }

    app.start()
}

private fun shutdownHook(shutdownCallback: () -> Unit) {
    Runtime.getRuntime().addShutdownHook(Thread(shutdownCallback))
}
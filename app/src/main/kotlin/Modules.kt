package codes.pedromanoel.orcamento.app

import com.natpryce.konfig.ConfigurationProperties
import com.natpryce.konfig.EnvironmentVariables
import com.natpryce.konfig.overriding
import io.javalin.Javalin
import org.koin.dsl.module
import java.io.File

val appModule = module {
    single { Javalin.create() }
    single {
        EnvironmentVariables() overriding
                ConfigurationProperties.fromOptionalFile(File("../application.properties"))
    }
    single { AppConfiguration(get())}
    single { App(get(), get()) }
}
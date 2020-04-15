package codes.pedromanoel.orcamento.app

import com.natpryce.konfig.Configuration
import com.natpryce.konfig.Key
import com.natpryce.konfig.booleanType
import com.natpryce.konfig.intType
import io.javalin.Javalin
import io.javalin.core.JavalinConfig

private val portKey = Key("port", intType)
private val bannerKey = Key("javalin.show-javalin-banner", booleanType)

class AppConfiguration(config: Configuration) {
    val port: Int? = config.getOrNull(portKey)
    val showJavalinBanner: Boolean? = config.getOrNull(bannerKey)

    fun applyTo(javalinConfig: JavalinConfig) {
        if (showJavalinBanner != null) {
            javalinConfig.showJavalinBanner = showJavalinBanner
        }
    }

    fun useToStart(javalin: Javalin) {
        if (port != null) {
            javalin.start(port)
        } else {
            javalin.start()
        }
    }
}
package codes.pedromanoel.orcamento.app

import com.natpryce.konfig.Configuration
import com.natpryce.konfig.Key
import com.natpryce.konfig.booleanType
import com.natpryce.konfig.intType

private val portKey = Key("port", intType)
private val bannerKey = Key("javalin.show-javalin-banner", booleanType)

class AppConfiguration(config: Configuration) {
    var port: Int? = config.getOrNull(portKey)
    var showJavalinBanner: Boolean? = config.getOrNull(bannerKey)
}
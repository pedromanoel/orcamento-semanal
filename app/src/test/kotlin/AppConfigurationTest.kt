package codes.pedromanoel.orcamento.app

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNull
import assertk.assertions.isTrue
import com.natpryce.konfig.ConfigurationMap
import com.natpryce.konfig.EmptyConfiguration
import io.javalin.Javalin
import io.javalin.core.JavalinConfig
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

private const val PORT_NUMBER = "1234"

internal class AppConfigurationTest {

    @Test
    internal fun `lê porta da configuração do kconfig`() {
        val config = ConfigurationMap("port" to PORT_NUMBER)

        val appConfig = AppConfiguration(config)

        assertThat(appConfig.port).isEqualTo(1234)
    }

    @Test
    internal fun `lê banner do javalin do kconfig`() {
        val config = ConfigurationMap("javalin.show-javalin-banner" to "true")

        val appConfig = AppConfiguration(config)

        assertThat(appConfig.showJavalinBanner).isEqualTo(true)
    }

    @Test
    internal fun `não falha quando kconfig está vazio`() {
        val config = EmptyConfiguration

        val appConfig = AppConfiguration(config)

        assertThat(appConfig.port).isNull()
        assertThat(appConfig.showJavalinBanner).isNull()
    }

    @Test
    internal fun `aplica configuração no JavalinConfig`() {
        val javalinConfig = JavalinConfig()
        assertThat(javalinConfig.showJavalinBanner).isTrue()

        val config = ConfigurationMap("javalin.show-javalin-banner" to "false")
        AppConfiguration(config).applyTo(javalinConfig)

        assertThat(javalinConfig.showJavalinBanner).isFalse()
    }

    @Test
    internal fun `usa config para iniciar javalin com a porta correta`() {
        val javalin = mockk<Javalin>(relaxed = true)
        val config = ConfigurationMap("port" to PORT_NUMBER)

        AppConfiguration(config).useToStart(javalin)

        verify { javalin.start(PORT_NUMBER.toInt()) }
    }
}
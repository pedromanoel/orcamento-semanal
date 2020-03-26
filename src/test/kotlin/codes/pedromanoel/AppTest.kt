/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package codes.pedromanoel

import com.natpryce.konfig.ConfigurationMap
import kong.unirest.Unirest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AppTest {

    private val config = ConfigurationMap("port" to "3000")
    private val app = App(config)

    @BeforeEach
    internal fun setUp() {
        app.start()
    }

    @AfterEach
    internal fun tearDown() {
        app.stop()
    }

    @Test
    fun `return root path`() {
        val response = Unirest.get("http://localhost:3000").asString()

        assertThat(response.status).isEqualTo(200)
        assertThat(response.body).isEqualTo("OK")
    }
}

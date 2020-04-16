/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package codes.pedromanoel.orcamento.app

import codes.pedromanoel.orcamento.domain.*
import codes.pedromanoel.orcamento.domain.usecases.ApresentarResumoDoRetrato
import codes.pedromanoel.orcamento.domain.usecases.CadastrarGastos
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.http.Context
import org.koin.core.context.startKoin
import java.math.BigDecimal

class App(
        private val config: AppConfiguration,
        private val cadastrarGastos: CadastrarGastos,
        private val apresentarResumoDoRetrato: ApresentarResumoDoRetrato
) {
    private val javalin = Javalin
            .create(this.config::applyTo)
            .routes {
                get { it.render("home.peb") }
                path("retrato") {
                    path("gastos") {
                        post {
                            it.gastos.forEach(cadastrarGastos::adicionarGasto)
                            it.status(201)
                        }
                    }
                }
            }

    fun start() {
        config.useToStart(javalin)
    }

    fun stop() {
        javalin.stop()
    }

    companion object {
        fun create(): App {
            return startKoin {
                printLogger()
                modules(appModule)
            }.koin.get()
        }
    }
}

private val Context.gastos get() = bodyAsClass(CadastraGastosRequest::class.java).gastos

data class CadastraGastosRequest(
        val fixos: List<GastoFixoRequest>,
        val variaveis: List<GastoVariavelRequest>,
        val sazonais: List<GastoSazonalRequest>
) {
    val gastos
        get() = listOf(fixos, variaveis, sazonais)
                .flatten()
                .map(GastoRequest::toDomain)
}

interface GastoRequest {
    val toDomain: Gasto
}

data class GastoFixoRequest(val nome: String, val valor: BigDecimal, val vencimento: Int) : GastoRequest {
    override val toDomain: Gasto
        get() = GastoFixo(nome, valor.setScale(2).unscaledValue().intValueExact(), vencimento)
}

data class GastoVariavelRequest(val nome: String, val valor: BigDecimal) : GastoRequest {
    override val toDomain: Gasto
        get() = GastoVariavelSemanal(nome, valor.setScale(2).unscaledValue().intValueExact())
}

data class GastoSazonalRequest(val nome: String, val valor: BigDecimal, val periodoEmMeses: Int) : GastoRequest {
    override val toDomain: Gasto
        get() = GastoSazonal(nome, valor.setScale(2).unscaledValue().intValueExact(), periodoEmMeses)
}

package codes.pedromanoel.orcamento.app

import codes.pedromanoel.orcamento.domain.Gasto
import codes.pedromanoel.orcamento.domain.GastoRepository
import codes.pedromanoel.orcamento.domain.RetratoConfiguration
import codes.pedromanoel.orcamento.domain.usecases.ApresentarResumoDoRetrato
import codes.pedromanoel.orcamento.domain.usecases.CadastrarGastos
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
    // repositories
    single<GastoRepository> { MemoryGastoRepository() }

    // use cases
    single { ApresentarResumoDoRetrato(get(), get()) }
    single { CadastrarGastos(get()) }

    single { RetratoConfiguration() }
    single { AppConfiguration(get()) }
    single { App(get(), get(), get()) }
}

class MemoryGastoRepository : GastoRepository {
    private val gastos: ArrayList<Gasto> = ArrayList()
    override fun adiciona(gasto: Gasto) {
        gastos.add(gasto)
    }

    override fun limpa() {
        gastos.clear()
    }

    override fun listaTodos() = gastos
}
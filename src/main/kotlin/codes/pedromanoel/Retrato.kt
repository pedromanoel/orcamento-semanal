package codes.pedromanoel

import java.time.DayOfWeek.MONDAY
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters.previous

class Retrato {
    val gastosFixos = GastosFixos()
    val gastosVariaveis = GastosVariaveis()
    val gastosSazonais = GastosSazonais()

}

class GastosSazonais {
}

class GastosVariaveis {

}

class GastosFixos {
    private val gastosFixos = ArrayList<GastoFixo>()

    fun adicionaGasto(gastoFixo: GastoFixo) =
        gastosFixos.add(gastoFixo)

    fun transacoesDaSemana(hoje: LocalDate) =
        Semana.doDia(hoje).transacoesDe(gastosFixos)

    fun limpar() =
        gastosFixos.clear()
}

data class Semana(
    private val inicioDaSemana: LocalDate,
    private val fimDaSemana: LocalDate = inicioDaSemana.plusWeeks(1)
) {
    fun transacoesDe(gastosFixos: List<GastoFixo>) =
        gastosFixos
            .map(this::paraTransacao)
            .filter(this::dentroDaSemana)

    private fun paraTransacao(gastoFixo: GastoFixo) =
        Transacao(
            gastoFixo.nome,
            vencimentoNoMes(gastoFixo),
            gastoFixo.valor
        )

    private fun dentroDaSemana(transacao: Transacao) =
        transacao.dia >= inicioDaSemana && transacao.dia < fimDaSemana

    private fun vencimentoNoMes(gastoFixo: GastoFixo) =
        inicioDaSemana.withDayOfMonth(gastoFixo.vencimento)

    companion object {
        fun doDia(dia: LocalDate) =
            when (dia.dayOfWeek) {
                MONDAY -> Semana(dia)
                else -> Semana(dia.with(previous(MONDAY)))
            }
    }
}

data class GastoFixo(val nome: String, val vencimento: Int, val valor: Int)

data class Transacao(val nome: String, val dia: LocalDate, val valor: Int)
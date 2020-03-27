package codes.pedromanoel.domain

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters.nextOrSame
import java.time.temporal.TemporalAdjusters.previousOrSame

data class Semana(
    val inicioDaSemana: LocalDate,
    val fimDaSemana: LocalDate
) {
    fun transacoesDaSemanaDado(gastos: List<Gasto>) =
        gastos
            .map { gasto -> transacaoPara(gasto) }
            .filter { transacao -> dentroDaSemana(transacao.data) }

    private fun transacaoPara(it: Gasto) =
        it.naDataUtilMaisProximaA(this)

    fun dentroDaSemana(data: LocalDate) =
        data >= inicioDaSemana && data < fimDaSemana

    fun proxima() =
        daData(fimDaSemana.plusDays(1))

    companion object {
        fun daData(data: LocalDate) =
            Semana(
                data.with(previousOrSame(DayOfWeek.MONDAY)),
                data.with(nextOrSame(DayOfWeek.SUNDAY))
            )
    }
}


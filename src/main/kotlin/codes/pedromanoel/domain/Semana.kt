package codes.pedromanoel.domain

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters.nextOrSame
import java.time.temporal.TemporalAdjusters.previousOrSame

data class Semana private constructor(
    val inicioDaSemana: LocalDate,
    val fimDaSemana: LocalDate
) {
    fun transacoesDaSemanaDado(gastosFixos: List<Gasto>) =
        gastosFixos
            .map(this::paraTransacao)
            .filter { this.dentroDaSemana(it.data) }

    fun dentroDaSemana(data: LocalDate) =
        data >= inicioDaSemana && data < fimDaSemana

    private fun paraTransacao(gastoFixo: Gasto) =
        gastoFixo.naDataUtilMaisProximaA(this)

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


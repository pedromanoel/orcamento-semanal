package codes.pedromanoel

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters.previousOrSame

data class Semana(
    val inicioDaSemana: LocalDate,
    val fimDaSemana: LocalDate = inicioDaSemana.plusWeeks(1)
) {
    fun transacoesDaSemanaDado(gastosFixos: List<Gasto>) =
        gastosFixos
            .map(this::paraTransacao)
            .filter(this::dentroDaSemana)

    private fun paraTransacao(gastoFixo: Gasto) =
        gastoFixo.naDataUtilMaisProximaA(this)

    private fun dentroDaSemana(transacao: Transacao) =
        transacao.data >= inicioDaSemana && transacao.data < fimDaSemana

    companion object {
        fun daData(data: LocalDate) = Semana(
            data.with(previousOrSame(DayOfWeek.MONDAY))
        )
    }
}


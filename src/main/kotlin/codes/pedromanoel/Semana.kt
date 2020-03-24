package codes.pedromanoel

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters.previousOrSame

data class Semana(
    private val inicioDaSemana: LocalDate,
    private val fimDaSemana: LocalDate = inicioDaSemana.plusWeeks(1)
) {
    fun transacoesDaSemanaParaGastos(gastosFixos: List<GastoFixo>) =
        gastosFixos
            .map(this::paraTransacao)
            .filter(this::dentroDaSemana)

    private fun paraTransacao(gastoFixo: GastoFixo) =
        gastoFixo.naDataMaisProximaA(inicioDaSemana)

    private fun dentroDaSemana(transacao: Transacao) =
        transacao.dia >= inicioDaSemana && transacao.dia < fimDaSemana

    companion object {
        fun doDia(dia: LocalDate) =
            Semana(
                dia.with(previousOrSame(DayOfWeek.MONDAY))
            )
    }
}


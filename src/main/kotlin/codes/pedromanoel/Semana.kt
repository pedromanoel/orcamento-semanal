package codes.pedromanoel

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters.previousOrSame

data class Semana(private val inicioDaSemana: LocalDate) {
    private val fimDaSemana: LocalDate = inicioDaSemana.plusWeeks(1)

    fun transacoesDaSemanaDado(gastosFixos: List<GastoFixo>) =
        gastosFixos
            .map(this::paraTransacao)
            .filter(this::dentroDaSemana)

    private fun paraTransacao(gastoFixo: GastoFixo) =
        gastoFixo.naDataUtilMaisProximaA(inicioDaSemana)

    private fun dentroDaSemana(transacao: Transacao) =
        transacao.data >= inicioDaSemana && transacao.data < fimDaSemana

    companion object {
        fun daData(data: LocalDate) = Semana(
            data.with(previousOrSame(DayOfWeek.MONDAY))
        )
    }
}


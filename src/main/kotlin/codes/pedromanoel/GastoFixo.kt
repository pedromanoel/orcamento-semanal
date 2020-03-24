package codes.pedromanoel

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.ChronoField
import java.time.temporal.Temporal
import java.time.temporal.TemporalAdjuster
import java.time.temporal.TemporalAdjusters.next

data class GastoFixo(
    val nome: String,
    val diaDoVencimento: Int,
    val valor: Long
) {

    fun naDataUtilMaisProximaA(data: LocalDate) =
        Transacao(
            nome,
            mesMaisProximo(data)
                .atDay(diaDoVencimento)
                .with(ProximoDiaUtil()),
            valor
        )

    private fun mesMaisProximo(data: LocalDate) =
        if (data.dayOfMonth > diaDoVencimento)
            YearMonth.from(data).plusMonths(1)
        else
            YearMonth.from(data)

    private inner class ProximoDiaUtil :
        TemporalAdjuster {

        override fun adjustInto(temporal: Temporal): Temporal =
            when (diaDaSemana(temporal)) {
                DayOfWeek.SATURDAY, DayOfWeek.SUNDAY ->
                    temporal.with(next(DayOfWeek.MONDAY))
                else ->
                    temporal
            }

        private fun diaDaSemana(temporal: Temporal) =
            DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK))
    }
}
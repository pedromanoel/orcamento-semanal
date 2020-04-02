package codes.pedromanoel.domain

import java.time.DayOfWeek
import java.time.temporal.ChronoField
import java.time.temporal.Temporal
import java.time.temporal.TemporalAdjuster
import java.time.temporal.TemporalAdjusters

class ProximoDiaUtil : TemporalAdjuster {

    override fun adjustInto(temporal: Temporal): Temporal =
        when (diaDaSemana(temporal)) {
            DayOfWeek.SATURDAY, DayOfWeek.SUNDAY ->
                temporal.with(
                    TemporalAdjusters.next(
                        DayOfWeek.MONDAY
                    )
                )
            else ->
                temporal
        }

    private fun diaDaSemana(temporal: Temporal) =
        DayOfWeek.of(
            temporal.get(
                ChronoField.DAY_OF_WEEK
            )
        )
}
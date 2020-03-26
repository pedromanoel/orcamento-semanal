package codes.pedromanoel.domain

import java.time.DayOfWeek
import java.time.temporal.TemporalAdjusters.next

data class GastoVariavelSemanal(val nome: String, val valor: Long) :
    Gasto {
    override fun naDataUtilMaisProximaA(semana: Semana) =
        Transacao(
            nome,
            semana.inicioDaSemana.with(next(DayOfWeek.FRIDAY)),
            valor
        )
}
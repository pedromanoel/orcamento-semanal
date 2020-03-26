package codes.pedromanoel.domain

import java.time.LocalDate
import java.time.YearMonth

data class GastoFixo(
    val nome: String,
    val valor: Long,
    val diaDoVencimento: Int
) : Gasto {

    override fun naDataUtilMaisProximaA(semana: Semana) =
        Transacao(
            nome,
            mesMaisProximo(semana.inicioDaSemana)
                .atDay(diaDoVencimento)
                .with(ProximoDiaUtil()),
            valor
        )

    private fun mesMaisProximo(data: LocalDate) =
        if (data.dayOfMonth > diaDoVencimento)
            YearMonth.from(data).plusMonths(1)
        else
            YearMonth.from(data)

}
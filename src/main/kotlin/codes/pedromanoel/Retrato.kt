package codes.pedromanoel

import java.time.LocalDate
import java.time.YearMonth

class Retrato {
    val gastosFixos = GastosFixos()
    val gastosVariaveis = GastosVariaveis()
    val gastosSazonais = GastosSazonais()

}

data class GastoFixo(val nome: String, val vencimento: Int, val valor: Long) {

    fun naDataMaisProximaA(data: LocalDate) = Transacao(
        nome,
        mesMaisProximo(data).atDay(vencimento),
        valor
    )

    private fun mesMaisProximo(proximaDara: LocalDate) =
        if (proximaDara.dayOfMonth > vencimento)
            YearMonth.from(proximaDara).plusMonths(1)
        else
            YearMonth.from(proximaDara)
}

data class Transacao(val nome: String, val dia: LocalDate, val valor: Long)
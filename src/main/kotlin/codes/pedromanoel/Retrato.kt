package codes.pedromanoel

import java.time.LocalDate
import java.time.YearMonth

class Retrato {
    val gastosFixos = GastosFixos()
    val gastosVariaveis = GastosVariaveis()
    val gastosSazonais = GastosSazonais()

}

data class GastoFixo(val nome: String, val diaDoVencimento: Int, val valor: Long) {

    fun naDataMaisProximaA(data: LocalDate) = Transacao(
        nome,
        mesMaisProximo(data).atDay(diaDoVencimento),
        valor
    )

    private fun mesMaisProximo(data: LocalDate) =
        if (data.dayOfMonth > diaDoVencimento)
            YearMonth.from(data).plusMonths(1)
        else
            YearMonth.from(data)
}

data class Transacao(val nome: String, val data: LocalDate, val valor: Long)
package codes.pedromanoel

import java.time.LocalDate

class Retrato {
    val gastosFixos = GastosFixos()
    val gastosVariaveis = GastosVariaveis()
    val gastosSazonais = GastosSazonais()

}

data class Transacao(val nome: String, val data: LocalDate, val valor: Long)
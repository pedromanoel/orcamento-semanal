package codes.pedromanoel.domain

import java.time.LocalDate

interface Gasto {
    fun naDataUtilMaisProximaA(semana: Semana): Transacao
}

data class Transacao(val nome: String, val data: LocalDate, val valor: Long)
package codes.pedromanoel.domain

import java.time.LocalDate

const val SEMANAS_NO_MES = 4

interface Gasto {
    fun naDataUtilMaisProximaA(semana: Semana): Transacao
    val totalMensal: Int
}

data class Transacao(val nome: String, val data: LocalDate, val valor: Int)
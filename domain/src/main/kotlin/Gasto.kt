package codes.pedromanoel.orcamento.domain

import java.time.LocalDate

const val SEMANAS_NO_MES = 4

interface Gasto {
    fun naDataUtilMaisProximaDaSemana(semana: Semana): Transacao
    val totalMensal: Int
}

data class Transacao(val nome: String, val data: LocalDate, val valor: Int)
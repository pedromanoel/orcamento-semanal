package codes.pedromanoel.domain

import java.time.LocalDate

class Retrato {
    val totalMensal: Int
        get() = gastos.sumBy { it.totalMensal }

    private val gastos = ArrayList<Gasto>()

    fun adicionaGasto(gasto: Gasto) =
        gastos.add(gasto)

    fun transacoesDaSemana(data: LocalDate) =
        Semana.daData(data).transacoesDaSemanaDado(gastos)

    fun limpar() {
        gastos.clear()
    }

}

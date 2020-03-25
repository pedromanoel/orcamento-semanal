package codes.pedromanoel

import java.time.LocalDate

class Retrato {
    private val gastos = ArrayList<Gasto>()
    private val gastosFixos = ArrayList<GastoFixo>()
    private val gastosVariaveis = ArrayList<GastoVariavelSemanal>()
    private val gastosSazonais = ArrayList<GastoSazonal>()

    fun adicionaGasto(gastoFixo: GastoFixo) =
        gastos.add(gastoFixo)

    fun adicionaGasto(gastoVariavelSemanal: GastoVariavelSemanal) =
        gastos.add(gastoVariavelSemanal)

    fun transacoesDaSemana(data: LocalDate) =
        Semana.daData(data).transacoesDaSemanaDado(gastos)

    fun limpar() {
        gastos.clear()
        gastosFixos.clear()
        gastosVariaveis.clear()
    }

}

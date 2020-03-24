package codes.pedromanoel

import java.time.LocalDate

class GastosFixos {
    private val gastosFixos = ArrayList<GastoFixo>()

    fun adicionaGasto(gastoFixo: GastoFixo) =
        gastosFixos.add(gastoFixo)

    fun transacoesDaSemana(hoje: LocalDate) =
        Semana.doDia(hoje).transacoesDaSemanaParaGastos(gastosFixos)

    fun limpar() =
        gastosFixos.clear()
}
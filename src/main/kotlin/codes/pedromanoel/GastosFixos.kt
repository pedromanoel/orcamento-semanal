package codes.pedromanoel

import java.time.LocalDate

class GastosFixos {
    private val gastosFixos = ArrayList<GastoFixo>()

    fun adicionaGasto(gastoFixo: GastoFixo) =
        gastosFixos.add(gastoFixo)

    fun transacoesDaSemana(data: LocalDate) =
        Semana.daData(data).transacoesDaSemanaDado(gastosFixos)

    fun limpar() =
        gastosFixos.clear()
}
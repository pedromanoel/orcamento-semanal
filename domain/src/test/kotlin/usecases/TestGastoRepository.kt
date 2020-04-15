package codes.pedromanoel.orcamento.domain.usecases

import codes.pedromanoel.orcamento.domain.Gasto
import codes.pedromanoel.orcamento.domain.GastoRepository

class TestGastoRepository : GastoRepository {
    private val gastos: ArrayList<Gasto> = ArrayList()
    override fun adiciona(gasto: Gasto) {
        gastos.add(gasto)
    }

    override fun limpa() {
        gastos.clear()
    }

    override fun listaTodos() = gastos
}
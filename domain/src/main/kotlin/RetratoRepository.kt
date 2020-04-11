package codes.pedromanoel.orcamento.domain

class RetratoRepository {
    private val gastos = ArrayList<Gasto>()

    fun adiciona(gasto: Gasto) {
        gastos.add(gasto)
    }

    fun limpa() {
        gastos.clear()
    }

    fun listaTodos() = gastos

}

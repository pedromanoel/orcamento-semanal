package codes.pedromanoel.orcamento.domain

interface GastoRepository {
    fun adiciona(gasto: Gasto)
    fun limpa()
    fun listaTodos(): List<Gasto>
}

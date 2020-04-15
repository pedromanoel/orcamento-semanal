package codes.pedromanoel.orcamento.domain.usecases

import codes.pedromanoel.orcamento.domain.Gasto
import codes.pedromanoel.orcamento.domain.GastoFixo
import codes.pedromanoel.orcamento.domain.GastoRepository

class CadastrarGastos(private val gastoRepository: GastoRepository) {

    fun adicionarGasto(gasto: Gasto) {
        gastoRepository.adiciona(gasto)
    }
}
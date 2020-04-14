package codes.pedromanoel.orcamento.domain.usecases

import codes.pedromanoel.orcamento.domain.*

class ApresentarResumoDoRetrato(
        private val retratoRepository: RetratoRepository,
        private val retratoConfig: RetratoConfiguration) {
    fun obterResumo(): ResumoDoRetrato {
        val gastosPorTipo = retratoRepository
                .listaTodos()
                .groupBy { it::class }
                .mapValues { (_, gastos) ->
                    gastos.sumBy(Gasto::totalMensal)
                }

        return ResumoDoRetrato(
                gastosPorTipo.getValue(GastoFixo::class),
                gastosPorTipo.getValue(GastoVariavelSemanal::class),
                gastosPorTipo.getValue(GastoSazonal::class),
                retratoConfig.margem
        )
    }
}
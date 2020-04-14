package codes.pedromanoel.orcamento.domain.usecases

import assertk.assertThat
import assertk.assertions.isEqualTo
import codes.pedromanoel.orcamento.domain.ResumoDoRetrato
import codes.pedromanoel.orcamento.domain.RetratoRepository
import codes.pedromanoel.orcamento.domain.fixture.umGasto
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ApresentarResumoDoRetratoTest {

    private val retratoRepository = RetratoRepository()
    private val retratoConfig = RetratoConfiguration()
    private val useCase: ApresentarResumoDoRetrato

    init {
        useCase = ApresentarResumoDoRetrato(retratoRepository, retratoConfig)
    }

    @BeforeEach
    internal fun setUp() {
        retratoRepository.limpa()
    }

    @Test
    internal fun `monta resumo do retrato`() {
        with(retratoRepository) {
            adiciona(umGasto().comValor(1000).fixo)
            adiciona(umGasto().comValor(300).variavel)
            adiciona(umGasto().comValor(1200).comPeriodoDe(12).sazonal)
        }

        retratoConfig.margem = 0.15

        assertThat(useCase.obterResumo())
                .isEqualTo(ResumoDoRetrato(1000, 1200, 100, 0.15))
    }
}


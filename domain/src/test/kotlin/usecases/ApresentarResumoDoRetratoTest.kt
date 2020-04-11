package usecases

import assertk.Assert
import assertk.all
import codes.pedromanoel.orcamento.domain.ResumoDoRetrato
import codes.pedromanoel.orcamento.domain.usecases.ApresentarResumoDoRetrato
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.prop
import codes.pedromanoel.orcamento.domain.RetratoRepository
import codes.pedromanoel.orcamento.domain.fixture.umGasto
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ApresentarResumoDoRetratoTest {

    private val retratoRepository: RetratoRepository = RetratoRepository()
    private val useCase: ApresentarResumoDoRetrato

    init {
        useCase = ApresentarResumoDoRetrato(retratoRepository)
    }

    @BeforeEach
    internal fun setUp() {
        retratoRepository.limpa()
    }

    @Test
    internal fun `monta resumo do retrato`() {
        with(retratoRepository) {
            adiciona(umGasto().comValor(1000).fixo)
            adiciona(umGasto().comValor(2000).fixo)

            adiciona(umGasto().comValor(300).variavel)
            adiciona(umGasto().comValor(400).variavel)

            adiciona(umGasto().comValor(1200).comPeriodoDe(12).sazonal)
            adiciona(umGasto().comValor(1200).comPeriodoDe(24).sazonal)
        }

        val useCase = ApresentarResumoDoRetrato(retratoRepository)

        val resumo: ResumoDoRetrato = useCase.obterResumo()

        assertThat(resumo).all {
            hasTotalFixos(3000)
            hasTotalVariavelMensal(2800)
            hasTotalSazonalMensal(150)
        }
    }
}

private fun Assert<ResumoDoRetrato>.hasTotalSazonalMensal(valor: Int) =
        prop("totalSazonalMensal", ResumoDoRetrato::totalSazonalMensal).isEqualTo(valor)

private fun Assert<ResumoDoRetrato>.hasTotalVariavelMensal(valor: Int) =
        prop("totalVariavelMensal", ResumoDoRetrato::totalVariavelMensal).isEqualTo(valor)

private fun Assert<ResumoDoRetrato>.hasTotalFixos(valor: Int) =
        prop("totalFixos", ResumoDoRetrato::totalFixos).isEqualTo(valor)


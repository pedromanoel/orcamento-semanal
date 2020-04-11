package codes.pedromanoel.orcamento.domain

import assertk.assertThat
import assertk.assertions.isEqualTo
import codes.pedromanoel.orcamento.domain.fixture.umGasto
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class RetratoTest {

    private val retrato = Retrato()

    @BeforeEach
    internal fun setUp() {
        retrato.limpar()
    }

    @Test
    internal fun `total mensal é calculado com gastos fixos, variáveis e sazonais`() {
        retrato.adicionaGasto(umGasto().comValor(1000).variavel)
        retrato.adicionaGasto(umGasto().comValor(2000).variavel)

        retrato.adicionaGasto(umGasto().comValor(300).fixo)
        retrato.adicionaGasto(umGasto().comValor(400).fixo)

        retrato.adicionaGasto(umGasto().comValor(1200).comPeriodoDe(12).sazonal)
        retrato.adicionaGasto(umGasto().comValor(1200).comPeriodoDe(24).sazonal)

        val totalVariavel = (1000 + 2000) * SEMANAS_NO_MES
        val totalFixo = 300 + 400
        val totalSazonal = 1200 / 12 + 1200 / 24

        assertThat(retrato.totalMensal).isEqualTo(totalVariavel + totalFixo + totalSazonal)
    }

}
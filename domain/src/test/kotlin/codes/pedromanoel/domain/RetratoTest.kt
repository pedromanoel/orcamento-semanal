package codes.pedromanoel.domain

import codes.pedromanoel.domain.fixture.umGasto
import org.assertj.core.api.Assertions.assertThat
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
        retrato.adicionaGasto(umGasto().comValor(1000).gastoVariavelSemanal)
        retrato.adicionaGasto(umGasto().comValor(2000).gastoVariavelSemanal)

        retrato.adicionaGasto(umGasto().comValor(300).gastoFixo)
        retrato.adicionaGasto(umGasto().comValor(400).gastoFixo)

        retrato.adicionaGasto(umGasto().comValor(1200).comPeriodoDe(12).gastoSazonal)
        retrato.adicionaGasto(umGasto().comValor(1200).comPeriodoDe(24).gastoSazonal)

        val totalVariavel = (1000 + 2000) * SEMANAS_NO_MES
        val totalFixo = 300 + 400
        val totalSazonal = 1200 / 12 + 1200 / 24

        assertThat(retrato.totalMensal).isEqualTo(totalVariavel + totalFixo + totalSazonal)
    }

}
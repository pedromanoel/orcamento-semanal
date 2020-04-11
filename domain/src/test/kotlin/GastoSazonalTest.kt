package codes.pedromanoel.orcamento.domain

import assertk.assertThat
import assertk.assertions.isEqualTo
import codes.pedromanoel.orcamento.domain.fixture.umGasto
import org.junit.jupiter.api.Test

internal class GastoSazonalTest {

    @Test
    internal fun `total do mês é computado a partir do período em meses`() {
        val gastoSazonal = umGasto().comValor(1200_00).comPeriodoDe(6).sazonal

        assertThat(gastoSazonal.totalMensal).isEqualTo(1200_00 / 6)
    }
}
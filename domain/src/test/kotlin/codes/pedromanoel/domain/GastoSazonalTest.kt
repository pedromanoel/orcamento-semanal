package codes.pedromanoel.domain

import codes.pedromanoel.domain.fixture.umGasto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GastoSazonalTest {

    @Test
    internal fun `total do mês é computado a partir do período em meses`() {
        val gastoSazonal = umGasto().comValor(1200_00).comPeriodoDe(6).gastoSazonal

        assertThat(gastoSazonal.totalMensal).isEqualTo(1200_00 / 6)
    }
}
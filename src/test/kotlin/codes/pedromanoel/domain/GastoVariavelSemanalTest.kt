package codes.pedromanoel.domain

import codes.pedromanoel.domain.GastoFixture.Factory.umGasto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GastoVariavelSemanalTest {

    @Test
    internal fun `total mensal é a soma do valor vezes número de semanas`() {
        assertThat(umGasto().comValor(10_00).gastoVariavelSemanal.totalMensal)
            .isEqualTo(10_00 * SEMANAS_NO_MES)
    }
}

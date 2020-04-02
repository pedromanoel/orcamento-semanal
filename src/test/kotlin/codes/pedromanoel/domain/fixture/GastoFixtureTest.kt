package codes.pedromanoel.domain.fixture

import codes.pedromanoel.domain.GastoFixo
import codes.pedromanoel.domain.GastoSazonal
import codes.pedromanoel.domain.GastoVariavelSemanal
import codes.pedromanoel.domain.fixture.GastoFixture.Factory.umGasto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GastoFixtureTest {

    @Test
    internal fun `cria gasto fixo com valores padrões`() {
        assertThat(umGasto().gastoFixo)
            .isEqualTo(GastoFixo(NOME, VALOR, DIA_DO_VENCIMENTO))
    }

    @Test
    internal fun `cria gasto variável com valores padrões`() {
        assertThat(umGasto().gastoVariavelSemanal)
            .isEqualTo(GastoVariavelSemanal(NOME, VALOR))
    }

    @Test
    internal fun `cria gasto sazonal com valores padrões`() {
        assertThat(umGasto().gastoSazonal)
            .isEqualTo(GastoSazonal(NOME, VALOR, PERIODO_EM_MESES))
    }

    @Test
    internal fun `substitui parâmetros do gasto fixo`() {
        assertThat(
            umGasto()
                .comNome("Um nome diferente")
                .comValor(15_00)
                .noDiaDoVencimento(15)
                .gastoFixo
        ).isEqualTo(GastoFixo("Um nome diferente", 15_00, 15))
    }

    @Test
    internal fun `substitui parâmetros do gasto variável`() {
        assertThat(
            umGasto()
                .comNome("Um nome diferente")
                .comValor(15_00)
                .gastoVariavelSemanal
        ).isEqualTo(GastoVariavelSemanal("Um nome diferente", 15_00))
    }

    @Test
    internal fun `substitui parâmetros do gasto sazonal`() {
        assertThat(
            umGasto()
                .comNome("Um nome diferente")
                .comValor(15_00)
                .comPeriodoDe(3)
                .gastoSazonal
        ).isEqualTo(GastoSazonal("Um nome diferente", 15_00, 3))
    }
}
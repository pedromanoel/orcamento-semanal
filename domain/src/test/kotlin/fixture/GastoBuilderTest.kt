package codes.pedromanoel.orcamento.domain.fixture

import assertk.assertThat
import assertk.assertions.isEqualTo
import codes.pedromanoel.orcamento.domain.GastoFixo
import codes.pedromanoel.orcamento.domain.GastoSazonal
import codes.pedromanoel.orcamento.domain.GastoVariavelSemanal
import org.junit.jupiter.api.Test

internal class GastoBuilderTest {

    @Test
    internal fun `cria gasto fixo com valores padrões`() {
        assertThat(umGasto().fixo)
            .isEqualTo(GastoFixo(NOME, VALOR, DIA_DO_VENCIMENTO))
    }

    @Test
    internal fun `cria gasto variável com valores padrões`() {
        assertThat(umGasto().variavel)
            .isEqualTo(GastoVariavelSemanal(NOME, VALOR))
    }

    @Test
    internal fun `cria gasto sazonal com valores padrões`() {
        assertThat(umGasto().sazonal)
            .isEqualTo(GastoSazonal(NOME, VALOR, PERIODO_EM_MESES))
    }

    @Test
    internal fun `substitui parâmetros do gasto fixo`() {
        assertThat(
            umGasto()
                .comNome("Um nome diferente")
                .comValor(15_00)
                .noDiaDoVencimento(15)
                .fixo
        ).isEqualTo(GastoFixo("Um nome diferente", 15_00, 15))
    }

    @Test
    internal fun `substitui parâmetros do gasto variável`() {
        assertThat(
            umGasto()
                .comNome("Um nome diferente")
                .comValor(15_00)
                .variavel
        ).isEqualTo(GastoVariavelSemanal("Um nome diferente", 15_00))
    }

    @Test
    internal fun `substitui parâmetros do gasto sazonal`() {
        assertThat(
            umGasto()
                .comNome("Um nome diferente")
                .comValor(15_00)
                .comPeriodoDe(3)
                .sazonal
        ).isEqualTo(GastoSazonal("Um nome diferente", 15_00, 3))
    }
}
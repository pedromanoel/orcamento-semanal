package codes.pedromanoel.domain

import codes.pedromanoel.domain.fixture.GastoFixture.Factory.umGasto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalDate

internal class RetratoTest {

    private val retrato = Retrato()

    @BeforeEach
    internal fun setUp() {
        retrato.limpar()
    }

    @Nested
    inner class GastosVariaveis {

        @ParameterizedTest
        @ValueSource(
            strings = [
                "2020-02-24",
                "2020-03-02",
                "2020-03-09",
                "2020-03-16",
                "2020-03-23",
                "2020-03-30"]
        )
        internal fun `transacao aparece na segunda-feira`(
            inicioDaSemana: LocalDate
        ) {
            retrato.adicionaGasto(umGasto().noDiaDoVencimento(1).gastoVariavelSemanal)

            assertThat(retrato.transacoesDaSemana(inicioDaSemana))
                .extracting("data")
                .contains(inicioDaSemana)
        }

        @Test
        internal fun `total mensal é computado somando as semanas`() {
            retrato.adicionaGasto(umGasto().comValor(1000).gastoVariavelSemanal)
            retrato.adicionaGasto(umGasto().comValor(2000).gastoVariavelSemanal)

            assertThat(retrato.totalMensal).isEqualTo((1000 + 2000) * SEMANAS_NO_MES)
        }
    }

    @Nested
    inner class GastosFixos {

        @Test
        internal fun `total mensal é computado com a soma dos gastos`() {
            retrato.adicionaGasto(umGasto().comValor(1000).gastoFixo)
            retrato.adicionaGasto(umGasto().comValor(2000).gastoFixo)

            assertThat(retrato.totalMensal).isEqualTo(1000 + 2000)
        }
    }

}
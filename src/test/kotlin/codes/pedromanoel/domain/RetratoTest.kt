package codes.pedromanoel.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

private val GASTO_FIXO_1 = GastoFixo("Tarifa Bradesco", 34_00, 1)
private val GASTO_FIXO_2 = GastoFixo("Luz", 55_43, 2)
private val GASTO_FIXO_6 = GastoFixo("Água", 49_81, 6)
private val GASTO_FIXO_9 = GastoFixo("Internet", 89_99, 9)
private val GASTO_FIXO_27 = GastoFixo("Empréstimo", 1500_00, 27)

private val GASTO_VARIAVEL_1 = GastoVariavelSemanal("Pizza", 50_00)

private val TRANSACAO_27_FEV = GASTO_FIXO_27.naDataUtilMaisProximaA(SEMANA_24_FEV)
private val TRANSACAO_2_MAR = GASTO_FIXO_2.naDataUtilMaisProximaA(SEMANA_2_MAR)
private val TRANSACAO_6_MAR = GASTO_FIXO_6.naDataUtilMaisProximaA(SEMANA_2_MAR)
private val TRANSACAO_1_ABR = GASTO_FIXO_1.naDataUtilMaisProximaA(SEMANA_30_MAR)
private val TRANSACAO_2_ABR = GASTO_FIXO_2.naDataUtilMaisProximaA(SEMANA_30_MAR)

private val TRANSACAO_FIXA_MAR_1 = GASTO_VARIAVEL_1.naDataUtilMaisProximaA(SEMANA_24_FEV)
private val TRANSACAO_FIXA_MAR_6 = GASTO_VARIAVEL_1.naDataUtilMaisProximaA(SEMANA_2_MAR)

internal class RetratoTest {

    private val retrato = Retrato()

    @BeforeEach
    internal fun setUp() {
        retrato.limpar()
    }

    @Nested
    inner class GastosVariaveis {

        @Test
        internal fun `transacao aparece em todas as semanas no último dia útil da semana`() {
            retrato.adicionaGasto(GASTO_VARIAVEL_1)

            assertThat(retrato.transacoesDaSemana(MAR_1))
                .contains(TRANSACAO_FIXA_MAR_1)
            assertThat(retrato.transacoesDaSemana(MAR_2))
                .contains(TRANSACAO_FIXA_MAR_6)
        }
    }

    @Nested
    inner class GastosFixos {

        @Test
        internal fun `retorna transacoes do mês anterior`() {
            retrato.adicionaGasto(GASTO_FIXO_27)

            assertThat(retrato.transacoesDaSemana(MAR_1))
                .containsExactly(TRANSACAO_27_FEV)
        }

        @Test
        internal fun `retorna transacoes do próximo mês`() {
            retrato.adicionaGasto(GASTO_FIXO_1)
            retrato.adicionaGasto(GASTO_FIXO_2)

            assertThat(retrato.transacoesDaSemana(MAR_31))
                .containsExactly(
                    TRANSACAO_1_ABR,
                    TRANSACAO_2_ABR
                )
        }

        @Test
        internal fun `retorna transacoes do início da semana`() {
            retrato.adicionaGasto(GASTO_FIXO_1)
            retrato.adicionaGasto(GASTO_FIXO_2)

            assertThat(retrato.transacoesDaSemana(MAR_2))
                .containsExactly(TRANSACAO_2_MAR)
        }

        @Test
        internal fun `retorna transacoes do fim da semana`() {
            retrato.adicionaGasto(GASTO_FIXO_6)
            retrato.adicionaGasto(GASTO_FIXO_9)

            assertThat(retrato.transacoesDaSemana(MAR_8))
                .containsExactly(TRANSACAO_6_MAR)
        }
    }

}
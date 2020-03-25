package codes.pedromanoel

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.Month
import java.time.YearMonth

private val FEV_2020 = YearMonth.of(2020, Month.FEBRUARY)
private val MAR_2020 = YearMonth.of(2020, Month.MARCH)
private val ABR_2020 = YearMonth.of(2020, Month.APRIL)

private val MAR_1 = MAR_2020.atDay(1)
private val MAR_2 = MAR_2020.atDay(2)
private val MAR_8 = MAR_2020.atDay(8)
private val MAR_31 = MAR_2020.atDay(31)

private val GASTO_FIXO_1 = GastoFixo("Tarifa Bradesco", 34_00, 1)
private val GASTO_FIXO_2 = GastoFixo("Luz", 55_43, 2)
private val GASTO_FIXO_6 = GastoFixo("Água", 49_81, 6)
private val GASTO_FIXO_9 = GastoFixo("Internet", 89_99, 9)
private val GASTO_FIXO_27 = GastoFixo("Empréstimo", 1500_00, 27)

private val SEMANA_24_FEV = Semana.daData(FEV_2020.atDay(24))
private val SEMANA_2_MAR = Semana.daData(MAR_2020.atDay(2))
private val SEMANA_30_MAR = Semana.daData(MAR_2020.atDay(30))

private val GASTO_VARIAVEL_1 = GastoVariavelSemanal("Pizza", 50_00)

private val TRANSACAO_27_FEV =
    GASTO_FIXO_27.naDataUtilMaisProximaA(SEMANA_24_FEV)
private val TRANSACAO_2_MAR =
    GASTO_FIXO_2.naDataUtilMaisProximaA(SEMANA_2_MAR)
private val TRANSACAO_6_MAR =
    GASTO_FIXO_6.naDataUtilMaisProximaA(SEMANA_2_MAR)
private val TRANSACAO_1_ABR =
    GASTO_FIXO_1.naDataUtilMaisProximaA(SEMANA_30_MAR)
private val TRANSACAO_2_ABR =
    GASTO_FIXO_2.naDataUtilMaisProximaA(SEMANA_30_MAR)

private val TRANSACAO_FIXA_MAR_1 =
    GASTO_VARIAVEL_1.naDataUtilMaisProximaA(SEMANA_24_FEV)
private val TRANSACAO_FIXA_MAR_6 =
    GASTO_VARIAVEL_1.naDataUtilMaisProximaA(SEMANA_2_MAR)

internal class RetratoTest {
    // Março
    // Seg  Ter  Qua  Qui  Sex  Sab  Dom
    //  24   25   26   27   28   29 |  1
    //   2    3    4    5    6    7    8
    //   9   10   11   12   13   14   15
    //  16   17   18   19   20   21   22
    //  23   24   25   26   27   28   29
    //  30   31 |  1    2    3    4    5

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
                .containsExactly(TRANSACAO_1_ABR, TRANSACAO_2_ABR)
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
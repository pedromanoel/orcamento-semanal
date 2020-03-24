package codes.pedromanoel

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
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

private val GASTO_1 = GastoFixo("Tarifa Bradesco", 1, 34_00)
private val GASTO_2 = GastoFixo("Luz", 2, 55_43)
private val GASTO_6 = GastoFixo("Água", 6, 49_81)
private val GASTO_9 = GastoFixo("Internet", 9, 89_99)
private val GASTO_27 = GastoFixo("Empréstimo", 27, 1500_00)

private val TRANSACAO_27_FEV = GASTO_27.naDataUtilMaisProximaA(FEV_2020.atDay(1))
private val TRANSACAO_2_MAR = GASTO_2.naDataUtilMaisProximaA(MAR_2020.atDay(1))
private val TRANSACAO_6_MAR = GASTO_6.naDataUtilMaisProximaA(MAR_2020.atDay(1))
private val TRANSACAO_1_ABR = GASTO_1.naDataUtilMaisProximaA(ABR_2020.atDay(1))
private val TRANSACAO_2_ABR = GASTO_2.naDataUtilMaisProximaA(ABR_2020.atDay(1))

internal class GastosFixosTest {
    // Março
    // Seg  Ter  Qua  Qui  Sex  Sab  Dom
    //  24   25   26   27   28   29 |  1
    //   2    3    4    5    6    7    8
    //   9   10   11   12   13   14   15
    //  16   17   18   19   20   21   22
    //  23   24   25   26   27   28   29
    //  30   31 |  1    2    3    4    5

    private val gastosFixos = GastosFixos()

    @BeforeEach
    internal fun setUp() {
        gastosFixos.limpar()
    }

    @Test
    internal fun `retorna transacoes do mês anterior`() {
        gastosFixos.adicionaGasto(GASTO_27)

        assertThat(gastosFixos.transacoesDaSemana(MAR_1))
            .containsExactly(TRANSACAO_27_FEV)
    }

    @Test
    internal fun `retorna transacoes do próximo mês`() {
        gastosFixos.adicionaGasto(GASTO_1)
        gastosFixos.adicionaGasto(GASTO_2)

        assertThat(gastosFixos.transacoesDaSemana(MAR_31))
            .containsExactly(TRANSACAO_1_ABR, TRANSACAO_2_ABR)
    }

    @Test
    internal fun `retorna transacoes do início da semana`() {
        gastosFixos.adicionaGasto(GASTO_1)
        gastosFixos.adicionaGasto(GASTO_2)

        assertThat(gastosFixos.transacoesDaSemana(MAR_2))
            .containsExactly(TRANSACAO_2_MAR)
    }

    @Test
    internal fun `retorna transacoes do fim da semana`() {
        gastosFixos.adicionaGasto(GASTO_6)
        gastosFixos.adicionaGasto(GASTO_9)

        assertThat(gastosFixos.transacoesDaSemana(MAR_8))
            .containsExactly(TRANSACAO_6_MAR)
    }
}
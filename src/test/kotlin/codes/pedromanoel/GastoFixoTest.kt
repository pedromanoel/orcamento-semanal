package codes.pedromanoel

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private val GASTO_28 = GastoFixo("Gasto", 1_00, 28)

internal class GastoFixoTest {
    // Março
    // Seg  Ter  Qua  Qui  Sex  Sab  Dom
    //  24   25   26   27   28   29 |  1
    //   2    3    4    5    6    7    8
    //   9   10   11   12   13   14   15
    //  16   17   18   19   20   21   22
    //  23   24   25   26   27   28   29
    //  30   31 |  1    2    3    4    5

    @Test
    internal fun `cria transação com mesmo dia quando vencimento é dia útil`() {
        val dataDaTransacao = GASTO_28
            .naDataUtilMaisProximaA(SEMANA_24_FEV)
            .data

        assertThat(dataDaTransacao).isEqualTo(FEV_2020.atDay(28))
    }

    @Test
    internal fun `cria transação no próximo dia útil`() {
        val dataDaTransacao = GASTO_28
            .naDataUtilMaisProximaA(SEMANA_23_MAR)
            .data

        assertThat(dataDaTransacao).isEqualTo(MAR_2020.atDay(30))
    }
}
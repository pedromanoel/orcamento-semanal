package codes.pedromanoel.orcamento.domain

import assertk.assertThat
import assertk.assertions.isEqualTo
import codes.pedromanoel.orcamento.domain.fixture.*
import org.junit.jupiter.api.Test

internal class GastoFixoTest {

    @Test
    internal fun `cria transação no dia do vencimento dentro da semana`() {
        // FEV                           MAR
        // Seg  Ter  Qua  Qui  Sex  Sab  Dom
        //  24   25   26   27   28   29 |  1
        val dataDaTransacao = umGasto()
            .noDiaDoVencimento(28)
            .fixo
            .naDataUtilMaisProximaDaSemana(SEMANA_24_FEV)
            .data

        assertThat(dataDaTransacao).isEqualTo(FEV_2020.atDay(28))
    }

    @Test
    internal fun `cria transação no próximo dia útil dentro da semana`() {
        // MAR
        // Seg  Ter  Qua  Qui  Sex  Sab  Dom
        //  23   24   25   26   27   28   29
        //  30   31 |  1    2    3    4    5
        val dataDaTransacao = umGasto()
            .noDiaDoVencimento(28)
            .fixo
            .naDataUtilMaisProximaDaSemana(SEMANA_23_MAR)
            .data

        assertThat(dataDaTransacao).isEqualTo(MAR_2020.atDay(30))
    }

    @Test
    internal fun `cria transação no próximo mês quando vencimento está fora da semana`() {
        // MAR       ABR
        // Seg  Ter  Qua  Qui  Sex  Sab  Dom
        //  23   24   25   26   27   28   29
        //  30   31 |  1    2    3    4    5
        val dataDaTransacao = umGasto()
            .noDiaDoVencimento(1)
            .fixo
            .naDataUtilMaisProximaDaSemana(SEMANA_23_MAR)
            .data

        assertThat(dataDaTransacao).isEqualTo(ABR_2020.atDay(1))
    }

    @Test
    internal fun `o total do mês é o mesmo que o valor`() {
        assertThat(umGasto().comValor(101_00).fixo.totalMensal)
            .isEqualTo(101_00)
    }
}
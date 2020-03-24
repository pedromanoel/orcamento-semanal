package codes.pedromanoel

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.Month
import java.time.YearMonth

private val MARCO_2020 = YearMonth.of(2020, Month.MARCH)

private val DOIS_DE_MARCO = MARCO_2020.atDay(2)
private val OITO_DE_MARCO = MARCO_2020.atDay(8)

private val GASTO_UM_MARCO = GastoFixo("Tarifa Bradesco", 1, 34_00)
private val GASTO_DOIS_MARCO = GastoFixo("Luz", 2, 55_43)
private val GASTO_OITO_MARCO = GastoFixo("Água", 8, 49_81)
private val GASTO_NOVE_MARCO = GastoFixo("Internet", 9, 89_99)

private val TRANSACAO_DOIS_MARCO = Transacao("Luz", DOIS_DE_MARCO, 55_43)
private val TRANSACAO_OITO_MARCO = Transacao("Água", OITO_DE_MARCO, 49_81)

internal class GastosFixosTest {
    // Março
    // Seg  Ter  Qua  Qui  Sex  Sab  Dom
    //                                 1
    //   2    3    4    5    6    7    8
    //   9   10   11   12   13   14   15
    //  16   17   18   19   20   21   22
    //  23   24   25   26   27   28   29
    //  30   31

    private val gastosFixos = GastosFixos()

    @BeforeEach
    internal fun setUp() {
        gastosFixos.limpar()
    }

    @Test
    internal fun `retorna transacoes do início da semana` () {
        gastosFixos.adicionaGasto(GASTO_UM_MARCO)
        gastosFixos.adicionaGasto(GASTO_DOIS_MARCO)

        val hoje = DOIS_DE_MARCO
        assertThat(gastosFixos.transacoesDaSemana(hoje))
            .containsExactly(TRANSACAO_DOIS_MARCO)
    }

    @Test
    internal fun `retorna transacoes do fim da semana`() {
        gastosFixos.adicionaGasto(GASTO_OITO_MARCO)
        gastosFixos.adicionaGasto(GASTO_NOVE_MARCO)

        val hoje = OITO_DE_MARCO
        assertThat(gastosFixos.transacoesDaSemana(hoje))
            .containsExactly(TRANSACAO_OITO_MARCO)
    }
}
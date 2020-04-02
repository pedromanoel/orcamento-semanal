package codes.pedromanoel.domain

import codes.pedromanoel.domain.fixture.FEV_2020
import codes.pedromanoel.domain.fixture.MAR_2020
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SemanaTest {
    // Março
    // Seg  Ter  Qua  Qui  Sex  Sab  Dom
    //  24   25   26   27   28   29 |  1
    //   2    3    4    5    6    7    8
    //   9   10   11   12   13   14   15
    //  16   17   18   19   20   21   22
    //  23   24   25   26   27   28   29
    //  30   31 |  1    2    3    4    5

    @Test
    internal fun `cria uma semana para uma data que a contém`() {
        val semana = Semana.daData(FEV_2020.atDay(27))

        assertThat(semana.inicioDaSemana).isEqualTo(FEV_2020.atDay(24))
        assertThat(semana.fimDaSemana).isEqualTo(MAR_2020.atDay(1))
    }

    @Test
    internal fun `testa se data está dentro da semana`() {
        val semana = Semana.daData(MAR_2020.atDay(2))

        val dentroDaSemana = listOf(
            MAR_2020.atDay(2),
            MAR_2020.atDay(3),
            MAR_2020.atDay(4),
            MAR_2020.atDay(5),
            MAR_2020.atDay(6),
            MAR_2020.atDay(7),
            MAR_2020.atDay(8)
        ).map(semana::dentroDaSemana)

        assertThat(dentroDaSemana)
            .containsExactly(
                true, // 2/mar
                true, // 3/mar
                true, // 4/mar
                true, // 5/mar
                true, // 6/mar
                true, // 7/mar
                false // 8/mar
            )
    }

    @Test
    internal fun `cria a próxima semana`() {
        val semana: Semana = Semana.daData(MAR_2020.atDay(2)).proxima()

        assertThat(semana.inicioDaSemana).isEqualTo(MAR_2020.atDay(9))
        assertThat(semana.fimDaSemana).isEqualTo(MAR_2020.atDay(15))
    }
}
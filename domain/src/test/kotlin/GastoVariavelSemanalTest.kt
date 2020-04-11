package codes.pedromanoel.orcamento.domain

import codes.pedromanoel.orcamento.domain.fixture.umGasto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.time.DayOfWeek
import java.time.LocalDate

internal class GastoVariavelSemanalTest {

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
    internal fun `transacao aparece sempre na segunda-feira`(inicioDaSemana: LocalDate) {
        val semana = Semana.daData(inicioDaSemana)
        val gastoVariavelSemanal = umGasto()
            .noDiaDoVencimento(1)
            .variavel

        assertThat(
            gastoVariavelSemanal
                .naDataUtilMaisProximaDaSemana(semana)
                .data
                .dayOfWeek
        ).isEqualTo(DayOfWeek.MONDAY)
    }

    @Test
    internal fun `total mensal é a soma do valor vezes número de semanas`() {
        assertThat(umGasto().comValor(10_00).variavel.totalMensal)
            .isEqualTo(10_00 * SEMANAS_NO_MES)
    }
}

package codes.pedromanoel.orcamento.domain

import assertk.all
import assertk.assertThat
import codes.pedromanoel.orcamento.domain.asserts.hasMargem
import codes.pedromanoel.orcamento.domain.asserts.hasTotalMensal
import org.junit.jupiter.api.Test

internal class ResumoDoRetratoTest {

    @Test
    internal fun `resumo calcula total dos gastos mensais`() {
        val resumoDoRetrato = ResumoDoRetrato(1000, 2000, 350)

        assertThat(resumoDoRetrato).hasTotalMensal(3350.0)
    }

    @Test
    internal fun `calcula total com margem`() {
        val resumoDoRetrato = ResumoDoRetrato(1000, 2000, 350, 0.10)

        assertThat(resumoDoRetrato).all {
            hasMargem(3350 * 0.10)
            hasTotalMensal(3350 * 1.10)
        }
    }
}
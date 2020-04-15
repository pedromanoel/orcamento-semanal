package codes.pedromanoel.orcamento.domain.usecases

import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.containsOnly
import codes.pedromanoel.orcamento.domain.fixture.umGasto
import org.junit.jupiter.api.Test

internal class CadastrarGastosTest {

    private val gastoRepository = TestGastoRepository()

    @Test
    internal fun `adiciona gastos`() {
        val useCase = CadastrarGastos(gastoRepository)

        val gastoFixo = umGasto().fixo
        val gastoVariavel = umGasto().variavel
        val gastoSazonal = umGasto().sazonal

        useCase.adicionarGasto(gastoFixo)
        useCase.adicionarGasto(gastoVariavel)
        useCase.adicionarGasto(gastoSazonal)

        assertThat(gastoRepository.listaTodos())
                .containsExactly(
                        gastoFixo,
                        gastoVariavel,
                        gastoSazonal
                )
    }
}
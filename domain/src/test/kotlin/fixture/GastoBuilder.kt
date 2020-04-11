package codes.pedromanoel.orcamento.domain.fixture

import codes.pedromanoel.orcamento.domain.GastoFixo
import codes.pedromanoel.orcamento.domain.GastoSazonal
import codes.pedromanoel.orcamento.domain.GastoVariavelSemanal

const val NOME = "Gasto"
const val VALOR = 10_00
const val DIA_DO_VENCIMENTO = 1
const val PERIODO_EM_MESES = 12

fun umGasto() : GastoBuilder = GastoBuilderImpl()

interface GastoBuilder {
    fun comNome(nome: String): GastoBuilder
    fun comValor(valor: Int): GastoBuilder
    fun noDiaDoVencimento(diaDoVencimento: Int): GastoBuilder
    fun comPeriodoDe(periodoEmMeses: Int): GastoBuilder

    val fixo : GastoFixo
    val variavel : GastoVariavelSemanal
    val sazonal : GastoSazonal
}

private class GastoBuilderImpl : GastoBuilder {
    private var nome: String = NOME
    private var valor = VALOR
    private var diaDoVencimento = DIA_DO_VENCIMENTO
    private var periodoEmMeses = PERIODO_EM_MESES

    override fun comNome(nome: String) =
        apply { this.nome = nome }

    override fun comValor(valor: Int) =
        apply { this.valor = valor }

    override fun noDiaDoVencimento(diaDoVencimento: Int) =
        apply { this.diaDoVencimento = diaDoVencimento }

    override fun comPeriodoDe(periodoEmMeses: Int) =
        apply { this.periodoEmMeses = periodoEmMeses }

    override val fixo
        get() = GastoFixo(nome, valor, diaDoVencimento)
    override val variavel
        get() = GastoVariavelSemanal(nome, valor)
    override val sazonal
        get() = GastoSazonal(nome, valor, periodoEmMeses)

}
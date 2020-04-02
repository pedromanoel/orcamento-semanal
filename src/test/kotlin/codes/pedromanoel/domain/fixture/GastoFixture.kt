package codes.pedromanoel.domain.fixture

import codes.pedromanoel.domain.GastoFixo
import codes.pedromanoel.domain.GastoSazonal
import codes.pedromanoel.domain.GastoVariavelSemanal

const val NOME = "Gasto"
const val VALOR = 10_00
const val DIA_DO_VENCIMENTO = 1
const val PERIODO_EM_MESES = 12

class GastoFixture private constructor() {
    private var nome: String = NOME
    private var valor = VALOR
    private var diaDoVencimento = DIA_DO_VENCIMENTO
    private var periodoEmMeses = PERIODO_EM_MESES

    fun comNome(nome: String) =
        apply { this.nome = nome }

    fun comValor(valor: Int) =
        apply { this.valor = valor }

    fun noDiaDoVencimento(diaDoVencimento: Int) =
        apply { this.diaDoVencimento = diaDoVencimento }

    fun comPeriodoDe(periodoEmMeses: Int) =
        apply { this.periodoEmMeses = periodoEmMeses }

    val gastoFixo
        get() = GastoFixo(nome, valor, diaDoVencimento)
    val gastoVariavelSemanal
        get() = GastoVariavelSemanal(nome, valor)
    val gastoSazonal
        get() = GastoSazonal(nome, valor, periodoEmMeses)

    companion object Factory {
        fun umGasto() = GastoFixture()
    }

}
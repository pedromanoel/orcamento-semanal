package codes.pedromanoel.domain

const val NOME = "Gasto"
const val VALOR = 10_00
const val DIA_DO_VENCIMENTO = 1

class GastoFixture private constructor() {
    private var nome: String = NOME
    private var valor = VALOR
    private var diaDoVencimento = DIA_DO_VENCIMENTO

    fun comNome(nome: String) =
        apply { this.nome = nome }

    fun comValor(valor: Int) =
        apply { this.valor = valor }

    fun noDiaDoVencimento(diaDoVencimento: Int) =
        apply { this.diaDoVencimento = diaDoVencimento }

    val gastoFixo
        get() = GastoFixo(nome, valor, diaDoVencimento)
    val gastoVariavelSemanal
        get() = GastoVariavelSemanal(nome, valor)

    companion object Factory {
        fun umGasto() = GastoFixture()
    }

}
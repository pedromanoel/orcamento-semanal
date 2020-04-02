package codes.pedromanoel.domain

data class GastoSazonal(
    val nome: String,
    val valor: Int,
    val periodoEmMeses: Int
) : Gasto {
    override fun naDataUtilMaisProximaDaSemana(semana: Semana): Transacao {
        TODO("Not yet implemented")
    }

    override val totalMensal: Int
        get() = valor / periodoEmMeses
}

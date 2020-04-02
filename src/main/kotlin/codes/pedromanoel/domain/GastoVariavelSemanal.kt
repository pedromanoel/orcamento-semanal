package codes.pedromanoel.domain

data class GastoVariavelSemanal(val nome: String, val valor: Int) :
    Gasto {
    override fun naDataUtilMaisProximaDaSemana(semana: Semana) =
        Transacao(
            nome,
            semana.inicioDaSemana,
            valor
        )

    override val totalMensal get() = valor * SEMANAS_NO_MES
}
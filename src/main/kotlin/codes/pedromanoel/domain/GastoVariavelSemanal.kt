package codes.pedromanoel.domain

data class GastoVariavelSemanal(val nome: String, val valor: Long) :
    Gasto {
    override fun naDataUtilMaisProximaA(semana: Semana) =
        Transacao(
            nome,
            semana.inicioDaSemana,
            valor
        )
}
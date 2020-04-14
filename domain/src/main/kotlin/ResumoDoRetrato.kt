package codes.pedromanoel.orcamento.domain

data class ResumoDoRetrato(
        val totalFixos: Int,
        val totalVariavelMensal: Int,
        val totalSazonalMensal: Int,
        private val _margem: Double = 0.0
) {
    val totalMensal: Double
        get() = margem + total

    val margem: Double
        get() = _margem * total

    private val total: Int
        get() = totalFixos + totalVariavelMensal + totalSazonalMensal
}
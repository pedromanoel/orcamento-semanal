package codes.pedromanoel.orcamento.domain.asserts

import assertk.Assert
import assertk.assertions.isCloseTo
import assertk.assertions.prop
import codes.pedromanoel.orcamento.domain.ResumoDoRetrato

internal fun Assert<ResumoDoRetrato>.hasTotalMensal(valor: Double) =
        prop("total", ResumoDoRetrato::totalMensal).isCloseTo(valor, 0.01)

internal fun Assert<ResumoDoRetrato>.hasMargem(valor: Double) =
        prop("margem", ResumoDoRetrato::margem).isCloseTo(valor, 0.01)

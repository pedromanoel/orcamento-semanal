package codes.pedromanoel.orcamento.domain.fixture

import codes.pedromanoel.orcamento.domain.Semana

// Março
// Seg  Ter  Qua  Qui  Sex  Sab  Dom
//  24   25   26   27   28   29 |  1
//   2    3    4    5    6    7    8
//   9   10   11   12   13   14   15
//  16   17   18   19   20   21   22
//  23   24   25   26   27   28   29
//  30   31 |  1    2    3    4    5

val SEMANA_24_FEV = Semana.daData(FEV_2020.atDay(24))
val SEMANA_2_MAR = SEMANA_24_FEV.proxima()
val SEMANA_23_MAR = SEMANA_2_MAR.proxima().proxima().proxima()
val SEMANA_30_MAR = SEMANA_23_MAR.proxima()
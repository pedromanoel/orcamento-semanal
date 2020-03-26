package codes.pedromanoel.domain

import java.time.LocalDate
import java.time.Month
import java.time.YearMonth

// Março
// Seg  Ter  Qua  Qui  Sex  Sab  Dom
//  24   25   26   27   28   29 |  1
//   2    3    4    5    6    7    8
//   9   10   11   12   13   14   15
//  16   17   18   19   20   21   22
//  23   24   25   26   27   28   29
//  30   31 |  1    2    3    4    5

val FEV_2020: YearMonth = YearMonth.of(2020, Month.FEBRUARY)
val MAR_2020: YearMonth = YearMonth.of(2020, Month.MARCH)

val MAR_1: LocalDate = MAR_2020.atDay(1)
val MAR_2: LocalDate = MAR_2020.atDay(2)
val MAR_8: LocalDate = MAR_2020.atDay(8)
val MAR_31: LocalDate = MAR_2020.atDay(31)

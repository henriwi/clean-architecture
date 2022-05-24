package no.bekk.power.domain.consumption

import no.bekk.power.domain.valuetypes.MeteringPointId
import no.bekk.power.domain.valuetypes.Period

class Consumption(val meteringPointId: MeteringPointId, val period: Period, val consumptionPeriodEntities: List<ConsumptionPeriodEntity>) {

    val totalConsumption: Double
        get() = consumptionPeriodEntities.sumOf { it.value }

}

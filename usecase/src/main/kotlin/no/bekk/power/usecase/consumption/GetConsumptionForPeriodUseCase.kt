package no.bekk.power.usecase.consumption

import no.bekk.power.domain.consumption.Consumption
import no.bekk.power.domain.consumption.ConsumptionPeriodEntity
import no.bekk.power.domain.customer.CustomerRepository
import no.bekk.power.usecase.consumption.service.MeterValuesService
import no.bekk.power.domain.valuetypes.CustomerId
import no.bekk.power.domain.valuetypes.MeteringPointId
import no.bekk.power.domain.valuetypes.Period
import no.bekk.power.domain.valuetypes.UnitOfMeasurement
import java.time.ZonedDateTime

class GetConsumptionForPeriodUseCase(
    private val customerRepository: CustomerRepository,
    private val meterValuesService: MeterValuesService) {

    fun getTotalConsumption(customerId: String, meteringPointId: String, from: ZonedDateTime, to: ZonedDateTime): Double {
        val customer = customerRepository.findByCustomerId(CustomerId(customerId))
            ?: throw IllegalArgumentException("Customer with id $customerId not found")

        val meteringPoint = customer.findMeteringPoint(MeteringPointId(meteringPointId))
            ?: throw IllegalArgumentException("Metering point with id $meteringPointId not found")

        val meterValues = meterValuesService.getMeterValues(meteringPoint.id, Period(from, to))

        val consumption = Consumption(meterValues.meteringPointId, meterValues.period, meterValues.timeSeries.map {
            ConsumptionPeriodEntity(
                period = it.period,
                value = it.value,
                uom = UnitOfMeasurement.valueOf(it.uom)
            )
        })

        return consumption.totalConsumption
    }
}
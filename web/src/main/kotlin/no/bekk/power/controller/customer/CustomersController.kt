package no.bekk.power.controller.customer

import no.bekk.power.application.CommandHandler
import no.bekk.power.application.QueryHandler
import no.bekk.power.application.customers.commands.CreateCustomerCommand
import no.bekk.power.application.customers.queries.GetCustomerQuery
import no.bekk.power.controller.customer.dto.CreateCustomerRequest
import no.bekk.power.controller.customer.dto.GetCustomerResponse
import no.bekk.power.domain.customer.Customer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
class CustomersController(
    private val createCustomerHandler: CommandHandler<CreateCustomerCommand>,
    private val getCustomerHandler: QueryHandler<GetCustomerQuery, Customer?>) {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass.name)

    @GetMapping("/customers/{id}")
    fun getCustomer(@PathVariable("id") customerId: String): ResponseEntity<Any> {
        val customer = getCustomerHandler.handle(GetCustomerQuery.with(customerId))
        return if (customer != null) {
            ResponseEntity.ok(GetCustomerResponse.from(customer))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/customers")
    fun createCustomer(@RequestBody request: CreateCustomerRequest): ResponseEntity<Any> {
        return try {
            createCustomerHandler.handle(
                CreateCustomerCommand.with(
                    name = request.name,
                    customerId = request.customerId,
                    country = request.country
                )
            )
            ResponseEntity.created(URI.create("/customer/${request.customerId}")).build()
        } catch (e: Exception) {
            log.error("Unable to create customer", e)
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }
}

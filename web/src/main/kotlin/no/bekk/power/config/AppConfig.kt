package no.bekk.power.config

import no.bekk.power.application.customers.handlers.CreateCustomerHandler
import no.bekk.power.application.customers.handlers.GetCustomersHandler
import no.bekk.power.db.customers.JdbcCustomerRepository
import no.bekk.power.domain.customer.CustomerRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

@Configuration
class AppConfig {

    @Bean
    fun customerRepository(jdbcTemplate: NamedParameterJdbcTemplate): CustomerRepository {
        return JdbcCustomerRepository(jdbcTemplate)
    }

    @Bean
    fun createCustomerHandler(customerRepository: CustomerRepository): CreateCustomerHandler {
        return CreateCustomerHandler(customerRepository)
    }

    @Bean
    fun getCustomerHandler(customerRepository: CustomerRepository): GetCustomersHandler {
        return GetCustomersHandler(customerRepository)
    }

}

package no.bekk.power.domain.valuetypes

data class CustomerId(val value: String) {

    init {
        require(value.length >= 8) { "Invalid length for customerId ${value.length}" }
    }
}

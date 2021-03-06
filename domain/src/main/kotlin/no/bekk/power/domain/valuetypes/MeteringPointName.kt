package no.bekk.power.domain.valuetypes

@JvmInline
value class MeteringPointName(val value: String) {
    init {
        require(value.length >= 3) { "Invalid length of meterintPointName: ${value.length}" }
    }
}

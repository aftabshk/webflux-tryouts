package com.example.webfluxtryouts

import reactor.core.publisher.Mono

class CustomerService {

    fun getCustomer(): Mono<Customer> {
        return Mono.just(Customer(name = "xyz", customerId = "123"))
    }

    fun getPersonalDetails(): Mono<String> {
        return Mono.just("Personal Details")
    }

    fun getAddressDetails(): Mono<String> {
        return Mono.empty()
    }
}

data class Customer(
    val name: String,
    val customerId: String
)

package com.example.webfluxtryouts

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.reactivestreams.Subscription
import reactor.core.publisher.BaseSubscriber
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier


class ConfigTest {

    @Test
    fun name() {
        val zip = Mono.zip(
            CustomerService().getPersonalDetails(),
            CustomerService().getAddressDetails()
        ).map {
            println(it)
            Pair(it.t1, it.t2)
        }

        StepVerifier.create(zip)
            .expectNext(Pair("Personal Details", "Address Details"))
            .verifyComplete()
    }
}

//@JsonDeserialize(using = CustDeserializer::class)
data class Cust(
    val name: String,
    val currentAddress: Address,
    val areBothAddressSame: Boolean,
    val permanentAddress: Address?
)

data class Address(
    val street: String
)

class CustDeserializer @JvmOverloads constructor(vc: Class<*>? = null) : StdDeserializer<Cust?>(vc) {
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext?): Cust {
        val node: JsonNode = jp.getCodec().readTree(jp)
        val name: String = node.get("name").asText()
        val currentAddress: Address = jacksonObjectMapper().readValue(node.get("currentAddress").toString(), Address::class.java)
        val areBothAddressSame = node.get("areBothAddressSame").asBoolean()
        if (areBothAddressSame)
            return Cust(name, currentAddress = currentAddress, areBothAddressSame = areBothAddressSame, permanentAddress = currentAddress)
        val permanentAddress = jacksonObjectMapper().readValue(node.get("permanentAddress").toString(), Address::class.java)
        return Cust(name, currentAddress = currentAddress, areBothAddressSame = areBothAddressSame, permanentAddress = permanentAddress)
    }
}
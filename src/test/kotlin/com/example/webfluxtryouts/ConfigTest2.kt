package com.example.webfluxtryouts

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test


class ConfigTest2 {


    @Test
    fun name() {
        val json = """{"name":"Luffy","currentAddress":{"street":"random"},"areBothAddressSame":true,"permanentAddress":{"street": "random1"}}"""

        println(jacksonObjectMapper().readValue(json, Cust::class.java))
    }
}

data class Customer(
    val name: String,
    val currentAddress: CommunicationAddress,
    val areBothAddressSame: Boolean,
    @JsonDeserialize(using = CustomerDeserializer::class)
    val permanentAddress: CommunicationAddress
)

data class CommunicationAddress(
    val street: String
)

class CustomerDeserializer @JvmOverloads constructor(vc: Class<*>? = null) : StdDeserializer<CommunicationAddress>(vc) {
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext?): CommunicationAddress {
        val node: JsonNode = jp.codec.readTree(jp)
        val currentAddress: CommunicationAddress = jacksonObjectMapper().readValue(node.get("currentAddress").toString(), CommunicationAddress::class.java)
        val areBothAddressSame = node.get("areBothAddressSame").asBoolean()
        println(areBothAddressSame)
        if (areBothAddressSame)
            return currentAddress
        return jacksonObjectMapper().readValue(node.get("permanentAddress").toString(), CommunicationAddress::class.java)
    }
}
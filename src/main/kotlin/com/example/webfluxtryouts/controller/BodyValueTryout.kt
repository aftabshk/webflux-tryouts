package com.example.webfluxtryouts.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.BodyInserters.fromValue
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@RestController
class BodyValueTryoutContoller {

    @GetMapping("/body")
    fun body(): Mono<String> {
        val p = mapOf("name" to "Monkey D. Luffy", "age" to "17")
        val returnType: Class<String> = String::class.java

        return WebClient.create().post()
            .uri("http://localhost:9006/partners/abhi/path")
            .body(fromValue(p))
            .retrieve()
            .bodyToMono(returnType)
    }
}

data class Person(
    val name: String,
    val age: String
)
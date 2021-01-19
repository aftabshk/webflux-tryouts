package com.example.webfluxtryouts.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.awt.PageAttributes

@RestController
class StreamController(
    @Autowired val webClient: WebClient.Builder
) {

    @GetMapping("/stream", produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun journey(): Flux<Int> {

        return webClient.build().get()
            .uri {
                it.scheme("http")
                    .host("localhost")
                    .port("9012")
                    .path("stream")
                    .build()
            }
            .retrieve()
            .bodyToFlux(Int::class.java)
            .map {
                println("Element is $it")
                it
            }
    }
}



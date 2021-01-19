package com.example.webfluxtryouts.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class ExceptionController {

    @GetMapping("/exception")
    fun exception(): Mono<String> {
        return Mono.just("hello").map {
            throw NullPointerException()
        }.map {
            "It won't come here"
        }
    }
}
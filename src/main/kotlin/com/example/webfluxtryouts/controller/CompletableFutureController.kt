package com.example.webfluxtryouts.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import reactor.util.retry.Retry
import java.time.Duration
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors

@RestController
class CompletableFutureController {

    @GetMapping("/future")
    fun journey(): Mono<String> {
        val completableFuture = CompletableFuture<String>();

        Executors.newCachedThreadPool().submit {
            completableFuture.complete("Running")
        }

        return completableFuture.toMono().flatMap {
            println("Retried...")
            if (it == "Running") Mono.error<String>(Exception("Error")) else Mono.just(it)
        }.retryWhen(Retry.fixedDelay(10, Duration.ofSeconds(1)))
    }
}

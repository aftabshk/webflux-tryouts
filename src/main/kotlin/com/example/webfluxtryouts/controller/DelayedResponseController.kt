package com.example.webfluxtryouts.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration

@RestController
class DelayedResponseController {

    @GetMapping("/delay", produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun journey(): Flux<Int> {
        return Flux.just(1, 2, 3, 4).delayElements(Duration.ofSeconds(1))
    }
}

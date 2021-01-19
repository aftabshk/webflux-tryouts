package com.example.webfluxtryouts.controller

import com.example.webfluxtryouts.filters.AuthenticationFilter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@RestController
class DemoController(
    val authenticationFilter: AuthenticationFilter
) {

    @GetMapping("/journey")
    fun journey(): Mono<Comment> {
        val webClient = WebClient.builder().baseUrl("http://localhost:8080").filter(authenticationFilter).build()
        val comment = Comment(text = "This is a comment")

        return webClient.post()
            .uri {
                it.path("comment").build()
            }
            .bodyValue(comment)
            .retrieve()
            .bodyToMono(Comment::class.java)
    }
}

data class Comment(
    val text: String
)


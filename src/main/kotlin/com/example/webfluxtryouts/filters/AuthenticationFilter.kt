package com.example.webfluxtryouts.filters

import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.ExchangeFunction
import reactor.core.publisher.Mono

@Component
class AuthenticationFilter : ExchangeFilterFunction {

    override fun filter(
        request: ClientRequest,
        next: ExchangeFunction
    ): Mono<ClientResponse> {
        return ClientRequest.from(request).headers {
            it.add(HttpHeaders.AUTHORIZATION, "token")
        }.build().let {
            next.exchange(it)
        }
    }
}
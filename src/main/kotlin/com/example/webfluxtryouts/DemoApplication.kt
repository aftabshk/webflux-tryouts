package com.example.webfluxtryouts

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.core.ResolvableType
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.http.MediaType
import org.springframework.http.ReactiveHttpInputMessage
import org.springframework.http.codec.HttpMessageReader
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

class MyMessageReader : HttpMessageReader<Response> {
    override fun getReadableMediaTypes(): MutableList<MediaType> {
        return mutableListOf()
    }

    override fun canRead(elementType: ResolvableType, mediaType: MediaType?): Boolean {
        return true
    }

    override fun read(
        elementType: ResolvableType,
        message: ReactiveHttpInputMessage,
        hints: MutableMap<String, Any>
    ): Flux<Response> {
        return Flux.just(Jackson2JsonDecoder().objectMapper.readValue(message.toString(), Response::class.java))
    }

    override fun readMono(
        elementType: ResolvableType,
        message: ReactiveHttpInputMessage,
        hints: MutableMap<String, Any>
    ): Mono<Response> {
        return message.body.map {
            val count = it.readableByteCount()
            val str = ByteArray(count)
            var count1 = 0
            while (count1 < count) {
                str[count1] = it.read()
                count1++
            }

            Jackson2JsonDecoder().objectMapper.readValue(str, Response::class.java)
        }.toMono()
    }
}

@EnableMongoAuditing
@SpringBootApplication
@ConfigurationPropertiesScan
class DemoApplication {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val webClient = WebClient.builder().build()
//            webClient.get()
//                .uri {
//                    it.scheme("http").host("localhost").port(8080).path("data").build()
//                }
//                .retrieve()
//                .bodyToMono(String::class.java)
//                .map {
//                    println("---------------------> Retrieved the response $it..... <----------------------")
//                }
//                .block()

            println("-------------> After retrieving response.... <------------------")

            SpringApplicationBuilder(DemoApplication::class.java).run(*args)
        }
    }
}

data class Response(val data: String)

package com.example.webfluxtryouts.beans

import com.example.webfluxtryouts.controller.BeansController
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MyBeansConfig(
    val applicationContext: ApplicationContext
) {

    @Bean
    fun mySampleObject(): MySampleObject {
        return MySampleObject()
    }

    @Bean
    fun beansController(): BeansController {
        return BeansController(mySampleObject(), applicationContext)
    }
}
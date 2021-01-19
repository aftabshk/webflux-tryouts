package com.example.webfluxtryouts.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.stereotype.Component

@ConfigurationProperties("org.props")
@ConstructorBinding
class Config(
    val apiKey: String
)
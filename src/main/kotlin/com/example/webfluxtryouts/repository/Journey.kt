package com.example.webfluxtryouts.repository

import org.springframework.data.mongodb.core.mapping.Document

@Document("journey")
data class Journey(
    val journeyId: Int,
    val premium: Int
)
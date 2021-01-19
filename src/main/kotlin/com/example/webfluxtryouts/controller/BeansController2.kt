package com.example.webfluxtryouts.controller

import com.example.webfluxtryouts.beans.MySampleObject
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BeansController2(val mySampleObject: MySampleObject) {

    @GetMapping("/beans2")
    fun beans(): String {
        return mySampleObject.name
    }
}
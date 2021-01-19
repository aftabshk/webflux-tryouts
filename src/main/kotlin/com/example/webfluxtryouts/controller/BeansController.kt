package com.example.webfluxtryouts.controller

import com.example.webfluxtryouts.beans.MySampleObject
import org.springframework.context.ApplicationContext
import org.springframework.web.bind.annotation.GetMapping

class BeansController(val mySampleObject: MySampleObject, val applicationContext: ApplicationContext) {

    @GetMapping("/beans")
    fun beans(): String {
        val res = mySampleObject.name
        mySampleObject.name = "BeansController"
        return res
    }

    @GetMapping("/getAllBeans")
    fun allBeans(): String {
        return applicationContext.getBean("mySampleObject").toString()
    }
}
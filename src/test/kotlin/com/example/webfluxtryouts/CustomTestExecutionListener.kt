package com.example.webfluxtryouts

import org.junit.platform.launcher.TestExecutionListener
import org.springframework.core.Ordered
import org.springframework.test.context.TestContext


class CustomTestExecutionListener : TestExecutionListener, Ordered {
    @Throws(Exception::class)
    fun beforeTestClass(testContext: TestContext) {
        println("beforeTestClass : {} ${testContext.testClass}")
    }

    @Throws(Exception::class)
    fun prepareTestInstance(testContext: TestContext) {
        println("prepareTestInstance : {} ${testContext.testClass}")
    }

    @Throws(Exception::class)
    fun beforeTestMethod(testContext: TestContext) {
        println("beforeTestMethod : {} ${testContext.testMethod}")
    }

    @Throws(Exception::class)
    fun afterTestMethod(testContext: TestContext) {
        println("afterTestMethod : {} ${testContext.testMethod}")
    }

    @Throws(Exception::class)
    fun afterTestClass(testContext: TestContext) {
        println("afterTestClass : {} ${testContext.testClass}")
    }

    override fun getOrder(): Int {
        return Int.MAX_VALUE
    }
}
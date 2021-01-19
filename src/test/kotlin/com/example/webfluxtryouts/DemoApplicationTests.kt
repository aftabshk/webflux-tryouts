package com.example.webfluxtryouts

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import javax.annotation.PostConstruct

@Component
class ContextC(@Autowired val applicationContext1: ApplicationContext){

	@PostConstruct
	fun dd(){
		applicationContext = applicationContext1
	}
	companion object{
		lateinit var  applicationContext : ApplicationContext
	}
}

//@TestExecutionListeners(listeners = [CustomTestExecutionListener::class])
class DemoApplicationTests {

	@Test
	@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
	fun `Test main application`() {

//		//val context: WebApplicationContext = ContextLoader.getCurrentWebApplicationContext()!!
//		DemoApplication.main(emptyArray())
//		val environment: Environment = ContextC.applicationContext.getBean(Environment::class.java)
//
//		println(environment.getProperty("server.port"))
//		println(environment.getProperty("foo"))

		println("Just print this")
	}
}

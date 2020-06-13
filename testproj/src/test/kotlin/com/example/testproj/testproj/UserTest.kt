package com.example.testproj.testproj

import com.example.testproj.testproj.service.UserService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class UserTest @Autowired constructor(
		val userService: UserService
){

	@Test
	internal fun `findUserById() to test findById `(){
		val user = userService.getUserById(1L)
		println(user)
		//How to write assertEquals
	}
	@Test
	internal fun `toString - What is This `() {

	}

	@Test
	internal fun getUserTest(){

	}

	@Test
	internal fun setUserTest(){

	}
}

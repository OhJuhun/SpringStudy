package com.example.testproj.testproj

import com.example.testproj.testproj.repository.UserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
internal class TestprojApplicationTests @Autowired constructor(
		val userRepository:UserRepository
){

	@Test
	internal fun `findById() to findAll by Id `(){
		val user = userRepository.findByIdOrNull(1L)
		assertEquals(user!!.id, 1L)     // break point
		assertEquals(user.name, "OhJuhun")
	}
	@Test
	fun `toString - What is This `() {
	}

}

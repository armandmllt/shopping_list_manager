package com.application.shoppinglistmanager;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

//@SpringBootTest
class ShoppinglistmanagerApplicationTests {


	Calculator underTest = new Calculator();
	@Test
	void itShouldAddNumbers() {
		//given 
		int numberOne = 20;
		int numberTwo = 65;

		//when
		int result = underTest.add(numberOne, numberTwo);

		//then
		assertThat(result).isEqualTo(85);
	}

	class Calculator {
		int add(int a, int b) {
			return a+b;
		}
	}

}

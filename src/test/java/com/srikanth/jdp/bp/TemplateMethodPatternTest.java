package com.srikanth.jdp.bp;

import org.junit.Test;

public class TemplateMethodPatternTest {

	@Test
	public void testTemplateMethodPattern() {
		Meal hamMeal = new HamburgerMeal();
		hamMeal.doMeal();

		Meal tacoMeal = new TacoMeal();
		tacoMeal.doMeal();
	}
}

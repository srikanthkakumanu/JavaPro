package com.srikanth.datastructures;

import static org.junit.Assert.*;

import org.junit.Test;

public class BigONotationTest {

	@Test
	public void testIsFirstElementNull() {
		String[] strings = new String[3];
		assertTrue(BigONotation.isFirstElementNull(strings));
	}
	@Test
	public void testContainsValue() {
		String[] strings = {"10","20","30"};
		assertTrue(BigONotation.ContainsValue(strings, "30"));
	}
	@Test
	public void testContainsDuplicates() {
		String[] strings = {"10","20","30"};
		assertFalse(BigONotation.ContainsDuplicates(strings));
	}

}

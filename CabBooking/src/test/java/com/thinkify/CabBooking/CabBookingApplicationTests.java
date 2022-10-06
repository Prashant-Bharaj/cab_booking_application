package com.thinkify.CabBooking;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class CabBookingApplicationTests {

	private final PrintStream standardOut = System.out;

	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@BeforeEach
	public void setUp() {
		System.setOut(new PrintStream(outputStreamCaptor));
	}

	@Test
	@DisplayName("Invalid fileName")
	void invalidFileName() {
		// Arrange
		CabBookingApplication.main(new String[] { "xyz" });
		// expected
		Assertions.assertEquals("", outputStreamCaptor.toString().trim());
	}

	@Test
	@DisplayName("Integration Test #1")
	void runTest1() {
		// Arrange
		List<String> arguments = new ArrayList<>(List.of("sample_input/input1.txt"));

		String expectedOutput = "List of availableDrivers:\n"
								+"Driver1\n" 
								+"Booked Sucessfully";

		// Act
		CabBookingApplication.run(arguments);
		// Assert
		Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
	}

	@Test
	@DisplayName("Integration Test #2")
	void runTest2() {
		// Arrange
		List<String> arguments = new ArrayList<>(List.of("sample_input/input2.txt"));

		String expectedOutput = "No availableDrivers found\nList of availableDrivers:\nDriver1\nBooked Sucessfully\nNo availableDrivers found";

		// Act
		CabBookingApplication.run(arguments);
		// Assert
		Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
	}

	@AfterEach
	public void tearDown() {
		System.setOut(standardOut);
	}

}

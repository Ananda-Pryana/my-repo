package com.example.demo;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController
{
	@GetMapping("/")
	public String index()
	{
		return "Greetings from Spring Boot!";
	}

	@GetMapping(value = "/test")
	@ResponseBody
	public String test()
	{
		System.out.println("test1");
		System.out.println("test2");
		return "{<br>&emsp;\"result\":\"success\"<br>}";
	}

	@GetMapping(value = "/testDB")
	@ResponseBody
	public String testDB() throws SQLException
	{
		return TestDB.testReadTable();
	}
}
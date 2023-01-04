package com.example.demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class TestDB
{
	private static DataSource getDataSource()
	{
		final BasicDataSource dataSource = new BasicDataSource();

		final Map<String, String> credentials = readDBCredentials();
		dataSource.setDriverClassName(credentials.get("DB_DRIVER"));
		dataSource.setUrl(credentials.get("DB_URL"));
		dataSource.setUsername(credentials.get("DB_USER"));
		dataSource.setPassword(credentials.get("DB_PASSWORD"));

		return dataSource;
	}

	private static String getFromEnvOrProperty(final String key)
	{
		String value = System.getenv(key);
		if (value == null || value.isEmpty())
		{
			value = System.getProperty(key);
		}
		if (value == null || value.isEmpty())
		{
			System.out.println(key + " : Value can not be empty");
			throw new RuntimeException(key + " : Value can not be empty");
		}
		return value;
	}

	private static Map<String, String> readDBCredentials()
	{
		final String[] dbinfo =
		{ "DB_USER", "DB_PASSWORD", "DB_DRIVER", "DB_URL" };

		final Map<String, String> credentials = new HashMap<>();

		for (final String dbProperty : dbinfo)
		{
			credentials.put(dbProperty, getFromEnvOrProperty(dbProperty));
		}

		return credentials;
	}

	public static String testReadTable() throws SQLException
	{
		final StringBuilder sb = new StringBuilder();
		final DataSource dataSource = getDataSource();

		final String sql = "select string from test2";
		try (	final Connection conn = dataSource.getConnection(); //
				final ResultSet resultSet = conn.prepareCall(sql).executeQuery())
		{
			sb.append("contents of test table 2:<br>");
			while (resultSet.next())
			{
				sb.append("&emsp;" + resultSet.getString(1) + "<br>");
			}
		}

		return sb.toString();
	}
}

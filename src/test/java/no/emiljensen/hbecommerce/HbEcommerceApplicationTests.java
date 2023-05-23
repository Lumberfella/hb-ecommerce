package no.emiljensen.hbecommerce;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class HbEcommerceApplicationTests {

	@Autowired
	private DataSource dataSource;

	@Test
	void contextLoads() {
		try (Connection connection = dataSource.getConnection();
			 Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery("SELECT 1 FROM Watch LIMIT 1");
			assertTrue(resultSet.next());
		} catch (SQLException e) {
			fail("Failed to query the Watch table: " + e.getMessage());
		}
	}
}

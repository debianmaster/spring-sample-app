package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Console;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import javax.sql.DataSource;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

@SpringBootApplication
public class SpringSampleAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSampleAppApplication.class, args);
	}
}

@RestController
@RequestMapping("/")
class HomeRestController {

	boolean healthy=true;
    String hostname="";
	public  HomeRestController(){
		try {
			hostname= "Hello from v1 " + InetAddress.getLocalHost().getHostName().toString();
		}
		catch (UnknownHostException ex){
			hostname= "error";
		}
	}

	@RequestMapping("/")
	public String home(){
         return "<h1>"+hostname+"</h1>";
	}

	@RequestMapping("/healthz")
	public ResponseEntity healthz(){
		if (healthy)
			return new ResponseEntity(HttpStatus.ACCEPTED);
		else
			return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
	}

	@RequestMapping("/cancer")
	public String cancer(){
		healthy=false;
		return "Killed "+hostname;
	}

	@Autowired
	private Environment env;

	@RequestMapping("/dbtest")
	public String dbtest(){

		String sql = "SELECT * FROM customer";
		Connection conn = null;

		try {
			conn =  DriverManager.getConnection(env.getProperty("spring.datasource.url"),env.getProperty("spring.datasource.username"),env.getProperty("spring.datasource.password"));
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String res="<h1>"+rs.getInt("CUST_ID") + rs.getString("NAME")+rs.getInt("Age")+"</h1>";
			return res;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}


}

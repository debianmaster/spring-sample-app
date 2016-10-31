package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

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
			hostname= "Hello from " + InetAddress.getLocalHost().getHostName().toString();
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

	@RequestMapping("/dbtest")
	public String dbtest(){
		String sql = "SELECT * FROM CUSTOMER";
		DataSource dataSource=DataSource("");
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			String res=rs.getInt("CUST_ID") + rs.getString("NAME")+rs.getInt("Age");
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
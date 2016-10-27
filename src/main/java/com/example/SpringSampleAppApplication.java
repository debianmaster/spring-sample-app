package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.InetAddress;
import java.net.UnknownHostException;
import sun.misc.Signal;
import sun.misc.SignalHandler;

@SpringBootApplication
public class SpringSampleAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSampleAppApplication.class, args);
	}
	private static final Signal SIG_INT = new Signal("INT");
	public static void attachSignalHandler(final Runnable runnable) {
		Signal.handle(SIG_INT, new SignalHandler() {
			@Override
			public void handle(Signal signal) {
				runnable.run();
			}
		});
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
         return hostname;
	}

	@RequestMapping("/healthz")
	public ResponseEntity healthz(){
		if (healthy)
			return new ResponseEntity(HttpStatus.FOUND);
		else
			return new ResponseEntity(HttpStatus.NOT_FOUND);
	}

	@RequestMapping("/cancer")
	public String cancer(){
		healthy=false;
		return "Killed "+hostname;
	}
}
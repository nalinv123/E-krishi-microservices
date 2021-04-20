package com.nvdevelopers.e_krishi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;

@SpringBootApplication
public class EKrishiApplication {

	public static void main(String[] args) throws UnknownHostException {
		System.setProperty("log.name", "e-krishi-" + InetAddress.getLocalHost().getHostName().toLowerCase(Locale.ROOT));
		SpringApplication.run(EKrishiApplication.class, args);
	}

}

package com.kb.web.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.json.JSONObject;

@Path("/test")
public class TestWebserviceController {
	final static Logger LOGGER = Logger.getLogger(TestWebserviceController.class);

	@GET
	@Path("/welcome")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		LOGGER.info("START - Test service for KB Webapp");
		return "Hello and Welcome to KB App";
	}
	
	@GET
	@Path("/jsontest")
	@Produces(MediaType.TEXT_PLAIN)
	public String jsonTest() {
		LOGGER.info("START - Json Test service for KB Webapp");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", "KB user");
		jsonObject.put("email", "kb@kb.com");
		System.out.println(jsonObject.toString());
		return jsonObject.toString();
	}
}

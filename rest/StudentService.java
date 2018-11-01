package com.marlabs.rest;

import java.io.File;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

@Path("/jsonServices")
public class StudentService {
	
	@GET
	@Path("/print/{name}/{lname}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student produceJSON( @PathParam("name") String name, @PathParam("lname") String lname) {
		
		Student st = new Student(name, lname,22,1);

		return st;

	}
	
	@POST
	@Path("/send")
	//@Consumes(MediaType.APPLICATION_JSON)
	public Response consumeJSON( Student student ) {
		String output="";
		//String output = student.toString();
		ObjectMapper mapper = new ObjectMapper();
		
		try{
		mapper.writeValue(new File("user.json"), student);
		output = mapper.writeValueAsString(student);
		System.out.println("Output:" + output);
		output = student.toString();
		} catch(Exception e) {
			e.printStackTrace();
		}

		return Response.status(200).entity(output).build();
	}


}

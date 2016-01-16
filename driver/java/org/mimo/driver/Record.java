package org.mimo.driver;

import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.mimo.driver.model.Driver;
import org.mimo.driver.model.Driver;
import org.mimo.driver.service.DriverService;


@Path("/Record")
public class Record {
	DriverService ms = new DriverService();
	private List<Driver> l;
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Driver getone() throws SQLException {
		Driver d = new Driver();
		d = ms.getsingle(1);
		return d;
	}

	@GET
	@Path("/messageId")
	@Produces(MediaType.TEXT_PLAIN)
	public String hopa() {
		return "hopa";
	}
	
	@GET
	@Path("/getten")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Driver> geten() throws SQLException {
		
		l=ms.get_ten();	
		
		return l;
	}
	@GET
	@Path("/getuser/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Driver getuser(@PathParam("username")String username) throws SQLException {
		Driver d = new Driver();
		d = ms.getsingleuser(username);
		return d;
	}
	@POST
	@Path("/Add")
	@Produces(MediaType.TEXT_PLAIN)
public String gettext(){
		return "mimo";
	}
	@POST
	@Path("/AddDriver")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Driver add_driver(Driver d) throws SQLException{
	Driver driver=ms.add_driver(d.getUsername(),d.getPassword());
	return driver;
	}
	@PUT
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void update_driver(@PathParam("id")int id,Driver driver) throws SQLException{
	System.out.print("id");
		String dr=ms.update_driver(id,driver);
	}
}

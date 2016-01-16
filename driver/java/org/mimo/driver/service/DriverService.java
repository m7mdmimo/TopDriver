package org.mimo.driver.service;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import org.mimo.driver.model.Driver;

public class DriverService {
	public Connection con;
	public Statement stat;
	ResultSet res;
	private List<Driver> l;
	Driver d;

	public DriverService() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/topdriver", "root", "");

		} catch (SQLException e) {
			System.out.print("55555555555555");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Driver getsingle(int id) throws SQLException {
		Driver d = new Driver();
		stat = con.createStatement();

		res = stat.executeQuery("select * from driver where id =1");
		while (res.next()) {
			String user = res.getString(2);
			d.setUsername(user);
			float record = res.getFloat(4);
			d.setRecord(record);
			int ids = res.getInt(1);
			d.setId(ids);

		}
		return d;
	}

	public Driver getsingleuser(String username) throws SQLException {
		Driver d = new Driver();
		stat = con.createStatement();

		res = stat.executeQuery("select * from driver where username =  " + " "
				+ "'" + username + "'");
		while (res.next()) {
			String user = res.getString(2);
			d.setUsername(user);
			float record = res.getFloat(4);
			d.setRecord(record);
			int ids = res.getInt(1);
			d.setId(ids);
			String password = res.getString(3);
			d.setPassword(password);
		}
		return d;
	}

	public File getimage() throws SQLException {
		stat = con.createStatement();
		l = new ArrayList<Driver>();
		res = stat.executeQuery("select username, record, image "
				+ "from driver order by record desc limit 10");
		BufferedImage bImageFromConvert = null;
		File file = null;
		while (res.next()) {
			d = new Driver();
			d.setUsername(res.getString(1));
			if (res.getBytes(3) != null) {
				byte[] image = (res.getBytes(3));
				d.setImage(image);
				d.setImagepath(image.toString());
			}

			d.setRecord(res.getFloat(2));
			System.out.print(d.getUsername());

			l.add(d);
		}
		return file;
	}

	public List<Driver> get_ten() throws SQLException {
		stat = con.createStatement();
		l = new ArrayList<Driver>();
		res = stat.executeQuery("select username, record, image "
				+ "from driver order by record desc limit 10");
		while (res.next()) {
			d = new Driver();
			d.setUsername(res.getString(1));
			if (res.getBytes(3) != null) {
				/*
				 * byte[] image = org.glassfish.jersey.internal.util.Base64
				 * .encode(res.getBytes(3)); d.setImage(image);
				 */
				d.setImagepath(res.getString(3));
			}

			d.setRecord(res.getFloat(2));
			System.out.print(d.getUsername());

			l.add(d);
		}

		return l;
	}

	/*
	 * try { bImageFromConvert = ImageIO.read(in); } catch (IOException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * try {
	 * 
	 * ImageIO.write(bImageFromConvert, "jpg", new File(
	 * "c:/Users/ma7mad/Desktop/new-darksouls.jpg")); } catch (IOException e) {
	 * // TODO Auto-generated catch block e.printStackTrace(); }
	 */

	/*
	 * File file =new File("c:/Users/ma7mad/Desktop/new-darksouls.jpg");
	 * d.setFinalimage(bImageFromConvert); d.setImagfile(file);
	 */
	public Driver add_driver(String username, String password)
			throws SQLException {
		stat = con.createStatement();

		/*
		 * res=stat.executeQuery("insert into driver(username,password)values("
		 * +"'"+d.getUsername()+"',"+"'"+d.getPassword()+"')" );
		 */

		final String query = "insert into driver (username,password)values(?,?)";
		java.sql.PreparedStatement preparedStatement = con
				.prepareStatement(query);
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, password);
		preparedStatement.execute();
		String s = username;
		return d;
	}

	public String update_driver(int id,Driver d) throws SQLException {

		String result = "  null";
		FileInputStream input;

		String sq = "update  driver set image=? where id =" + id;
		java.sql.PreparedStatement preparedStatement = con.prepareStatement(sq);
		//preparedStatement.setBytes(1, image);
		preparedStatement.setString(1, d.getImagepath());
		preparedStatement.execute();

		/*
		 * final String
		 * query="update  driver set image="+"'"+d.getImage()+"'"+" where id = "
		 * +id; stat.executeUpdate(query); final String sql="select image " +
		 * "from driver"+" where id ="+id; res=stat.executeQuery(sql);
		 * while(res.next()){ result=res.getString("image"); }
		 */
		return result;
	}
}

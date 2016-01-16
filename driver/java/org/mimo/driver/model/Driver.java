package org.mimo.driver.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.crypto.Data;

import com.mysql.jdbc.Blob;

@XmlRootElement
public class Driver {
	private int id;
	private float record;
	private byte[] image;
	private String password, username,imagepath;
	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public Driver() {

	}

	public Driver(int id, float record, String password, String username,
			byte[] image) {
		this.id = id;
		this.record = record;
		this.password = password;
		this.username = username;
		this.image = image;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getRecord() {
		return record;
	}

	public void setRecord(float record) {
		this.record = record;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	
}

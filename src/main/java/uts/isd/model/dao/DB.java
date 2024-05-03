package uts.isd.model.dao;

import java.sql.Connection;

public abstract class DB {
	protected String URL = "jdbc:mysql://localhost:3306/";
	protected String db = "frilab";
	protected String dbuser = "root";
	protected String dbpass = "12345";
	protected String driver = "com.mysql.cj.jdbc.Driver";
	protected Connection conn;
}
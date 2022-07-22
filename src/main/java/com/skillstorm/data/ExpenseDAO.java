package com.skillstorm.data;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.Expenses;
import beans.Status;

public class ExpenseDAO implements Serializable{
	
	private PreparedStatement statement;
	private Connection connection;
	private String url = "jdbc:mysql://localhost:3306/expenseproject";
	private String username = "root";
	private String password = "root";
	private String sql;
	
	public void ExpenseDAO(String url, String username, String password) throws ClassNotFoundException{
		
		this.url = url;
		this.username = username;
		this.password = password;
		Class.forName("com.mysql.cj.jdbc.Driver");
	}
	
	protected void connect() throws SQLException {

		this.connection = DriverManager.getConnection(url, username, password);
	}
	
	protected void disconnect() throws SQLException{
		//this.statement.close();
		this.connection.close();
	}
	//cruds
	
	// post
	public Expenses insert(Expenses expense) throws SQLException{

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("breaking at insert loading driver");
			e.printStackTrace();
		}
		connect();
		connection.setAutoCommit(true);
		
		sql = "insert into expenses(name, amount, reason) values (?,?,?)";
		
		PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, expense.getName());
		statement.setDouble(2, expense.getAmount());  
		statement.setString(3, expense.getReason());
		statement.executeUpdate();
		
		ResultSet rs = statement.getGeneratedKeys();
		rs.next();
		int generatedId = rs.getInt(1);
		expense.setIdexpenses(generatedId);
		
		//disconnect();
		return expense;
	}
	
	// get
	public Set<Expenses> findAll() throws SQLException{
		connect();
		Set<Expenses> expense = new HashSet<>();
		sql = "select * from expenses inner join status on expenses.idexpenses = status.idexpenses;";
		Statement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()) {
			Expenses row = new Expenses();
			int id = rs.getInt("idexpenses");
			String name = rs.getString("name");
			double amount = rs.getDouble("amount");
			String reason = rs.getString("reason");
			Boolean pending = rs.getBoolean("pending");
			Boolean approved = rs.getBoolean("approved");
			Boolean denied = rs.getBoolean("denied");
			
			row.setIdexpenses(id);
			row.setName(name);
			row.setAmount(amount);
			row.setReason(reason);
			row.setPending(pending);
			row.setApproved(approved);
			row.setDenied(denied);
			expense.add(row);
		}
		System.out.println(expense);
		//disconnect();
		return expense;
	}
	
	//put
	public boolean update(Status status) throws SQLException {
		connect();
		
		sql = "update status set expenseid = ?, pending = ?. approved = ?, denied = ?";
		PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.setInt(1, status.getIdexpenses());
		statement.setBoolean(2, status.getPending());
		statement.setBoolean(3, status.getApproved());  
		statement.setBoolean(4, status.getDenied());
		
		boolean updated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return updated;
		//String sql = "update expenses set"
	}
	
	
	
	
	//delete
	
	public boolean delete(Expenses expense) throws SQLException{
		connect();
		sql = "delete from expenses where idexpenses = ?";
		PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.setInt(1, 0);
		ResultSet rs = statement.executeQuery(sql);
		
		return statement.executeUpdate()==1;
		//return true;
		
	}
}

package com.skillstorm.expenseProject;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.data.ExpenseDAO;

import beans.Expenses;
import beans.Status;

@WebServlet(urlPatterns = "/*")
public class ExpenseController extends HttpServlet{

	
	private ExpenseDAO dao = new ExpenseDAO();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ObjectMapper objectmapper = new ObjectMapper();
		String json;
		try {
			Set<Expenses> expense = dao.findAll();
			json = objectmapper.writeValueAsString(expense);
			
			
			resp.setHeader("Content-Type", "application/json");
			resp.setContentType("application/json");
			resp.getWriter().print(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//parsing httprequest
		InputStream requests = req.getInputStream();
		ObjectMapper objectmapper = new ObjectMapper();
		Expenses expense = objectmapper.readValue(requests, Expenses.class);
		
		try {
			expense = dao.insert(expense);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//System.out.println(expense);
		
		resp.setHeader("Content-Type", "application/json");
		resp.setContentType("application/json");
		resp.getWriter().print(objectmapper.writeValueAsString(expense));
		resp.setStatus(201);
		
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream requests = req.getInputStream();
		ObjectMapper objectmapper = new ObjectMapper();
		Expenses expense = objectmapper.readValue(requests, Expenses.class);
		System.out.println("deleting");
		try {
			dao.delete(expense);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream requests = req.getInputStream();
		ObjectMapper objectmapper = new ObjectMapper();
		Status status = objectmapper.readValue(requests, Status.class);
		System.out.println("updating");
		try {
			 dao.update(status);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
}

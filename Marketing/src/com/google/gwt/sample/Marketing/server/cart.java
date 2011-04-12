package com.google.gwt.sample.Marketing.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class cart extends HttpServlet {
	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ServletException {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession(true);
			out.println("ID " + session.getId());
			out.println("Created: " + session.getCreationTime());
			out.println("Last Accessed: " + session.getLastAccessedTime());
			out.close();
	}
	}
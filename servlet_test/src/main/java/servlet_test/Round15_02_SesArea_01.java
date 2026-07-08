package servlet_test;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Round15_02_SesArea_01 extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	    request.setAttribute("data1", "김승현");

	    HttpSession session = request.getSession(true);
	    session.setAttribute("data2", "자바");
	    Calendar data3 = Calendar.getInstance( );
	    session.setAttribute("data3", data3);

	    ServletContext context = this.getServletContext( );
	    RequestDispatcher dispatcher = context.getRequestDispatcher("/Servlet02_02");
	    dispatcher.forward(request, response);
	}
}

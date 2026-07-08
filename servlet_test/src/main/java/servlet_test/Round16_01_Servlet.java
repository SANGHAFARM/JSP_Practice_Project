package servlet_test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Round16_01_Servlet")
public class Round16_01_Servlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	    request.setCharacterEncoding("utf-8");

	    String name = request.getParameter("name");
	    String age = request.getParameter("age");
	    String school = request.getParameter("school");
	    String filename = request.getParameter("filename");

	    ServletContext context = this.getServletContext( );
	    String path = context.getRealPath("/personInfo");
	    File dir = new File(path);
	    if(!dir.exists( )) dir.mkdir( );
	    File file = new File(dir, filename);
	    PrintWriter f_out = new PrintWriter(new BufferedWriter(new FileWriter(file)));

	    f_out.println("name : " + name);
	    f_out.println("age : " + age);
	    f_out.println("school : " + school);
	    f_out.close( );

	    response.setContentType("text/html;charset=utf-8");
	    PrintWriter out = response.getWriter( );
	    out.println("<html><body><center><h3>");
	    out.println(path + File.separator + filename + "의 파일에 정보가 저장되었습니다.");
	    out.println("</h3></center></body></html>");
	}
}

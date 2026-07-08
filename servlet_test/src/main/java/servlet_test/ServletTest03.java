package servlet_test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTest03 extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        
        Enumeration names = request.getParameterNames();
        while(names.hasMoreElements()) {
            String name = (String)names.nextElement();
            String value = request.getParameter(name);
            //String[] values = request.getParameterValues(name);
            out.println(name + " : " + value);
        }
    }
}
package servlet_test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTest06_Taegu extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");

        String name = request.getParameter("name");
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String area = request.getParameter("area");

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter( );
        out.println("<html><body>");
        out.println("그카니깐 니 이름이 " + name);
        out.println("이라는 말이고,<br/>");
        out.println("니가 사용할라 카는 아뒤가 " + id);
        out.println("라는 말이재?");
        out.println("</body></html>");
    }
}
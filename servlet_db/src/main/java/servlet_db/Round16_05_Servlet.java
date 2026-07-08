package servlet_db;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/Servlet_05")
public class Round16_05_Servlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");

		String subject = request.getParameter("subject");
		String author = request.getParameter("author");
		String contents = request.getParameter("contents");

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body><center><h3>");

		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "insert into tbl_bbs values (seq_bbs.NEXTVAL, ?, ?, ?)";

		try {
			Context context = new InitialContext();
			DataSource source = (DataSource) context.lookup("java:comp/env/jdbc/myconn");
			conn = source.getConnection();
		} catch (Exception e) {
		}

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, subject);
			pstmt.setString(2, author);
			pstmt.setString(3, contents);
			int res = pstmt.executeUpdate();
			if (res > 0)
				out.println("Success Save!!");
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			out.println("SQL Process Error : " + e.getMessage());
		}

		out.println("</h3></center></body></html>");
	}
}

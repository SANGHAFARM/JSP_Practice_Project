package ticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import ticket.model.Ticket;

public class TicketDao {

	// 폼 화면에 보여주기 위한 가상 번호 조회
	public int getNextTnoForDisplay(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 현재 가장 큰 tno 값을 찾고 1을 더함
			String sql = "SELECT NVL(MAX(tno), 0) + 1 FROM ticket_tbl_01";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}
			return 1;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public List<Ticket> selectList(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT tno, carno, phone, grade, tstat, startdate, enddate "
					+ "FROM ticket_tbl_01 ORDER BY tno DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			List<Ticket> result = new ArrayList<>();
			while (rs.next()) {
				// ResultSet에서 데이터를 먼저 변수로 추출
				int tno = rs.getInt("tno");
				String carno = rs.getString("carno");
				String phone = rs.getString("phone");
				String grade = rs.getString("grade");
				String tstat = rs.getString("tstat");
				Date startdate = rs.getDate("startdate");
				Date enddate = rs.getDate("enddate");

				// Ticket VO 객체 생성
				Ticket ticket = new Ticket(tno, carno, phone, grade, tstat, startdate, enddate);

				result.add(ticket);
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public Ticket selectByCarno(Connection conn, String carno) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT tno, carno, phone, grade, tstat, startdate, enddate "
					+ "FROM ticket_tbl_01 WHERE carno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, carno);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int tno = rs.getInt("tno");
				String dbCarno = rs.getString("carno");
				String phone = rs.getString("phone");
				String grade = rs.getString("grade");
				String tstat = rs.getString("tstat");
				java.util.Date startdate = rs.getDate("startdate");
				java.util.Date enddate = rs.getDate("enddate");

				// Ticket VO 객체 생성
				return new Ticket(tno, dbCarno, phone, grade, tstat, startdate, enddate);
			}

			// 해당하는 차량번호가 없을 경우 null 반환
			return null;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public void insert(Connection conn, Ticket ticket) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into ticket_tbl_01 (tno, carno, phone, grade, tstat, startdate, enddate) "
					+ "values (seq_tno.nextval, ?, ?, ?, ?, SYSDATE, SYSDATE + ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ticket.getCarno());
			pstmt.setString(2, ticket.getPhone());
			pstmt.setString(3, ticket.getGrade());
			pstmt.setString(4, ticket.getTstat());

			int addDays = "Y".equalsIgnoreCase(ticket.getGrade()) ? 365 : 30;
			pstmt.setInt(5, addDays);

			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
}
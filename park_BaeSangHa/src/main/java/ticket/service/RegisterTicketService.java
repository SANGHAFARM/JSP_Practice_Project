package ticket.service;

import java.sql.Connection;
import java.sql.SQLException;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import ticket.dao.TicketDao;
import ticket.model.Ticket;

public class RegisterTicketService {

	private TicketDao ticketDao = new TicketDao();

	// 디스플레이용 번호 로직
	public int getNextTnoForDisplay() {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			return ticketDao.getNextTnoForDisplay(conn);
		} catch (SQLException e) {
			throw new RuntimeException("번호 조회 실패", e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	// 서비스 로직
	public void register(Ticket ticket) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			ticketDao.insert(conn, ticket);

			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 저장 실패: " + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
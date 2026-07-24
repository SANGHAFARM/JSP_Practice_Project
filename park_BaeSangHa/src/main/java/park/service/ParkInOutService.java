package park.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import park.dao.ParkDao;
import park.model.Park;
import ticket.dao.TicketDao;
import ticket.model.Ticket;

public class ParkInOutService {

	private ParkDao parkDao = new ParkDao();
	private TicketDao ticketDao = new TicketDao();

	// 입고 가능 상태 체크
	public int checkInboundStatus(String carno) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			// 이미 입고 중인 차량인지 검사
			if (parkDao.checkParked(conn, carno)) {
				return 2;
			}
			
			// 정기권 유무 체크
			Ticket ticket = ticketDao.selectByCarno(conn, carno);
			if (ticket == null) {
				return 3;
			}

			// 정기권 상태 N인지 체크
			if ("N".equalsIgnoreCase(ticket.getTstat())) {
				return 4; 
			}

			// 입고 가능
			return 1; 
		} catch (SQLException e) {
			throw new RuntimeException("입고 상태 확인 실패", e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public Ticket getTicketByCarno(String carno) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			return ticketDao.selectByCarno(conn, carno);
		} catch (SQLException e) {
			throw new RuntimeException("정기권 조회 실패", e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	// 입고 처리
	public void processInbound(Park park) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			parkDao.saveOrUpdateInbound(conn, park);

			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("입고 처리 실패", e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	// 출고 대상 조회
	public Park getParkedCar(String carno) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			return parkDao.selectParkedByCarno(conn, carno);
		} catch (SQLException e) {
			throw new RuntimeException("입고 차량 조회 실패", e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	// 출고 처리
	public boolean processOutbound(String carno) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			int updatedRows = parkDao.updateOutbound(conn, carno);

			if (updatedRows > 0) {
	            conn.commit();
	            return true;
	        } else {
	            conn.rollback();
	            return false;
	        }
	    } catch (SQLException e) {
	        JdbcUtil.rollback(conn);
	        throw new RuntimeException("출고 처리 실패", e);
	    } finally {
	        JdbcUtil.close(conn);
	    }
	}

	// 주차 현황 목록 조회
	public List<Park> getParkStatusList() {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			return parkDao.selectList(conn);
		} catch (SQLException e) {
			throw new RuntimeException("주차 현황 조회 실패", e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
package ticket.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import ticket.dao.TicketDao;
import ticket.model.Ticket;

public class ListTicketService {	
    private TicketDao ticketDao = new TicketDao();

    // 정기권 전체 목록을 구하는 로직
    public List<Ticket> getTicketList() {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            
            // DAO 호출하여 목록 반환
            return ticketDao.selectList(conn);
            
        } catch (SQLException e) {
            throw new RuntimeException("목록 조회 실패: " + e.getMessage(), e);
        } finally {
            JdbcUtil.close(conn);
        }
    }
}
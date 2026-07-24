package park.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JdbcUtil;
import park.model.Park;

public class ParkDao {

    // 현재 입고 중인 차량인지 확인
    public boolean checkParked(Connection conn, String carno) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT COUNT(*) FROM park_info_tbl WHERE carno = ? AND tstat = '1'";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, carno);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
    }

    // park_info_tbl에 차량 존재 여부 확인
    public boolean existsCarno(Connection conn, String carno) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT COUNT(*) FROM park_info_tbl WHERE carno = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, carno);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
    }

    // 입고 처리
    public void saveOrUpdateInbound(Connection conn, Park park) throws SQLException {
        // 이미 테이블에 존재하는 차량인 경우 UPDATE
        if (existsCarno(conn, park.getCarno())) {
            PreparedStatement pstmt = null;
            try {
                String sql = "UPDATE park_info_tbl SET tstat = '1', indate = SYSDATE, outdate = NULL, grade = ? " +
                             "WHERE carno = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, park.getGrade());
                pstmt.setString(2, park.getCarno());
                pstmt.executeUpdate();
            } finally {
                JdbcUtil.close(pstmt);
            }
        } 
        // 최초 입고 차량인 경우 INSERT
        else {
            PreparedStatement pstmt = null;
            try {
                String sql = "INSERT INTO park_info_tbl (parkno, carno, grade, tstat, indate, outdate) " +
                             "VALUES (seq_parkno.nextval, ?, ?, '1', SYSDATE, NULL)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, park.getCarno());
                pstmt.setString(2, park.getGrade());
                pstmt.executeUpdate();
            } finally {
                JdbcUtil.close(pstmt);
            }
        }
    }

    // 입고 중인 차량 조회
    public Park selectParkedByCarno(Connection conn, String carno) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT parkno, carno, grade, tstat, indate, outdate " +
                         "FROM park_info_tbl WHERE carno = ? AND tstat = '1'";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, carno);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int parkno = rs.getInt("parkno");
                String dbCarno = rs.getString("carno");
                String grade = rs.getString("grade");
                String tstat = rs.getString("tstat");
                Date indate = rs.getTimestamp("indate");
                Date outdate = rs.getTimestamp("outdate");

                return new Park(parkno, dbCarno, grade, tstat, indate, outdate);
            }
            return null;
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
    }

    // 출고 처리
    public int updateOutbound(Connection conn, String carno) throws SQLException {
    	PreparedStatement pstmt = null;
        try {
            String sql = "UPDATE park_info_tbl SET tstat = '0', outdate = SYSDATE " +
                         "WHERE carno = ? AND tstat = '1'";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, carno);
            
            return pstmt.executeUpdate();
        } finally {
            JdbcUtil.close(pstmt);
        }
    }

    // 주차 현황 전체 목록 조회
    public java.util.List<Park> selectList(Connection conn) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT parkno, carno, grade, tstat, indate, outdate " +
                         "FROM park_info_tbl ORDER BY parkno DESC";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            java.util.List<Park> list = new java.util.ArrayList<>();
            while (rs.next()) {
                int parkno = rs.getInt("parkno");
                String carno = rs.getString("carno");
                String grade = rs.getString("grade");
                String tstat = rs.getString("tstat");
                Date indate = rs.getTimestamp("indate");
                Date outdate = rs.getTimestamp("outdate");

                list.add(new Park(parkno, carno, grade, tstat, indate, outdate));
            }
            return list;
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
    }
}
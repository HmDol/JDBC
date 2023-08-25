package comm.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conn.DBConnect;

public class BoardDao {
	private DBConnect dbconn;
	
	public BoardDao() {
		dbconn = DBConnect.getInstance();
	}
	
	public void insert(Board b) {
		//1. db연결
		Connection conn = dbconn.conn();
		//2. sql 작성
		String sql = "insert into board values(seq_board.nextval,sysdate,?,?,?)";
		//3. preparedstatement 생성
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//4. 매칭
			pstmt.setString(1, b.getWriter());
			pstmt.setString(2, b.getTitle());
			pstmt.setString(3, b.getContent());
			
			//5. 실행
			int cnt = pstmt.executeUpdate();
			System.out.println(cnt + "줄이 추가됨");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Board select(int num) {
		Connection conn = dbconn.conn();
		String sql = "select * from board where num = ?";
		
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return new Board(rs.getInt(1),rs.getDate(2),rs.getString(3),rs.getString(4),rs.getString(5));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public ArrayList<Board> selectAll(){
		ArrayList<Board> list = new ArrayList<Board>();
		Connection conn = dbconn.conn();
		String sql = "select * from board";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				list.add(new Board(rs.getInt(1),rs.getDate(2),rs.getString(3),rs.getString(4),rs.getString(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public ArrayList<Board> selectByWriter(String writer){
		ArrayList<Board> list = new ArrayList<Board>();
		Connection conn = dbconn.conn();
		String sql = "select * from board where writer = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(new Board(rs.getInt(1),rs.getDate(2),rs.getString(3),rs.getString(4),rs.getString(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public ArrayList<Board> selectByTitle(String title){
		ArrayList<Board> list = new ArrayList<Board>();
		Connection conn = dbconn.conn();
		String sql = "select * from board where title=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,title);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(new Board(rs.getInt(1),rs.getDate(2),rs.getString(3),rs.getString(4),rs.getString(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public void update(Board b) {//수정할 글번호, 새제목, 새내용
		Connection conn = dbconn.conn();
		String sql = "update board set title = ?, content = ? where num=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getContent());
			pstmt.setInt(3, b.getNum());
			
			int cnt = pstmt.executeUpdate();
			System.out.println(cnt + "줄이 수정됨");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void delete(int num) {
		Connection conn = dbconn.conn();
		
		String sql = "delete board where num = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			
			int cnt = pstmt.executeUpdate();
			System.out.println(cnt + "줄이 삭제됨");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

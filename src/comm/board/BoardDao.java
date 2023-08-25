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

	// 글작성
	public void insert(Board b) {
		// 1. db연결
		Connection conn = dbconn.conn();

		// 2. sql 작성
		String sql = "insert into board values(seq_board.nextval,sysdate,?,?,?)";

		try {
			// 3. preparedstatement 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 4. ? 매칭
			pstmt.setString(1, b.getWriter());
			pstmt.setString(2, b.getTitle());
			pstmt.setString(3, b.getContent());

			// 5. 실행
			int cnt = pstmt.executeUpdate();
			System.out.println(cnt + "줄이 추가됨");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 글 번호로 검색
	public Board select(int num) {
		// 1. db연결
		Connection conn = dbconn.conn();

		// 2. sql 작성
		String sql = "select * from board where num=?";

		try {
			// 3. preparedstatement 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 4. ? 매칭
			pstmt.setInt(1, num);

			// 5. 실행
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return new Board(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	// 전체검색
	public ArrayList<Board> selectAll() {

		ArrayList<Board> list = new ArrayList<Board>();

		// 1. db연결
		Connection conn = dbconn.conn();

		// 2. sql 작성
		String sql = "select * from board";

		try {
			// 3. preparedstatement 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 5. 실행
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new Board(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	// 작성자로 검색
	public ArrayList<Board> selectByWriter(String writer) {
		ArrayList<Board> list = new ArrayList<Board>();

		// 1. db연결
		Connection conn = dbconn.conn();

		// 2. sql 작성
		String sql = "select * from board where writer=?";

		try {
			// 3. preparedstatement 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			//4. ?매칭
			pstmt.setString(1, writer);

			// 5. 실행
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new Board(rs.getInt(1), rs.getDate(2), rs.getString(3), 
						rs.getString(4), rs.getString(5)));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	// 제목으로 검색
	public ArrayList<Board> selectByTitle(String title) {
		ArrayList<Board> list = new ArrayList<Board>();

		// 1. db연결
		Connection conn = dbconn.conn();

		// 2. sql 작성
		String sql = "select * from board where title like ?";

		try {
			// 3. preparedstatement 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			//4. ?매칭
			pstmt.setString(1, "%"+title+"%");

			// 5. 실행
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new Board(rs.getInt(1), rs.getDate(2), rs.getString(3), 
						rs.getString(4), rs.getString(5)));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	// 수정
	public void update(Board b) {// 수정할 글번호, 새제목, 새내용
		// 1. db연결
		Connection conn = dbconn.conn();

		// 2. sql 작성
		String sql = "update board set title=?, content=? where num=?";

		try {
			// 3. preparedstatement 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 4. ? 매칭
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getContent());
			pstmt.setInt(3, b.getNum());

			// 5. 실행
			int cnt = pstmt.executeUpdate();
			System.out.println(cnt + "줄이 수정됨");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 글삭제
	public void delete(int num) {// 수정할 글번호, 새제목, 새내용
		// 1. db연결
		Connection conn = dbconn.conn();

		// 2. sql 작성
		String sql = "delete board where num=?";

		try {
			// 3. preparedstatement 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 4. ? 매칭
			pstmt.setInt(1, num);

			// 5. 실행
			int cnt = pstmt.executeUpdate();
			System.out.println(cnt + "줄이 삭제됨");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
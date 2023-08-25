package comm.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conn.DBConnect;

public class MemberDao {
	private DBConnect dbconn;

	public MemberDao() {
		dbconn = DBConnect.getInstance();
	}

	public void insert(Member m) {
		// 1. db연결
		Connection conn = dbconn.conn();

		// 2. sql문 작성
		String sql = "insert into member values(?,?,?,?)";

		try {
			// 3. preparedstatement 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 4. ? 매칭
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getPwd());
			pstmt.setString(3, m.getName());
			pstmt.setString(4, m.getEmail());

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

	public Member select(String id) {
		// 1. db 연결
		Connection conn = dbconn.conn();

		// 2. sql 작성
		String sql = "select * from member where id=?";

		try {
			// 3. preparedStatement 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 4. ? 매칭
			pstmt.setString(1, id);

			// 5. 실행
			ResultSet rs = pstmt.executeQuery(); // 1줄 검색되거나 0줄

			// 6. 읽을 줄로 이동
			if (rs.next()) {
				return new Member(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
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

	public void update(Member m) {
		// 1. db연결
		Connection conn = dbconn.conn();

		// 2. sql 작성
		String sql = "update member set pwd=?, name=? where id=?";

		try {
			// 3. preparedstatement 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 4. ? 매칭
			pstmt.setString(1, m.getPwd());
			pstmt.setString(2, m.getName());
			pstmt.setString(3, m.getId());

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

	public void delete(String id) {
		// 1. db연결
		Connection conn = dbconn.conn();

		// 2. sql 작성
		String sql = "delete member where id=?";

		try {
			// 3. preparedstatement 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 4. ? 매칭
			pstmt.setString(1, id);

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
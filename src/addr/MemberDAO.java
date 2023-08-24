package addr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conn.DBConnect;
import emp.Emp;

//DAO.Member를 여러개 담을 수 잇는 배열 생성
//그 배열에 Member객체를 추가, 검색, 수정, 삭제.... 등의 작업 클래스

public class MemberDAO {
	private DBConnect dbconn;
	
	public MemberDAO() {
		dbconn = DBConnect.getInstance();
	}
	
	
	public void insert(Member m) {// emp:사번, 이름, 부서, 입사일, salary
		// 1. db 연결
		Connection conn = dbconn.conn();

		// 2. 실행할 sql문 작성
		String sql = "insert into addr values(?,?,?)";

		try {
			// 3. PreparedStatement 객체 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// pstmt.set컬럼타입(?순서, 들어갈 값)
			pstmt.setString(1,m.getName() );// 1번 물음표에 emp.id를 넣어라
			pstmt.setString(2,m.getTel());
			pstmt.setString(3, m.getAddress());
			int cnt = pstmt.executeUpdate();// sql 실행. 적용된 줄 수 반환
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
	
	// 이름을 찾아서 tel, address를 새값으로 수정
		public void update(Member m) {// emp: 수정할 사람 번호, 새 salary
			// 1. db 연결
			Connection conn = dbconn.conn();

			// 2. sql 작성
			String sql = "update addr set tel=?, address=? where name=?";

			try {
				// 3. preparedstatement 생성
				PreparedStatement pstmt = conn.prepareStatement(sql);

				// 4. ? 매칭
				pstmt.setString(1, m.getTel());
				pstmt.setString(2, m.getAddress());
				pstmt.setString(3, m.getName());
				// 5. sql실행
				int cnt = pstmt.executeUpdate();
				System.out.println(cnt + " 줄이 수정됨");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					// 6. db연결 끊음
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
		//이름을 찾아서 삭제
		public void delete(String name) {
			// 1. db 연결
			Connection conn = dbconn.conn();

			// 2. sql 작성
			String sql = "delete from addr where name=?";

			try {
				// 3. preparedstatement 생성
				PreparedStatement pstmt = conn.prepareStatement(sql);

				// 4. ? 매칭
				pstmt.setString(1, name);

				// 5. sql 실행
				int cnt = pstmt.executeUpdate();
				System.out.println(cnt + "줄 삭제됨");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					// 6. db 연결 끊음
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
		
		public Member select(String name) {
			Member m = null;//검색 결과 담을 객체

			// 1. db 연결
			Connection conn = dbconn.conn();
			
			//2. sql문 작성
			String sql = "select * from addr where name=?";
			
			
			try {
				//3. preparedStatement 생성
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				//4. ? 매칭
				pstmt.setString(1, name);
				
				//5. sql 실행
				ResultSet rs = pstmt.executeQuery();//executeQuery(): 검색한 결과를 ResultSet에 담아서 줌
				//rs.next(): ResultSet의 읽을 위치를 다음줄로 이동. 읽을 값이 있으면 true, 없으면 false반환
				if(rs.next()) {//true:검색된게 있다. false면 없다
//					int id2 = rs.getInt(1);//ResultSet의 getter는 컬럼값 꺼내는 메서드. get컬럼타입(컬럼순서)
//					String name = rs.getString(2);
//					int dept = rs.getInt(3);
//					Date d = rs.getDate(4);
//					double salary = rs.getDouble(5);
//					emp = new Emp(id2, name, dept, d, salary);
					m = new Member(rs.getString(1), rs.getString(2), rs.getString(3));
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
			return m;
	
		}

		//전체검색
		public ArrayList<Member> selectAll(){
			ArrayList<Member> list = new ArrayList<>();
			
			//1. db연결
			Connection conn = dbconn.conn();
			
			//2. sql 작성
			String sql = "select * from addr";
			
			try {
				//3. preparedstatement 생성
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				//4. sql 실행
				ResultSet rs = pstmt.executeQuery();
				
				//ResultSet에 데이터 있을 때까지 줄 이동
				while(rs.next()) {
					//각 컬럼값 꺼냄
					list.add(new Member(rs.getString(1), rs.getString(2), rs.getString(3)));
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


}

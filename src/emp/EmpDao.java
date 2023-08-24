package emp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conn.DBConnect;

//db 작업 클래스
public class EmpDao {
	private DBConnect dbconn;

	public EmpDao() {
		// 싱글톤은 new로 객체 생성못함. getInstance()로 받아옴
		dbconn = DBConnect.getInstance();
	}

	// emp 테이블에 한 줄 추가.
	public void insert(Emp emp) {// emp:사번, 이름, 부서, 입사일, salary
		// 1. db 연결
		Connection conn = dbconn.conn();

		// 2. 실행할 sql문 작성
		String sql = "insert into emp values(?,?,?,?,?)";

		try {
			// 3. PreparedStatement 객체 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// pstmt.set컬럼타입(?순서, 들어갈 값)
			pstmt.setInt(1, emp.getId());// 1번 물음표에 emp.id를 넣어라
			pstmt.setString(2, emp.getName());
			pstmt.setInt(3, emp.getDepartmentId());
			pstmt.setDate(4, emp.getHireDate());
			pstmt.setDouble(5, emp.getSalary());
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

	// 번호로 찾아서 salary를 새값으로 수정
	public void update(Emp emp) {// emp: 수정할 사람 번호, 새 salary
		// 1. db 연결
		Connection conn = dbconn.conn();

		// 2. sql 작성
		String sql = "update emp set salary=? where id=?";

		try {
			// 3. preparedstatement 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 4. ? 매칭
			pstmt.setDouble(1, emp.getSalary());
			pstmt.setInt(2, emp.getId());

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

	// 삭제: 사번으로 찾아서 행 삭제
	public void delete(int id) {
		// 1. db 연결
		Connection conn = dbconn.conn();

		// 2. sql 작성
		String sql = "delete from emp where id=?";

		try {
			// 3. preparedstatement 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 4. ? 매칭
			pstmt.setInt(1, id);

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

	// 사번으로 검색. 파람으로 검색할 사람 사번 받음. 검색한 결과를 Emp(vo)에 담아서 반환
	public Emp select(int id) {
		Emp emp = null;//검색 결과 담을 객체

		// 1. db 연결
		Connection conn = dbconn.conn();
		
		//2. sql문 작성
		String sql = "select * from emp where id=?";
		
		
		try {
			//3. preparedStatement 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			//4. ? 매칭
			pstmt.setInt(1, id);
			
			//5. sql 실행
			ResultSet rs = pstmt.executeQuery();//executeQuery(): 검색한 결과를 ResultSet에 담아서 줌
			//rs.next(): ResultSet의 읽을 위치를 다음줄로 이동. 읽을 값이 있으면 true, 없으면 false반환
			if(rs.next()) {//true:검색된게 있다. false면 없다
//				int id2 = rs.getInt(1);//ResultSet의 getter는 컬럼값 꺼내는 메서드. get컬럼타입(컬럼순서)
//				String name = rs.getString(2);
//				int dept = rs.getInt(3);
//				Date d = rs.getDate(4);
//				double salary = rs.getDouble(5);
//				emp = new Emp(id2, name, dept, d, salary);
				emp = new Emp(rs.getInt(1), rs.getString(2), rs.getInt(3), 
						rs.getDate(4), rs.getDouble(5));
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

		return emp;
	}
	
	//전체검색
	public ArrayList<Emp> selectAll(){
		ArrayList<Emp> list = new ArrayList<>();
		
		//1. db연결
		Connection conn = dbconn.conn();
		
		//2. sql 작성
		String sql = "select * from emp";
		
		try {
			//3. preparedstatement 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			//4. sql 실행
			ResultSet rs = pstmt.executeQuery();
			
			//ResultSet에 데이터 있을 때까지 줄 이동
			while(rs.next()) {
				//각 컬럼값 꺼냄
				list.add(new Emp(rs.getInt(1), rs.getString(2), rs.getInt(3), 
						rs.getDate(4), rs.getDouble(5)));
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




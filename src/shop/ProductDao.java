package shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//import board.Board;
import conn.DBConnect;

public class ProductDao {
	private DBConnect dbconn;

	public ProductDao() {
		dbconn = DBConnect.getInstance();
	}

	public void insert(Product p) {
		// 1. db연결
		Connection conn = dbconn.conn();

		// 2. sql 작성
		String sql = "insert into product values(seq_prod.nextval,?,?,?,?)";

		try {
			// 3. preparedstatement 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 4. ? 매칭
			pstmt.setString(1, p.getName());
			pstmt.setInt(2, p.getPrice());
			pstmt.setInt(3, p.getAmount());
			pstmt.setString(4, p.getSeller());

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

	public Product select(int num) {
		// 1. db연결
		Connection conn = dbconn.conn();

		// 2. sql 작성
		String sql = "select * from product where num=?";

		try {
			// 3. preparedstatement 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 4. ? 매칭
			pstmt.setInt(1, num);

			// 5. 실행
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), 
						rs.getInt(4), rs.getString(5));
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
	
	public ArrayList<Product> selectByName(String name){
		ArrayList<Product> list = new ArrayList<>();

		// 1. db연결
		Connection conn = dbconn.conn();

		// 2. sql 작성
		String sql = "select * from product where name=?";

		try {
			// 3. preparedstatement 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			//4. ?매칭
			pstmt.setString(1, "%"+name+"%");

			// 5. 실행
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), 
						rs.getInt(4), rs.getString(5)));
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
	
	public ArrayList<Product> selectByPrice(int price1, int price2){
		ArrayList<Product> list = new ArrayList<>();

		// 1. db연결
		Connection conn = dbconn.conn();

		// 2. sql 작성
		String sql = "select * from product where price between ? and ?";

		try {
			// 3. preparedstatement 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			//4. ?매칭
			pstmt.setInt(1, price1);
			pstmt.setInt(2, price2);

			// 5. 실행
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), 
						rs.getInt(4), rs.getString(5)));
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
	
	public ArrayList<Product> selectBySeller(String seller){
		ArrayList<Product> list = new ArrayList<>();

		// 1. db연결
		Connection conn = dbconn.conn();

		// 2. sql 작성
		String sql = "select * from product where seller=?";

		try {
			// 3. preparedstatement 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			//4. ?매칭
			pstmt.setString(1, seller);

			// 5. 실행
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), 
						rs.getInt(4), rs.getString(5)));
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
	
	public ArrayList<Product> selectAll(){
		ArrayList<Product> list = new ArrayList<>();

		// 1. db연결
		Connection conn = dbconn.conn();

		// 2. sql 작성
		String sql = "select * from product";

		try {
			// 3. preparedstatement 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);
		
			// 5. 실행
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), 
						rs.getInt(4), rs.getString(5)));
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
	
	public void update(Product p) {
		// 1. db연결
		Connection conn = dbconn.conn();

		// 2. sql 작성
		String sql = "update product set price=? where num=?";

		try {
			// 3. preparedstatement 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 4. ? 매칭
			pstmt.setInt(1, p.getPrice());
			pstmt.setInt(2, p.getNum());

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
	
	public void delete(int num) {
		// 1. db연결
		Connection conn = dbconn.conn();

		// 2. sql 작성
		String sql = "delete product where num=?";

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







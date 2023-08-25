package comm.member;

import java.util.Scanner;

public class MemberService {
	private MemberDao dao;
	public static String loginId;// 로그인 아이디

	public MemberService() {
		dao = new MemberDao();
	}

	public void join(Scanner sc) {
		System.out.println("=== 회원가입 ===");
		String id;
		Member m = null;
		do {
			System.out.print("id:");
			id = sc.next();
			m = dao.select(id);
		} while (m != null); // id 중복되면 루프돔
		System.out.print("pwd: ");
		String pwd = sc.next();
		System.out.print("name:");
		String name = sc.next();
		System.out.print("email:");
		String email = sc.next();

		// db에 추가
		dao.insert(new Member(id, pwd, name, email));
	}

	public void login(Scanner sc) {
		System.out.println("=== 로그인 ===");
		System.out.print("id:");
		String id = sc.next();
		System.out.print("pwd:");
		String pwd = sc.next();
		Member m = dao.select(id);
		if (m == null) {
			System.out.println("없는 아이디");
		} else {
			if (pwd.equals(m.getPwd())) {
				System.out.println("로그인 성공");
				loginId = id;
			} else {
				System.out.println("패스워드 불일치");
			}
		}
	}

	public void logout() {
		System.out.println("=== 로그아웃 ===");
		if (loginId == null) {
			System.out.println("로그인 먼저 하시오");
			return;
		}
		loginId = null; //로그아웃
	}
	
	//내 정보 확인
	public void printMyInfo() {
		System.out.println("=== 내정보확인 ===");
		if (loginId == null) {
			System.out.println("로그인 먼저 하시오");
			return;
		}
		Member m = dao.select(loginId);
		System.out.println(m);
	}
	
	public void editMyInfo(Scanner sc) {
		System.out.println("=====내정보수정=====");
		if(loginId == null) {
			System.out.println("로그인을 먼저하시오");
			return;
		}
		System.out.print("pwd: ");
		String pwd = sc.next();
		System.out.print("name:");
		String name = sc.next();
		
		dao.update(new Member(loginId,pwd,name," "));
	}
	
	public void delMember() {
		System.out.println("=====탈퇴=====");
		if(loginId == null) {
			System.out.println("로그인을 먼저하시오");
			return;
		}
		dao.delete(loginId);
		loginId = null;
	}
	
	
	
	
	
	
}





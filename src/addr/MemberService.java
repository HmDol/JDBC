package addr;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberService {
	private MemberDao dao;

	public MemberService() {
		dao = new MemberDao();
	}

	public void addMember(Scanner sc) {
		// 이름 중복 안됨
		System.out.println("====추가====");
		String name = "";
		Member m = null;
		do {
			System.out.print("name:");
			name = sc.next();
			m = dao.select(name);// 있으면 Member객체, 없으면 null반환
		} while (m != null);
		System.out.print("tel:");
		String tel = sc.next();
		System.out.print("address:");
		String address = sc.next();
		dao.insert(new Member(name, tel, address));
	}

	public void printAll() {
		ArrayList<Member> list = dao.selectAll();
		for (Member m : list) {
			System.out.println(m);
		}
	}

	public void printMember(Scanner sc) {
		System.out.println("====이름으로 검색====");
		System.out.print("검색할 이름:");
		String name = sc.next();
		Member m = dao.select(name);
		if (m == null) {
			System.out.println("없는 이름");
		} else {
			System.out.println(m);
		}
	}

	public void editMember(Scanner sc) {
		System.out.println("====수정====");

		System.out.print("수정할 사람 name:");
		String name = sc.next();
		System.out.print("new tel:");
		String tel = sc.next();
		System.out.print("new address:");
		String address = sc.next();

		dao.update(new Member(name, tel, address));
	}

	public void delMember(Scanner sc) {
		System.out.println("====삭제====");

		System.out.print("삭제할 사람 name:");
		String name = sc.next();
		dao.delete(name);
		
	}

}
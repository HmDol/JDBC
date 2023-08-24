//package addr;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.util.Scanner;
//
////service : 사용자에 제공할 기능 구현
//
//public class MemberService {
//	private MemberDAO dao; // 기능에서 저장하거나 검색.... 등을 제공하기 위해
//	
//	
//	public MemberService() {
//		dao = new MemberDAO();
//	}
//	
//
//	//추가
//	public void addMember(Scanner sc) {
//		System.out.println("---추가 -----");
//		String name = "";
//		int idx = 0;
//		while(idx >= 0) {
//			System.out.println("name : ");
//			name = sc.next();
//			idx = dao.getByName(name);
//		}
//		System.out.println("tel : ");
//		String tel = sc.next();
//		System.out.println("addrress: ");
//		String address = sc.next();
//		
//		boolean flag = dao.add(new Member(name,tel,address));
//		
//		if(flag) {
//			System.out.println("추가가 정상 처리 됨");
//		}
//		else {
//			System.out.println("추가가 안됨");
//		}
//		
//	}
//	
//
//	//검색
//	public void printMember(Scanner sc) {
//		System.out.println("====이름으로 검색====");
//		System.out.print("검색할 이름:");
//		String name = sc.next();
//		int idx = dao.getByName(name);
//		if (idx < 0) {
//			System.out.println("없는 이름");
//		} else {
//			Member m = dao.getByIdx(idx);
//			System.out.println(m);
//		}
//	}
//	//수정
//	public void editMember(Scanner sc) {
//		System.out.println("====수정====");
//		System.out.print("수정할 이름:");
//		String name = sc.next();
//		int idx = dao.getByName(name);
//		if (idx < 0) {
//			System.out.println("없는 이름. 수정 취소");
//		} else {
//			Member m = dao.getByIdx(idx);
//			System.out.println("수정전 정보:" + m);
//			System.out.print("new tel:");
//			m.setTel(sc.next());
//			System.out.print("new address:");
//			m.setAddress(sc.next());
//		}
//	}
//	
//	//삭제
//	public void delMember(Scanner sc) {
//		System.out.println("====삭제====");
//		System.out.print("삭제할 이름:");
//		String name = sc.next();
//		int idx = dao.getByName(name);
//		if (idx < 0) {
//			System.out.println("없는 이름. 삭제 취소");
//		} else {
////			dao.delete(idx);
//		}
//	}
//	//전체보기
//	public void printAll() {
//		dao.getAll();
//	}
//}

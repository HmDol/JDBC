package comm.board;

import java.util.ArrayList;
import java.util.Scanner;

import comm.member.MemberService;

public class BoardService {
	private BoardDao dao;

	public BoardService() {
		dao = new BoardDao();
	}

	// 글작성. 작성자: MemberService.loginId. 제목과 내용만 입력받음
	public void addBoard(Scanner sc) {
		System.out.println("=== 글작성 ===");
		System.out.print("title:");
		String title = sc.next();
		System.out.print("content:");
		String content = sc.next();
		dao.insert(new Board(0, null, MemberService.loginId, title, content));
	}

	// 번호로 검색
	public void getBoard(Scanner sc) {
		System.out.println("=== 번호로 검색 ===");
		System.out.print("num:");
		int num = sc.nextInt();
		Board b = dao.select(num);
		if (b == null) {
			System.out.println("없는 글");
		} else {
			System.out.println(b);
		}
	}

	// 작성자로 검색
	public void getByWriterBoard(Scanner sc) {
		System.out.println("=== 작성자로 검색 ===");
		System.out.print("writer:");
		String writer = sc.next();
		ArrayList<Board> list = dao.selectByWriter(writer);
		printAll(list);
	}

	// 전체출력
	public void printAll(ArrayList<Board> list) {
		for (Board b : list) {
			System.out.println(b);
		}
	}

	// 제목으로 검색
	public void getByTitleBoard(Scanner sc) {
		System.out.println("=== 제목으로 검색 ===");
		System.out.print("title:");
		String title = sc.next();
		ArrayList<Board> list = dao.selectByTitle(title);
		printAll(list);
	}

	// 전체 검색
	public void getAll() {
		System.out.println("=== 전체 검색 ===");
		ArrayList<Board> list = dao.selectAll();
		printAll(list);
	}

	// 수정. 자기글만 수정할 수 있다
	public void editBoard(Scanner sc) {
		System.out.println("=== 글수정 ===");
		System.out.print("num:");
		int num = sc.nextInt();
		Board b = dao.select(num);
		if (b == null) {
			System.out.println("없는 글");
		} else {
			if(MemberService.loginId.equals(b.getWriter())) {
				System.out.println("수정전:" + b);
				
				System.out.print("new title:");
				b.setTitle(sc.next());
				System.out.print("new content:");
				b.setContent(sc.next());
				
				dao.update(b);
			}else {
				System.out.println("본인글만 수정 가능");
			}
			
		}
		
	}

	// 삭제. 자기글만 삭제할 수 있다
	public void delBoard(Scanner sc) {
		System.out.println("=== 글삭제 ===");
		System.out.print("num:");
		int num = sc.nextInt();
		Board b = dao.select(num);
		if (b == null) {
			System.out.println("없는 글");
		} else {
			if(MemberService.loginId.equals(b.getWriter())) {
				dao.delete(num);
			}else {
				System.out.println("본인글만 삭제 가능");
			}
			
		}
	}
}

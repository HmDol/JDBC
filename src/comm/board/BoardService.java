package comm.board;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;


public class BoardService {
	private BoardDao dao = null;
	
	public BoardService () {
		dao = new BoardDao();
	}
	
	//글 작성, 작성자 : MemberService.loginId
	public void addBoard(Scanner sc) {
		System.out.println("===== 글 작성 =====");
		System.out.println("작성자 : " + comm.member.MemberService.loginId);
		System.out.print("제목 : ");
		String title = sc.next();
		System.out.println("내용 : ");
		String content = sc.next();
		
		dao.insert(new Board(0,null,comm.member.MemberService.loginId,title,content ));
	}
	
	
	public void getBoard(Scanner sc) {
		System.out.println("=====번호로 글 찾기 ======");
		System.out.print("찾을 글의 번호 : ");
		int num = sc.nextInt();
		Board b = dao.select(num);
		if(b == null) {
			System.out.println("없는 글 ");
			return;
		}else {
			b.toString();
		}
		
	}
	
	public void getByWriterBoard(Scanner sc) {
		System.out.println("=====작성자로 글 찾기 ======");
		System.out.print("찾을 글의 작성자 : ");
		String writer = sc.next();
	
		ArrayList<Board> list = dao.selectByWriter(writer);
		if (list.isEmpty() ){
			System.out.println("없는 글 ");
			return;
		}else {
			list.toString();
		}
		
	}
	
	public void getByTitleBoard(Scanner sc) {
		System.out.println("=====제목으로 글 찾기 ======");
		System.out.print("찾을 글의 제목 : ");
		String title = sc.next();
	
		ArrayList<Board> list = dao.selectByTitle(title);
		if (list.isEmpty() ){
			System.out.println("없는 글 ");
			return;
		}else {
			list.toString();
		}
	}
	
	public void getAll() {
		System.out.println("===모든 게시글 보기 ====");
		dao.selectAll().toString();
	}
	
	public void editBoard(Scanner sc) {
		System.out.println("====게시글 수정=====");
		System.out.print("수정할 게시글 번호 : ");
		int num = sc.nextInt();
		Board b = dao.select(num);
		if(b == null) {
			System.out.println("없는 글 ");
			return;
		}else {
			System.out.println("(수정 전 글)");
			b.toString();
		}
		System.out.print("수정 후 제목 : ");
		String title = sc.next();
		System.out.print("수정 후 내용 : ");
		String content = sc.next();
		
		dao.update(new Board(num, null,comm.member.MemberService.loginId,title,content));
	}
	
	public void delBoard(Scanner sc) {
		System.out.println("=== 게시글 삭제 ===");
		System.out.print("삭제할 게시글 번호 : ");
		int num = sc.nextInt();
		Board b = dao.select(num);
		if(b == null) {
			System.out.println("없는 글 ");
			return;
		}
		if(comm.member.MemberService.loginId.equals(b.getWriter())) {
			dao.delete(num);
		}else {
			System.out.println("본인 글만 삭제 가능!");
		}
			
	}
	
}

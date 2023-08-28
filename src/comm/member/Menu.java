package comm.member;

import java.util.Scanner;

import comm.board.BoardService;

public class Menu {
	private MemberService mservice;
	private BoardService bservice;

	public Menu() {
		mservice = new MemberService();
		bservice = new BoardService();
	}

	public void run1(Scanner sc) {// 로그인 전 메뉴
		boolean flag = true;
		int m = 0;
		while (flag) {
			System.out.println("1.회원가입 2.로그인 3.종료");
			m = sc.nextInt();
			switch (m) {
			case 1:
				mservice.join(sc);
				break;
			case 2:
				mservice.login(sc);
				if (MemberService.loginId == null) {
					System.out.println("로그인 실패");
				} else {
					run2(sc);
				}
				break;
			case 3:
				flag = false;
				break;
			}
		}
	}

	public void run2(Scanner sc) {// 로그인 후 메뉴
		boolean flag = true;
		int m = 0;
		while (flag) {
			System.out.println("1.로그아웃 2.내정보확인 3.내정보수정 4.탈퇴 5.게시판 6.종료");
			m = sc.nextInt();
			switch (m) {
			case 1:
				mservice.logout();
				return;
			case 2:
				mservice.printMyInfo();
				break;
			case 3:
				mservice.editMyInfo(sc);
				break;
			case 4:
				mservice.delMember();
				return;
			case 5:
				boardRun(sc);
				break;
			case 6:
				flag = false;
				break;
			}
		}
	}

	public void boardRun(Scanner sc) {
		// 게시판 메뉴 실행
		boolean flag = true;
		int m = 0;
		while (flag) {
			System.out.println("1.글작성 2.번호로검색 3.작성자로검색 4.제목으로검색 "
					+ "5.전체검색 6.수정 7.삭제 8.종료");
			m = sc.nextInt();
			switch (m) {
			case 1:
				bservice.addBoard(sc);
				break;
			case 2:
				bservice.getBoard(sc);
				break;
			case 3:
				bservice.getByWriterBoard(sc);
				break;
			case 4:
				bservice.getByTitleBoard(sc);
				break;
			case 5:
				bservice.getAll();
				break;
			case 6:
				bservice.editBoard(sc);
				break;
			case 7:
				bservice.delBoard(sc);
				break;
			case 8:
				flag = false;
				break;
			}
		}
	}

	public void run(Scanner sc) {
		if (MemberService.loginId == null) {//로그인 안된 상태
			run1(sc);
		} else {//로그인 상태
			run2(sc);
		}
	}
}






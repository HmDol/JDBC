package comm;

import java.util.Scanner;

import comm.member.Menu;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Menu m = new Menu();
		m.run(sc);
		System.out.println("완료");
		
		System.out.println("커밋용");
		
	}

}
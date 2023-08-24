package emp;

import java.sql.Date;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmpDao dao = new EmpDao();
//		 dao.insert(new Emp(100, "aaa", 20, new Date(23,1,1), 5500));
//		 dao.update(new Emp(100, "", 0, null, 6500));
//		 dao.delete(100);
		Emp emp = dao.select(123);
		if (emp == null) {
			System.out.println("없는 사번");
		} else {
			System.out.println(emp);
		}
		ArrayList<Emp> list = dao.selectAll();
		for(Emp e:list) {
			System.out.println(e);
		}
	}

}




package comm.board;

import java.sql.Date;

public class Board {
	private int num;
	private Date w_date;
	private String writer;
	private String title;
	private String content;
	
	public Board() {}
	
	public Board(int num, Date w_date, String writer, String title, String content) {
		super();
		this.num = num;
		this.w_date = w_date;
		this.writer = writer;
		this.title = title;
		this.content = content;
	}
	
	
	
	@Override
	public String toString() {
		return "Board [num=" + num + ", w_date=" + w_date + ", writer=" + writer + ", title=" + title + ", content="
				+ content + "]";
	}
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Date getW_date() {
		return w_date;
	}

	public void setW_date(Date w_date) {
		this.w_date = w_date;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
	
	
}

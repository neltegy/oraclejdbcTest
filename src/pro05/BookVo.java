package pro05;

public class BookVo {
	private int id;
	private String title;
	private String pubs;
	private String pubdate;
	private String AuthorName;
	private String statecode;
	public BookVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookVo(String title, String pubs, String pubdate, String authorName, String statecode) {
		super();
		this.title = title;
		this.pubs = pubs;
		this.pubdate = pubdate;
		AuthorName = authorName;
		this.statecode = statecode;
	}
	public BookVo(int id, String title, String pubs, String pubdate, String authorName, String statecode) {
		super();
		this.id = id;
		this.title = title;
		this.pubs = pubs;
		this.pubdate = pubdate;
		AuthorName = authorName;
		this.statecode = statecode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPubs() {
		return pubs;
	}
	public void setPubs(String pubs) {
		this.pubs = pubs;
	}
	public String getPubdate() {
		return pubdate;
	}
	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}
	public String getAuthorName() {
		return AuthorName;
	}
	public void setAuthorName(String authorName) {
		AuthorName = authorName;
	}
	public String getStatecode() {
		return statecode;
	}
	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}
	@Override
	public String toString() {
		if(statecode.equals("0")) {
			statecode = "대여중";
		}else if(statecode.equals("1")) {
			statecode = "재고있음";
		}
		return "책 제목:" + title + ", 작가:" + AuthorName + ", 대여 유무:" + statecode ;
	}
	
}

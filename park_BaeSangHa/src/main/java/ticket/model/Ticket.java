package ticket.model;

import java.util.Date;

public class Ticket {
	private int tno;
	private String carno;
	private String phone;
	private String grade;
	private String tstat;

	private Date startdate;
	private Date enddate;

	// 기본 생성자
	public Ticket() {
	}

	// 등록 요청 시 사용하는 생성자
    public Ticket(int tno, String carno, String phone, String grade, String tstat) {
        this(tno, carno, phone, grade, tstat, null, null);
    }

    // DB 조회 시 전체 필드를 초기화하는 생성자
    public Ticket(int tno, String carno, String phone, String grade, String tstat, Date startdate, Date enddate) {
        this.tno = tno;
        this.carno = carno;
        this.phone = phone;
        this.grade = grade;
        this.tstat = tstat;
        this.startdate = startdate;
        this.enddate = enddate;
    }

	// Getter
	public int getTno() {
		return tno;
	}

	public String getCarno() {
		return carno;
	}

	public String getPhone() {
		return phone;
	}

	public String getGrade() {
		return grade;
	}

	public String getTstat() {
		return tstat;
	}

	public Date getStartdate() {
		return startdate;
	}

	public Date getEnddate() {
		return enddate;
	}
}

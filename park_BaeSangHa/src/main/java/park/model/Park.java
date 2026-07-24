package park.model;

import java.util.Date;

public class Park {
    private int parkno;
    private String carno;
    private String grade;
    private String tstat;
    private Date indate;
    private Date outdate;
    
    // 기본 생성자
    public Park() {}

    // 입고 등록 시 사용하는 생성자
    public Park(int parkno, String carno, String grade, String tstat, Date indate, Date outdate) {
        this.parkno = parkno;
        this.carno = carno;
        this.grade = grade;
        this.tstat = tstat;
        this.indate = indate;
        this.outdate = outdate;
    }

    public int getParkno() { return parkno; }
    public String getCarno() { return carno; }
    public String getGrade() { return grade; }
    public String getTstat() { return tstat; }
    public Date getIndate() { return indate; }
    public Date getOutdate() { return outdate; }
}
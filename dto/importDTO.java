package dto;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class importDTO {
	private int IDIM;
	private int IDNCC;
	private int IDTAIKHOAN;
	private Date NGAYNHAPKHO;
	private double TONGGIATRI;

    public importDTO() {
    }

    public importDTO(int IDIM, int IDNCC, int IDTAIKHOAN, Date NGAYNHAPKHO, double TONGGIATRI) {
        this.IDIM = IDIM;
        this.IDNCC = IDNCC;
        this.IDTAIKHOAN = IDTAIKHOAN;
        this.NGAYNHAPKHO = NGAYNHAPKHO;
        this.TONGGIATRI = TONGGIATRI;
    }

    public int getIDIM() {
        return IDIM;
    }

    public void setIDIM(int IDIM) {
        this.IDIM = IDIM;
    }

    public int getIDNCC() {
        return IDNCC;
    }

    public void setIDNCC(int IDNCC) {
        this.IDNCC = IDNCC;
    }

    public int getIDTAIKHOAN() {
        return IDTAIKHOAN;
    }

    public void setIDTAIKHOAN(int IDTAIKHOAN) {
        this.IDTAIKHOAN = IDTAIKHOAN;
    }

    public Date getNGAYNHAPKHO() {
        return NGAYNHAPKHO;
    }

    public void setNGAYNHAPKHO(Date NGAYNHAPKHO) {
        this.NGAYNHAPKHO = NGAYNHAPKHO;
    }

    public double getTONGGIATRI() {
        return TONGGIATRI;
    }

    public void setTONGGIATRI(double TONGGIATRI) {
        this.TONGGIATRI = TONGGIATRI;
    }
	
}

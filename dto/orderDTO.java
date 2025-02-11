package dto;

import java.util.Date;

public class orderDTO {
	private int IDHOADON;
	private int IDTAIKHOAN;
	private int IDPHUONGTHUCVANCHUYEN;
        private int IDTHANHTOAN;
        private Date NGAYDAT;
        private String CHUTHICH;
	private double TONGTIEN;

    public orderDTO() {
    }

    public orderDTO(int IDHOADON, int IDTAIKHOAN, int IDPHUONGTHUCVANCHUYEN, int IDTHANHTOAN, Date NGAYDAT, String CHUTHICH, double TONGTIEN) {
        this.IDHOADON = IDHOADON;
        this.IDTAIKHOAN = IDTAIKHOAN;
        this.IDPHUONGTHUCVANCHUYEN = IDPHUONGTHUCVANCHUYEN;
        this.IDTHANHTOAN = IDTHANHTOAN;
        this.NGAYDAT = NGAYDAT;
        this.CHUTHICH = CHUTHICH;
        this.TONGTIEN = TONGTIEN;
    }

    public int getIDHOADON() {
        return IDHOADON;
    }

    public void setIDHOADON(int IDHOADON) {
        this.IDHOADON = IDHOADON;
    }

    public int getIDTAIKHOAN() {
        return IDTAIKHOAN;
    }

    public void setIDTAIKHOAN(int IDTAIKHOAN) {
        this.IDTAIKHOAN = IDTAIKHOAN;
    }

    public int getIDPHUONGTHUCVANCHUYEN() {
        return IDPHUONGTHUCVANCHUYEN;
    }

    public void setIDPHUONGTHUCVANCHUYEN(int IDPHUONGTHUCVANCHUYEN) {
        this.IDPHUONGTHUCVANCHUYEN = IDPHUONGTHUCVANCHUYEN;
    }

    public int getIDTHANHTOAN() {
        return IDTHANHTOAN;
    }

    public void setIDTHANHTOAN(int IDTHANHTOAN) {
        this.IDTHANHTOAN = IDTHANHTOAN;
    }

    public Date getNGAYDAT() {
        return NGAYDAT;
    }

    public void setNGAYDAT(Date NGAYDAT) {
        this.NGAYDAT = NGAYDAT;
    }

    public String getCHUTHICH() {
        return CHUTHICH;
    }

    public void setCHUTHICH(String CHUTHICH) {
        this.CHUTHICH = CHUTHICH;
    }

    public double getTONGTIEN() {
        return TONGTIEN;
    }

    public void setTONGTIEN(double TONGTIEN) {
        this.TONGTIEN = TONGTIEN;
    }
	


	
	
}

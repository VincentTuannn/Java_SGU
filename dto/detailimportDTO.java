package dto;

public class detailimportDTO {
	private int IDPHIEUNHAP;
	private int IDLOHANG;
	private int SOLUONG;
	private double DONGIANHAP;
        private double TONGTIEN;

    public detailimportDTO() {
    }

    public detailimportDTO(int IDPHIEUNHAP, int IDLOHANG, int SOLUONG, double DONGIANHAP, double TONGTIEN) {
        this.IDPHIEUNHAP = IDPHIEUNHAP;
        this.IDLOHANG = IDLOHANG;
        this.SOLUONG = SOLUONG;
        this.DONGIANHAP = DONGIANHAP;
        this.TONGTIEN = TONGTIEN;
    }

    public int getIDPHIEUNHAP() {
        return IDPHIEUNHAP;
    }

    public void setIDPHIEUNHAP(int IDPHIEUNHAP) {
        this.IDPHIEUNHAP = IDPHIEUNHAP;
    }

    public int getIDLOHANG() {
        return IDLOHANG;
    }

    public void setIDLOHANG(int IDLOHANG) {
        this.IDLOHANG = IDLOHANG;
    }

    public int getSOLUONG() {
        return SOLUONG;
    }

    public void setSOLUONG(int SOLUONG) {
        this.SOLUONG = SOLUONG;
    }

    public double getDONGIANHAP() {
        return DONGIANHAP;
    }

    public void setDONGIANHAP(double DONGIANHAP) {
        this.DONGIANHAP = DONGIANHAP;
    }

    public double getTONGTIEN() {
        return TONGTIEN;
    }

    public void setTONGTIEN(double TONGTIEN) {
        this.TONGTIEN = TONGTIEN;
    }
	
}

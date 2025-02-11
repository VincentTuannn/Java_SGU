package dto;

import java.util.ArrayList;

public class ProductDTO {
    private int IDSP;
    private int IDLOAISP;
    private int IDKHUYENMAI;
    private String TEN;
    private int SOLUONG;
    private double GIA;
    private double DUNGTICH;
    private double NONGDOCON;
    private int TRANGTHAI;
    private String XUATXU;
    private String THUONGHIEU;
    private String MOTA;
    private String ANH;
    public ProductDTO() {
    }

    public ProductDTO(int IDSP, int IDLOAISP, int IDKHUYENMAI, String TEN, int SOLUONG, double GIA, double DUNGTICH, double NONGDOCON, int TRANGTHAI, String XUATXU, String THUONGHIEU, String MOTA, String ANH) {
        this.IDSP = IDSP;
        this.IDLOAISP = IDLOAISP;
        this.IDKHUYENMAI = IDKHUYENMAI;
        this.TEN = TEN;
        this.SOLUONG = SOLUONG;
        this.GIA = GIA;
        this.DUNGTICH = DUNGTICH;
        this.NONGDOCON = NONGDOCON;
        this.TRANGTHAI = TRANGTHAI;
        this.XUATXU = XUATXU;
        this.THUONGHIEU = THUONGHIEU;
        this.MOTA = MOTA;
        this.ANH = ANH;
    }

    public int getIDSP() {
        return IDSP;
    }

    public int getIDLOAISP() {
        return IDLOAISP;
    }

    public int getIDKHUYENMAI() {
        return IDKHUYENMAI;
    }

    public String getTEN() {
        return TEN;
    }

    public int getSOLUONG() {
        return SOLUONG;
    }

    public double getGIA() {
        return GIA;
    }

    public double getDUNGTICH() {
        return DUNGTICH;
    }

    public double getNONGDOCON() {
        return NONGDOCON;
    }

    public int getTRANGTHAI() {
        return TRANGTHAI;
    }

    public String getXUATXU() {
        return XUATXU;
    }

    public String getTHUONGHIEU() {
        return THUONGHIEU;
    }

    public String getMOTA() {
        return MOTA;
    }

    public String getANH() {
        return ANH;
    }

    public void setIDSP(int IDSP) {
        this.IDSP = IDSP;
    }

    public void setIDLOAISP(int IDLOAISP) {
        this.IDLOAISP = IDLOAISP;
    }

    public void setIDKHUYENMAI(int IDKHUYENMAI) {
        this.IDKHUYENMAI = IDKHUYENMAI;
    }

    public void setTEN(String TEN) {
        this.TEN = TEN;
    }

    public void setSOLUONG(int SOLUONG) {
        this.SOLUONG = SOLUONG;
    }

    public void setGIA(double GIA) {
        this.GIA = GIA;
    }

    public void setDUNGTICH(double DUNGTICH) {
        this.DUNGTICH = DUNGTICH;
    }

    public void setNONGDOCON(double NONGDOCON) {
        this.NONGDOCON = NONGDOCON;
    }

    public void setTRANGTHAI(int TRANGTHAI) {
        this.TRANGTHAI = TRANGTHAI;
    }

    public void setXUATXU(String XUATXU) {
        this.XUATXU = XUATXU;
    }

    public void setTHUONGHIEU(String THUONGHIEU) {
        this.THUONGHIEU = THUONGHIEU;
    }

    public void setMOTA(String MOTA) {
        this.MOTA = MOTA;
    }

    public void setANH(String ANH) {
        this.ANH = ANH;
    }
    
}  
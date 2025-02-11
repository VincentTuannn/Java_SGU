/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.Date;

public class VoucherDTO {
    private int IDKHUYENMAI;
    private String TEN;
    private double GIATRI;
    private Date NGAYBATDAU;
    private Date NGAYKETTHUC;
    private String MOTA;
    private int TRANGTHAI;

    public VoucherDTO() {
    }

    public VoucherDTO(int IDKHUYENMAI, String TEN, double GIATRI, Date NGAYBATDAU, Date NGAYKETTHUC, String MOTA, int TRANGTHAI) {
        this.IDKHUYENMAI = IDKHUYENMAI;
        this.TEN = TEN;
        this.GIATRI = GIATRI;
        this.NGAYBATDAU = NGAYBATDAU;
        this.NGAYKETTHUC = NGAYKETTHUC;
        this.MOTA = MOTA;
        this.TRANGTHAI = TRANGTHAI;
    }

    public int getIDKHUYENMAI() {
        return IDKHUYENMAI;
    }

    public String getTEN() {
        return TEN;
    }

    public double getGIATRI() {
        return GIATRI;
    }

    public Date getNGAYBATDAU() {
        return NGAYBATDAU;
    }

    public Date getNGAYKETTHUC() {
        return NGAYKETTHUC;
    }

    public String getMOTA() {
        return MOTA;
    }

    public int getTRANGTHAI() {
        return TRANGTHAI;
    }

    public void setIDKHUYENMAI(int IDKHUYENMAI) {
        this.IDKHUYENMAI = IDKHUYENMAI;
    }

    public void setTEN(String TEN) {
        this.TEN = TEN;
    }

    public void setGIATRI(double GIATRI) {
        this.GIATRI = GIATRI;
    }

    public void setNGAYBATDAU(Date NGAYBATDAU) {
        this.NGAYBATDAU = NGAYBATDAU;
    }

    public void setNGAYKETTHUC(Date NGAYKETTHUC) {
        this.NGAYKETTHUC = NGAYKETTHUC;
    }

    public void setMOTA(String MOTA) {
        this.MOTA = MOTA;
    }

    public void setTRANGTHAI(int TRANGTHAI) {
        this.TRANGTHAI = TRANGTHAI;
    }
    

}

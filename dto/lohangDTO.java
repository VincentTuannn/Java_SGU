/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.Date;

public class lohangDTO{
    private int IDLOHANG;
    private Date NGAYSANXUAT;
    private int HANSUDUNG;
    private int SOLUONG;
    private int IDSANPHAM;

    public lohangDTO(int IDLOHANG, Date NGAYSANXUAT, int HANSUDUNG, int SOLUONG, int IDSANPHAM) {
        this.IDLOHANG = IDLOHANG;
        this.NGAYSANXUAT = NGAYSANXUAT;
        this.HANSUDUNG = HANSUDUNG;
        this.SOLUONG = SOLUONG;
        this.IDSANPHAM = IDSANPHAM;
    }

    public lohangDTO() {
    }

    public int getIDLOHANG() {
        return IDLOHANG;
    }

    public void setIDLOHANG(int IDLOHANG) {
        this.IDLOHANG = IDLOHANG;
    }

    public Date getNGAYSANXUAT() {
        return NGAYSANXUAT;
    }

    public void setNGAYSANXUAT(Date NGAYSANXUAT) {
        this.NGAYSANXUAT = NGAYSANXUAT;
    }

    public int getHANSUDUNG() {
        return HANSUDUNG;
    }

    public void setHANSUDUNG(int HANSUDUNG) {
        this.HANSUDUNG = HANSUDUNG;
    }

    public int getSOLUONG() {
        return SOLUONG;
    }

    public void setSOLUONG(int SOLUONG) {
        this.SOLUONG = SOLUONG;
    }

    public int getIDSANPHAM() {
        return IDSANPHAM;
    }

    public void setIDSANPHAM(int IDSANPHAM) {
        this.IDSANPHAM = IDSANPHAM;
    }
    
}

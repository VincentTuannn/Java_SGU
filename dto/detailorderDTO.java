/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Lap4all
 */

public class detailorderDTO{
    private int IDHOADON;
    private int IDLOHANG;
    private int SOLUONG;
    private double DONGIA;
    private double THANHTIEN;

    public detailorderDTO() {
    }

    public detailorderDTO(int IDHOADON, int IDLOHANG, int SOLUONG, double DONGIA, double THANHTIEN) {
        this.IDHOADON = IDHOADON;
        this.IDLOHANG = IDLOHANG;
        this.SOLUONG = SOLUONG;
        this.DONGIA = DONGIA;
        this.THANHTIEN = THANHTIEN;
    }



    public int getIDHOADON() {
        return IDHOADON;
    }

    public void setIDHOADON(int IDHOADON) {
        this.IDHOADON = IDHOADON;
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

    public double getDONGIA() {
        return DONGIA;
    }

    public void setDONGIA(double DONGIA) {
        this.DONGIA = DONGIA;
    }

    public double getTHANHTIEN() {
        return THANHTIEN;
    }

    public void setTHANHTIEN(double THANHTIEN) {
        this.THANHTIEN = THANHTIEN;
    }


}
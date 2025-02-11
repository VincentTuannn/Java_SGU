/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Lap4all
 */
public class vanchuyenDTO {
    private int IDVANCHUYEN;
    private String TEN;
    private int status;
    public vanchuyenDTO() {
    }

    public vanchuyenDTO(int IDVANCHUYEN, String TEN, int status) {
        this.IDVANCHUYEN = IDVANCHUYEN;
        this.TEN = TEN;
        this.status = status;
    }

    public int getIDVANCHUYEN() {
        return IDVANCHUYEN;
    }

    public void setIDVANCHUYEN(int IDVANCHUYEN) {
        this.IDVANCHUYEN = IDVANCHUYEN;
    }

    public String getTEN() {
        return TEN;
    }

    public void setTEN(String TEN) {
        this.TEN = TEN;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

   
    
}


package dto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class AccountDTO{
    private int IDTAIKHOAN;
    private String SODIENTHOAI;
    private String HOTEN;
    private char GIOITINH;
    private String MATKHAU;
    private String EMAIL;
    private Date NGAYSINH;
    private String DIACHI;
    private char TRANGTHAI;
    private String QUYEN;
    
    public AccountDTO(){}

    public AccountDTO(int IDTAIKHOAN, String SODIENTHOAI, String HOTEN, char GIOITINH, String MATKHAU, String EMAIL, Date NGAYSINH, String DIACHI, char TRANGTHAI, String QUYEN) {
        this.IDTAIKHOAN = IDTAIKHOAN;
        this.SODIENTHOAI = SODIENTHOAI;
        this.HOTEN = HOTEN;
        this.GIOITINH = GIOITINH;
        this.MATKHAU = MATKHAU;
        this.EMAIL = EMAIL;
        this.NGAYSINH = NGAYSINH;
        this.DIACHI = DIACHI;
        this.TRANGTHAI = TRANGTHAI;
        this.QUYEN = QUYEN;
    }

    public String getQUYEN() {
        return QUYEN;
    }

    public void setQUYEN(String QUYEN) {
        this.QUYEN = QUYEN;
    }

    

    public char getTRANGTHAI() {
        return TRANGTHAI;
    }

    public void setTRANGTHAI(char TRANGTHAI) {
        this.TRANGTHAI = TRANGTHAI;
    }
    

    // Getter methods
    public int getIDTAIKHOAN() {
        return IDTAIKHOAN;
    }


    public String getSODIENTHOAI() {
        return SODIENTHOAI;
    }

    public String getHOTEN() {
        return HOTEN;
    }

    public char getGIOITINH() {
        return GIOITINH;
    }

    public String getMATKHAU() {
        return MATKHAU;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public Date getNGAYSINH() {
        return NGAYSINH;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    // Setter methods
    public void setIDTAIKHOAN(int IDTAIKHOAN) {
        this.IDTAIKHOAN = IDTAIKHOAN;
    }


    public void setSODIENTHOAI(String SODIENTHOAI) {
        this.SODIENTHOAI = SODIENTHOAI;
    }

    public void setHOTEN(String HOTEN) {
        this.HOTEN = HOTEN;
    }

    public void setGIOITINH(char GIOITINH) {
        this.GIOITINH = GIOITINH;
    }

    public void setMATKHAU(String MATKHAU) {
        this.MATKHAU = MATKHAU;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public void setNGAYSINH(Date NGAYSINH) {
        this.NGAYSINH = NGAYSINH;
    }

    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
    }
}
package dto;
public class SupplierDTO {
    private int IDSU;
    private String EMAIL;
    private String SODIENTHOAI;
    private String DIACHI;
    private String TEN;
    private int status;
    // Constructor
    public SupplierDTO(){}

    public SupplierDTO(int IDSU, String EMAIL, String SODIENTHOAI, String DIACHI, String TEN, int status) {
        this.IDSU = IDSU;
        this.EMAIL = EMAIL;
        this.SODIENTHOAI = SODIENTHOAI;
        this.DIACHI = DIACHI;
        this.TEN = TEN;
        this.status = status;
    }

    public SupplierDTO(int IDSU, String EMAIL, String SODIENTHOAI, String DIACHI, String TEN) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
   

    // Getter methods
    public int getIDSU() {
        return IDSU;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public String getSODIENTHOAI() {
        return SODIENTHOAI;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    public String getTEN() {
        return TEN;
    }

    // Setter methods
    public void setIDSU(int IDSU) {
        this.IDSU = IDSU;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public void setSODIENTHOAI(String SODIENTHOAI) {
        this.SODIENTHOAI = SODIENTHOAI;
    }

    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
    }

    public void setTEN(String TEN) {
        this.TEN = TEN;
    }
}

package dto;

import java.sql.Timestamp;
import java.util.Objects;

public class PHUONGTHUCTHANHTOANDTO {
	private int IDTHANHTOAN;
	private String TEN;
        private int status;

    public PHUONGTHUCTHANHTOANDTO() {
    }

    public PHUONGTHUCTHANHTOANDTO(int IDTHANHTOAN, String TEN, int status) {
        this.IDTHANHTOAN = IDTHANHTOAN;
        this.TEN = TEN;
        this.status = status;
    }

    public int getIDTHANHTOAN() {
        return IDTHANHTOAN;
    }

    public void setIDTHANHTOAN(int IDTHANHTOAN) {
        this.IDTHANHTOAN = IDTHANHTOAN;
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

package dto;

public class CategoryDTO {
	private int IDLOAISP;
	private String TENLOAISANPHAM;
	private int status;
	public CategoryDTO() {
		
	}

    public CategoryDTO(int categoryId, String categoryName, int status) {
        this.IDLOAISP = categoryId;
        this.TENLOAISANPHAM = categoryName;
        this.status = status;
    }

    public int getIDLOAISP() {
        return IDLOAISP;
    }

    public String getTENLOAISANPHAM() {
        return TENLOAISANPHAM;
    }

    public int getStatus() {
        return status;
    }

    public void setIDLOAISP(int IDLOAISP) {
        this.IDLOAISP = IDLOAISP;
    }

    public void setTENLOAISANPHAM(String TENLOAISANPHAM) {
        this.TENLOAISANPHAM = TENLOAISANPHAM;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    
	
	
	
	
}

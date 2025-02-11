package bus;

import dao.detailimportDAO;
import dto.detailimportDTO;
import java.util.ArrayList;
import view.Import;

public class detailimportBUS {
    private final detailimportDAO detailimportDAO = new detailimportDAO();
	private ArrayList<detailimportDTO> listdetailimport = new ArrayList<>();
    
    public detailimportBUS() {
		listdetailimport = detailimportDAO.selectAll();
	}
	
	public ArrayList<detailimportDTO> getALL() {
		listdetailimport = detailimportDAO.selectAll();
		return this.listdetailimport;
	}
	public detailimportDTO getByIndex(int index) {
		return this.listdetailimport.get(index);
	}
	
	public int getIndexById(int IDPHIEUNHAP) {
		int i = 0;
		int locate = -1;
		while (i < this.listdetailimport.size() && locate == -1) {
			if (listdetailimport.get(i).getIDPHIEUNHAP()==IDPHIEUNHAP) {
				locate = i;
			}else {
				i++;
			}
		}
		return locate;
	}	
//	public Boolean add(detailimportDTO product) {
//		boolean check = detailimportDAO.insert(Import) != 0;
//		if (check) {
//			this.listdetailimport.add(Import);
//		}
//		return check;
//	}
	public Boolean delete(detailimportDTO Import) {
        boolean check = detailimportDAO.delete(Import.getIDPHIEUNHAP()) != 0;
        return check;
    }
        
	
   public Boolean update(detailimportDTO Import) {
        boolean check = detailimportDAO.update(Import) != 0;
        if (check) {
            this.listdetailimport.set(getIndexById(Import.getIDPHIEUNHAP()), Import);
        }
        return check;
    }
   
   public Boolean restore(detailimportDTO Import) {
       boolean check = detailimportDAO.restore(Import) != 0;
       if (check) {
    	   this.listdetailimport.set(getIndexById(Import.getIDPHIEUNHAP()), Import);
       }
       return check;
   }
       
   
   public boolean checkDup( int IDPHIEUNHAPToExclude) {
	    boolean check = true;
	    int i = 0;
	    while (i < this.listdetailimport.size() && check) {
	        if (this.listdetailimport.get(i).getIDPHIEUNHAP()==IDPHIEUNHAPToExclude) {
                    return false;
                }
                i++; 
	    }
	    return check;
	}
}

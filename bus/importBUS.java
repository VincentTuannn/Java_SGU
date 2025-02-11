/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dao.importDAO;
import dto.importDTO;
import java.util.ArrayList;
import view.Import;

/**
 *
 * @author Lap4all
 */
public class importBUS {
    private final importDAO importDAO = new importDAO();
	private ArrayList<importDTO> listimport = new ArrayList<>();
    
    public importBUS() {
		listimport = importDAO.selectAll();
	}
	
	public ArrayList<importDTO> getALL() {
		listimport = importDAO.selectAll();
		return this.listimport;
	}
	public importDTO getByIndex(int index) {
		return this.listimport.get(index);
	}
	
	public int getIndexById(int IDIM) {
		int i = 0;
		int locate = -1;
		while (i < this.listimport.size() && locate == -1) {
			if (listimport.get(i).getIDIM()==IDIM) {
				locate = i;
			}else {
				i++;
			}
		}
		return locate;
	}	
	public Boolean add(importDTO Import) {
		boolean check = importDAO.insert(Import) != 0;
		if (check) {
			this.listimport.add(Import);
		}
		return check;
	}
	public Boolean delete(importDTO Import) {
        boolean check = importDAO.delete(Import.getIDIM()) != 0;
        return check;
    }
        
	
   public Boolean update(importDTO Import) {
        boolean check = importDAO.update(Import) != 0;
        if (check) {
            this.listimport.set(getIndexById(Import.getIDIM()), Import);
        }
        return check;
    }
   
   public Boolean restore(importDTO Import) {
       boolean check = importDAO.restore(Import) != 0;
       if (check) {
    	   this.listimport.set(getIndexById(Import.getIDIM()), Import);
       }
       return check;
   }
       
   
   public boolean checkDup( int IDIMToExclude) {
	    boolean check = true;
	    int i = 0;
	    while (i < this.listimport.size() && check) {
	        if (this.listimport.get(i).getIDIM()==IDIMToExclude) {
                    return false;
                }
                i++; 
	    }
	    return check;
	}
}

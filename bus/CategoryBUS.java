package bus;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import dao.CategoryDAO;
import dto.CategoryDTO;
import javax.swing.JOptionPane;

public class CategoryBUS {
	private final CategoryDAO cateDAO = new CategoryDAO();
	private ArrayList<CategoryDTO> listCate = new ArrayList<>();
	
	public CategoryBUS() {
		listCate = cateDAO.selectAll();
	}
	
	public ArrayList<CategoryDTO> getALL() {
                listCate = cateDAO.selectAll();
		return this.listCate;
	}
	
	public CategoryDTO getByIndex(int index) {
		return this.listCate.get(index);
	}
	
	public int getIndexById(int categoryId) {
		int i = 0;
		int locate = -1;
		while (i < this.listCate.size() && locate == -1) {
			if (listCate.get(i).getIDLOAISP()==categoryId) {
				locate = i;
			}else {
				i++;
			}
		}
		return locate;
	}
	
	public Boolean add(CategoryDTO cate) {
		boolean check = cateDAO.insert(cate) != 0;
		if (check) {
			this.listCate.add(cate);
		}
		return check;
	}

	
	public Boolean delete(CategoryDTO cate) {
        boolean check = cateDAO.delete(cate.getIDLOAISP()) != 0;
        if (check) {
            this.listCate.remove(cate);
        }
        return check;
    }
        public Boolean nondelete(CategoryDTO cate) {
        boolean check = cateDAO.restore(cate) != 0;
        if (check) {
    	   this.listCate.set(getIndexById(cate.getIDLOAISP()), cate);
        }
        return check;}

    public Boolean update(CategoryDTO cate) {
        boolean check = cateDAO.update(cate) != 0;
        if (check) {
            this.listCate.set(getIndexById(cate.getIDLOAISP()), cate);
        }
        return check;
    }
    
    public ArrayList<CategoryDTO> search(String text) {
        text = text.toLowerCase();
        ArrayList<CategoryDTO> result = new ArrayList<>();
        for (CategoryDTO c : this.listCate) {
            if (c.getIDLOAISP()==Integer.parseInt(text) || c.getTENLOAISANPHAM().toLowerCase().contains(text.toLowerCase())) {
                result.add(c);
            }
        }
        return result;
    }
    
    public String[] getArrCategoryName() {
        int size = listCate.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = listCate.get(i).getTENLOAISANPHAM();
        }
        return result;
    }

    public String getCategoryName(int categoryId) {
        return this.listCate.get(this.getIndexById(categoryId)).getTENLOAISANPHAM();
    }
    public boolean checkDup(String name) {
        boolean check = true;
        int i = 0;
        while (i < this.listCate.size() && check == true) {
            if (this.listCate.get(i).getTENLOAISANPHAM().toLowerCase().contains(name.toLowerCase())){
                check = false;
            } else {
                i++;
            }
        }
        return check;
    }
    public boolean checkDup(String name, int cateid) {
	    boolean check = true;
	    int i = 0;
	    while (i < this.listCate.size() && check) {
	        if (this.listCate.get(i).getIDLOAISP()== (cateid)||this.listCate.get(i).getTENLOAISANPHAM().toLowerCase().contains(name.toLowerCase())) {
                    return false;
                }
                i++; 
	    }
	    return check;
	}
    public void importExcel () { 	
	   		JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(null);
	  		int result = fileChooser.showOpenDialog(null);
	  		String url = fileChooser.getSelectedFile().getAbsolutePath();
			if(result == JFileChooser.APPROVE_OPTION) {
		    	try {
		    		FileInputStream inputStream = new FileInputStream(url);
					cateDAO.importDatabase(inputStream);
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	   }
}

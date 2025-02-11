package bus;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import dao.ProductDAO;
import dto.CategoryDTO;
import dto.ProductDTO;

public class ProductBUS {
	private final ProductDAO productDAO = new ProductDAO();
	private ArrayList<ProductDTO> listProduct = new ArrayList<>();
	
	public ProductBUS() {
		listProduct = productDAO.selectAll();
	}
	
	public ArrayList<ProductDTO> getALL() {
		listProduct = productDAO.selectAll();
		return this.listProduct;
	}
	public ProductDTO getByIndex(int index) {
		return this.listProduct.get(index);
	}
	
	public int getIndexById(int productId) {
		int i = 0;
		int locate = -1;
		while (i < this.listProduct.size() && locate == -1) {
			if (listProduct.get(i).getIDSP()==productId) {
				locate = i;
			}else {
				i++;
			}
		}
		return locate;
	}	
	public Boolean add(ProductDTO product) {
		boolean check = productDAO.insert(product) != 0;
		if (check) {
			this.listProduct.add(product);
		}
		return check;
	}
	public Boolean delete(ProductDTO product) {
        boolean check = productDAO.delete(product.getIDSP()) != 0;
        return check;
    }
        
	
   public Boolean update(ProductDTO product) {
        boolean check = productDAO.update(product) != 0;
        if (check) {
            this.listProduct.set(getIndexById(product.getIDSP()), product);
        }
        return check;
    }
   
   public Boolean restore(ProductDTO product) {
       boolean check = productDAO.restore(product) != 0;
       if (check) {
    	   this.listProduct.set(getIndexById(product.getIDSP()), product);
       }
       return check;
   }
   

	    
   public ArrayList<ProductDTO> search(String text) {
       text = text.toLowerCase();
       ArrayList<ProductDTO> result = new ArrayList<>();
       for (ProductDTO c : this.listProduct) {
           if (c.getIDSP() ==Integer.parseInt(text) || c.getTEN().toLowerCase().contains(text.toLowerCase())) {
               result.add(c);
           }
       }
       return result;
   }
   
   public String getProductName(int productId) {
        for (ProductDTO c : this.listProduct)
            if(c.getIDSP()==productId)
                return c.getTEN();
        return null;
   }
       
   
   public boolean checkDup(String name, int productIdToExclude) {
	    boolean check = true;
	    int i = 0;
	    while (i < this.listProduct.size() && check) {
	        if (this.listProduct.get(i).getIDSP()==productIdToExclude||this.listProduct.get(i).getTEN().toLowerCase().contains(name.toLowerCase())) {
                    return false;
                }
                i++; 
	    }
	    return check;
	}
   public void importExcel () { 	
//	   	JFileChooser fileChooser = new JFileChooser();
//			fileChooser.setCurrentDirectory(null);
//	  		int result = fileChooser.showOpenDialog(null);
//	  		String url = fileChooser.getSelectedFile().getAbsolutePath();
//			if(result == JFileChooser.APPROVE_OPTION) {
//		    	try {
//		    		FileInputStream inputStream = new FileInputStream(url);
//					productDAO.importDatabase(inputStream);
//					inputStream.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
	   }
}

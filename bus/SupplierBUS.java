package bus;

import dto.SupplierDTO;
import dao.SupplierDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplierBUS {
    private final SupplierDAO supplierDAO = new SupplierDAO();
    private ArrayList<SupplierDTO> supplierList;
    private ArrayList<SupplierDTO> listSupplier = new ArrayList<>();

    public SupplierBUS() {
        supplierList = supplierDAO.selectAll();
    }

    // Thêm một nhà cung cấp vào danh sách nếu chưa tồn tại, và kiểm tra tính hợp lệ của email và số điện thoại
//    public boolean addSupplier(SupplierDTO supplier) {
//        if (!supplierList.contains(supplier) && isValidEmail(supplier.getEmail()) && isValidPhoneNumber(supplier.getNumberPhone()) && !isEmailExist(supplier.getEmail()) && !isPhoneExist(supplier.getNumberPhone())) {
//            supplierList.add(supplier);
//            return true;
//        }
//        return false;
//    }

    // Kiểm tra xem email có hợp lệ hay không
    public boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Kiểm tra xem số điện thoại có hợp lệ hay không
    public boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^[0-9]{10}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    // Kiểm tra xem email đã tồn tại trong danh sách nhà cung cấp hay không
    public boolean isEmailExist(String email) {
        for (SupplierDTO supplier : supplierList) {
            if (supplier.getEMAIL().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

//     Kiểm tra xem số điện thoại đã tồn tại trong danh sách nhà cung cấp hay không
//    public boolean isPhoneExist(String numberPhone) {
//        for (SupplierDTO supplier : supplierList) {
//            if (supplier.getNumberPhone().equals(numberPhone)) {
//                return true;
//            } 
//        }
//        return false;
//    }

    // Xóa một nhà cung cấp khỏi danh sách
    public void removeSupplier(SupplierDTO supplier) {
        supplierList.remove(supplier);
    }

    public boolean checkDup(String name, int supplierIdToExclude) {
	    boolean check = true;
	    int i = 0;
	    while (i < this.listSupplier.size() && check) {
	        if (this.listSupplier.get(i).getIDSU()==supplierIdToExclude||this.listSupplier.get(i).getTEN().toLowerCase().contains(name.toLowerCase())) {
                    return false;
                }
                i++; 
	    }
	    return check;
	}
    
     // Trả về danh sách tất cả nhà cung cấp
    public ArrayList<SupplierDTO> getAllSuppliers() {
        listSupplier = supplierDAO.selectAll();
		return this.listSupplier;
    }
    
    public int getIndexById(int supplierId) {
		int i = 0;
		int locate = -1;
		while (i < this.listSupplier.size() && locate == -1) {
			if (listSupplier.get(i).getIDSU()==supplierId) {
				locate = i;
			}else {
				i++;
			}
		}
		return locate;
	}	
    
    
    public Boolean add(SupplierDTO supplier) {
		boolean check = supplierDAO.insert(supplier) != 0;
		if (check) {
			this.listSupplier.add(supplier);
		}
		return check;
	}
	public Boolean delete(SupplierDTO supplier) {
        boolean check = supplierDAO.delete(supplier.getIDSU()) != 0;
        if (check) {
            this.listSupplier.remove(supplier);
        }
        return check;
    }
    
         public Boolean update(SupplierDTO supplier) {
        boolean check = supplierDAO.update(supplier) != 0;
        if (check) {
            this.listSupplier.set(getIndexById(supplier.getIDSU()), supplier);
        }
        return check;
    }
        
    // Tìm nhà cung cấp theo ID
//    public Supplier findSupplierByID(int IDSU) {
//        for (Supplier supplier : supplierList) {
//            if (supplier.getIDSU().equals(IDSU)) {
//                return supplier;
//            }
//        }
//        return null; // Trả về null nếu không tìm thấy
//    }

    // Tìm nhà cung cấp theo từ khóa trong tên
    public List<SupplierDTO> findSuppliersByKeyword(String keyword) {
        List<SupplierDTO> foundSuppliers = new ArrayList<>();
        for (SupplierDTO supplier : supplierList) {
            if (supplier.getTEN().toLowerCase().contains(keyword.toLowerCase())) {
                foundSuppliers.add(supplier);
            }
        }
        return foundSuppliers;
    }

   
}
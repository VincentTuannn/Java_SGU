/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dao.voucherDAO;
import dto.ProductDTO;
import dto.VoucherDTO;
import java.util.ArrayList;

public class VoucherBUS {

    private ArrayList<VoucherDTO> voucherList;
    private final voucherDAO voucherDAO = new voucherDAO();
    private final ProductBUS productBUS = new ProductBUS();
    public VoucherBUS() {
        voucherList = voucherDAO.selectAll();
    }

    public ArrayList<VoucherDTO> getALL() {
        voucherList = voucherDAO.selectAll();
        return this.voucherList;
    }

    
public ArrayList<ProductDTO> getlistproductbyMaKhuyenMai(int MaKhuyenMai) {
    // Tạo danh sách mới để lưu các sản phẩm có IDKhuyenMai tương ứng
    ArrayList<ProductDTO> newList = new ArrayList<>();

    // Lấy danh sách tất cả sản phẩm
    ArrayList<ProductDTO> list = productBUS.getALL();

    // Duyệt qua danh sách sản phẩm
    for (ProductDTO product : list) {
        // Kiểm tra nếu IDKhuyenMai của sản phẩm bằng với MaKhuyenMai được truyền vào
        if (product.getIDKHUYENMAI()== MaKhuyenMai) {
            // Thêm sản phẩm này vào danh sách mới
            newList.add(product);
        }
    }

    // Trả về danh sách mới chỉ chứa các sản phẩm có IDKhuyenMai tương ứng
    return newList;
}

    
    public VoucherDTO getByIndex(int index) {
        return this.voucherList.get(index);
    }

    public int getIndexById(int voucherid) {
        int i = 0;
        int locate = -1;
        while (i < this.voucherList.size() && locate == -1) {
            if (voucherList.get(i).getIDKHUYENMAI() == voucherid) {
                locate = i;
            } else {
                i++;
            }
        }
        return locate;
    }

    // Phương thức thêm một Voucher vào danh sách voucherList
    public boolean addVoucher(VoucherDTO voucher) {
        boolean check = voucherDAO.insert(voucher) != 0;
        if (check) {
            this.voucherList.add(voucher);
        }
        return check;
    }

    // Phương thức xóa một Voucher khỏi danh sách voucherList
    public boolean delete(VoucherDTO voucher) {
        boolean check = voucherDAO.delete(voucher.getIDKHUYENMAI()) != 0;
        return check;
    }

    public Boolean update(VoucherDTO voucher) {
        boolean check = voucherDAO.update(voucher) != 0;
        if (check) {
            this.voucherList.set(getIndexById(voucher.getIDKHUYENMAI()), voucher);
        }
        return check;
    }

    public Boolean restore(VoucherDTO voucher) {
        boolean check = voucherDAO.restore(voucher) != 0;
        return check;
    }

    public ArrayList<VoucherDTO> search(String text) {
        text = text.toLowerCase();
        ArrayList<VoucherDTO> result = new ArrayList<>();
        for (VoucherDTO c : this.voucherList) {
            if (c.getIDKHUYENMAI() == Integer.parseInt(text) || c.getTEN().toLowerCase().contains(text.toLowerCase())) {
                result.add(c);
            }
        }
        return result;
    }

    public String getVoucherName(int voucherid) {
        for (VoucherDTO c : this.voucherList) {
            if (c.getIDKHUYENMAI() == voucherid) {
                return c.getTEN();
            }
        }
        return null;
    }

    public boolean checkDup(String name, int voucherid) {
        boolean check = true;
        int i = 0;
        while (i < this.voucherList.size() && check) {
            if (this.voucherList.get(i).getIDKHUYENMAI() == voucherid || this.voucherList.get(i).getTEN().toLowerCase().contains(name.toLowerCase())) {
                return false;
            }
            i++;
        }
        return check;
    }

    // Phương thức lọc Voucher theo giá trị
    public ArrayList<VoucherDTO> filterVouchersByValue(double minValue, double maxValue) {
        ArrayList<VoucherDTO> filteredVouchers = new ArrayList<>();
        for (VoucherDTO voucher : voucherList) {
            if (voucher.getGIATRI() >= minValue && voucher.getGIATRI() <= maxValue) {
                filteredVouchers.add(voucher);
            }
        }
        return filteredVouchers;
    }

    public void importExcel() {
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

    }}

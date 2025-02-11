package bus;

import dao.PHUONGTHUCTHANHTOANDAO;
import dto.PHUONGTHUCTHANHTOANDTO;
import java.util.ArrayList;

/**
 *
 * @author Lap4all
 */
public class phuongthucthanhtoanBUS {

    private final PHUONGTHUCTHANHTOANDAO thanhtoanDAO = new PHUONGTHUCTHANHTOANDAO();
    private ArrayList<PHUONGTHUCTHANHTOANDTO> listthanhtoan = new ArrayList<>();

    public phuongthucthanhtoanBUS() {
        listthanhtoan = thanhtoanDAO.selectAll();
    }

    public ArrayList<PHUONGTHUCTHANHTOANDTO> getALL() {
        listthanhtoan = thanhtoanDAO.selectAll();
        return this.listthanhtoan;
    }

    public ArrayList<PHUONGTHUCTHANHTOANDTO> search(String name) {
        ArrayList<PHUONGTHUCTHANHTOANDTO> list = new ArrayList<>();

        for (PHUONGTHUCTHANHTOANDTO product : listthanhtoan) {
            if (product.getTEN().toLowerCase().contains(name.toLowerCase())) {
                list.add(product);
            }
        }
        return list;
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

    }

    public String getthanhtoanname(int iddthanhtoan) {
        for (PHUONGTHUCTHANHTOANDTO c : this.listthanhtoan) {
            if (c.getIDTHANHTOAN() == iddthanhtoan) {
                return c.getTEN();
            }
        }
        return null;
    }

    public ArrayList<PHUONGTHUCTHANHTOANDTO> getlistThanhToanByMa(int IDThanhToan) {
        // Tạo danh sách mới để lưu các sản phẩm có IDKhuyenMai tương ứng
        ArrayList<PHUONGTHUCTHANHTOANDTO> newList = new ArrayList<>();

        // Lấy danh sách tất cả sản phẩm
        ArrayList<PHUONGTHUCTHANHTOANDTO> list = getALL();

        // Duyệt qua danh sách sản phẩm
        for (PHUONGTHUCTHANHTOANDTO product : list) {
            // Kiểm tra nếu IDKhuyenMai của sản phẩm bằng với MaKhuyenMai được truyền vào
            if (product.getIDTHANHTOAN() == IDThanhToan) {
                // Thêm sản phẩm này vào danh sách mới
                newList.add(product);
            }
        }

        // Trả về danh sách mới chỉ chứa các sản phẩm có IDKhuyenMai tương ứng
        return newList;
    }

    public PHUONGTHUCTHANHTOANDTO getByIndex(int index) {
        return this.listthanhtoan.get(index);
    }

    public boolean addthanhtoan(PHUONGTHUCTHANHTOANDTO thanhtoan) {
        boolean check = thanhtoanDAO.insert(thanhtoan) != 0;
        if (check) {
            this.listthanhtoan.add(thanhtoan);
        }
        return check;
    }

    // Phương thức xóa một Voucher khỏi danh sách voucherList
    public boolean delete(PHUONGTHUCTHANHTOANDTO thanhtoan) {
        boolean check = thanhtoanDAO.delete(thanhtoan.getIDTHANHTOAN()) != 0;
        return check;
    }

    public Boolean update(PHUONGTHUCTHANHTOANDTO thanhtoan) {
        boolean check = thanhtoanDAO.update(thanhtoan) != 0;
        if (check) {
            this.listthanhtoan.set(getIndexById(thanhtoan.getIDTHANHTOAN()), thanhtoan);
        }
        return check;
    }

    public int getIndexById(int voucherid) {
        int i = 0;
        int locate = -1;
        while (i < this.listthanhtoan.size() && locate == -1) {
            if (listthanhtoan.get(i).getIDTHANHTOAN() == voucherid) {
                locate = i;
            } else {
                i++;
            }
        }
        return locate;
    }

    public Boolean restore(PHUONGTHUCTHANHTOANDTO voucher) {
        boolean check = thanhtoanDAO.restore(voucher) != 0;
        return check;
    }

}

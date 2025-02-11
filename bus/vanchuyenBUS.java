package bus;

import dao.vanchuyenDAO;
import dto.vanchuyenDTO;
import java.util.ArrayList;

/**
 *
 * @author Lap4all
 */
public class vanchuyenBUS {

    private final vanchuyenDAO vanchuyenDAO = new vanchuyenDAO();
    private ArrayList<vanchuyenDTO> listvanchuyen = new ArrayList<>();

    public vanchuyenBUS() {
        listvanchuyen = vanchuyenDAO.selectAll();
    }

    public ArrayList<vanchuyenDTO> getALL() {
        listvanchuyen = vanchuyenDAO.selectAll();
        return this.listvanchuyen;
    }

    public ArrayList<vanchuyenDTO> search(String name) {
        ArrayList<vanchuyenDTO> list = new ArrayList<>();

        for (vanchuyenDTO product : listvanchuyen) {
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

    public String getvanchuyenname(int iddvanchuyen) {
        for (vanchuyenDTO c : listvanchuyen) {
            if (c.getIDVANCHUYEN() == iddvanchuyen) {
                return c.getTEN();
            }
        }
        return null;
    }

    public ArrayList<vanchuyenDTO> listvanchuyen(int IDvanchuyen) {
        // Tạo danh sách mới để lưu các sản phẩm có IDKhuyenMai tương ứng
        ArrayList<vanchuyenDTO> newList = new ArrayList<>();

        // Lấy danh sách tất cả sản phẩm
        ArrayList<vanchuyenDTO> list = getALL();

        // Duyệt qua danh sách sản phẩm
        for (vanchuyenDTO product : list) {
            // Kiểm tra nếu IDKhuyenMai của sản phẩm bằng với MaKhuyenMai được truyền vào
            if (product.getIDVANCHUYEN() == IDvanchuyen) {
                // Thêm sản phẩm này vào danh sách mới
                newList.add(product);
            }
        }

        // Trả về danh sách mới chỉ chứa các sản phẩm có IDKhuyenMai tương ứng
        return newList;
    }

    public vanchuyenDTO getByIndex(int index) {
        return this.listvanchuyen.get(index);
    }

    public boolean addvanchuyen(vanchuyenDTO vanchuyen) {
        boolean check = vanchuyenDAO.insert(vanchuyen) != 0;
        if (check) {
            this.listvanchuyen.add(vanchuyen);
        }
        return check;
    }

    // Phương thức xóa một Voucher khỏi danh sách voucherList
    public boolean delete(vanchuyenDTO vanchuyen) {
        boolean check = vanchuyenDAO.delete(vanchuyen.getIDVANCHUYEN()) != 0;
        return check;
    }

    public Boolean update(vanchuyenDTO vanchuyen) {
        boolean check = vanchuyenDAO.update(vanchuyen) != 0;
        if (check) {
            this.listvanchuyen.set(getIndexById(vanchuyen.getIDVANCHUYEN()), vanchuyen);
        }
        return check;
    }

    public int getIndexById(int voucherid) {
        int i = 0;
        int locate = -1;
        while (i < this.listvanchuyen.size() && locate == -1) {
            if (listvanchuyen.get(i).getIDVANCHUYEN()== voucherid) {
                locate = i;
            } else {
                i++;
            }
        }
        return locate;
    }

    public Boolean restore(vanchuyenDTO voucher) {
        boolean check = vanchuyenDAO.restore(voucher) != 0;
        return check;
    }

}

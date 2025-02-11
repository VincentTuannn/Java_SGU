package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import bus.CategoryBUS;
import JDBCUtil.JDBCConnect;
import bus.ProductBUS;
import dto.CategoryDTO;
import dto.ProductDTO;

public class ProductDAO implements DAOInterface<ProductDTO> {

    public static ProductDAO getInstance() {
        return new ProductDAO();
    }

    @Override
    public int insert(ProductDTO t) {
        // TODO Auto-generated method stub
        int result = 0;
        System.err.println(t.getTHUONGHIEU());
        System.out.println(t.getXUATXU());
        System.out.println(t.getMOTA());
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "INSERT INTO `product`(`IDSP`,`IDLOAISP`,`IDKHUYENMAI`, `TEN`, `SOLUONG`, `GIA`, `DUNGTICH`, `NONGDOCON`,`TRANGTHAI`,`XUATXU`,`THUONGHIEU`,`MOTA`,`ANH`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getIDSP());
            pst.setInt(2, t.getIDLOAISP());
            pst.setInt(3, t.getIDKHUYENMAI());
            pst.setString(4, t.getTEN());
            pst.setInt(5, t.getSOLUONG());
            pst.setDouble(6, t.getGIA());
            pst.setDouble(7, t.getDUNGTICH());
            pst.setDouble(8, t.getNONGDOCON());
            pst.setInt(9, t.getTRANGTHAI());
            pst.setString(10, t.getXUATXU());
            pst.setString(11, t.getTHUONGHIEU());
            pst.setString(12, t.getMOTA());
            pst.setString(13, t.getANH());
            result = pst.executeUpdate();
            JDBCConnect.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(ProductDTO t) {
        int result = 0;
        try {
            Connection con = JDBCConnect.getConnection();
            String sql = "UPDATE `product` SET  `IDLOAISP`= ?,`IDKHUYENMAI`= ?, `TEN`= ?, `SOLUONG`= ?, `GIA`= ?, `DUNGTICH`= ?, `NONGDOCON`= ?,`TRANGTHAI`= ?,`XUATXU`= ?,`THUONGHIEU`= ?,`MOTA`= ?,`ANH`= ? WHERE `IDSP` = ?";
            try (PreparedStatement pst = con.prepareStatement(sql)) {

                pst.setInt(1, t.getIDLOAISP());
                pst.setInt(2, t.getIDKHUYENMAI());
                pst.setString(3, t.getTEN());
                pst.setInt(4, t.getSOLUONG());
                pst.setDouble(5, t.getGIA());
                pst.setDouble(6, t.getDUNGTICH());
                pst.setDouble(7, t.getNONGDOCON());
                pst.setInt(8, t.getTRANGTHAI());
                pst.setString(9, t.getXUATXU());
                pst.setString(10, t.getTHUONGHIEU());
                pst.setString(11, t.getMOTA());
                pst.setString(12, t.getANH());
                pst.setInt(13, t.getIDSP());
                result = pst.executeUpdate();
            }
            JDBCConnect.closeConnection(con);
        } catch (SQLException ex) {
            // Log or print the exception details
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(int t) {
        // TODO Auto-generated method stub
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "UPDATE `product` SET `TRANGTHAI` = 0 WHERE `IDSP`= ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t);
            result = pst.executeUpdate();
            JDBCConnect.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<ProductDTO> selectAll() {
        // TODO Auto-generated method stub
        ArrayList<ProductDTO> result = new ArrayList<ProductDTO>();
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "SELECT * FROM product";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int IDSP = rs.getInt("IDSP");
                int IDLOAISP = rs.getInt("IDLOAISP");
                int IDKHUYENMAI = rs.getInt("IDKHUYENMAI");
                String TEN = rs.getString("TEN");
                int SOLUONG = rs.getInt("SOLUONG");
                double GIA = rs.getDouble("GIA");
                double DUNGTICH = rs.getDouble("DUNGTICH");
                double NONGDOCON = rs.getDouble("NONGDOCON");
                int TRANGTHAI = rs.getInt("TRANGTHAI");
                String XUATXU = rs.getString("XUATXU");
                String THUONGHIEU = rs.getString("THUONGHIEU");
                String MOTA = rs.getString("MOTA");
                String ANH = rs.getString("ANH");
                ProductDTO product = new ProductDTO(IDSP, IDLOAISP, IDKHUYENMAI, TEN, SOLUONG, GIA, DUNGTICH, NONGDOCON, TRANGTHAI, XUATXU, THUONGHIEU, MOTA, ANH);
                result.add(product);

            }
        } catch (SQLException e) {
        }
        return result;
    }
    public static void main(String[] args) {
        ProductDAO pr = new ProductDAO();
        ArrayList<ProductDTO> ar = pr.selectAll();
        System.out.println(ar.size());
    }

    @Override
    public ProductDTO selectById(int t) {
        ProductDTO product = new ProductDTO();
        try {
            Connection con = JDBCConnect.getConnection();
            String sql = "SELECT * FROM product WHERE `IDSP`=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t); // Thiết lập giá trị cho tham số productId
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int IDSP = rs.getInt("IDSP");
                int IDLOAISP = rs.getInt("IDLOAISP");
                int IDKHUYENMAI = rs.getInt("IDKHUYENMAI");
                String TEN = rs.getString("TEN");
                int SOLUONG = rs.getInt("SOLUONG");
                double GIA = rs.getDouble("GIA");
                double DUNGTICH = rs.getDouble("DUNGTICH");
                double NONGDOCON = rs.getDouble("NONGDOCON");
                int TRANGTHAI = rs.getInt("TRANGTHAI");
                String XUATXU = rs.getString("XUATXU");
                String THUONGHIEU = rs.getString("THUONGHIEU");
                String MOTA = rs.getString("MOTA");
                String ANH = rs.getString("ANH");
                product = new ProductDTO(IDSP, IDLOAISP, IDKHUYENMAI, TEN, SOLUONG, GIA, DUNGTICH, NONGDOCON, TRANGTHAI, XUATXU, THUONGHIEU, MOTA, ANH);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public ArrayList<ProductDTO> selectByCondition(String condition) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getAutoIncrement() {
        // TODO Auto-generated method stub
        int result = -1;
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'RUOU' AND   TABLE_NAME   = 'product'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            if (!rs2.isBeforeFirst()) {
                System.out.println("No data");
            } else {
                while (rs2.next()) {
                    result = rs2.getInt("AUTO_INCREMENT");

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int restore(ProductDTO t) {
        // TODO Auto-generated method stub
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "UPDATE `product` SET `TRANGTHAI`=1 WHERE `IDSP`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getIDSP());
            result = pst.executeUpdate();
            JDBCConnect.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    @Override
    public ArrayList<ProductDTO> selectAll(String t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void importDatabase(FileInputStream inputStream) {
//        // TODO Auto-generated method stub
//        int batchSize = 20;
//        try {
//
//            Connection con = JDBCConnect.getConnection();
//
//            Workbook workbook = new XSSFWorkbook(inputStream);
//            Sheet firstSheet = workbook.getSheetAt(0);
//            Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = firstSheet.iterator();
//
//            String sql = "INSERT INTO `product`(`IDLOAISP`,`IDKHUYENMAI`, `TEN`, `SOLUONG`, `GIA`, `DUNGTICH`, `NONGDOCON`,`TRANGTHAI`,`XUATXU`,`THUONGHIEU`,`MOTA`,`ANH`) "
//                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
//            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
//
//            int count = 0;
//
//            rowIterator.next(); // skip the header row
//
//            while (rowIterator.hasNext()) {
//                org.apache.poi.ss.usermodel.Row nextRow = rowIterator.next();
//                Iterator<Cell> cellIterator = ((org.apache.poi.ss.usermodel.Row) nextRow).cellIterator();
//
//                while (cellIterator.hasNext()) {
//                    Cell cell = cellIterator.next();
//                    int columnIndex = cell.getColumnIndex();         
//                    switch (columnIndex) {
//                        case 1:
//                            int IDLOAISP = Integer.parseInt(cell.getStringCellValue());
//                            pst.setInt(1, IDLOAISP);
//                            break;
//                        case 2:
//                            CategoryBUS categoryBUS = new CategoryBUS();
//                            ArrayList<CategoryDTO> listCate = categoryBUS.getALL();
//                            String categoryId = cell.getStringCellValue();
//                            System.out.println(categoryId);
//                            pst.setString(2, listCate.get(categoryBUS.getIndexById(Integer.parseInt(categoryId))).getCategoryName());
//                            break;
//
//                        case 3:
//                            Double purchasePrice = cell.getNumericCellValue();
//                            pst.setDouble(3, purchasePrice);
//                            break;
//
//                        case 4:
//                            Double sellingPrice = cell.getNumericCellValue();
//                            pst.setDouble(4, sellingPrice);
//                            break;
//
//                        case 5:
//                            int quantity = (int) cell.getNumericCellValue();
//                            pst.setInt(5, quantity);
//                            break;
//                        case 6:
//                            String image = null;
//                            pst.setString(6, image);
//                            break;
//                        case 7:
//                            String description = cell.getStringCellValue();
//                            pst.setString(7, description);
//                            break;
//                        case 8:
//                            break;
//                        case 9:
//                            break;
//                        case 10:
//                            break;
//                        case 11:
//                            break;
//                        case 12:
//                            break;
//                    }
//                }
//                pst.addBatch();
//
//                if (++count % batchSize == 0) {
//                    pst.executeBatch();
//                }
//            }
//            workbook.close();
//            // execute the remaining queries
//            pst.executeBatch();
//
//            JDBCConnect.closeConnection(con);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            System.out.println("Khong doc duoc file");
//            e.printStackTrace();
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            System.out.println("Loi du lieu");
//            e.printStackTrace();
//        }
    }
}

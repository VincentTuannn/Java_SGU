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

import JDBCUtil.JDBCConnect;
import dto.CategoryDTO;
import dto.ProductDTO;

public class CategoryDAO implements DAOInterface<CategoryDTO>{
	public static CategoryDAO getInstance() {
		return new CategoryDAO();
	}

	@Override
	public int insert(CategoryDTO t) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			Connection con = (Connection) JDBCConnect.getConnection();
			String sql = "INSERT INTO `category`(`IDLOAISP`,`TENLOAISANPHAM`,`status`) VALUES (?,?,?)";
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setInt(1, t.getIDLOAISP());
                        pst.setString(2, t.getTENLOAISANPHAM());
                        pst.setInt(3,1);
			result = pst.executeUpdate();
			JDBCConnect.closeConnection(con);
		} catch(SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

	@Override
	public int update(CategoryDTO t) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "UPDATE `category` SET `TENLOAISP`=? WHERE `IDLOAISP`=?";
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTENLOAISANPHAM());
            pst.setInt(2, t.getIDLOAISP());
            result = pst.executeUpdate();
			JDBCConnect.closeConnection(con);
		} catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}
    @Override
    public int delete(int t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "UPDATE `category` SET `status` = 0 WHERE `IDLOAISP`= ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t);
            result = pst.executeUpdate();
            JDBCConnect.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<CategoryDTO> selectAll() {
        ArrayList<CategoryDTO> result = new ArrayList<CategoryDTO>();
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "SELECT * FROM category ";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int IDLOAISP = rs.getInt("IDLOAISP");
                String TENLOAISP = rs.getString("TENLOAISANPHAM");
                int STATUS = rs.getInt("status");
                CategoryDTO lh = new CategoryDTO(IDLOAISP, TENLOAISP,STATUS);
                result.add(lh);
            }
            JDBCConnect.closeConnection(con);
        } catch (Exception e) {
        	
        }
        return result;
    }

    @Override
    public CategoryDTO selectById(int t) {
        CategoryDTO result = null;
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "SELECT * FROM category WHERE IDLOAISP=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int IDLOAISP = rs.getInt("IDLOAISP");
                String TENLOAISP = rs.getString("TENLOAISANPHAM");
                int STATUS = rs.getInt("status");

                result = new CategoryDTO(IDLOAISP, TENLOAISP,STATUS);
            }
            JDBCConnect.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
	@Override
	public ArrayList<CategoryDTO> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'RUOU' AND   TABLE_NAME   = 'category'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            if (!rs2.isBeforeFirst() ) {
                System.out.println("No data");
            } else {
                while ( rs2.next() ) {
                    result = rs2.getInt("AUTO_INCREMENT");
                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
	@Override
	public ArrayList<CategoryDTO> selectAll(String t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void importDatabase(FileInputStream inputStream){
//		try {
//			
//			Connection con = JDBCConnect.getConnection();
//			
//			Workbook workbook = new XSSFWorkbook(inputStream);
//			Sheet firstSheet = workbook.getSheetAt(0);
//			Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = firstSheet.iterator();
//			
//			rowIterator.next(); // skip the header row
//			
//			while(rowIterator.hasNext()) {
//				org.apache.poi.ss.usermodel.Row nextRow =  rowIterator.next();
//                Iterator<Cell> cellIterator = ((org.apache.poi.ss.usermodel.Row) nextRow).cellIterator();
//                CategoryDTO c = new CategoryDTO();
//                
//                try {
//	                while (cellIterator.hasNext()) {
//	                    Cell cell = cellIterator.next();
//	                    int rowIndex = cell.getRowIndex();
//	                    if(rowIndex == 0) {
//	                    	break;
//	                    }
//	                    int columnIndex = cell.getColumnIndex();
//	                    
//	                    switch (columnIndex) {
//	                    case 1:
//	                        c.setCategoryName(cell.getStringCellValue());
//	                        break;                    
//	                    }
//	                }
//	            }catch (Exception e) {
//	            	System.out.println("Lỗi định dạng thông tin trong file excel!");
//	            	e.printStackTrace();
//	            }
//                if(c.getCategoryName() != null){
//                    try {
//                    	String sql = "INSERT INTO `category`(`categoryName`) VALUES (?)";
//            			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
//            			if(c.getCategoryName() != null)
//            				pst.setString(1, c.getCategoryName());
//                    	int result = pst.executeUpdate();
//                    } catch (SQLException e) {
//                    	System.out.println("Lỗi dữ liệu");
//                    }
//                }
//			}
//			workbook.close();
//			
//			JDBCConnect.closeConnection(con);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			System.out.println("Khong doc duoc file");
//			e.printStackTrace();
//		}
	}

    @Override
    public int restore(CategoryDTO t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "UPDATE `category` SET `status` = 1 WHERE `IDLOAISP`= ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getIDLOAISP());
            result = pst.executeUpdate();
            JDBCConnect.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }




}

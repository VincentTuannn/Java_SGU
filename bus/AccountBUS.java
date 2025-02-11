/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;
import dto.AccountDTO;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class AccountBUS {
    private ArrayList<AccountDTO> accountList; // Danh sách các tài khoản

    public AccountBUS() {
        accountList = new ArrayList<>(); // Khởi tạo danh sách tài khoản
    }
    public void removeAccount(AccountDTO account) {
        accountList.remove(account); // Xóa tài khoản khỏi danh sách
    }
    public AccountDTO getAccountByID(int IDTAIKHOAN) {
        for (AccountDTO account : accountList) {
            if (account.getIDTAIKHOAN() == IDTAIKHOAN) {
                return account; // Trả về tài khoản với IDTAIKHOAN tương ứng
            }
        }
        return null; // Trả về null nếu không tìm thấy tài khoản
    }

    public ArrayList<AccountDTO> getAllAccounts() {
        return accountList; // Trả về toàn bộ danh sách tài khoản
    }

    public ArrayList<AccountDTO> searchAccountsByHOTEN(String HOTEN) {
        ArrayList<AccountDTO> searchResult = new ArrayList<>(); // Danh sách kết quả tìm kiếm
        for (AccountDTO account : accountList) {
            if (account.getHOTEN().equalsIgnoreCase(HOTEN)) {
                searchResult.add(account); // Thêm tài khoản vào danh sách kết quả nếu tên đầy đủ khớp
            }
        }
        return searchResult; // Trả về danh sách kết quả tìm kiếm
    }

    public ArrayList<AccountDTO> searchAccountsByKeyword(String keyword) {
        ArrayList<AccountDTO> searchResult = new ArrayList<>(); // Danh sách kết quả tìm kiếm
        for (AccountDTO account : accountList) {
            if (account.getHOTEN().toLowerCase().contains(keyword.toLowerCase())
                    || account.getEMAIL().toLowerCase().contains(keyword.toLowerCase())) {
                searchResult.add(account); // Thêm tài khoản vào danh sách kết quả nếu tên hoặc EMAIL chứa từ khóa
            }
        }
        return searchResult; // Trả về danh sách kết quả tìm kiếm
    }
    
    public void addAccount(AccountDTO account) {
        if (isEMAILValid(account.getEMAIL()) && isSODIENTHOAIValid(account.getSODIENTHOAI()) && isUniqueEMAIL(account.getEMAIL()) && isUniqueSODIENTHOAI(account.getSODIENTHOAI())) {
            accountList.add(account);
        } else {
            System.out.println("Invalid EMAIL or phone number, or EMAIL/phone number already exists. Account creation failed.");
        }
    }

    private boolean isEMAILValid(String EMAIL) {
        // Kiểm tra tính hợp lệ của EMAIL bằng biểu thức chính quy
        String EMAILRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return Pattern.matches(EMAILRegex, EMAIL);
    }

    private boolean isSODIENTHOAIValid(String SODIENTHOAI) {
    // Kiểm tra tính hợp lệ của số điện thoại bằng biểu thức chính quy
    // Số điện thoại phải có 10 chữ số và chỉ chứa các ký tự số
    String phoneRegex = "0\\d{9}"; // Bắt đầu từ số 0 và tiếp theo là 9 chữ số
    return Pattern.matches(phoneRegex, SODIENTHOAI);
    }

    private boolean isUniqueEMAIL(String EMAIL) {
        for (AccountDTO account : accountList) {
            if (account.getEMAIL().equalsIgnoreCase(EMAIL)) {
                return false; // EMAIL đã tồn tại trong danh sách
            }
        }
        return true; // EMAIL chưa tồn tại trong danh sách
    }

    private boolean isUniqueSODIENTHOAI(String SODIENTHOAI) {
        for (AccountDTO account : accountList) {
            if (account.getSODIENTHOAI().equals(SODIENTHOAI)) {
                return false; // Số điện thoại đã tồn tại trong danh sách
            }
        }
        return true; // Số điện thoại chưa tồn tại trong danh sách
    }
}

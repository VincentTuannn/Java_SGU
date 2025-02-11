/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dao.lohangDAO;
import dto.lohangDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Lap4all
 */
public class lohangBUS {

    private final lohangDAO lohangDAO = new lohangDAO();
    private ArrayList<lohangDTO> listlohang = new ArrayList<>();

    public lohangBUS() {
        listlohang = lohangDAO.selectAll();
    }

    public ArrayList<lohangDTO> getALL() {
        listlohang = lohangDAO.selectAll();
        return this.listlohang;
    }

    public ArrayList<lohangDTO> search(String date, int idsp) {
        ArrayList<lohangDTO> result = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Trường hợp 1: date = ' ' và idsp = 0
        if (date.equals("") && idsp == 0) {
            return this.listlohang;
        }

        // Trường hợp 2: date có giá trị và idsp = 0
        if (!date.equals("") && idsp == 0) {
            for (lohangDTO c : this.listlohang) {
                String dateString = sdf.format(c.getNGAYSANXUAT());

                if (dateString.contains(date)) {
                    result.add(c);
                }
            }
            return result;
        }

        // Trường hợp 3: date = ' ' và idsp != 0
        if (date.equals("") && idsp != 0) {
            for (lohangDTO c : this.listlohang) {
                if (c.getIDSANPHAM() == idsp) {
                    result.add(c);
                }
            }
            return result;
        }

        // Trường hợp 4: date có giá trị và idsp != 0
        for (lohangDTO c : this.listlohang) {
            String dateString = sdf.format(c.getNGAYSANXUAT());
            if (c.getIDSANPHAM() == idsp && dateString.contains(date)) {
                result.add(c);
            }
        }
        return result;
    }
}

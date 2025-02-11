//package bus;
//
//import dto.DetailOrder;
//import dto.Order;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class OrderBUS {
//    private List<Order> orders;
//
//    public OrderBUS() {
//        orders = new ArrayList<>();
//    }
//
//
//     // Thêm một đơn đặt hàng vào danh sách.
//
//    public void addOrder(Order order) {
//        orders.add(order);
//    }
//
//
//     // Xóa một đơn đặt hàng khỏi danh sách.
//
//    public void removeOrder(Order order) {
//        orders.remove(order);
//    }
//
//
//     // Trả về danh sách tất cả các đơn đặt hàng.
//
//    public List<Order> getAllOrders() {
//        return orders;
//    }
//
//     // Trả về đơn đặt hàng theo ID.
//
//
//    public Order getOrderByID(int orderID) {
//        for (Order order : orders) {
//            if (order.getIDOR() == orderID) {
//                return order;
//            }
//        }
//        return null;
//    }
//
//     // Trả về danh sách các đơn đặt hàng theo ID tài khoản.
//
//    public List<Order> getOrdersByAccountID(int accountID) {
//        List<Order> ordersByAccountID = new ArrayList<>();
//        for (Order order : orders) {
//            if (order.getIDAC() == accountID) {
//                ordersByAccountID.add(order);
//            }
//        }
//        return ordersByAccountID;
//    }
//
//     // Cập nhật thông tin đơn đặt hàng.
//
//    public void updateOrder(Order updatedOrder) {
//        int index = orders.indexOf(updatedOrder);
//        if (index != -1) {
//            orders.set(index, updatedOrder);
//        }
//    }
//
//
//     // Trả về tổng số sản phẩm trong các đơn đặt hàng của một tài khoản.
//
//
//    public int getTotalQuantityByAccountID(int accountID) {
//        int totalQuantity = 0;
//        for (Order order : orders) {
//            if (order.getIDAC() == accountID) {
//                totalQuantity += order.getTotalQuantity();
//            }
//        }
//        return totalQuantity;
//    }
//
//
//     // Thêm một chi tiết đơn hàng vào đơn đặt hàng.
//
//
//    public void addDetailOrderToOrder(Order order, DetailOrder detailOrder) {
//        order.addDetailOrder(detailOrder);
//    }
//
//
//    //Trả về danh sách chi tiết đơn hàng của một đơn đặt hàng.
//
//    public List<DetailOrder> getDetailOrdersByOrder(Order order) {
//        return order.getDetailOrders();
//    }
//}
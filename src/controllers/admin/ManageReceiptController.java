package controllers.admin;

import Utils.ConnectJDBCUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.*;

import Utils.SetIconUtil;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import views.admin.ManageReceiptUI;

public class ManageReceiptController {
    private ManageReceiptUI view;
    private Connection connection;

    public ManageReceiptController(ManageReceiptUI view) {
        this.view = view;
        connection = ConnectJDBCUtil.getConnection();
        loadReceipts();
        view.getBtnDetail().addActionListener(e -> showReceiptDetails());
        view.getBtnChart().addActionListener(e -> showRevenueChart());
        view.getBtnDailyTotal().addActionListener(e -> filterReceiptsByDate());
    }

    private void loadReceipts() {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM HD ORDER BY receipt_id ASC");
             ResultSet rs = stmt.executeQuery()) {

            DefaultTableModel tableModel = view.getTableModel();
            tableModel.setRowCount(0);

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getString(1), rs.getDate(2), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getDouble(3)
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi tải danh sách hóa đơn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showReceiptDetails() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow != -1) {
            String receiptId = (String) view.getTableModel().getValueAt(selectedRow, 0);
            try (PreparedStatement stmt = connection.prepareStatement(
                    "SELECT hms_id, sale_id, pur_quantity FROM HBHD WHERE receipt_id = ?")) {
                stmt.setString(1, receiptId);
                ResultSet rs = stmt.executeQuery();

                StringBuilder details = new StringBuilder("Chi tiết hóa đơn:\n");
                while (rs.next()) {
                    details.append("Hàng: ").append(rs.getString("hms_id"))
                            .append(", Mã KM: ").append(rs.getString("sale_id"))
                            .append(", Số lượng: ").append(rs.getInt("pur_quantity")).append("\n");
                }

                JOptionPane.showMessageDialog(view, details.toString(), "Chi tiết hóa đơn", JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(view, "Lỗi khi tải chi tiết hóa đơn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn hóa đơn!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void showRevenueChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        double[] monthlyRevenue = new double[12];
        for (int i = 0; i < 12; i++) {
            monthlyRevenue[i] = 0;
        }

        String query = "SELECT MONTH(ini_time) AS month, SUM(total_price) AS revenue " +
                "FROM HD GROUP BY MONTH(ini_time) ORDER BY MONTH(ini_time)";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int month = rs.getInt("month") - 1;
                double revenue = rs.getDouble("revenue");
                monthlyRevenue[month] = revenue;
            }

            for (int i = 0; i < 12; i++) {
                dataset.addValue(monthlyRevenue[i], "Doanh thu", " " + (i + 1));
            }

            JFreeChart barChart = ChartFactory.createBarChart(
                    "Biểu đồ doanh thu hàng tháng",
                    "Tháng",
                    "Doanh thu (triệu VND)",
                    dataset);

            ChartPanel chartPanel = new ChartPanel(barChart);
            chartPanel.setMouseWheelEnabled(true);

            JFrame chartFrame = new JFrame("Biểu đồ doanh thu");
            chartFrame.setIconImage(SetIconUtil.getIcon().getImage());
            chartFrame.setSize(800, 600);
            chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            chartFrame.add(chartPanel);
            chartFrame.setLocationRelativeTo(view);
            chartFrame.setVisible(true);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi tải dữ liệu biểu đồ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }


    private void filterReceiptsByDate() {
        String date = view.getSelectedDate();
        Double total = 0.0;
        if (date != null) {
            try (PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM HD WHERE DATE(ini_time) = ? ORDER BY receipt_id ASC")) {
                stmt.setString(1, date);
                ResultSet rs = stmt.executeQuery();

                DefaultTableModel tableModel = view.getTableModel();
                tableModel.setRowCount(0);

                while (rs.next()) {
                    tableModel.addRow(new Object[]{
                            rs.getString(1), rs.getDate(2), rs.getString(4),
                            rs.getString(5), rs.getString(6), rs.getDouble(3)
                    });
                    total += rs.getDouble(3);
                }
                view.setLblTotal("Tổng:" + total.toString());
                JOptionPane.showMessageDialog(view, "Đã lọc hóa đơn theo ngày " + date, "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(view, "Lỗi khi lọc hóa đơn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn ngày!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }
}
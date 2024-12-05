package views.admin;

import Utils.SetIconUtil;
import controllers.admin.ManageReceiptController;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class ManageReceiptUI extends JFrame {
    private MenuAdminUI menuAd;
    private JLabel lblTotal, lblDate;
    private JDatePickerImpl datePicker;
    private JButton btnDetail, btnChart, btnDailyTotal;
    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    public ManageReceiptUI(MenuAdminUI menuAd) {
        this.menuAd = menuAd;
        setTitle("Quản lý hóa đơn");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setIconImage(SetIconUtil.getIcon().getImage());


        // Buttons
        btnDetail = new JButton("Xem chi tiết hóa đơn");
        btnChart = new JButton("Biểu đồ thống kê");
        btnDailyTotal = new JButton("Xem đơn theo ngày");

        btnDetail.setBounds(20, 70, 150, 30);
        btnChart.setBounds(180, 70, 150, 30);
        btnDailyTotal.setBounds(240, 20, 150, 27);

        // Table
        scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 120, 550, 300);
        table = new JTable();
        scrollPane.setViewportView(table);

        String[] columns = {
                "Mã hóa đơn", "Thời gian tạo", "Mã PTTT",
                "Mã khách hàng", "Mã nhân viên", "Tổng tiền đơn"
        };
        tableModel = new DefaultTableModel(columns, 0);
        table.setModel(tableModel);


        lblTotal = new JLabel("Tổng:");
        lblTotal.setBounds(432, 410, 100, 30);

        lblDate = new JLabel("Lịch:");
        lblDate.setBounds(20, 20, 60, 30);

        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Hôm nay");
        p.put("text.month", "Tháng");
        p.put("text.year", "Năm");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
        datePicker.getComponent(0).setBackground(Color.WHITE);
        datePicker.setBounds(80, 20, 150, 30);

        add(lblTotal);
        add(lblDate);
        add(datePicker);
        add(btnDetail);
        add(btnChart);
        add(btnDailyTotal);
        add(scrollPane);

        addWindowListener(new WindowCloseListener());
        ManageReceiptController controller = new ManageReceiptController(this);
    }

    private class WindowCloseListener extends WindowAdapter {
        @Override
        public void windowClosed(WindowEvent e) {
            menuAd.setVisible(true);
        }
    }
    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTable() {
        return table;
    }

    public JButton getBtnDetail() {
        return btnDetail;
    }

    public JButton getBtnChart() {
        return btnChart;
    }

    public JButton getBtnDailyTotal() {
        return btnDailyTotal;
    }
    public void setLblTotal(String total){
        lblTotal.setText(total);
    }

    public String getSelectedDate() {
        Date selectedDate = (Date) datePicker.getModel().getValue();
        if (selectedDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(selectedDate);
        }
        return null;
    }
}

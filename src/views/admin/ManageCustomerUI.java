
package views.admin;

import Utils.SetIconUtil;
import controllers.admin.ManageCustomerController;
import controllers.admin.ManageStaffController;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.table.DefaultTableModel;


public class ManageCustomerUI extends JFrame {
        private MenuAdminUI menuAd;
        
        private JLabel lblTenKhachHang,lblDiaChi,lblSDT;
        private JTextField jtfTenKhachHang,jtfDiaChi,jtfSDT;
        private JButton btnThem,btnSua,btnXoa,btnNhapLai;
        private JTable table;
        private DefaultTableModel tableModel;
        private JScrollPane scrollPane;

    public ManageCustomerUI(MenuAdminUI menuAd) {
        this.menuAd = menuAd;

        setTitle("Quản lý khách hàng");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setIconImage(SetIconUtil.getIcon().getImage());
        setLayout(null);

        lblTenKhachHang=new JLabel("Tên khách hàng: ");
        lblSDT=new JLabel("Số điện thoại: ");
        lblDiaChi=new JLabel("Địa chỉ: ");
       
        jtfTenKhachHang=new JTextField();
        jtfSDT=new JTextField();
        jtfDiaChi=new JTextField();
        
        
        
        btnThem=new JButton("Thêm");
        btnSua=new JButton("Sửa");
        btnXoa=new JButton("Xóa");
        btnNhapLai=new JButton("Nhập lại");
        
               
        
        lblTenKhachHang.setBounds(50, 100,100,30);
        lblSDT.setBounds(50,150,100,30);
        lblDiaChi.setBounds(50,200,100,30);


        jtfTenKhachHang.setBounds(150, 100,200,30);
        jtfSDT.setBounds(150,150,200,30);
        jtfDiaChi.setBounds(150,200,200,30);
        
        btnThem.setBounds(50,300,100,30);
        btnSua.setBounds(180,300,100,30);
        btnXoa.setBounds(310,300,100,30);
        btnNhapLai.setBounds(440,300,100,30);
        
        scrollPane= new JScrollPane();
        scrollPane.setBounds(20,350,700,200);
        table=new JTable();
        scrollPane.add(table);
        scrollPane.setViewportView(table);
        
        String []name= {
            "Mã khách hàng", "Tên khách hàng","Số điện thoại","Địa chỉ","Điểm thân thiết"
        };
        tableModel=new DefaultTableModel(name ,0);
        table.setModel(tableModel);
        
        
        
        
//        jtfMaMau.setBounds(50, )
        
        add(lblTenKhachHang);
        add(lblSDT);
        add(lblDiaChi);
        add(jtfTenKhachHang);
        add(jtfSDT);
        add(jtfDiaChi);
        add(btnThem);
        add(btnSua);
        add(btnXoa);
        add(btnNhapLai);
        add(scrollPane);

        addWindowListener(new ManageCustomerUI.WindowCloseListener());
        ManageCustomerController controller = new ManageCustomerController(this);
    }

    private class WindowCloseListener extends WindowAdapter {
        @Override
        public void windowClosed(WindowEvent e) {
            menuAd.setVisible(true); 
        }
    }
    public void addCusButtonListener(ActionListener addStaffButton) {
        btnThem.addActionListener(addStaffButton);
    }

    //Add listener for "Cập nhật thông tin nhân viên" button
    public void updateCusButtonListener(ActionListener updateStaffButton) {
        btnSua.addActionListener(updateStaffButton);
    }

    public void deleteCusButtonListener(ActionListener deleteStaffButton) {
        btnXoa.addActionListener(deleteStaffButton);
    }

    public void re_enterCusButtonListener(ActionListener deleteStaffButton) {
        btnNhapLai.addActionListener(deleteStaffButton);
    }
    // Getters for form inputs and table model
    public JButton getBtnAddCustomer() {
        return btnThem;
    }

    public JTextField getTxtName() {
        return jtfTenKhachHang;
    }

    public JTextField getTxtAddress() {return jtfDiaChi;}

    public JTextField getTxtPhone() {
        return jtfSDT;
    }

    public DefaultTableModel getTableModel() {
        return (DefaultTableModel) table.getModel();
    }

    public JTable getTable() {
        return table;  // Trả về đối tượng JTable
    }
}

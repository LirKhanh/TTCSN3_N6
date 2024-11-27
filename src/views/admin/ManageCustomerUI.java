
package views.admin;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.table.DefaultTableModel;


public class ManageCustomerUI extends JFrame {
        private MenuAdminUI menuAd;
        
        private JLabel lblMaKhachHang, lblTenKhachHang,lblDiaChi,lblSDT,lblDiemThanThiet;
        private JTextField jtfMaKhachHang,jtfTenKhachHang,jtfDiaChi,jtfSDT,jtfDiemThanThiet;
        private JButton btnThem,btnSua,btnXoa,btnNhapLai;
        private JTable table;
        private DefaultTableModel tableModel;
        private JScrollPane scrollPane;

    public ManageCustomerUI(MenuAdminUI menuAd) {
        this.menuAd = menuAd;

        setTitle("Manage Customer");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        lblMaKhachHang= new JLabel("Mã khách hàng: ");
        lblTenKhachHang=new JLabel("Tên khách hàng: ");
        lblSDT=new JLabel("Số điện thoại: ");
        lblDiaChi=new JLabel("Địa chỉ: ");
        lblDiemThanThiet=new JLabel("Điểm thân thiết: ");
       
        jtfMaKhachHang=new JTextField();
        jtfTenKhachHang=new JTextField();
        jtfSDT=new JTextField();
        jtfDiaChi=new JTextField();
        jtfDiemThanThiet=new JTextField();
        
        
        
        btnThem=new JButton("Thêm");
        btnSua=new JButton("Sửa");
        btnXoa=new JButton("Xóa");
        btnNhapLai=new JButton("Nhập lại");
        
               
        
        lblMaKhachHang.setBounds(50, 50, 100, 30);
        lblTenKhachHang.setBounds(50, 100,100,30);
        lblSDT.setBounds(50,150,100,30);
        lblDiaChi.setBounds(50,200,100,30);
        lblDiemThanThiet.setBounds(50,250,100,30);
        
        
        jtfMaKhachHang.setBounds(150, 50,200,30);
        jtfTenKhachHang.setBounds(150, 100,200,30);
        jtfSDT.setBounds(150,150,200,30);
        jtfDiaChi.setBounds(150,200,200,30);
        jtfDiemThanThiet.setBounds(150,250,200,30);
        
        
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
        
        add(lblMaKhachHang);
        add(lblTenKhachHang);
        add(lblSDT);
        add(lblDiaChi);
        add(lblDiemThanThiet);
        add(jtfMaKhachHang);
        add(jtfTenKhachHang);
        add(jtfSDT);
        add(jtfDiaChi);
        add(jtfDiemThanThiet);
        add(btnThem);
        add(btnSua);
        add(btnXoa);
        add(btnNhapLai);
        add(scrollPane);

        addWindowListener(new WindowCloseListener());
    }

    private class WindowCloseListener extends WindowAdapter {
        @Override
        public void windowClosed(WindowEvent e) {
            menuAd.setVisible(true); 
        }
    }
}

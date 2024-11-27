
package views.admin;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ManageStaffUI extends JFrame{
        private MenuAdminUI menuAd;
        
        private JLabel lblMaNhanVien, lblTenNhanVien,lblTaiKhoan,lblMatKhau;
        private JTextField jtfMaNhanVien,jtfTenNhanVien,jtfTaiKhoan,jtfMatKhau;
        private JButton btnThem,btnSua,btnXoa,btnNhapLai;
        private JTable table;
        private DefaultTableModel tableModel;
        private JScrollPane scrollPane;
        
        public ManageStaffUI(MenuAdminUI menuAd) {
        this.menuAd = menuAd;

        setTitle("Manage Staff");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        lblMaNhanVien= new JLabel("Mã nhân viên: ");
        lblTenNhanVien=new JLabel("Tên nhân viên: ");
        lblTaiKhoan=new JLabel("Tài khoản: ");
        lblMatKhau=new JLabel("Mật khẩu: ");
        
        jtfMaNhanVien=new JTextField();
        jtfTenNhanVien=new JTextField();
        jtfTaiKhoan=new JTextField();
        jtfMatKhau=new JTextField(); 
        
        btnThem=new JButton("Thêm");
        btnSua=new JButton("Sửa");
        btnXoa=new JButton("Xóa");
        btnNhapLai=new JButton("Nhập lại");
        
               
        lblMaNhanVien.setBounds(50, 50, 100, 30);
        lblTenNhanVien.setBounds(50, 100,100,30);
        lblTaiKhoan.setBounds(50,150,100,30);
        lblMatKhau.setBounds(50,200,100,30);
        
        
        jtfMaNhanVien.setBounds(150, 50,200,30);
        jtfTenNhanVien.setBounds(150, 100,200,30);
        jtfTaiKhoan.setBounds(150,150,200,30);
        jtfMatKhau.setBounds(150,200,200,30);
        
        
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
            "Mã nhân viên", "Tên nhân viên","Tài khoản","Mật khẩu"
        };
        tableModel=new DefaultTableModel(name ,0);
        table.setModel(tableModel);
        
      
       
        
        add(lblMaNhanVien);
        add(lblTenNhanVien);
        add(lblTaiKhoan);
        add(lblMatKhau);
        add(jtfMaNhanVien);
        add(jtfTenNhanVien);
        add(jtfTaiKhoan);
        add(jtfMatKhau);
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

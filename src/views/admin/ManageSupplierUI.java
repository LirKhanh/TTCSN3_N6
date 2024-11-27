
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

public class ManageSupplierUI extends JFrame{
           private MenuAdminUI menuAd;
    
        private JLabel lblMaNhaCungCap, lblTenNhaCungCap,lblSDT ;
        private JTextField jtfMaNhaCungCap,jtfTenNhaCungCap,jtfSDT;
        private JButton btnThem,btnSua,btnXoa,btnNhapLai;
        private JTable table;
        private DefaultTableModel tableModel;
        private JScrollPane scrollPane;

        public ManageSupplierUI(MenuAdminUI menuAd) {
            this.menuAd = menuAd;

            setTitle("Manage Supplier");
            setSize(600, 500);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLayout(null);

            lblMaNhaCungCap= new JLabel("Mã nhà cung cấp: ");
            lblTenNhaCungCap=new JLabel("Tên nhà cung cấp: ");
            lblSDT=new JLabel("Số điện thoại: ");
            
            
            jtfMaNhaCungCap=new JTextField();
            jtfTenNhaCungCap=new JTextField();
            jtfSDT=new JTextField();

            btnThem=new JButton("Thêm");
            btnSua=new JButton("Sửa");
            btnXoa=new JButton("Xóa");
            btnNhapLai=new JButton("Nhập lại");

            lblMaNhaCungCap.setBounds(50, 50, 150, 30);
            lblTenNhaCungCap.setBounds(50, 100,150,30);
            lblSDT.setBounds(50,150 ,150,30);
            
            jtfMaNhaCungCap.setBounds(200, 50,200,30);
            jtfTenNhaCungCap.setBounds(200, 100,200,30);
            jtfSDT.setBounds(200, 150, 200, 30);

            btnThem.setBounds(50,250,100,30);
            btnSua.setBounds(180,250,100,30);
            btnXoa.setBounds(310,250,100,30);
            btnNhapLai.setBounds(440,250,100,30);

            scrollPane= new JScrollPane();
            scrollPane.setBounds(20,300,450,200);
            table=new JTable();
            scrollPane.add(table);
            scrollPane.setViewportView(table);

            String []name= {
                "Mã nhà cung cấp", "Tên nhà cung cấp","Số điện thoại"
            };
            tableModel=new DefaultTableModel(name ,0);
            table.setModel(tableModel);




    //        jtfMaMau.setBounds(50, )

            add(lblMaNhaCungCap);
            add(lblTenNhaCungCap);
            add(lblSDT);
            add(jtfMaNhaCungCap);
            add(jtfTenNhaCungCap);
            add(jtfSDT);
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

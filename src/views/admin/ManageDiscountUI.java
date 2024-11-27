
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

public class ManageDiscountUI extends JFrame {
        private MenuAdminUI menuAd;
    
        private JLabel lblMaDotGiamGia, lblTenDotGiamGia,lblPhanTramGiamGia ;
        private JTextField jtfMaDotGiamGia,jtfTenDotGiamGia,jtfPhanTramGiamGia;
        private JButton btnThem,btnSua,btnXoa,btnNhapLai;
        private JTable table;
        private DefaultTableModel tableModel;
        private JScrollPane scrollPane;

        public ManageDiscountUI(MenuAdminUI menuAd) {
            this.menuAd = menuAd;

            setTitle("Manage Discount");
            setSize(600, 500);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLayout(null);

            lblMaDotGiamGia= new JLabel("Mã đợt giảm giá: ");
            lblTenDotGiamGia=new JLabel("Tên đợt giảm giá: ");
            lblPhanTramGiamGia=new JLabel("Phần trăm giảm giá: ");
            
            
            jtfMaDotGiamGia=new JTextField();
            jtfTenDotGiamGia=new JTextField();
            jtfPhanTramGiamGia=new JTextField();

            btnThem=new JButton("Thêm");
            btnSua=new JButton("Sửa");
            btnXoa=new JButton("Xóa");
            btnNhapLai=new JButton("Nhập lại");

            lblMaDotGiamGia.setBounds(50, 50, 150, 30);
            lblTenDotGiamGia.setBounds(50, 100,150,30);
            lblPhanTramGiamGia.setBounds(50,150 ,150,30);
            
            jtfMaDotGiamGia.setBounds(200, 50,200,30);
            jtfTenDotGiamGia.setBounds(200, 100,200,30);
            jtfPhanTramGiamGia.setBounds(200, 150, 200, 30);

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
                "Mã đợt giảm giá", "Tên đợt giảm giá","Phần trăm giảm giá"
            };
            tableModel=new DefaultTableModel(name ,0);
            table.setModel(tableModel);




    //        jtfMaMau.setBounds(50, )

            add(lblMaDotGiamGia);
            add(lblTenDotGiamGia);
            add(lblPhanTramGiamGia);
            add(jtfMaDotGiamGia);
            add(jtfTenDotGiamGia);
            add(jtfPhanTramGiamGia);
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


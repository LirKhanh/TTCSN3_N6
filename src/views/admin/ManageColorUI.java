package views.admin;

import java.awt.Label;
import java.awt.ScrollPane;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.table.DefaultTableModel;

public class ManageColorUI extends JFrame {
    private MenuAdminUI menuAd;
    
    private JLabel lblMaMau, lblTenMau;
    private JTextField jtfMaMau,jtfTenMau;
    private JButton btnThem,btnSua,btnXoa,btnNhapLai;
    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    
    public ManageColorUI(MenuAdminUI menuAd) {
        this.menuAd = menuAd;

        setTitle("Manage Color");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        lblMaMau= new JLabel("Mã màu: ");
        lblTenMau=new JLabel("Tên màu: ");
       
        jtfMaMau=new JTextField();
        jtfTenMau=new JTextField();
        
        btnThem=new JButton("Thêm");
        btnSua=new JButton("Sửa");
        btnXoa=new JButton("Xóa");
        btnNhapLai=new JButton("Nhập lại");
        
               
        
        lblMaMau.setBounds(50, 50, 100, 30);
        lblTenMau.setBounds(50, 100,100,30);
        
        jtfMaMau.setBounds(150, 50,200,30);
        jtfTenMau.setBounds(150, 100,200,30);
        
        btnThem.setBounds(50,150,100,30);
        btnSua.setBounds(180,150,100,30);
        btnXoa.setBounds(310,150,100,30);
        btnNhapLai.setBounds(440,150,100,30);
        
        scrollPane= new JScrollPane();
        scrollPane.setBounds(20,200,450,200);
        table=new JTable();
        scrollPane.add(table);
        scrollPane.setViewportView(table);
        
        String []name= {
            "Mã màu", "Tên màu"
        };
        tableModel=new DefaultTableModel(name ,0);
        table.setModel(tableModel);
        
        
        
        
//        jtfMaMau.setBounds(50, )
        
        add(lblMaMau);
        add(lblTenMau);
        add(jtfMaMau);
        add(jtfTenMau);
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

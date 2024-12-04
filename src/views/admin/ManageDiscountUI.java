
package views.admin;

import Utils.SetIconUtil;
import java.awt.event.ActionListener;
import controllers.admin.ManageDiscountController;
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
    
        private JLabel lblTenDotGiamGia,lblPhanTramGiamGia ;
        private JTextField jtfTenDotGiamGia,jtfPhanTramGiamGia;
        private JButton btnThem,btnSua,btnXoa,btnNhapLai;
        private JTable table;
        private DefaultTableModel tableModel;
        private JScrollPane scrollPane;

        public ManageDiscountUI(MenuAdminUI menuAd) {
            this.menuAd = menuAd;

            setTitle("Quản lý đợt giảm giá");
            setSize(600, 500);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setIconImage(SetIconUtil.getIcon().getImage());
            setLayout(null);

            lblTenDotGiamGia=new JLabel("Tên đợt giảm giá: ");
            lblPhanTramGiamGia=new JLabel("Phần trăm giảm giá: ");
            
            
            jtfTenDotGiamGia=new JTextField();
            jtfPhanTramGiamGia=new JTextField();

            btnThem=new JButton("Thêm");
            btnSua=new JButton("Sửa");
            btnXoa=new JButton("Xóa");
            btnNhapLai=new JButton("Nhập lại");

            lblTenDotGiamGia.setBounds(50, 100,150,30);
            lblPhanTramGiamGia.setBounds(50,150 ,150,30);
            
            jtfTenDotGiamGia.setBounds(200, 100,200,30);
            jtfPhanTramGiamGia.setBounds(200, 150, 200, 30);

            btnThem.setBounds(50,250,100,30);
            btnSua.setBounds(180,250,100,30);
            btnXoa.setBounds(310,250,100,30);
            btnNhapLai.setBounds(440,250,100,30);

            scrollPane= new JScrollPane();
          //scrollPane.setBounds(20,300,450,200);
            scrollPane.setBounds(20, 300, 550, 150);
            table=new JTable();
            scrollPane.add(table);
            scrollPane.setViewportView(table);

            String []name= {
                "Mã đợt giảm giá", "Tên đợt giảm giá","Phần trăm giảm giá"
            };
            tableModel=new DefaultTableModel(name ,0);
            table.setModel(tableModel);




    //        jtfMaMau.setBounds(50, )

            add(lblTenDotGiamGia);
            add(lblPhanTramGiamGia);
            add(jtfTenDotGiamGia);
            add(jtfPhanTramGiamGia);
            add(btnThem);
            add(btnSua);
            add(btnXoa);
            add(btnNhapLai);
            add(scrollPane);
            

            addWindowListener(new WindowCloseListener());
            ManageDiscountController controller = new ManageDiscountController(this);
        }

        private class WindowCloseListener extends WindowAdapter {
            @Override
            public void windowClosed(WindowEvent e) {
                menuAd.setVisible(true); 
            }
        }
       
    public void addDisButtonListener(ActionListener addDisButton) {
        btnThem.addActionListener(addDisButton);
    }

    public void updateDisButtonListener(ActionListener updateDisButton) {
        btnSua.addActionListener(updateDisButton);
    }

    public void deleteDisButtonListener(ActionListener deleteDisButton) {
        btnXoa.addActionListener(deleteDisButton);
    }

    public void re_enterCusButtonListener(ActionListener deleteDisButton) {
        btnNhapLai.addActionListener(deleteDisButton);
    }
    // Getters for form inputs and table model
    public JButton getBtnAddDiscount() {
        return btnThem;
    }

    public JTextField getTxtName() {
        return jtfTenDotGiamGia;
    }

    public JTextField getTxtPercent() {
        return jtfPhanTramGiamGia;
    }

    public DefaultTableModel getTableModel() {
        return (DefaultTableModel) table.getModel();
    }

    public JTable getTable() {
        return table;  // Trả về đối tượng JTable
    }
    
}        


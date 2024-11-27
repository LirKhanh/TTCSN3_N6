
package views.admin;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ManageSizeUI extends JFrame {
        private MenuAdminUI menuAd;
        
        private JLabel lblMaKichCo, lblTenKichCo;
        private JTextField jtfMaKichCo,jtfTenKichCo;
        private JButton btnThem,btnSua,btnXoa,btnNhapLai;
        private JTable table;
        private DefaultTableModel tableModel;
        private JScrollPane scrollPane;
        
        public ManageSizeUI(MenuAdminUI menuAdmin)
        {
            this.menuAd=menuAdmin;
            setTitle("Manage Size");
            setSize(600, 500);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLayout(null);
            
       

            lblMaKichCo= new JLabel("Mã Kích Cỡ: ");
            lblTenKichCo=new JLabel("Tên Kích Cỡ: ");

            jtfMaKichCo=new JTextField();
            jtfTenKichCo=new JTextField();

            btnThem=new JButton("Thêm");
            btnSua=new JButton("Sửa");
            btnXoa=new JButton("Xóa");
            btnNhapLai=new JButton("Nhập lại");



            lblMaKichCo.setBounds(50, 50, 100, 30);
            lblTenKichCo.setBounds(50, 100,100,30);

            jtfMaKichCo.setBounds(150, 50,200,30);
            jtfTenKichCo.setBounds(150, 100,200,30);

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
                "Mã kích cỡ", "Tên kích cỡ"
            };
            tableModel=new DefaultTableModel(name ,0);
            table.setModel(tableModel);




    //        jtfMaMau.setBounds(50, )

            add(lblMaKichCo);
            add(lblTenKichCo);
            add(jtfMaKichCo);
            add(jtfTenKichCo);
            add(btnThem);
            add(btnSua);
            add(btnXoa);
            add(btnNhapLai);
            add(scrollPane);

            
            addWindowListener(new WindowCloseListener());
            
        }
        private class WindowCloseListener extends WindowAdapter{
             @Override
             public void windowClosed(WindowEvent e){
                 menuAd.setVisible(true);
             }
        }
}

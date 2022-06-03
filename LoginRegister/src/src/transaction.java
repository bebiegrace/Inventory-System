/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import static src.Unapproveusers.jTable10;
import static src.inventorypage.jTable4;

/**
 *
 * @author 1styrGroupB
 */
public class transaction extends javax.swing.JFrame {

    /**
     * Creates new form cashiertransaction
     */
     My_Connection myc;
    public transaction() {
        initComponents();
        Connect();
        pstaff();
        pscashier();
//         showbydate("");
          myc = new My_Connection();
    }
     public transaction(String username) {
        initComponents();
        jusername.setText(username);
        Connect();
         pstaff();
        pscashier();
//         showbydate("");
          myc = new My_Connection();
    }
    
     Connection con;
    PreparedStatement pst;
    PreparedStatement pst1;
    ResultSet rs;
    DefaultTableModel df;
    
    public void Connect()
  {
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/bebieinventorysystem", "root", "");
    } catch (ClassNotFoundException ex){
         Logger.getLogger(bookshop.class.getName()).log(Level.SEVERE, null, ex);
    }   catch (SQLException ex) {
            Logger.getLogger(bookshop.class.getName()).log(Level.SEVERE, null, ex);
        }
    
  }
    
    public class usersitem{
        int id;
        String name;
        public usersitem(int id, String name)
        {
            this.id =id;
            this.name = name;
            
        }
        public String toString(){
            return name;
        }
    }
    public void pstaff(){
    try{
        pst=con.prepareStatement("SELECT * FROM users where role= 'Staff'");
        rs = pst.executeQuery();
        perstaff.removeAllItems();
        
        while (rs.next())
        {
            perstaff.addItem(new usersitem(rs.getInt(1), rs.getString(2)));
        }
    }   catch (SQLException ex) {
            Logger.getLogger(transaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
     public void pscashier(){
    try{
        pst=con.prepareStatement("SELECT * FROM users where role= 'Cashier'");
        rs = pst.executeQuery();
        percashier.removeAllItems();
        
        while (rs.next())
        {
            percashier.addItem(new usersitem(rs.getInt(1), rs.getString(2)));
        }
    }   catch (SQLException ex) {
            Logger.getLogger(transaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
     
      public void staff(){

             DefaultTableModel df = (DefaultTableModel)jTable16.getModel();
             df.setRowCount(0);

         try {
                Statement st = con.createStatement();
                String query1 = "select * from `stransaction` where UserID= 38";
                ResultSet rs1 = st.executeQuery(query1);
                //

                while(rs1.next()){
                    //data wil added until finished..
                    String id = rs1.getString("id");
                    String hh = rs1.getString("InventoryID");
                    String des1 = rs1.getString("quantity");
                    String trr = rs1.getString("type_transaction");
                    String buy = rs1.getString("UserID");
                    String sell1 = rs1.getString("date");
                    String pro = rs1.getString("time");

                    //string array for store data into jtable..
                    String tbData[] = {id,hh,des1, trr ,buy,sell1,pro};
                    DefaultTableModel tblModel = (DefaultTableModel)jTable16.getModel();

                    //add string array data into jtable..

                    tblModel.addRow(tbData);
                    
                    //                    invetorytransac();
                }

//                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(cashierusers.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    }
        public void staff1(){

             DefaultTableModel df = (DefaultTableModel)jTable16.getModel();
             df.setRowCount(0);

         try {
                Statement st = con.createStatement();
                String query1 = "select * from `stransaction` where UserID= 41";
                ResultSet rs1 = st.executeQuery(query1);
                //

                while(rs1.next()){
                    //data wil added until finished..
                    String id = rs1.getString("id");
                    String hh = rs1.getString("InventoryID");
                    String des1 = rs1.getString("quantity");
                    String trr = rs1.getString("type_transaction");
                    String buy = rs1.getString("UserID");
                    String sell1 = rs1.getString("date");
                    String pro = rs1.getString("time");

                    //string array for store data into jtable..
                    String tbData[] = {id,hh,des1, trr ,buy,sell1,pro};
                    DefaultTableModel tblModel = (DefaultTableModel)jTable16.getModel();

                    //add string array data into jtable..

                    tblModel.addRow(tbData);
                    
                    //                    invetorytransac();
                }

//                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(cashierusers.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    }
        public void cashier(){

             DefaultTableModel df = (DefaultTableModel)jTable18.getModel();
             df.setRowCount(0);

         try {
                Statement st = con.createStatement();
                String query1 = "select * from `ctransaction` where UserID= 34";
                ResultSet rs1 = st.executeQuery(query1);
                //

                while(rs1.next()){
                    //data wil added until finished..
                    String id = rs1.getString("ctrans_id");
                    String hh = rs1.getString("InventoryID");
                    String des1 = rs1.getString("quantity");
                    String trr = rs1.getString("type_transaction");
                    String buy = rs1.getString("UserID");
                    String sell1 = rs1.getString("date");
                    String pro = rs1.getString("time");

                    //string array for store data into jtable..
                    String tbData[] = {id,hh,des1, trr ,buy,sell1,pro};
                    DefaultTableModel tblModel = (DefaultTableModel)jTable18.getModel();

                    //add string array data into jtable..

                    tblModel.addRow(tbData);
                    
                    //                    invetorytransac();
                }

//                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(cashierusers.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    }
        public void cashier1(){

             DefaultTableModel df = (DefaultTableModel)jTable18.getModel();
             df.setRowCount(0);

         try {
                Statement st = con.createStatement();
                String query1 = "select * from `ctransaction` where UserID= 40";
                ResultSet rs1 = st.executeQuery(query1);
                //

                while(rs1.next()){
                    //data wil added until finished..
                    String id = rs1.getString("ctrans_id");
                    String hh = rs1.getString("InventoryID");
                    String des1 = rs1.getString("quantity");
                    String trr = rs1.getString("type_transaction");
                    String buy = rs1.getString("UserID");
                    String sell1 = rs1.getString("date");
                    String pro = rs1.getString("time");

                    //string array for store data into jtable..
                    String tbData[] = {id,hh,des1, trr ,buy,sell1,pro};
                    DefaultTableModel tblModel = (DefaultTableModel)jTable18.getModel();

                    //add string array data into jtable..

                    tblModel.addRow(tbData);
                    
                    //                    invetorytransac();
                }

//                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(cashierusers.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    }
//        public void showbydate(String da){
            
              
//             DefaultTableModel df = (DefaultTableModel)jTable16.getModel();
//             df.setRowCount(0);
////             int row=jTable16.getSelectedRow();
////             String tab=jTable16.getModel().getValueAt(row, 5).toString();
////             Date dtg = jDateChooser1.getDate();
//
//         try {
//                Statement st = con.createStatement();
//                String query1 = "SELECT * FROM `stransaction`";
//                ResultSet rs1 = st.executeQuery(query1);
//                while(rs1.next()){
//                    String id = rs1.getString("id");
//                    String hh = rs1.getString("InventoryID");
//                    String des1 = rs1.getString("quantity");
//                    String trr = rs1.getString("type_transaction");
//                    String buy = rs1.getString("UserID");
//                    String sell1 = rs1.getString("date");
//                    String pro = rs1.getString("time");
//
//                    //string array for store data into jtable..
//                    String tbData[] = {id,hh,des1, trr ,buy,sell1,pro};
//                    DefaultTableModel tblModel = (DefaultTableModel)jTable16.getModel();
//
//                    //add string array data into jtable..
//
//                    tblModel.addRow(tbData);
//                    
//                    //                    invetorytransac();
//                }
//
////                con.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(cashierusers.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
            
//          Connection cone = myc.getConn();
//          PreparedStatement pst;
//          
//          ResultSet rs;
//         try {
//             
//             if(da.equals(""))
//             {
////                  Statement st = con.createStatement();
//                  pst = cone.prepareStatement("select * from `stransaction`");
//                  pst.setString(1, da);
//   
//                 rs= pst.executeQuery();
//            
//                 DefaultTableModel  model =(DefaultTableModel)jTable16.getModel();
//                 Object[] row;
//                 
//                 while(rs.next()){
//                     row = new Object[7];
//                     row[0] = rs.getInt(1);
//                     row[1] = rs.getInt(2);
//                     row[2] = rs.getInt(3);
//                     row[3] = rs.getString(4);
//                     row[4] = rs.getInt(5);
//                     row[5] = rs.getString(6);
//                     row[6] = rs.getString(7);
//                     
//                     model.addRow(row);
//     
//                 }
//             }
//         } catch (Exception ex) {
//                Logger.getLogger(cashierusers.class.getName()).log(Level.SEVERE, null, ex);
//            }
//    
//    }
//      
//        


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jusername = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable16 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable18 = new javax.swing.JTable();
        perstaff = new javax.swing.JComboBox();
        percashier = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton4 = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 204, 204));
        jLabel9.setText("Approve  New Users");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 204, 204));
        jLabel11.setText("Add User Admin");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 204, 204));
        jLabel13.setText("Sign-out");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/flowercon.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jusername.setBackground(new java.awt.Color(204, 204, 204));
        jusername.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jusername.setForeground(new java.awt.Color(255, 0, 0));
        jusername.setText("Username");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 204, 204));
        jLabel16.setText("Home");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/avatarbebie.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(45, 45, 45)
                .addComponent(jLabel11)
                .addGap(61, 61, 61)
                .addComponent(jLabel13)
                .addGap(100, 100, 100)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jusername, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(jLabel16))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jusername)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jTable16.setBackground(new java.awt.Color(255, 204, 204));
        jTable16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTable16.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "inventory_id", "quantity", "transaction_type", "User id", "Date", "time"
            }
        ));
        jScrollPane2.setViewportView(jTable16);

        jLabel6.setBackground(new java.awt.Color(255, 102, 102));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Staff Transactions");
        jLabel6.setOpaque(true);
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(255, 102, 102));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Cashier Transactions");
        jLabel7.setOpaque(true);
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jTable18.setBackground(new java.awt.Color(255, 204, 204));
        jTable18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTable18.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "inventory_id", "quantity", "transaction_type", "User id", "Date", "time"
            }
        ));
        jScrollPane4.setViewportView(jTable18);

        perstaff.setBackground(new java.awt.Color(255, 204, 204));
        perstaff.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        perstaff.setMaximumRowCount(20);

        percashier.setBackground(new java.awt.Color(255, 204, 204));
        percashier.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        percashier.setMaximumRowCount(20);

        jButton1.setBackground(new java.awt.Color(255, 255, 153));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 0, 51));
        jButton1.setText("View");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 153));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 0, 0));
        jButton2.setText("View");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 153));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 0, 51));
        jButton3.setText("View");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jDateChooser1.setDateFormatString("yyyy/MM/dd");

        jDateChooser2.setDateFormatString("yyyy/MM/dd");

        jButton4.setBackground(new java.awt.Color(255, 255, 153));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 0, 0));
        jButton4.setText("View");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4))
                        .addGap(59, 59, 59)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                    .addComponent(perstaff, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(percashier, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2)
                            .addComponent(jButton4)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jButton3))))
                .addGap(123, 123, 123))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(perstaff, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(33, 33, 33)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(57, 57, 57)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(percashier, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 1145, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        LoginUsers cashregis = new  LoginUsers();
        cashregis.setVisible(true);
        cashregis.pack();
        cashregis.setLocationRelativeTo(null);
        cashregis.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
        LoginUsers main = new  LoginUsers();
        main.setVisible(true);
        main.pack();
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:

        adduser admin = new adduser(jusername.getText());
        admin.show();
        dispose();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:


        Unapproveusers admin = new Unapproveusers(jusername.getText());
        admin.show();
        dispose();

        try {
            Statement st = con.createStatement();
            String query1 = "select * from `user_applicant`";
            ResultSet rs1 = st.executeQuery(query1);

            while(rs1.next()){
                //data wil added until finished..
                String bid = rs1.getString("id");
                String username1 = rs1.getString("username");
                String password1 = rs1.getString("password");
                String email_id1 = rs1.getString("email_id");
                String gender1 = rs1.getString("gender");
                String age1 = rs1.getString("age");
                String rol= rs1.getString("role");
                String sts = rs1.getString("status");

                //string array for store data into jtable..
                String tbData[] = {bid,username1,password1,email_id1,gender1,age1,rol,sts};

                DefaultTableModel modelu = (DefaultTableModel)jTable10.getModel();

                //add string array data into jtable..

                modelu.addRow(tbData);

            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(adduser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        
          DefaultTableModel model = (DefaultTableModel)jTable16.getModel();
          TableRowSorter<DefaultTableModel> tr= new TableRowSorter<DefaultTableModel>(model);
          jTable16.setRowSorter(tr);
             DefaultTableModel df = (DefaultTableModel)jTable16.getModel();
             df.setRowCount(0);

        
         try {
                Statement st = con.createStatement();
                String query1 = "select * from `stransaction`";
                ResultSet rs1 = st.executeQuery(query1);
                //

                while(rs1.next()){
                    //data wil added until finished..
                    String id = rs1.getString("id");
                    String hh = rs1.getString("InventoryID");
                    String des1 = rs1.getString("quantity");
                    String trr = rs1.getString("type_transaction");
                    String buy = rs1.getString("UserID");
                    String sell1 = rs1.getString("date");
                    String pro = rs1.getString("time");

                    //string array for store data into jtable..
                    String tbData[] = {id,hh,des1, trr ,buy,sell1,pro};
                    DefaultTableModel tblModel = (DefaultTableModel)jTable16.getModel();

                    //add string array data into jtable..

                    tblModel.addRow(tbData);
                    
                    //                    invetorytransac();
                }

//                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(cashierusers.class.getName()).log(Level.SEVERE, null, ex);
                            }
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        
        DefaultTableModel model = (DefaultTableModel)jTable18.getModel();
        TableRowSorter<DefaultTableModel> tr= new TableRowSorter<DefaultTableModel>(model);
        jTable18.setRowSorter(tr);
         DefaultTableModel df = (DefaultTableModel)jTable18.getModel();
         df.setRowCount(0);
        try {
            Statement st = con.createStatement();
            String query1 = "select * from `ctransaction`";
            ResultSet rs1 = st.executeQuery(query1);

            while (rs1.next()) {
                //data wil added until finished..
                String id = rs1.getString("ctrans_id");
                String hh = rs1.getString("InventoryID");
                String des1 = rs1.getString("quantity");
                String trr = rs1.getString("type_transaction");
                String buy = rs1.getString("UserID");
                String sell1 = rs1.getString("date");
                String pro = rs1.getString("time");

                //string array for store data into jtable..
                String tbData[] = {id, hh, des1, trr, buy, sell1, pro};
                DefaultTableModel tblModel = (DefaultTableModel) jTable18.getModel();

                //add string array data into jtable..
                tblModel.addRow(tbData);

            }

//            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(cashierusers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        // TODO add your handling code here:

        adminpage admin = new adminpage(jusername.getText());
        admin.show();
        dispose();

    }//GEN-LAST:event_jLabel16MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
  
        if (perstaff.getSelectedIndex() == 0) {
            staff();
        }else if(perstaff.getSelectedIndex() == 1) {
            staff1();
        }
        DefaultTableModel model = (DefaultTableModel)jTable16.getModel();
        TableRowSorter<DefaultTableModel> tr= new TableRowSorter<DefaultTableModel>(model);
        jTable16.setRowSorter(tr);


        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         if (percashier.getSelectedIndex() == 0) {
           cashier();
        }else if(percashier.getSelectedIndex() == 1) {
            cashier1();
        }
         
        DefaultTableModel model = (DefaultTableModel)jTable18.getModel();
        TableRowSorter<DefaultTableModel> tr= new TableRowSorter<DefaultTableModel>(model);
        jTable18.setRowSorter(tr);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
//
        try{
//        jTable16.setModel(new DefaultTableModel(null, new Object[]{"id","InventoryID","quantity","type_transaction","UserID","date","time"}));
        SimpleDateFormat dATE = new SimpleDateFormat("yyyy/MM/dd");
        String date1 = dATE.format(jDateChooser1.getDate());
 
//        showbydate(date1);
        DefaultTableModel model = (DefaultTableModel)jTable16.getModel();
        TableRowSorter<DefaultTableModel> tr= new TableRowSorter<DefaultTableModel>(model);
        jTable16.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(date1.trim()));
//        
        }catch (Exception e){
       
        } 
       
        
        
        
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
         try{
//        jTable16.setModel(new DefaultTableModel(null, new Object[]{"id","InventoryID","quantity","type_transaction","UserID","date","time"}));
        SimpleDateFormat dATE = new SimpleDateFormat("yyyy/MM/dd");
        String date1 = dATE.format(jDateChooser2.getDate());
 
//        showbydate(date1);
        DefaultTableModel model = (DefaultTableModel)jTable18.getModel();
        TableRowSorter<DefaultTableModel> tr= new TableRowSorter<DefaultTableModel>(model);
        jTable18.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(date1.trim()));
//        
        }catch (Exception e){
       
        } 
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new transaction().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable16;
    private javax.swing.JTable jTable18;
    private javax.swing.JLabel jusername;
    private javax.swing.JComboBox percashier;
    private javax.swing.JComboBox perstaff;
    // End of variables declaration//GEN-END:variables
}

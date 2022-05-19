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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static src.ApprovedUsers.jTable11;

import static src.category.jTable6;
import static src.categoryinventory.jTable8;

/**
 *
 * @author 1styrGroupB
 */
public class Unapproveusers extends javax.swing.JFrame {

    /**
     * Creates new form Unapproveusers
     */
    public Unapproveusers() {
        initComponents();
        Connect();
//        toapproveuser();
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
    
     public static void AddRowToJcategoryTable(Object[] dataRow)   
     {
       DefaultTableModel model10 = (DefaultTableModel)jTable10.getModel(); 
       model10.addRow(dataRow);
     }
     
      public static void AddRowToJapprovedTable(Object[] dataRow)   
     {
       DefaultTableModel model11 = (DefaultTableModel)jTable11.getModel(); 
       model11.addRow(dataRow);
     }
      public void toapproveuser()
  {
      
      
       String username = jname.getText();
       String password = jpassword.getText();
       String email_id = jemailid.getText();
       String gender = jgender.getText();
       String age = jage.getText();
     
       String role1;
       role1=jrole.getSelectedItem().toString();
      
       String status;
       status=jstatus.getSelectedItem().toString();
      
      try{
        
          String query ="insert into `users`(username,password,email_id,gender,age,role,status)values(?,?,?,?,?,?,?);";
          pst = con.prepareStatement(query);
          
          pst.setString(1, username);
          pst.setString(2, password);
          pst.setString(3, email_id);
          pst.setString(4, gender);
          pst.setString(5, age);
//          pst.setInt(5, Integer.valueOf(jage.getText()));
       
          pst.setString(6, role1);
          
      
          pst.setString(7, status);
          pst.executeUpdate();

          
          
          
          
//          pst.setString(1, username);
//          pst.setString(2, password);
//          pst.setString(3, email_id);
//          pst.setString(4, gender);
//          pst.setString(5, age);
////          pst.setInt(5, Integer.valueOf(jage.getText()));
//          pst.setString(6, role1);
//          pst.setString(7, status);
//          pst.executeUpdate();

//           int i=pst.executeUpdate();
           
            
//            JOptionPane.showMessageDialog(null, "inserted");
                  
//            else{
//                      JOptionPane.showMessageDialog(null, username+"  You are NOT! successfully registered as Admin User!" + "Failed with User id as Email id"+email_id); 
//                    }
          
      }catch(SQLException ex){
           Logger.getLogger(bookshop.class.getName()).log(Level.SEVERE, null, ex);
      }
  
  }
//      
 
       public void updateuserapplicant(){
        String sql ="select from `user_applicant`";
        try{
            pst=con.prepareStatement(sql);
            rs = pst.executeQuery();
//            jTable3.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
//           JOptionPane.showMessageDialog(null, e);
        }finally{
        try{
            rs.close();
            pst.close();
            
        }catch(Exception e){
            
        }
    }
       }
        public void todeclineusers(){
        String sql ="select from `user_applicant`";
        try{
            pst=con.prepareStatement(sql);
            rs = pst.executeQuery();
//            jTable3.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
//           JOptionPane.showMessageDialog(null, e);
        }finally{
        try{
            rs.close();
            pst.close();
            
        }catch(Exception e){
            
        }
    }
       }
       
      

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        japprove = new javax.swing.JButton();
        jremove = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jemailid = new javax.swing.JTextField();
        jage = new javax.swing.JTextField();
        jgender = new javax.swing.JTextField();
        jname = new javax.swing.JTextField();
        jpassword = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jrole = new javax.swing.JComboBox<>();
        jstatus = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        activeuser = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jTable10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTable10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Username", "Password", "Email id", "Gender", "Age", "Role", "Status"
            }
        ));
        jTable10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable10MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable10);

        japprove.setBackground(new java.awt.Color(255, 255, 153));
        japprove.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        japprove.setText("Approve");
        japprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                japproveActionPerformed(evt);
            }
        });

        jremove.setBackground(new java.awt.Color(255, 255, 153));
        jremove.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jremove.setText("Remove");
        jremove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jremoveActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Username");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Password");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Email ID");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Gender");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Age");

        jemailid.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jage.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jgender.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jname.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jpassword.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 102, 0));
        jLabel5.setText(" User Data");

        jrole.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jrole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cashier", "Staff", " " }));

        jstatus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive" }));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Role");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Status");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel14))
                                .addGap(79, 79, 79)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jname, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jemailid, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jgender, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jage, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jrole, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel5)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jemailid, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jgender, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jage, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jrole, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(98, 98, 98)))
                        .addComponent(jstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel14))
                .addGap(23, 23, 23))
        );

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        activeuser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        activeuser.setForeground(new java.awt.Color(255, 102, 102));
        activeuser.setText("Admin user");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 204, 204));
        jLabel8.setText("Sign-out");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 204, 204));
        jLabel15.setText("Add User Admin");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 204, 204));
        jLabel16.setText("Home");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/flowercon.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(79, 79, 79)
                .addComponent(jLabel16)
                .addGap(90, 90, 90)
                .addComponent(jLabel15)
                .addGap(87, 87, 87)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(activeuser, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(activeuser, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 102));
        jLabel3.setText("Approve Users to Manage System");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addComponent(japprove, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112)
                        .addComponent(jremove, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 629, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(336, 336, 336)
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(japprove, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jremove, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1087, 685));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable10MouseClicked
        // TODO add your handling code here:
         int Myindex = jTable10.getSelectedRow();
         TableModel model2 = (TableModel) jTable10.getModel();
        
         int Mycolumn = jTable10.getSelectedColumn();
  
         String value = model2.getValueAt(Myindex, Mycolumn).toString();
       
//        int id = Integer.parseInt(model2.getValueAt(Myindex, 0).toString());
   
        jname.setText(model2.getValueAt(Myindex, 1).toString());
        jpassword.setText(model2.getValueAt(Myindex, 2).toString());
        jemailid.setText(model2.getValueAt(Myindex, 3).toString());
        jgender.setText(model2.getValueAt(Myindex, 4).toString());
        jage.setText(model2.getValueAt(Myindex, 5).toString());
        
        String rolea =model2.getValueAt(Myindex, 6).toString();
        
        switch(rolea){
            case "Cashier":
                jrole.setSelectedIndex(0);
                break;
            case "Staff":
                jrole.setSelectedIndex(1);
                break;        
        }
        
        String statusa = model2.getValueAt(Myindex, 7).toString();
        switch(statusa){
            case "Active":
                jstatus.setSelectedIndex(0);
                break;
            case "Inactive":
                jstatus.setSelectedIndex(1);
                break;        
        }
        
        
    }//GEN-LAST:event_jTable10MouseClicked

    private void japproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_japproveActionPerformed
        
        DefaultTableModel tbmodel = (DefaultTableModel)jTable10.getModel();
        if(jTable10.getSelectedRowCount() == 1){
          JOptionPane.showMessageDialog(null, "Approved!");
          toapproveuser();
          
          //      to remove approved applicants/registrants..  
        String role1;
        role1 = jrole.getSelectedItem().toString();

        int row = jTable10.getSelectedRow();

        String cell = jTable10.getModel().getValueAt(row, 0).toString();

        String sql = "DELETE FROM `user_applicant` where id= " + cell;

        try {
            pst = con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, " New " + role1 + " Added!");
            updateuserapplicant();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                pst.close();
                rs.close();

            } catch (Exception e) {
            }
     
        
                
         DefaultTableModel model = (DefaultTableModel)jTable10.getModel();
         //delete row
         if(jTable10.getSelectedRowCount()==1){
             //if single row is selected then delete
              model.removeRow(jTable10.getSelectedRow());
              jname.setText("");
              jpassword.setText("");
              jemailid.setText("");
              jgender.setText("");
              jage.setText("");
              jrole.setSelectedIndex(0);
              jstatus.setSelectedIndex(0);
         }
       }
      
           
       }else{
           if(jTable10.getSelectedRowCount() == 0){
               //if table is empty np data the show message..
                JOptionPane.showMessageDialog(null, "Table is empty!");
           }else{
                    JOptionPane.showMessageDialog(null, "Please select a single row to delete!");
           }
       }
         


         
        
    }//GEN-LAST:event_japproveActionPerformed

    private void jremoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jremoveActionPerformed
        // TODO add your handling code here:
        
        DefaultTableModel tbmodel = (DefaultTableModel) jTable10.getModel();
        if (jTable10.getSelectedRowCount() == 1) {
            
            String username = jname.getText();
            String role1;
            role1 = jrole.getSelectedItem().toString();
            int row = jTable10.getSelectedRow();
            String cell = jTable10.getModel().getValueAt(row, 0).toString();
            String sql = "DELETE FROM `user_applicant` where id= " + cell;

            try {
                pst = con.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "You declined this user registration as  " + role1 + "   named    " + username + "!");
                todeclineusers();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                try {
                    pst.close();
                    rs.close();

                } catch (Exception e) {
                }

                DefaultTableModel model = (DefaultTableModel) jTable10.getModel();
                //delete row
                if (jTable10.getSelectedRowCount() == 1) {
                    //if single row is selected then delete
                    model.removeRow(jTable10.getSelectedRow());
                    jname.setText("");
                    jpassword.setText("");
                    jemailid.setText("");
                    jgender.setText("");
                    jage.setText("");
                }
            }

        } else {
            if (jTable10.getSelectedRowCount() == 0) {
                //if table is empty np data the show message..
                JOptionPane.showMessageDialog(null, "Table is empty!");
            } else {
                JOptionPane.showMessageDialog(null, "Please select a single row to delete!");
            }
        }

        
        
       
                
    }//GEN-LAST:event_jremoveActionPerformed

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        LoginUsers main = new  LoginUsers();
        main.setVisible(true);
        main.pack();
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        // TODO add your handling code here:

        adduser adminregis = new adduser();
        adminregis.setVisible(true);
        adminregis.pack();
        adminregis.setLocationRelativeTo(null);
        adminregis.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.dispose();

    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        // TODO add your handling code here:
        adminpage admin = new adminpage();
        admin.setVisible(true);
        admin.pack();
        admin.setLocationRelativeTo(null);
        admin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.dispose();
        
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        
        LoginUsers cashregis = new LoginUsers();
        cashregis.setVisible(true);
        cashregis.pack();
        cashregis.setLocationRelativeTo(null);
        cashregis.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

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
            java.util.logging.Logger.getLogger(Unapproveusers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Unapproveusers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Unapproveusers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Unapproveusers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Unapproveusers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel activeuser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable10;
    public javax.swing.JTextField jage;
    private javax.swing.JButton japprove;
    public javax.swing.JTextField jemailid;
    public javax.swing.JTextField jgender;
    public javax.swing.JTextField jname;
    public javax.swing.JPasswordField jpassword;
    private javax.swing.JButton jremove;
    private javax.swing.JComboBox<String> jrole;
    private javax.swing.JComboBox<String> jstatus;
    // End of variables declaration//GEN-END:variables
}

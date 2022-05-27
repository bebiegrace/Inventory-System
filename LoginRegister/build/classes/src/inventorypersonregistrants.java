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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static src.adminregistrants.jTable1;
//import static src.cashierregistrants.jTable2;


/**
 *
 * @author 1styrGroupB
 */
public class inventorypersonregistrants extends javax.swing.JFrame {

    /**
     * Creates new form inventorypersonregistrants
     */
    public inventorypersonregistrants() {
        initComponents();
        Connect();
        disabled1();
    }
    
    public static void AddRowToJadminTable(Object[] dataRow)   
     {
       DefaultTableModel model1 = (DefaultTableModel)jTable1.getModel(); 
       model1.addRow(dataRow);
     }

    public static void AddRowToJinventorypersonTable(Object[] dataRow)   
     {
       DefaultTableModel model = (DefaultTableModel)jTable3.getModel(); 
       model.addRow(dataRow);
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
    
    private void  deleteinvpersonusers(){
        String sql ="select id, username, password, email_id, gender,age,status from `users` where role='Staff'";
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
    public void disabled1(){
          jname.setEnabled(false);
          jpassword.setEnabled(false);
          jemailid.setEnabled(false);
          jgender.setEnabled(false);
           jage.setEnabled(false);
    }
    //insert user to database...
//    
//        public void InvenPersonuser()
//  {
//      String username = jname.getText();
//      String password = jpassword.getText();
//      String email_id = jemailid.getText();
//      String gender = jgender.getText();
//      String age = jage.getText();
//      
//      try{
//          
//          String query ="insert into `inventoryperson_users`(Username,Password,Email_id,Gender,Age)values(?,?,?,?,?);";
//          pst = con.prepareStatement(query);
//          pst.setString(1, username);
//          pst.setString(2, password);
//          pst.setString(3, email_id);
//          pst.setString(4, gender);
//          pst.setString(5, age);
//          pst.executeUpdate();
//
////           int i=pst.executeUpdate();
//           
//            
//            JOptionPane.showMessageDialog(null,  username+" has been successfully registered User as Inventory Person! " + "Successful with User id as Email id   "+email_id);
//                  
////            else{
////                      JOptionPane.showMessageDialog(null, username+"  You are NOT! successfully registered as Admin User!" + "Failed with User id as Email id"+email_id); 
////                    }
//          
//      }catch(SQLException ex){
//           Logger.getLogger(bookshop.class.getName()).log(Level.SEVERE, null, ex);
//      }
//  }

        public void inpersonsupdate(){
             try{
           pst = con.prepareStatement("select id, username, password, email_id, gender,age,status from `users` where role='Staff'");
           rs = pst.executeQuery();
           
           ResultSetMetaData rsd = rs.getMetaData();
           int c;
           
           c = rsd.getColumnCount();
           DefaultTableModel de = (DefaultTableModel)jTable3.getModel();
           de.setRowCount(0);
           
           while(rs.next())
           {
               Vector v2 = new Vector();
               for(int i=1; i<=c; i++)
               {
                   v2.add(rs.getString("id"));
                   v2.add(rs.getString("username"));
                   v2.add(rs.getString("password"));
                   v2.add(rs.getString("email_id"));
                   v2.add(rs.getString("gender"));
                   v2.add(rs.getString("age"));
                   v2.add(rs.getString("status"));
                
                   
               }
               de.addRow(v2);
           }
       } catch (SQLException ex) {
            Logger.getLogger(inventorypage.class.getName()).log(Level.SEVERE, null, ex);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jclear = new javax.swing.JButton();
        jDelete = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
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
        jLabel5 = new javax.swing.JLabel();
        jstatus = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jpassword = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 102, 102));
        jLabel3.setText("Username");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("x");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
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

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 204, 204));
        jLabel15.setText("Add User Admin");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 204, 204));
        jLabel8.setText("Sign-out");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/flowercon.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addGap(90, 90, 90)
                .addComponent(jLabel15)
                .addGap(90, 90, 90)
                .addComponent(jLabel8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(231, 231, 231)
                        .addComponent(jLabel2)
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(59, 59, 59))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15)
                            .addComponent(jLabel8)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jclear.setBackground(new java.awt.Color(255, 255, 204));
        jclear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jclear.setText("Clear");
        jclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jclearActionPerformed(evt);
            }
        });

        jDelete.setBackground(new java.awt.Color(255, 255, 204));
        jDelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jDelete.setText("Delete");
        jDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDeleteActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 153));
        jLabel1.setText("Inventory Person Users");

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));

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

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 102, 0));
        jLabel5.setText("Cashier User Data");

        jstatus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive" }));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Status");

        jpassword.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel6))
                        .addGap(67, 67, 67)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jemailid)
                                    .addComponent(jgender)
                                    .addComponent(jpassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jname, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(jage, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 14, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel7)
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jemailid, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jgender, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jage, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(jstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jTable3.setBackground(new java.awt.Color(255, 204, 204));
        jTable3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Username", "Password", "Email_id", "Gender", "Age", "Status"
            }
        ));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable3);

        jUpdate.setBackground(new java.awt.Color(255, 255, 204));
        jUpdate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jUpdate.setText("Update");
        jUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jclear, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addComponent(jUpdate)
                        .addGap(67, 67, 67)
                        .addComponent(jDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(180, 180, 180))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel1)
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jclear, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(53, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        
                System.exit(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteActionPerformed
        // TODO add your handling code here:
        
 
        int row =jTable3.getSelectedRow();
        String cell = jTable3.getModel().getValueAt(row, 0).toString();
        String sql="DELETE FROM `users` where id= " + cell;
        
        try{
            pst = con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Successfully Deleted!");
            deleteinvpersonusers();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                pst.close();
                rs.close();
                
            }catch (Exception e){
        }
        
                
         DefaultTableModel model = (DefaultTableModel)jTable3.getModel();
         //delete row
         if(jTable3.getSelectedRowCount()==1){
             //if single row is selected then delete
             model.removeRow(jTable3.getSelectedRow());
             jname.setText("");
             jpassword.setText("");
             jemailid.setText("");
             jgender.setText("");
             jage.setText("");
             jstatus.setSelectedIndex(0);
              
             
         }
        }
          
    }//GEN-LAST:event_jDeleteActionPerformed

    private void jclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jclearActionPerformed
        // TODO add your handling code here:
        
        jname.setText("");
        jpassword.setText("");
     
        jemailid.setText("");
        jgender.setText("");
        jage.setText("");
        jstatus.setSelectedIndex(0);
    }//GEN-LAST:event_jclearActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
         jname.setEnabled(false);
          jpassword.setEnabled(false);
           jemailid.setEnabled(false);
            jgender.setEnabled(false);
             jage.setEnabled(false);
        int Myindex = jTable3.getSelectedRow();
        TableModel model2 = (TableModel) jTable3.getModel();

        int Mycolumn = jTable3.getSelectedColumn();

        String value = model2.getValueAt(Myindex, Mycolumn).toString();

//        int id = Integer.parseInt(model2.getValueAt(Myindex, 0).toString());

        jname.setText(model2.getValueAt(Myindex, 1).toString());
        jpassword.setText(model2.getValueAt(Myindex, 2).toString());
        jemailid.setText(model2.getValueAt(Myindex, 3).toString());
        jgender.setText(model2.getValueAt(Myindex, 4).toString());
        jage.setText(model2.getValueAt(Myindex, 5).toString());

        String statusa = model2.getValueAt(Myindex, 6).toString();
        switch(statusa){
            case "active":
            jstatus.setSelectedIndex(0);
            break;
            case "inactive":
            jstatus.setSelectedIndex(1);
            break;
        }
        

    }//GEN-LAST:event_jTable3MouseClicked

    private void jUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUpdateActionPerformed
        // TODO add your handling code here:

        DefaultTableModel model2 = (DefaultTableModel) jTable3.getModel();
        int Myindex = jTable3.getSelectedRow();
        int Mycolumn = jTable3.getSelectedColumn();

        String value = model2.getValueAt(Myindex, Mycolumn).toString();
        int id = Integer.parseInt(model2.getValueAt(Myindex, 0).toString());
        String uname = jname.getText();
        String pass = jpassword.getText();
        String emai1 = jemailid.getText();
        String gend = jgender.getText();
        String agedd = jage.getText();
        String status;
       status = jstatus.getSelectedItem().toString();
       

        try{
            pst = con.prepareStatement("UPDATE users set username= ?, password= ?, email_id= ?, gender= ?, age= ?, status= ? where id= ?");
            //
            pst.setString(1, uname );
            pst.setString(2, pass);
            pst.setString(3,emai1);
            pst.setString(4, gend);
            pst.setString(5, agedd);
             pst.setString(6, status);
             pst.setInt(7, id);
            

            int k= pst.executeUpdate();

            if(k==1)
            {
                JOptionPane.showMessageDialog(this, "User Updated");
                jname.setText("");
                jpassword.setText("");
                jemailid.setText("");
                jgender.setText("");
                jage.setText("");
                jstatus.setSelectedIndex(0);
             
                jname.requestFocus();

               inpersonsupdate();

                //                jadd.setEnabled(true);

            }
        } catch (SQLException ex) {
            Logger.getLogger(inventorypage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jUpdateActionPerformed

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        // TODO add your handling code here:
        adminpage admin = new adminpage();
        admin.setVisible(true);
        admin.pack();
        admin.setLocationRelativeTo(null);
        admin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.dispose();

    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        // TODO add your handling code here:

        adduser adminregis = new adduser();
        adminregis.setVisible(true);
        adminregis.pack();
        adminregis.setLocationRelativeTo(null);
        adminregis.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        LoginUsers main = new  LoginUsers();
        main.setVisible(true);
        main.pack();
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        
           LoginUsers cashregis = new LoginUsers();
        cashregis.setVisible(true);
        cashregis.pack();
        cashregis.setLocationRelativeTo(null);
        cashregis.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

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
            java.util.logging.Logger.getLogger(inventorypersonregistrants.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inventorypersonregistrants.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inventorypersonregistrants.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inventorypersonregistrants.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inventorypersonregistrants().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jDelete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable3;
    private javax.swing.JButton jUpdate;
    public javax.swing.JTextField jage;
    private javax.swing.JButton jclear;
    public javax.swing.JTextField jemailid;
    public javax.swing.JTextField jgender;
    public javax.swing.JTextField jname;
    public javax.swing.JPasswordField jpassword;
    private javax.swing.JComboBox<String> jstatus;
    // End of variables declaration//GEN-END:variables
}

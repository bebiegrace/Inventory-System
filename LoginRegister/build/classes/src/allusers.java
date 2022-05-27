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
import static src.ApprovedUsers.jTable11;
import static src.Unapproveusers.jTable10;
import static src.adminpage.jTable20;
import static src.cashierusers.jTable12;
import static src.inventorypersonregistrants.jTable3;

/**
 *
 * @author 1styrGroupB
 */
public class allusers extends javax.swing.JFrame {

    /**
     * Creates new form allusers
     */
    public allusers() {
        initComponents();
            Connect();
            displayuser();
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
      
         public static void AddRowTousersable(Object[] dataRow)   
     {
       DefaultTableModel model11 = (DefaultTableModel)jTable13.getModel(); 
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
        public void displayuser(){
         try {
            Statement st = con.createStatement();
            String query1 = "select * from `users`";
            ResultSet rs1 = st.executeQuery(query1);

            while(rs1.next()){
                //data wil added until finished..
                String Id1 = rs1.getString("id");
                String username1 = rs1.getString("username");
                String password1 = rs1.getString("password");
                String email_id1 = rs1.getString("email_id");
                String gender1 = rs1.getString("gender");
                String age1 = rs1.getString("age");
                String rolr = rs1.getString("role");

                String status1= rs1.getString("status");

                //string array for store data into jtable..
                String tbData[] = {Id1,username1,password1,email_id1,gender1,age1,rolr,status1};
                DefaultTableModel tblModel = (DefaultTableModel)jTable13.getModel();

                //add string array data into jtable..

                tblModel.addRow(tbData);

            }

//            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(adduser.class.getName()).log(Level.SEVERE, null, ex);
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
        public void usersupdate(){
       try{
           pst = con.prepareStatement("select * from `users`");
           rs = pst.executeQuery();
           
           ResultSetMetaData rsd = rs.getMetaData();
           int c;
           
           c = rsd.getColumnCount();
           DefaultTableModel de = (DefaultTableModel)jTable13.getModel();
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
                   v2.add(rs.getString("role"));
                   v2.add(rs.getString("status"));
                   
               }
               de.addRow(v2);
           }
       } catch (SQLException ex) {
            Logger.getLogger(inventorypage.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        
        
        public void  deleteusers(){
        String sql ="select from `users`";
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
        jclear = new javax.swing.JButton();
        jUpdate = new javax.swing.JButton();
        jDelete = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        activeuser = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
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
        jrole = new javax.swing.JComboBox<>();
        jstatus = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable13 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jclear.setBackground(new java.awt.Color(255, 255, 204));
        jclear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jclear.setText("Clear");
        jclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jclearActionPerformed(evt);
            }
        });

        jUpdate.setBackground(new java.awt.Color(255, 255, 204));
        jUpdate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jUpdate.setText("Update");
        jUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUpdateActionPerformed(evt);
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
        jLabel1.setForeground(new java.awt.Color(255, 255, 102));
        jLabel1.setText("System Users");

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));

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

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/flowercon.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(67, 67, 67)
                .addComponent(jLabel16)
                .addGap(103, 103, 103)
                .addComponent(jLabel15)
                .addGap(77, 77, 77)
                .addComponent(jLabel8)
                .addGap(244, 244, 244)
                .addComponent(activeuser, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(activeuser, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

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

        jrole.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jrole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Cashier", "Staff", "" }));

        jstatus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive" }));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Role");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Status");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 102, 0));
        jLabel5.setText(" User Data");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jname, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jemailid, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jgender, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jage, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jrole, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jemailid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jgender, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jrole, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jstatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(23, 23, 23))
        );

        jTable13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTable13.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Username", "Password", "Email_id", "Gender", "Age", "Role", "Status"
            }
        ));
        jTable13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable13MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable13);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jclear, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(jUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(jDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(212, 212, 212))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jclear, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void jDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteActionPerformed
        // TODO add your handling code here:
        int row =jTable13.getSelectedRow();
        String cell = jTable13.getModel().getValueAt(row, 0).toString();
        String sql="DELETE FROM `users` where id= " + cell;

        try{
            pst = con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Successfully Deleted!");
            deleteusers();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                pst.close();
                rs.close();

            }catch (Exception e){
            }

            DefaultTableModel model = (DefaultTableModel)jTable13.getModel();
            //delete row
            if(jTable13.getSelectedRowCount()==1){
                //if single row is selected then delete
                model.removeRow(jTable13.getSelectedRow());
                jname.setText("");
                jpassword.setText("");
                jemailid.setText("");
                jgender.setText("");
                jage.setText("");
                jrole.setSelectedIndex(0);
                jstatus.setSelectedIndex(0);
            }
        }
        //        jadd.setEnabled(true);
    }//GEN-LAST:event_jDeleteActionPerformed

    private void jUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUpdateActionPerformed
        // TODO add your handling code here:

        DefaultTableModel model3 = (DefaultTableModel) jTable13.getModel();
        int Myindex = jTable13.getSelectedRow();
        int Mycolumn = jTable13.getSelectedColumn();

        String value = model3.getValueAt(Myindex, Mycolumn).toString();
        int id = Integer.parseInt(model3.getValueAt(Myindex, 0).toString());
        String uname = jname.getText();
        String pass = jpassword.getText();
        String emai1 = jemailid.getText();
        String gend = jgender.getText();
        String agedd = jage.getText();
        String role;
        role=jrole.getSelectedItem().toString();
        String status;
        status=jstatus.getSelectedItem().toString();

        try{
            pst = con.prepareStatement("UPDATE users set username= ?, password= ?, email_id= ?, gender= ?, age= ?, role= ?, status= ? where id= ?");
            //
            pst.setString(1, uname );
            pst.setString(2, pass);
            pst.setString(3,emai1);
            pst.setString(4, gend);
            pst.setString(5, agedd);
            pst.setString(6, role);
            pst.setString(7, status);
            pst.setInt(8, id);

            int k= pst.executeUpdate();

            if(k==1)
            {
                JOptionPane.showMessageDialog(this, "User Updated");
                jname.setText("");
                jpassword.setText("");
                jemailid.setText("");
                jgender.setText("");
                jage.setText("");
                jrole.setSelectedIndex(0);
                jstatus.setSelectedIndex(0);
                jname.requestFocus();

                usersupdate();

                //                jadd.setEnabled(true);

            }
        } catch (SQLException ex) {
            Logger.getLogger(inventorypage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jUpdateActionPerformed

    private void jclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jclearActionPerformed
        // TODO add your handling code here:
        jname.setText("");
        jpassword.setText("");
        //        jconfirmpassword.setText("");
        jemailid.setText("");
        jgender.setText("");
        jage.setText("");
        jrole.setSelectedIndex(0);
        jstatus.setSelectedIndex(0);

    }//GEN-LAST:event_jclearActionPerformed

    private void jTable13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable13MouseClicked
        // TODO add your handling code here:
         jname.setEnabled(false);
          jpassword.setEnabled(false);
           jemailid.setEnabled(false);
            jgender.setEnabled(false);
             jage.setEnabled(false);
         
        DefaultTableModel model2 = (DefaultTableModel) jTable13.getModel();
        int Myindex = jTable13.getSelectedRow();
        TableModel model3 = (TableModel) jTable13.getModel();

        int Mycolumn = jTable13.getSelectedColumn();

        String value = model2.getValueAt(Myindex, Mycolumn).toString();

        //        int id = Integer.parseInt(model2.getValueAt(Myindex, 0).toString());

        jname.setText(model2.getValueAt(Myindex, 1).toString());
        jpassword.setText(model2.getValueAt(Myindex, 2).toString());
        jemailid.setText(model2.getValueAt(Myindex, 3).toString());
        jgender.setText(model2.getValueAt(Myindex, 4).toString());
        jage.setText(model2.getValueAt(Myindex, 5).toString());

        String rolea =model2.getValueAt(Myindex, 6).toString();

        switch(rolea){
            case "Admin":
            jrole.setSelectedIndex(0);
            break;
            case "Cashier":
            jrole.setSelectedIndex(1);
            break;
            case "Staff":
            jrole.setSelectedIndex(2);
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
    }//GEN-LAST:event_jTable13MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        
         LoginUsers cashregis = new  LoginUsers();
            cashregis.setVisible(true);
            cashregis.pack();
            cashregis.setLocationRelativeTo(null);
            cashregis.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

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
            java.util.logging.Logger.getLogger(allusers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(allusers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(allusers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(allusers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new allusers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel activeuser;
    private javax.swing.JButton jDelete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable13;
    private javax.swing.JButton jUpdate;
    public javax.swing.JTextField jage;
    private javax.swing.JButton jclear;
    public javax.swing.JTextField jemailid;
    public javax.swing.JTextField jgender;
    public javax.swing.JTextField jname;
    public javax.swing.JPasswordField jpassword;
    private javax.swing.JComboBox<String> jrole;
    private javax.swing.JComboBox<String> jstatus;
    // End of variables declaration//GEN-END:variables
}

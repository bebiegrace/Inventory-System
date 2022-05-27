/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static src.Unapproveusers.jTable10;
import static src.adminregistrants.jTable1;
import static src.allusers.jTable13;


import static src.cashierusers.jTable12;
import static src.category.jTable6;
import static src.inventorypersonregistrants.jTable3;
import static src.soldadmin.jTable9;
import static src.soldproducts.jTable5;


/**
 *
 * @author 1styrGroupB
 */
public class adminpage extends javax.swing.JFrame {

    /**
     * Creates new form adminpage
     */
    public adminpage() {
        initComponents();
          Connect();
          dt();
          time();
          displayall();
    }
      public adminpage(String userad) {
        initComponents();
        jusernamee.setText(userad);
        Connect();
        dt();
        time();
        displayall();
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
     public static void AddRowToJadminTable(Object[] dataRow)   
     {
       DefaultTableModel modelu = (DefaultTableModel)jTable10.getModel(); 
       modelu.addRow(dataRow);
     }
//       public void toapproveuser()
//  {
//      
//      
//       String username = jname.getText();
//       String password = jpassword.getText();
//       String email_id = jemailid.getText();
//       String gender = jgender.getText();
//       String age = jage.getText();
//     
//       String role1;
//       role1=jrole.getSelectedItem().toString();
//      
//       String status;
//       status=jstatus.getSelectedItem().toString();
//      
//      try{
//        
//          String query ="insert into `users`(username,password,email_id,gender,age,role,status)values(?,?,?,?,?,?,?);";
//          pst = con.prepareStatement(query);
//          
//          pst.setString(1, username);
//          pst.setString(2, password);
//          pst.setString(3, email_id);
//          pst.setString(4, gender);
//          pst.setString(5, age);
////          pst.setInt(5, Integer.valueOf(jage.getText()));
//       
//          pst.setString(6, role1);
//          
//      
//          pst.setString(7, status);
//          pst.executeUpdate();
// }catch(SQLException ex){
//           Logger.getLogger(bookshop.class.getName()).log(Level.SEVERE, null, ex);
//      }
  
//  }
       public void displayall(){
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
                DefaultTableModel tblModel = (DefaultTableModel)jTable20.getModel();

                //add string array data into jtable..

                tblModel.addRow(tbData);

            }

//            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(adduser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                                    

       
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
        public void usersupdate(){
       try{
           pst = con.prepareStatement("select * from `users`");
           rs = pst.executeQuery();
           
           ResultSetMetaData rsd = rs.getMetaData();
           int c;
           
           c = rsd.getColumnCount();
           DefaultTableModel de = (DefaultTableModel)jTable20.getModel();
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
     
     
     
     public void dt(){
         
         Date d = new Date();
         
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         
         String dd = sdf.format(d);
         date.setText(dd);
     }
         Timer t;
         SimpleDateFormat st;
          public void time(){
              t = new Timer(0, new ActionListener(){
                  @Override
                  public void actionPerformed(ActionEvent e){
                      Date dt = new Date();
                      st = new SimpleDateFormat("hh-mm-ss a");
                      String tt =st.format(dt);
                      time.setText(tt);
                      
                  }
              });
                  t.start();    
          }

     
//      public static void AddRowToallusersable(Object[] dataRow)   
//     {
//       DefaultTableModel modelu = (DefaultTableModel)jTable14.getModel(); 
//       modelu.addRow(dataRow);
//     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable20 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jusernamee = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        date = new javax.swing.JTextField();
        time = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jstaff = new javax.swing.JLabel();
        jcashier = new javax.swing.JLabel();
        jadmin = new javax.swing.JLabel();
        jcategory = new javax.swing.JLabel();
        jtransaction = new javax.swing.JLabel();
        jsold = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 51, 51));
        jLabel4.setText("Update");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jTable20.setBackground(new java.awt.Color(255, 204, 204));
        jTable20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTable20.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Username", "Password", "Email_id", "Gender", "Age", "Role", "Status"
            }
        ));
        jTable20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable20MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable20);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(307, 307, 307))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 204, 204));
        jLabel7.setText("Approve  New Users");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 204, 204));
        jLabel9.setText("Add User Admin");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel9MouseEntered(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 204, 204));
        jLabel11.setText("Sign-out");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/flowercon.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jusernamee.setBackground(new java.awt.Color(204, 204, 204));
        jusernamee.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jusernamee.setForeground(new java.awt.Color(255, 0, 0));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/avatarbebie.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(59, 59, 59)
                .addComponent(jLabel9)
                .addGap(65, 65, 65)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jusernamee, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11)))
                            .addComponent(jLabel1))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jusernamee, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 102));
        jLabel3.setText("Admin Management");

        jPanel5.setBackground(new java.awt.Color(153, 153, 153));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Date:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Time:");

        date.setBackground(new java.awt.Color(51, 51, 51));
        date.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        date.setForeground(new java.awt.Color(255, 51, 51));
        date.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        date.setText("0");

        time.setBackground(new java.awt.Color(51, 51, 51));
        time.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        time.setForeground(new java.awt.Color(255, 51, 51));
        time.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        time.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(time, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(date))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(0, 15, Short.MAX_VALUE))
                    .addComponent(time))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));

        jstaff.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jstaff.setForeground(new java.awt.Color(255, 51, 51));
        jstaff.setText("Staff");
        jstaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jstaffMouseClicked(evt);
            }
        });

        jcashier.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jcashier.setForeground(new java.awt.Color(255, 51, 51));
        jcashier.setText("Cashier");
        jcashier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcashierMouseClicked(evt);
            }
        });

        jadmin.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jadmin.setForeground(new java.awt.Color(255, 51, 51));
        jadmin.setText("Admin");
        jadmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jadminMouseClicked(evt);
            }
        });

        jcategory.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jcategory.setForeground(new java.awt.Color(255, 51, 51));
        jcategory.setText("Available Products");
        jcategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcategoryMouseClicked(evt);
            }
        });

        jtransaction.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jtransaction.setForeground(new java.awt.Color(255, 51, 51));
        jtransaction.setText("Sales_Transactions");
        jtransaction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtransactionMouseClicked(evt);
            }
        });

        jsold.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jsold.setForeground(new java.awt.Color(255, 51, 51));
        jsold.setText("Sold Product");
        jsold.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jsoldMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jstaff, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jadmin, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcashier, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jcategory))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jtransaction))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jsold)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jstaff, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jcashier, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jadmin, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jcategory)
                .addGap(29, 29, 29)
                .addComponent(jtransaction)
                .addGap(37, 37, 37)
                .addComponent(jsold)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(131, 131, 131)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(185, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel3)))
                        .addGap(40, 40, 40)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1150, 681));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jsoldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jsoldMouseClicked
        // TODO add your handling code here:
        soldadmin admin = new soldadmin(jusernamee.getText());
        admin.show();
        dispose();
  
        try {
            Statement st = con.createStatement();
            String query1 = "select * from `sales_products`";
            ResultSet rs1 = st.executeQuery(query1);

            while(rs1.next()){
                //data wil added until finished..
                String bid2 = rs1.getString("id");
                String salesid2 = rs1.getString("sales_id");
                String bookn2 = rs1.getString("bookname");
                String price2 = rs1.getString("price");
                String qty2 = rs1.getString("quantity");
                String totl2 = rs1.getString("total");

                //string array for store data into jtable..
                String tbData[] = {bid2,salesid2,bookn2,price2,qty2,totl2};
                DefaultTableModel tabledata = (DefaultTableModel)jTable9.getModel();

                //add string array data into jtable..

                tabledata.addRow(tbData);

            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(adduser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jsoldMouseClicked

    private void jcategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcategoryMouseClicked
        // TODO add your handling code here:

        category admin = new category(jusernamee.getText());
        admin.show();
        dispose();
      
    }//GEN-LAST:event_jcategoryMouseClicked

    private void jtransactionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtransactionMouseClicked
        // TODO add your handling code here:
        
        transaction admin = new transaction(jusernamee.getText());
        admin.show();
        dispose();
      
    }//GEN-LAST:event_jtransactionMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        LoginUsers cashregis = new  LoginUsers();
        cashregis.setVisible(true);
        cashregis.pack();
        cashregis.setLocationRelativeTo(null);
        cashregis.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        LoginUsers main = new  LoginUsers();
        main.setVisible(true);
        main.pack();
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        adduser cashregis = new adduser(jusernamee.getText());
        cashregis.show();
        dispose();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:

        Unapproveusers cashregis = new Unapproveusers(jusernamee.getText());
        cashregis.show();
        dispose();

        try {
            Statement st1 = con.createStatement();
            String query1 = "select * from `user_applicant`";
            ResultSet rs1 = st1.executeQuery(query1);

            while(rs1.next()){
                //data wil added until finished..
                String bid = rs1.getString("id");
                String username1 = rs1.getString("username");
                String password1 = rs1.getString("password");
                String email_id1 = rs1.getString("email_id");
                String gender1 = rs1.getString("gender");
                String age1 = rs1.getString("age");
                String rol= rs1.getString("role");
//                String sts = rs1.getString("status");

                //string array for store data into jtable..
                String tbData[] = {bid,username1,password1,email_id1,gender1,age1,rol};

                DefaultTableModel modelu = (DefaultTableModel)jTable10.getModel();

                //add string array data into jtable..

                modelu.addRow(tbData);

            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(adduser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
  
        allusers cashregis = new allusers(jusernamee.getText());
        cashregis.show();
        dispose();
        
//         try {
//            Statement st4 = con.createStatement();
//            String query1 = "select * from `users`";
//            ResultSet rs1 = st4.executeQuery(query1);
//
//            while(rs1.next()){
//                //data wil added until finished..
//                String Id1 = rs1.getString("id");
//                String username1 = rs1.getString("username");
//                String password1 = rs1.getString("password");
//                String email_id1 = rs1.getString("email_id");
//                String gender1 = rs1.getString("gender");
//                String age1 = rs1.getString("age");
//                String rolr = rs1.getString("role");
//
//                String status1= rs1.getString("status");
//
//                //string array for store data into jtable..
//                String tbData[] = {Id1,username1,password1,email_id1,gender1,age1,rolr,status1};
//                DefaultTableModel tblModel = (DefaultTableModel)jTable13.getModel();
//
//                //add string array data into jtable..
//
//                tblModel.addRow(tbData);
//
//            }
//
////            con.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(adduser.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jstaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jstaffMouseClicked
        // TODO add your handling code here:
        
        inventorypersonregistrants inventory = new inventorypersonregistrants(jusernamee.getText());
        inventory.show();
        dispose();
         try {
            Statement st = con.createStatement();
            String query1 = "select id, username, password, email_id, gender, age, status from `users` where role='Staff'";
            ResultSet rs1 = st.executeQuery(query1);
            
            while(rs1.next()){
                //data wil added until finished..
                String bid = rs1.getString("id");
                String username1 = rs1.getString("username");
                String password1 = rs1.getString("password");
                String email_id1 = rs1.getString("email_id");
                String gender1 = rs1.getString("gender");
                String age1 = rs1.getString("age");
                String stat = rs1.getString("status");
              
                
                //string array for store data into jtable..
                String tbData[] = {bid,username1,password1,email_id1,gender1,age1,stat};
                  DefaultTableModel tblModel = (DefaultTableModel)jTable3.getModel();

                //add string array data into jtable..
                
                tblModel.addRow(tbData);

            }
            
//            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(adduser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jstaffMouseClicked

    private void jadminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jadminMouseClicked
        // TODO add your handling code here:
        adminregistrants admin = new adminregistrants(jusernamee.getText());
        admin.show();
        dispose();
        
         try {
            Statement st = con.createStatement();
            String query1 = "select id, username, password, email_id, gender,age,status from `users` where role='Admin'";
            ResultSet rs1 = st.executeQuery(query1);
            
            while(rs1.next()){
                //data wil added until finished..
                String bid = rs1.getString("id");
                String username1 = rs1.getString("username");
                String password1 = rs1.getString("password");
                String email_id1 = rs1.getString("email_id");
                String gender1 = rs1.getString("gender");
                String age1 = rs1.getString("age");
                String stat = rs1.getString("status");
              
                
                //string array for store data into jtable..
                String tbData[] = {bid,username1,password1,email_id1,gender1,age1,stat};
                  DefaultTableModel tblModel = (DefaultTableModel)jTable1.getModel();

                //add string array data into jtable..
                
                tblModel.addRow(tbData);

            }
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(adduser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jadminMouseClicked

    private void jcashierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcashierMouseClicked
        // TODO add your handling code here:
        cashierusers cash = new cashierusers(jusernamee.getText());
        cash.show();
        dispose();

       try {
            Statement st = con.createStatement();
            String query1 = "select id, username, password, email_id, gender, age, status from `users` where role='Cashier'";
            ResultSet rs1 = st.executeQuery(query1);
            
            while(rs1.next()){
                //data wil added until finished..
                String bid = rs1.getString("id");
                String username1 = rs1.getString("username");
                String password1 = rs1.getString("password");
                String email_id1 = rs1.getString("email_id");
                String gender1 = rs1.getString("gender");
                String age1 = rs1.getString("age");
                String stat = rs1.getString("status");
              
                
                //string array for store data into jtable..
                String tbData[] = {bid,username1,password1,email_id1,gender1,age1,stat};
                  DefaultTableModel tblModel = (DefaultTableModel)jTable12.getModel();

                //add string array data into jtable..
                
                tblModel.addRow(tbData);

            }
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(adduser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jcashierMouseClicked

    private void jTable20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable20MouseClicked
        // TODO add your handling code here:
//        jname.setEnabled(false);
//        jpassword.setEnabled(false);
//        jemailid.setEnabled(false);
//        jgender.setEnabled(false);
//        jage.setEnabled(false);
//
//        DefaultTableModel model2 = (DefaultTableModel) jTable20.getModel();
//        int Myindex = jTable20.getSelectedRow();
//        TableModel model3 = (TableModel) jTable20.getModel();
//
//        int Mycolumn = jTable20.getSelectedColumn();
//
//        String value = model2.getValueAt(Myindex, Mycolumn).toString();
//
//        //        int id = Integer.parseInt(model2.getValueAt(Myindex, 0).toString());
//
//        jname.setText(model2.getValueAt(Myindex, 1).toString());
//        jpassword.setText(model2.getValueAt(Myindex, 2).toString());
//        jemailid.setText(model2.getValueAt(Myindex, 3).toString());
//        jgender.setText(model2.getValueAt(Myindex, 4).toString());
//        jage.setText(model2.getValueAt(Myindex, 5).toString());
//
//        String rolea =model2.getValueAt(Myindex, 6).toString();
//
//        switch(rolea){
//            case "Admin":
//            jrole.setSelectedIndex(0);
//            jrole.setEnabled(false);
//            jstatus.setEnabled(false);
//            break;
//            case "Cashier":
//            jrole.setSelectedIndex(1);
//            break;
//            case "Staff":
//            jrole.setSelectedIndex(2);
//            break;
//        }
//
//        String statusa = model2.getValueAt(Myindex, 7).toString();
//        switch(statusa){
//            case "Active":
//            jstatus.setSelectedIndex(0);
//            break;
//            case "Inactive":
//            jstatus.setSelectedIndex(1);
//            break;
//        }
    }//GEN-LAST:event_jTable20MouseClicked

    private void jLabel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel9MouseEntered

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
            java.util.logging.Logger.getLogger(adminpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adminpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adminpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new adminpage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField date;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JTable jTable20;
    private javax.swing.JLabel jadmin;
    private javax.swing.JLabel jcashier;
    private javax.swing.JLabel jcategory;
    private javax.swing.JLabel jsold;
    private javax.swing.JLabel jstaff;
    private javax.swing.JLabel jtransaction;
    public javax.swing.JLabel jusernamee;
    private javax.swing.JTextField time;
    // End of variables declaration//GEN-END:variables
}

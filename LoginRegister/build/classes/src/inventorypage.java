/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

//import com.ibm.icu.util.Calendar;
//import com.ibm.icu.util.GregorianCalendar;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import static src.LoginUsers.id;

import static src.category.jTable6;
import static src.inventorypersonregistrants.jTable3;
import static src.soldproducts.jTable5;

/**
 *
 * @author 1styrGroupB
 */
public class inventorypage extends javax.swing.JFrame {

    String filename = null;
    byte[] product_image = null;

    public inventorypage() {
        
         initComponents();

        Connect();
   
        dt();
        time();
        display();
     }
    
    
 
    public void display() {
        try {
            Statement st = con.createStatement();
            String query1 = "select * from `products`";
            ResultSet rs1 = st.executeQuery(query1);
//            Statement = con.prepareStatement(query1);
//            Statement.executeUpdate("Drop Table if Exists Schedule");
//            pStatement.executeUpdate(query);
//
            while (rs1.next()) {
                //data wil added until finished..
                String id = rs1.getString("barcode");
                String hh = rs1.getString("product_name");
                String des1 = rs1.getString("description");
                String trr = rs1.getString("quantity");
                String buy = rs1.getString("buyingprice");
                String sell1 = rs1.getString("sellingprice");
                String pro = rs1.getString("productImage");

                //string array for store data into jtable..
                String tbData[] = {id,hh, des1, trr, buy, sell1, pro};
                DefaultTableModel tblModel = (DefaultTableModel) jTable4.getModel();

                //add string array data into jtable..
                tblModel.addRow(tbData);
//
            }

        } catch (SQLException ex) {
            Logger.getLogger(cashierusers.class.getName()).log(Level.SEVERE, null, ex);
        }
//
    }
//
//    public inventorypage(String usernamee) {
//        initComponents();
//        jusernamee.setText(usernamee);
//    }
//     
     public void salesupdate(){
           try{
           pst = con.prepareStatement("select * from `products`");
           rs = pst.executeQuery();
           
           ResultSetMetaData rsd = rs.getMetaData();
           int c;
           
           c = rsd.getColumnCount();
           DefaultTableModel de = (DefaultTableModel)jTable4.getModel();
           de.setRowCount(0);
           
           while(rs.next())
           {
               Vector v2 = new Vector();
               for(int i=1; i<=c; i++)
               {
                   v2.add(rs.getString("barcode"));
                   v2.add(rs.getString("product_name"));
                   v2.add(rs.getString("description"));
                   v2.add(rs.getString("quantity"));
                   v2.add(rs.getString("buyingprice"));
                   v2.add(rs.getString("sellingprice"));
                   v2.add(rs.getString("productImage"));

                   
               }
               de.addRow(v2);
               
           }
       } catch (SQLException ex) {
            Logger.getLogger(inventorypage.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    public static void AddRowToJcategoryTable(Object[] dataRow) {
        DefaultTableModel model6 = (DefaultTableModel) jTable6.getModel();
        model6.addRow(dataRow);
    }

    public static void AddRowToJsoldproductTable(Object[] dataRow) {
        DefaultTableModel model5 = (DefaultTableModel) jTable5.getModel();
        model5.addRow(dataRow);
    }

    Connection con;
    PreparedStatement pst;
    PreparedStatement pst9;
    PreparedStatement pst1;
    ResultSet rs;
    DefaultTableModel df;

//    inventorypage() {
//       inventorypage in = new inventorypage();
//       
//    }

    public void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/bebieinventorysystem", "root", "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(bookshop.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(bookshop.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    String photopath = "";

    public ImageIcon resetImageSize(String photopath, byte[] photo) {
        ImageIcon myPhoto = null;
        if (photopath != null) {
            myPhoto = new ImageIcon(photopath);

        } else {
            myPhoto = new ImageIcon(photo);
        }
        Image img = myPhoto.getImage();
        Image img1 = img.getScaledInstance(jproductimage.getWidth(), jproductimage.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon ph = new ImageIcon(img1);
        return ph;
    }

    public void updateproductsales() {
        String sql = "select from `products`";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
//            jTable3.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
//           JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                rs.close();
                pst.close();

            } catch (Exception e) {

            }
        }
    }

    public void dt() {

        Date d = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String dd = sdf.format(d);
        jdate.setText(dd);
    }
    Timer t;
    SimpleDateFormat st;

    public void time() {
        t = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date dt = new Date();
                st = new SimpleDateFormat("hh-mm-ss a");
                String tt = st.format(dt);
                time.setText(tt);

            }
        });
        t.start();
    }

    public void product() {
//
        Calendar cal = new GregorianCalendar();
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String date = dt.format(now);
       
        String timee = time.getText();
        String bname = jbname.getText();
        String desc = jdescription.getText();
        String buyingprice = jbuyprice.getText();
        String sellingprice = jsellprice.getText();
        String img1 = jlinkimage.getText();
//   
        try {
  
            String query6 = "insert into `products`(product_name,description,quantity,buyingprice,sellingprice,date_addproduct,time_addproduct,productImage)values(?,?,?,?,?,?,?,?);";
            pst = con.prepareStatement(query6, Statement.RETURN_GENERATED_KEYS);
            pst9 = con.prepareStatement(query6); 
            
            pst9.setString(1, bname);
            pst9.setString(2, desc);
//           
//         pst.setFloat(3, Float.parseFloat(jquantity.getText()));
//         pst.setDouble(3, Double.parseDouble(jquantity.getText()));
//         pst.setInt(3, qty);
            pst9.setInt(3, Integer.parseInt(jquantity.getText()));
            pst9.setString(4, buyingprice);
            pst9.setString(5, sellingprice);
            pst9.setString(6, date);
            pst9.setString(7, timee);
            pst9.setString(8, img1);

            pst9.executeUpdate();
//            rs = pst.getGeneratedKeys();

            JOptionPane.showMessageDialog(this, "Product Added");

        } catch (SQLException e) {
            Logger.getLogger(bookshop.class.getName()).log(Level.SEVERE, null, e);
//      
        }
    }

    //create a fuction to display image into jtable
    public void displayImage(String imgPath, JLabel label) {
        ImageIcon imgicon = new ImageIcon(imgPath);
        Image img = imgicon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(img));
    }

    //insert into staff transaction.......
    public void invetorytransac() {

        Calendar cal = new GregorianCalendar();
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String date = dt.format(now);
        String timee = time.getText();
        String prod = jbname.getText();
        String username = jusernamee.getText();
        String types = "Added Products";

        int damm = 0;

   String id = jTable4.getModel().getValueAt(jTable4.getModel().getRowCount() - 1, 0).toString();
        try {

            String query = "insert into stransaction(InventoryID,quantity,type_transaction,UserID,date,time)values(?,?,?,?,?,?)";
            pst = con.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(id));//inventory id
            pst.setDouble(2, Double.parseDouble(jquantity.getText()));
            pst.setString(3, types);
//          pst.setString(4, new LoginUsers().id);//username id...
            pst.setInt(4, new LoginUsers().ID());//username id...
            pst.setString(5, date);
            pst.setString(6, timee);
            pst.executeUpdate();

        } catch (Exception e) {
            Logger.getLogger(bookshop.class.getName()).log(Level.SEVERE, null, e);
     
        }
    }
    
    //insert delete type of transaction..
    public void invetorytransacdelete() {

        Calendar cal = new GregorianCalendar();
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String date = dt.format(now);
        String timee = time.getText();
        String prod = jbname.getText();
        String username = jusernamee.getText();
        String types = "Deleted Products";

//        int damm = 0;
//        int row = jTable4.getSelectedRow();
//        String prdct = jTable4.getValueAt(row, 0).toString();
    

   String id = jTable4.getModel().getValueAt(jTable4.getModel().getRowCount() - 1, 0).toString();
        try {

            String query = "insert into stransaction(InventoryID,quantity,type_transaction,UserID,date,time)values(?,?,?,?,?,?)";
            pst = con.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(id));//inventory id
            pst.setDouble(2, Double.parseDouble(jquantity.getText()));
            pst.setString(3, types);
//          pst.setString(4, new LoginUsers().id);//username id...
            pst.setInt(4, new LoginUsers().ID());
            pst.setString(5, date);
            pst.setString(6, timee);
            pst.executeUpdate();

        } catch (Exception e) {
            Logger.getLogger(bookshop.class.getName()).log(Level.SEVERE, null, e);
//      
        }
    }
    
     public void invetorytransacupdate() {

        Calendar cal = new GregorianCalendar();
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String date = dt.format(now);
        String timee = time.getText();
        String prod = jbname.getText();
        String username = jusernamee.getText();
        String types = "Updated Products";

//        int damm = 0;
//        int row = jTable4.getSelectedRow();
//        String prdct = jTable4.getValueAt(row, 0).toString();
    

   String id = jTable4.getModel().getValueAt(jTable4.getModel().getRowCount() - 1, 0).toString();
        try {

            String query = "insert into stransaction(InventoryID,quantity,type_transaction,UserID,date,time)values(?,?,?,?,?,?)";
            pst = con.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(id));//inventory id
            pst.setDouble(2, Double.parseDouble(jquantity.getText()));
            pst.setString(3, types);
//          pst.setString(4, new LoginUsers().id);//username id...
            pst.setInt(4, new LoginUsers().ID());
            pst.setString(5, date);
            pst.setString(6, timee);
            pst.executeUpdate();

        } catch (Exception e) {
            Logger.getLogger(bookshop.class.getName()).log(Level.SEVERE, null, e);
     
        }
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jsoldproduct = new javax.swing.JPanel();
        jsold = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jdate = new javax.swing.JTextField();
        time = new javax.swing.JTextField();
        jadd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jbname = new javax.swing.JTextField();
        jquantity = new javax.swing.JTextField();
        jbuyprice = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jimageproduct = new javax.swing.JButton();
        jproductimage = new javax.swing.JLabel();
        jusernamee = new javax.swing.JLabel();
        jlinkimage = new javax.swing.JTextField();
        jsellprice = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jdescription = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jsoldproduct.setBackground(new java.awt.Color(51, 51, 51));

        jsold.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jsold.setForeground(new java.awt.Color(255, 204, 204));
        jsold.setText("Sold Poducts");
        jsold.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jsoldMouseClicked(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 204, 204));
        jLabel19.setText("Sign-out");
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/flowercon.png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(153, 153, 153));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Date:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Time:");

        jdate.setBackground(new java.awt.Color(51, 51, 51));
        jdate.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jdate.setForeground(new java.awt.Color(255, 51, 51));
        jdate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jdate.setText("0");

        time.setBackground(new java.awt.Color(51, 51, 51));
        time.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
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
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(time, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addComponent(jdate))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jdate, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jsoldproductLayout = new javax.swing.GroupLayout(jsoldproduct);
        jsoldproduct.setLayout(jsoldproductLayout);
        jsoldproductLayout.setHorizontalGroup(
            jsoldproductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jsoldproductLayout.createSequentialGroup()
                .addGroup(jsoldproductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jsoldproductLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jsoldproductLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jsoldproductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jsold)
                            .addComponent(jLabel7)))
                    .addGroup(jsoldproductLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel19)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jsoldproductLayout.setVerticalGroup(
            jsoldproductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jsoldproductLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(jsold)
                .addGap(48, 48, 48)
                .addComponent(jLabel19)
                .addGap(68, 68, 68)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jadd.setBackground(new java.awt.Color(255, 255, 153));
        jadd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jadd.setText("Add");
        jadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jaddActionPerformed(evt);
            }
        });

        jTable4.setBackground(new java.awt.Color(255, 204, 204));
        jTable4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Flower Name", "Description", "Quatity", "Buying Price", "Sellintg Price", "Image"
            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable4);

        jLabel4.setBackground(new java.awt.Color(255, 102, 0));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 102));
        jLabel4.setText("Product Sales");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 102));
        jLabel5.setText("Quantity");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 102));
        jLabel6.setText("Buying Price");

        jbname.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnameActionPerformed(evt);
            }
        });

        jquantity.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jbuyprice.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 51, 51));
        jLabel3.setText("Product Inventory");

        jButton3.setBackground(new java.awt.Color(255, 255, 153));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jimageproduct.setBackground(new java.awt.Color(255, 255, 204));
        jimageproduct.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jimageproduct.setText("Choose Product");
        jimageproduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jimageproductActionPerformed(evt);
            }
        });

        jproductimage.setBackground(new java.awt.Color(255, 255, 204));
        jproductimage.setOpaque(true);

        jusernamee.setBackground(new java.awt.Color(204, 204, 204));
        jusernamee.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jusernamee.setForeground(new java.awt.Color(255, 0, 0));
        jusernamee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jusernamee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/avatarbebie.png"))); // NOI18N
        jusernamee.setText("Username");
        jusernamee.setOpaque(true);

        jlinkimage.setMaximumSize(new java.awt.Dimension(6, 19));
        jlinkimage.setMinimumSize(new java.awt.Dimension(6, 19));

        jsellprice.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 102));
        jLabel8.setText("Selling Price");

        jdescription.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 102));
        jLabel10.setText("Description");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 102));
        jLabel9.setText("Flower Name");

        jButton2.setBackground(new java.awt.Color(255, 255, 204));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 204));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Delete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jsoldproduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(58, 58, 58)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jbuyprice, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jsellprice, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jdescription, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jquantity, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbname, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jimageproduct, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jproductimage, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlinkimage, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jadd, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jusernamee, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(212, 212, 212))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jsoldproduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jusernamee, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jadd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jquantity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jsellprice, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jbuyprice, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jdescription, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jproductimage, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jimageproduct, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76)))
                        .addComponent(jlinkimage, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)))
                .addContainerGap())
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1204, 606));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jaddActionPerformed
         
        String date = jdate.getText();
        String qty = jquantity.getText();
        String timee = time.getText();
        String bname = jbname.getText();
        String desc = jdescription.getText();
        String buyingprice = jbuyprice.getText();
        String sellingprice = jsellprice.getText();
        String img1 = jlinkimage.getText();
        
         if (bname.trim().equals("") || bname.trim().equals("")
                || qty.trim().equals("") || qty.trim().equals("")
                || sellingprice.trim().equals("") || sellingprice.trim().equals("")
                || img1.trim().equals("") || img1.trim().equals("")
                || desc.trim().equals("") || desc.trim().equals("")
                || buyingprice.trim().equals("") || buyingprice.trim().equals("")) {
             JOptionPane.showMessageDialog(null, "Other fields are empty!");
            
        }
             
        else {
             
               product();
//              
             DefaultTableModel df = (DefaultTableModel)jTable4.getModel();
             df.setRowCount(0);
             
//            df = (DefaultTableModel) jTable4.getModel();
//             df.addRow(new Object[]);
//             {
//          
//            jbname.getText(),
//            jquantity.getText(),
//            jbuyprice.getText(),
//            jsellprice.getText(),
//            jdescription.getText(),
//            jlinkimage.getText(),
//        });
         
         
             
            try {
            Statement st7 = con.createStatement();
            String query1 = "select * from `products`";
            ResultSet rs1 = st7.executeQuery(query1);

            while (rs1.next()) {
                //data wil added until finished..
              String id = rs1.getString("barcode");
                String hh = rs1.getString("product_name");
                String des1 = rs1.getString("description");
                String trr = rs1.getString("quantity");
                String buy = rs1.getString("buyingprice");
                String sell1 = rs1.getString("sellingprice");
                String pro = rs1.getString("productImage");

                //string array for store data into jtable..
                String tbData[] = {id, hh, des1, trr, buy, sell1, pro};
                DefaultTableModel tblModel = (DefaultTableModel) jTable4.getModel();

                //add string array data into jtable..
                tblModel.addRow(tbData);

            
            }
             invetorytransac();
         
         }catch (SQLException ex) {
            Logger.getLogger(cashierusers.class.getName()).log(Level.SEVERE, null, ex);
        }
            
         
     
        jbname.setText("");
        jbuyprice.setText("");
        jquantity.setText("");
        jsellprice.setText("");
        jlinkimage.setText("");
          jdescription.setText("");

         }   

    }//GEN-LAST:event_jaddActionPerformed

    private void jsoldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jsoldMouseClicked
        // TODO add your handling code here:

        soldproducts sold = new soldproducts();
        sold.setVisible(true);
        sold.pack();
        sold.setLocationRelativeTo(null);
        sold.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.dispose();

        try {
            Statement st = con.createStatement();
            String query1 = "select * from `sales_products` ";
            ResultSet rs1 = st.executeQuery(query1);

            while (rs1.next()) {
                //data wil added until finished..
                String bid = rs1.getString("Id");
                String salesid = rs1.getString("sales_id");
                String bookn = rs1.getString("bookname");
                String price = rs1.getString("price");
                String qty = rs1.getString("quantity");
                String totl = rs1.getString("total");

                //string array for store data into jtable..
                String tbData[] = {bid, salesid, bookn, price, qty, totl};
                DefaultTableModel tblModel = (DefaultTableModel) jTable5.getModel();

                //add string array data into jtable..
                tblModel.addRow(tbData);

            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(adduser.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jsoldMouseClicked

    private void jbnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbnameActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // TODO add your handling code here:

        DefaultTableModel model2 = (DefaultTableModel) jTable4.getModel();
//          InputStream is=new FileInputStream(new File(photopath));
//          pst.setBlob(5, is);
        int Myindex = jTable4.getSelectedRow();
        int Mycolumn = jTable4.getSelectedColumn();

        String value = model2.getValueAt(Myindex, Mycolumn).toString();
        int id = Integer.parseInt(model2.getValueAt(Myindex, 0).toString());
        jbname.setText(model2.getValueAt(Myindex, 1).toString());
        jdescription.setText(model2.getValueAt(Myindex, 2).toString());
        jquantity.setText(model2.getValueAt(Myindex, 3).toString());
        jbuyprice.setText(model2.getValueAt(Myindex, 4).toString());
        jsellprice.setText(model2.getValueAt(Myindex, 5).toString());

        jlinkimage.setText(model2.getValueAt(Myindex, 6).toString());

        jadd.setEnabled(false);


    }//GEN-LAST:event_jTable4MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jbname.setText("");
        jbuyprice.setText("");
        jquantity.setText("");
        jsellprice.setText("");
        jlinkimage.setText("");
      jadd.setEnabled(true);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        LoginUsers main = new LoginUsers();
        main.setVisible(true);
        main.pack();
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel19MouseClicked
//for selecting the photo
    private void jimageproductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jimageproductActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter fnef = new FileNameExtensionFilter(".Flower-Sales", "jpg", "png", "webp", "jpeg");
        chooser.addChoosableFileFilter(fnef);
        int ans = chooser.showSaveDialog(null);
        if (ans == JFileChooser.APPROVE_OPTION) {
            File selectedPhoto = chooser.getSelectedFile();
            String path = selectedPhoto.getAbsolutePath();
            jproductimage.setIcon(resetImageSize(path, null));
            this.photopath = path;
            jlinkimage.setText(path);
            //or...
//            displayImage(path, jproductimage);
            System.out.println(path);

        } else {
            System.out.println("no file selected");
        }

    }//GEN-LAST:event_jimageproductActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        LoginUsers cashregis = new LoginUsers();
        cashregis.setVisible(true);
        cashregis.pack();
        cashregis.setLocationRelativeTo(null);
        cashregis.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model2 = (DefaultTableModel) jTable4.getModel();
        int Myindex = jTable4.getSelectedRow();
        int Mycolumn = jTable4.getSelectedColumn();

        String value = model2.getValueAt(Myindex, Mycolumn).toString();
        int id = Integer.parseInt(model2.getValueAt(Myindex, 0).toString());
        String bname = jbname.getText();
        String qty = jquantity.getText();
        String bprice = jbuyprice.getText();
        String sprice = jsellprice.getText();
        String des = jdescription.getText();
        String imgl = jlinkimage.getText();
  
        invetorytransacupdate();
        try{
            pst = con.prepareStatement("UPDATE `products` set product_name= ?, quantity= ?, buyingprice= ?,sellingprice= ?,description= ?,productImage= ? where barcode= ?");
            //
            pst.setString(1, bname);
            pst.setString(2, qty);
            pst.setString(3,bprice);
            pst.setString(4,sprice);
            pst.setString(5,des);
            pst.setString(6,imgl);
            pst.setInt(7, id);

            int k= pst.executeUpdate();

            if(k==1)
            {
                JOptionPane.showMessageDialog(this, "Successfully Updated");
                jbname.setText("");
                jquantity.setText("");
                jbuyprice.setText("");
                jsellprice.setText("");
                 jdescription.setText("");
                 jlinkimage.setText("");
                jbname.requestFocus();

                salesupdate();

            

            }
            
           
               
        } catch (SQLException ex) {
            Logger.getLogger(inventorypage.class.getName()).log(Level.SEVERE, null, ex);
        }
        jadd.setEnabled(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        invetorytransacdelete();
        int row =jTable4.getSelectedRow();
        String cell = jTable4.getModel().getValueAt(row, 0).toString();
        String sql="DELETE FROM `products` where barcode= " + cell;

        try{

            pst = con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Successfully Deleted!");
            updateproductsales();
            
          

        }catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);

        }finally{
            try{
                pst.close();
                rs.close();
          
            }catch (Exception e){
            }

            DefaultTableModel model = (DefaultTableModel)jTable4.getModel();
            //delete row
            if(jTable4.getSelectedRowCount()==1){
                //if single row is selected then delete
                model.removeRow(jTable4.getSelectedRow());
                jbname.setText("");
                jquantity.setText("");
                jbuyprice.setText("");
                jsellprice.setText("");
                jdescription.setText("");
                jlinkimage.setText("");

//                         JOptionPane.showMessageDialog(this, "");
//                
//                         }else{
//                             if(jTable6.getRowCount()==0){
//                                     //if table is not empty(no data) than display message
//                                     JOptionPane.showMessageDialog(this, "Table is empty.");
//                                 }else{
//                                     //if table is not empty but row is not selected or multiple row is selected
//                    
//                                      JOptionPane.showMessageDialog(this, "Please select a single row to delete.");
//                                 }
                
                          
        }
           
        }
           
        jadd.setEnabled(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(inventorypage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inventorypage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inventorypage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inventorypage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new inventorypage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable4;
    private javax.swing.JButton jadd;
    private javax.swing.JTextField jbname;
    private javax.swing.JTextField jbuyprice;
    private javax.swing.JTextField jdate;
    private javax.swing.JTextField jdescription;
    private javax.swing.JButton jimageproduct;
    private javax.swing.JTextField jlinkimage;
    private javax.swing.JLabel jproductimage;
    private javax.swing.JTextField jquantity;
    private javax.swing.JTextField jsellprice;
    private javax.swing.JLabel jsold;
    private javax.swing.JPanel jsoldproduct;
    public javax.swing.JLabel jusernamee;
    private javax.swing.JTextField time;
    // End of variables declaration//GEN-END:variables
}

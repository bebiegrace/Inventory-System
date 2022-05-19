/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.GregorianCalendar;
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
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
//import static src.cashierregistrants.jTable2;
import static src.category.jTable6;
import static src.categoryinventory.jTable8;
import static src.inventorypersonregistrants.jTable3;
import static src.soldproducts.jTable5;

/**
 *
 * @author 1styrGroupB
 */
public class inventorypage extends javax.swing.JFrame {

   String filename = null;
   byte[] product_image=null;
    public inventorypage() {
        initComponents();
        Connect();
        dt();
        time();
//        booksupdate();
    }
     public static void AddRowToJcategoryTable(Object[] dataRow)   
     {
       DefaultTableModel model6 = (DefaultTableModel)jTable6.getModel(); 
       model6.addRow(dataRow);
     }
     public static void AddRowToJsoldproductTable(Object[] dataRow)   
     {
       DefaultTableModel model5 = (DefaultTableModel)jTable5.getModel(); 
       model5.addRow(dataRow);
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
  
  String photopath="";
  public ImageIcon resetImageSize(String photopath,byte[] photo){
     ImageIcon myPhoto=null;
     if (photopath!=null){
      myPhoto=new ImageIcon(photopath);
      
  }else{
          myPhoto=new ImageIcon(photo);
          }
       Image img = myPhoto.getImage();
       Image img1 =img.getScaledInstance(jproductimage.getWidth(),jproductimage.getHeight(),
                  Image.SCALE_SMOOTH);
       ImageIcon ph=new ImageIcon(img1);
       return ph;
  }
 public void updateproductsales(){
        String sql ="select from `products`";
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
     
  public void product()
  {
//      Timer time;
//      SimpleDateFormat st;
//      time = new Timer(0, new ActionListener(){
//          
//      })
      
      Calendar cal = new GregorianCalendar();
      DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
      LocalDateTime now = LocalDateTime.now();
      String date = dt.format(now);
      String timee =time.getText();
      
//      int second =cal.get(Calendar.SECOND);
//      int minute = cal.get(Calendar.MINUTE);
//      int hour = cal.get(Calendar.HOUR);
//      times.setText(hour+":"+(minute)+":"+second);
      
//        Date dta = new Date();
//        st = new SimpleDateFormat("hh-mm-ss a");
//        LocalTime now1 = LocalTime.now();
//        String tt =st.format(now1);
      
      String bname = jbname.getText();
      String quantity = jquantity.getText();
      String price = jprice.getText();
      String image =jproductimage.getText();
      int damm =0;
      
      
              
      
      try{
          
          String query ="insert into `products`(product_name,quantity,price,date_addproduct,time_addproduct,productImage)values(?,?,?,?,?,?);";
          pst = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
          pst.setString(1, bname);
          pst.setFloat(2, Float.parseFloat(jquantity.getText()));
          pst.setString(3, price);
          pst.setString(4, date);
           pst.setString(5, timee);
      
          
          InputStream is=new FileInputStream(new File(photopath));
          pst.setBlob(6, is);
          
           pst.executeUpdate();
           rs = pst.getGeneratedKeys();
         
           JOptionPane.showMessageDialog(this, "Product Added");
          
      }catch(Exception e){
           Logger.getLogger(bookshop.class.getName()).log(Level.SEVERE, null, e);
//      
      }
  }
//   public void booksupdate(){
//       try{
//           pst = con.prepareStatement("select * from products");
//           rs = pst.executeQuery();
//           
//           ResultSetMetaData rsd = rs.getMetaData();
//           int c;
//           
//           c = rsd.getColumnCount();
//           DefaultTableModel de = (DefaultTableModel)jTable4.getModel();
//           de.setRowCount(0);
//           
//           while(rs.next())
//           {
//               Vector v2 = new Vector();
//               for(int i=0; i<=c; i++)
//               {
////                   v2.add(rs.getString("id"));
//                   v2.add(rs.getString("bookname"));
//                   v2.add(rs.getString("quantity"));
//                   v2.add(rs.getString("price"));
//                    v2.add(rs.getString("price"));
//               }
//               de.addRow(v2);
//           }
//       } catch (SQLException ex) {
//            Logger.getLogger(inventorypage.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//   }
   
   
      
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jsoldproduct = new javax.swing.JPanel();
        jcategory = new javax.swing.JLabel();
        jsold = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        date = new javax.swing.JTextField();
        time = new javax.swing.JTextField();
        jadd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jbname = new javax.swing.JTextField();
        jquantity = new javax.swing.JTextField();
        jprice = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jimageproduct = new javax.swing.JButton();
        jproductimage = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jsoldproduct.setBackground(new java.awt.Color(51, 51, 51));

        jcategory.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jcategory.setForeground(new java.awt.Color(255, 204, 204));
        jcategory.setText("Available Products");
        jcategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcategoryMouseClicked(evt);
            }
        });

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

        date.setBackground(new java.awt.Color(51, 51, 51));
        date.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        date.setForeground(new java.awt.Color(255, 51, 51));
        date.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        date.setText("0");

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
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(time)
                    .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jsoldproductLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jcategory)
                .addGap(28, 28, 28))
            .addGroup(jsoldproductLayout.createSequentialGroup()
                .addGroup(jsoldproductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jsoldproductLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel7))
                    .addGroup(jsoldproductLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jsold))
                    .addGroup(jsoldproductLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel19))
                    .addGroup(jsoldproductLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jsoldproductLayout.setVerticalGroup(
            jsoldproductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jsoldproductLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(jcategory)
                .addGap(51, 51, 51)
                .addComponent(jsold)
                .addGap(54, 54, 54)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );

        jadd.setBackground(new java.awt.Color(255, 255, 153));
        jadd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jadd.setText("Add");
        jadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jaddActionPerformed(evt);
            }
        });

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Flower Name", "Quatity", "Price", "Image"
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

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 102));
        jLabel2.setText("Flower Name");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 102));
        jLabel5.setText("Quantity");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 102));
        jLabel6.setText("Price");

        jbname.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnameActionPerformed(evt);
            }
        });

        jquantity.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jprice.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setText("Username");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jsoldproduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel6))
                                        .addGap(28, 28, 28)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jbname, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                            .addComponent(jquantity)
                                            .addComponent(jprice)))
                                    .addComponent(jimageproduct, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jproductimage, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jadd, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(91, 91, 91)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(121, 121, 121))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(246, 246, 246))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(769, 769, 769)
                .addComponent(jLabel1)
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(jquantity, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jbname, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(83, 83, 83)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jprice, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jimageproduct, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jproductimage, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(51, 51, 51)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jadd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(35, 35, 35))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jsoldproduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1155, 619));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jaddActionPerformed
        // TODO add your handling code here:
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String date = dt.format(now);
//         t = new Timer(0, new ActionListener();

        String bname = jbname.getText();
        String quantity = jquantity.getText();
        String price = jprice.getText();
        String image = jproductimage.getText();
       
      
         
 
         if (bname.trim().equals("") || bname.trim().equals("") ||   
            quantity.trim().equals("") || quantity.trim().equals("") ||
//            image.trim().equals("") || image.trim().equals("") ||
            price.trim().equals("") ||  price.trim().equals(""))
                
            {
            JOptionPane.showMessageDialog(null, "Other fields are empty!");
            }else{
             
              product();
         }
         
         
         df = (DefaultTableModel)jTable4.getModel();
         df.addRow(new Object[]
        {
            
            jbname.getText(),
            jprice.getText(),
            jquantity.getText(),
            jproductimage.getText(),
      
        });
         
         
         
         try {
            Statement st = con.createStatement();
            String query1 = "select * from `products`";
            ResultSet rs1 = st.executeQuery(query1);
            
            while(rs1.next()){
                //data wil added until finished..
//              String id = rs1.getString("id");
                String hh = rs1.getString("product_name");
                String trr = rs1.getString("quantity");
                String err = rs1.getString("price");
                String pro = rs1.getString("productImage");
               
                
                //string array for store data into jtable..
                String tbData[] = {hh, trr ,err,pro};
                DefaultTableModel tblModel = (DefaultTableModel)jTable4.getModel();

                //add string array data into jtable..
                
                tblModel.addRow(tbData);

            }
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(cashierusers.class.getName()).log(Level.SEVERE, null, ex);
        }
               
             
               jbname.setText("");
               jprice.setText("");
               jquantity.setText("");
//               jproductimage.getIcon("");
          
        
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
            
            while(rs1.next()){
                //data wil added until finished..
                String bid = rs1.getString("Id");
                String salesid = rs1.getString("sales_id");
                String bookn = rs1.getString("bookname");
                String price = rs1.getString("price");
                String qty = rs1.getString("quantity");
                String totl = rs1.getString("total");
                
                //string array for store data into jtable..
                String tbData[] = {bid,salesid,bookn,price,qty,totl};
                  DefaultTableModel tblModel = (DefaultTableModel)jTable5.getModel();

                //add string array data into jtable..
                
                tblModel.addRow(tbData);

            }
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(adduser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_jsoldMouseClicked

    private void jcategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcategoryMouseClicked
        // TODO add your handling code here:
        
        categoryinventory cat = new categoryinventory();
        cat.setVisible(true);
        cat.pack();
        cat.setLocationRelativeTo(null);
        cat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.dispose();
        
         try {
            Statement st = con.createStatement();
            String query1 = "select * from `book`";
            ResultSet rs1 = st.executeQuery(query1);
            
            while(rs1.next()){
                //data wil added until finished..
              
                String bid = rs1.getString("id");
                String bookn = rs1.getString("bookname");
                String qtys = rs1.getString("quantity");
                String price2 = rs1.getString("price");
                
                
                //string array for store data into jtable..
                String tbData[] = {bid,bookn,qtys,price2};
                  DefaultTableModel tblModel = (DefaultTableModel)jTable8.getModel();

                //add string array data into jtable..
                
                tblModel.addRow(tbData);

            }
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(adduser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jcategoryMouseClicked

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
        
//        String value = model2.getValueAt(Myindex, Mycolumn).toString();
        
//        int id = Integer.parseInt(model2.getValueAt(Myindex, 0).toString());
        jbname.setText(model2.getValueAt(Myindex, 0).toString());
        jquantity.setText(model2.getValueAt(Myindex, 1).toString());
        jprice.setText(model2.getValueAt(Myindex, 2).toString());
        jprice.setText(model2.getValueAt(Myindex, 3).toString());
        jproductimage.setText(model2.getValueAt(Myindex, 4).toString());
       
        
//        byte[] img = 
     
    }//GEN-LAST:event_jTable4MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jbname.setText("");
        jquantity.setText("");
        jprice.setText("");
        

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        LoginUsers main = new  LoginUsers();
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
       FileNameExtensionFilter fnef = new FileNameExtensionFilter(".Flower-Sales", "jpg","png","webp");
        chooser.addChoosableFileFilter(fnef);
        int ans=chooser.showSaveDialog(null);
        if (ans==JFileChooser.APPROVE_OPTION){
            File selectedPhoto=chooser.getSelectedFile();
            String path=selectedPhoto.getAbsolutePath();
            jproductimage.setIcon(resetImageSize(path, null));
            this.photopath=path;
        }
        





// TODO add your handling code here:
//        JFileChooser chooser = new JFileChooser();
//        chooser.showOpenDialog(null);
//        File f = chooser.getSelectedFile();
//        filename = f.getAbsolutePath();
//        ImageIcon imageIcon = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(lbl_img.getWidth(),lbl-img.getHeight(),Image.SCALE_SMOOTH));
//        lbl_img.setIcon(imageIcon);
        
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
                new inventorypage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField date;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable4;
    private javax.swing.JButton jadd;
    private javax.swing.JTextField jbname;
    private javax.swing.JLabel jcategory;
    private javax.swing.JButton jimageproduct;
    private javax.swing.JTextField jprice;
    private javax.swing.JLabel jproductimage;
    private javax.swing.JTextField jquantity;
    private javax.swing.JLabel jsold;
    private javax.swing.JPanel jsoldproduct;
    private javax.swing.JTextField time;
    // End of variables declaration//GEN-END:variables
}

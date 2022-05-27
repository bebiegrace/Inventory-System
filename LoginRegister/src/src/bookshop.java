/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
//import static src.cashierregistrants.jTable2;
import static src.category.jTable6;
import static src.categorycashier.jTable56;
import static src.soldproducts.jTable5;

/**
 *
 * @author 1styrGroupB
 */
public class bookshop extends javax.swing.JFrame {

//    private static TableModel resultSetToTableModel(ResultSet RS) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    /**
     * Creates new form bookshop
     */
    public bookshop() {
        initComponents();
        Connect();
        dt();
        time();
//        cathegory();

    }
    public bookshop(String usernamee) {
        initComponents();
        jusername.setText(usernamee);
         Connect();
         dt();
        time();
//        cathegory();

    }
    
    
     public static void AddRowToJcategoryTable(Object[] dataRow)   
     {
       DefaultTableModel model6 = (DefaultTableModel)jTable6.getModel(); 
       model6.addRow(dataRow);
     }
    
      
    Connection con;
    PreparedStatement pst;
    PreparedStatement pst1;
     PreparedStatement pst2;
    ResultSet rs;
    DefaultTableModel df;
    
    Connection con1 = null;
    Statement st = null;
    ResultSet RS = null;
    
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
//    public void cathegory(){
        
//     try {
//            Statement st = con.createStatement();
//            String query1 = "select * from `products`";
//            ResultSet rs1 = st.executeQuery(query1);
//
//            while(rs1.next()){
//                //data wil added until finished..
//
//                String bid = rs1.getString("barcode");
//                String bookn = rs1.getString("product_name");
//                String des = rs1.getString("description");
//                String qty = rs1.getString("quantity");
//               
//                  String price2 = rs1.getString("sellingprice");
//                   String image = rs1.getString("productImage");
//
//                //string array for store data into jtable..
//                String tbData[] = {bid,bookn,des,qty,price2,image};
//                DefaultTableModel tblModel = (DefaultTableModel)jTable7.getModel();
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
//    }
  public void purchased(){
     
        try {
            String brcode = jcode.getText();
            pst=con.prepareStatement("select * from products where barcode=?");
            rs=pst.executeQuery();
            
            while(rs.next())
            {
                int currentquantity;
                currentquantity=rs.getInt("quantity");
                int price=Integer.parseInt(jprice.getText());
                int qty=Integer.parseInt(quantity.getValue().toString());
                int totn = price * qty;
                
                if(qty >= currentquantity){
                    JOptionPane.showMessageDialog(this, "Stock product quantity is not enough!!!");
                    
                }
                else{
                    df = (DefaultTableModel)jTable2.getModel();
                    df.addRow(new Object[]
                    {
                        jcode.getText(),
                        jbname.getText(),
                        jprice.getText(),
                        quantity.getValue().toString(),
                        totn
                    
                    });
                            
                                
                            
                }
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(bookshop.class.getName()).log(Level.SEVERE, null, ex);
        }
     
  }
//   public void printsales(){
//            
//        }
  

  public void sales()
  {
      
    
      
      String totalcost = jtcost.getText();
      String pay = jpay.getText();
      String bal = jbalance.getText();
      int damm =0;
      
      try{
          String query ="insert into `sales`(subtotal,pay,balance)values(?,?,?);";
          pst = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
          pst.setString(1, totalcost);
          pst.setString(2, pay);
          pst.setString(3, bal);
          pst.executeUpdate();
          rs = pst.getGeneratedKeys();
         
          if(rs.next())
          {
              damm = rs.getInt(1);
          }
            int rows = jTable2.getRowCount();
            
            String query1 = "insert into `sales_products`(sales_id, bookname, price, quantity, total) values (?,?,?,?,?);";
            pst1 =con.prepareStatement(query1);
  
           String bname ="";
            String price;
            String qty;
//            String id;
            int total = 0;
            for(int i=0; i<jTable2.getRowCount(); i++)
            {
//                id = (String)jTable2.getValueAt(i, 0);
                bname = (String)jTable2.getValueAt(i, 0);
                price = (String)jTable2.getValueAt(i, 1);
                qty = (String)jTable2.getValueAt(i, 2);
                total = (int)jTable2.getValueAt(i, 3);
                
                pst1.setInt(1, damm);
                pst1.setString(2, bname);
                pst1.setString(3, price);
                pst1.setString(4, qty);
                pst1.setInt(5, total);
                pst1.executeUpdate();
                
            }
            
//            String query4="update products set quantity=quantity- ? where barcode =?";
//            pst2=con.prepareStatement(query4);
//            
//            for (int i=0; i<jTable2.getRowCount(); i++)
//            {
//                
//                qty=(String)jTable2.getValueAt(i, 3);
//                
//                pst2.setString(1, qty);
//                pst2.setString(2, productid);
//                pst2.executeUpdate();
//            }
            
            JOptionPane.showMessageDialog(this, "Sales completed");
          
      }catch(SQLException ex){
           Logger.getLogger(bookshop.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
   public void dt(){
         
         Date d = new Date();
         
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         
         String dd = sdf.format(d);
         date.setText(dd);
     }
         Timer t;
         SimpleDateFormat stt;
          public void time(){
              t = new Timer(0, new ActionListener(){
                  @Override
                  public void actionPerformed(ActionEvent e){
                      Date dt = new Date();
                      stt = new SimpleDateFormat("hh-mm-ss a");
                      String tt =stt.format(dt);
                      time.setText(tt);
                      
                  }
              });
                  t.start();    
          }

       
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jusername = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        quantity = new javax.swing.JSpinner();
        jcode = new javax.swing.JTextField();
        jbname = new javax.swing.JTextField();
        jprice = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jtcost = new javax.swing.JTextField();
        jpay = new javax.swing.JTextField();
        jbalance = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jtquantity = new javax.swing.JTextField();
        jtotalq = new javax.swing.JTextField();
        jtotalc = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jcategory = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        date = new javax.swing.JTextField();
        time = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jreciept = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jButtondelete = new javax.swing.JButton();
        jadd = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel3.setBackground(new java.awt.Color(255, 153, 102));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 247, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 163, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jusername.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jusername.setForeground(new java.awt.Color(255, 0, 0));
        jusername.setText("Cashieruser");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/avatarbebie.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jusername, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jusername, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));
        jPanel2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jPanel2ComponentHidden(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Flower Code");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Product name");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Price");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Quantity");

        quantity.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jcode.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcodeActionPerformed(evt);
            }
        });
        jcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcodeKeyPressed(evt);
            }
        });

        jbname.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jprice.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Total Cost");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Pay");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Balance");

        jtcost.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtcost.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtcostMouseClicked(evt);
            }
        });

        jpay.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jbalance.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Total Qty");

        jtquantity.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jtotalq.setBackground(new java.awt.Color(255, 204, 204));
        jtotalq.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jtotalc.setBackground(new java.awt.Color(255, 204, 204));
        jtotalc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(11, 11, 11))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcode, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jbname, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                .addComponent(jprice))
                            .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel6))))))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jpay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                    .addComponent(jtcost, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtquantity, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbalance))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtotalq)
                    .addComponent(jtotalc, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtquantity, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jcode, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addComponent(jLabel9))))
                    .addComponent(jtotalq))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbname, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtcost, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtotalc, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jprice, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8)
                            .addComponent(quantity, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                            .addComponent(jbalance))
                        .addGap(67, 67, 67))))
        );

        jTable2.setBackground(new java.awt.Color(255, 204, 204));
        jTable2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Flower_Name", "Price", "Quantity", "Total"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(3).setResizable(false);
        }

        jButton1.setBackground(new java.awt.Color(255, 255, 204));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Print Invoice");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));

        jcategory.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jcategory.setForeground(new java.awt.Color(255, 204, 204));
        jcategory.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jcategory.setText("Available Sales");
        jcategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcategoryMouseClicked(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 204, 204));
        jLabel18.setText("Exit");
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
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

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/flowercon.png"))); // NOI18N
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(153, 153, 153));

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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addComponent(time))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(jLabel19))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(jLabel18)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 10, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jcategory, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(jcategory)
                .addGap(39, 39, 39)
                .addComponent(jLabel19)
                .addGap(46, 46, 46)
                .addComponent(jLabel18)
                .addGap(74, 74, 74)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1605, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(153, 153, 153));

        jreciept.setBackground(new java.awt.Color(255, 204, 204));
        jreciept.setColumns(20);
        jreciept.setRows(5);
        jScrollPane3.setViewportView(jreciept);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122))
        );

        jButton2.setBackground(new java.awt.Color(255, 255, 153));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButtondelete.setBackground(new java.awt.Color(255, 255, 153));
        jButtondelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtondelete.setText("Cancel");
        jButtondelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtondeleteActionPerformed(evt);
            }
        });

        jadd.setBackground(new java.awt.Color(255, 255, 153));
        jadd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jadd.setText("Add");
        jadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jaddActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("Cashier Management");

        jTable7.setBackground(new java.awt.Color(255, 204, 204));
        jTable7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Flowername", "Description", "Quantity", "Selling Price", "productImage"
            }
        ));
        jTable7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable7MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable7);
        if (jTable7.getColumnModel().getColumnCount() > 0) {
            jTable7.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 102, 102));
        jLabel14.setText("Available Products");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(278, 278, 278)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jadd, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jButtondelete)
                                .addGap(27, 27, 27)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69))))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jadd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtondelete, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(25, 25, 25)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1255, 673));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel2ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel2ComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2ComponentHidden

    private void jcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcodeKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            try{
                String bcode = jcode.getText();
                pst = (PreparedStatement) con.prepareStatement("select * from products where barcode = ?");
                pst.setString(1, bcode);
                rs = pst.executeQuery();
                
                if(rs.next() == false)
                {
                    JOptionPane.showMessageDialog(this, "Book Code not Found");
                }
                else
                {
                    String bname = rs.getString("product_name");
                    jbname.setText(bname.trim());
                    
                    String price = rs.getString("sellingprice");
                    jprice.setText(price.trim());
                    
                    quantity.requestFocus();
                }
            } catch (SQLException ex) {
                Logger.getLogger(bookshop.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_jcodeKeyPressed

    private void jaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jaddActionPerformed
        try {
            // TODO add your handling code here:
            
//            pst=con.prepareStatement("select * from products where barcode=?");
//            rs=pst.executeQuery();
//            
            
             int currentquantity;
             currentquantity=rs.getInt("quantity");
            
            int price =     Integer.parseInt(jprice.getText());
            int qty =     Integer.parseInt(quantity.getValue().toString());
            
            int totl = price * qty;
             if(qty >= currentquantity){
                    JOptionPane.showMessageDialog(this, "Stock product quantity is not enough!!!");
                    
                }
                else{
            df = (DefaultTableModel)jTable2.getModel();
            df.addRow(new Object[]
            {
//                jcode.getText(),
                jbname.getText(),
                jprice.getText(),
                quantity.getValue().toString(),
                totl
            });
             }
            int sum = 0;
            int sum1 = 0;
            for(int i=0; i<jTable2.getRowCount(); i++)
            {
                sum1 = sum1 + Integer.parseInt(jTable2.getValueAt(i, 2).toString());   
                sum = sum + Integer.parseInt(jTable2.getValueAt(i, 3).toString());              
            }
            
//           int numrow = jTable2.getRowCount();
//           double tot = 0;
//           for (int i = 0; i<numrow; i++){
//               double val = Double.valueOf(jTable2.getValueAt(i, 2).toString());
//               tot += val;
//           
//        }
   
            jtcost.setText(String.valueOf(sum));
            jtquantity.setText(String.valueOf(sum1));
             jtotalc.setText(String.valueOf(sum));
            jtotalq.setText(String.valueOf(sum1));
           
            
//        jcode.setText("");
//        jbname.setText("");
//        jprice.setText("");
//        quantity.setValue(0);
//        jcode.requestFocus();
        } catch (SQLException ex) {
            Logger.getLogger(bookshop.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jaddActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
       
         
        
        //================================================
        int pay = Integer.parseInt(jpay.getText());
        int totalcost = Integer.parseInt(jtcost.getText());
        
        int bal = pay - totalcost;
        jbalance.setText(String.valueOf(bal));
        String na=  jbname.getText();
        String pric = jprice.getText();
        String quan= jtotalq.getText().toString();
//        String quan= quantity.getValue().toString();
        String cost = jtotalc.getText();
        String balanc = jbalance.getText();
        String cash = jpay.getText();
    
        
        sales();
        Date da = new Date();
        String datet =da.toString();
        jreciept.setText(jreciept.getText() + "+++++++++BEBIE'S FLOWER SHOP+++++++++++++\n\n");
        jreciept.setText(jreciept.getText() + "\n====================================\n\n");
        jreciept.setText(jreciept.getText() + "              \n  " + datet + "           \n\n");
        jreciept.setText(jreciept.getText() + "\n______________________________________\n\n");
        jreciept.setText(jreciept.getText() + "Flower Name  +++++++++++++++ :   " + na + "\n\n");
        jreciept.setText(jreciept.getText() + "Price        +++++++++++++++ : " + pric + "\n\n");
        jreciept.setText(jreciept.getText() + "Quatity      +++++++++++++++ : " + quan + "\n\n");
        jreciept.setText(jreciept.getText() + "Total Cost   +++++++++++++++ : " + cost + "\n\n");
        jreciept.setText(jreciept.getText() + "Cash         +++++++++++++++ : " + cash + "\n\n");
        jreciept.setText(jreciept.getText() + "-------------------------------------------------\n\n");
        jreciept.setText(jreciept.getText() + "Change       +++++++++++++++ : " + balanc + "\n\n");
        jreciept.setText(jreciept.getText() + "______________________________________________\n\n");
        jreciept.setText(jreciept.getText() + "Save and Purchase\n\n");
        jreciept.setText(jreciept.getText() + "Thank you for availling our products..\n\n");
        
               jcode.setText("");
               jbname.setText("");
               jprice.setText("");
               quantity.setValue(0);
               jtcost.setText("");
               jpay.setText("");
//               jbalance.setText("");
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jcategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcategoryMouseClicked
        // TODO add your handling code here:
        
 
        categorycashier cash = new categorycashier(jusername.getText());
        cash.show();
        dispose();


        
    }//GEN-LAST:event_jcategoryMouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
     
     
            DefaultTableModel model1 = (DefaultTableModel)jTable2.getModel();
            int Myindex = jTable2.getSelectedRow();
            int Mycolumn = jTable2.getSelectedColumn();
            
            String value = model1.getValueAt(Myindex, Mycolumn).toString();
            
            jbname.setText(model1.getValueAt(Myindex, 0).toString());
            jprice.setText(model1.getValueAt(Myindex, 1).toString());
            int quantity_ = Integer.parseInt(model1.getValueAt(Myindex, 2).toString());
            quantity.setValue((int)quantity_);
         
            jtcost.setText(model1.getValueAt(Myindex, 3).toString());
            jtquantity.setText(model1.getValueAt(Myindex, 2).toString());
            
       
   
      
        
        
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButtondeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtondeleteActionPerformed
        // TODO add your handling code here:
       
         DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
         //delete row
         if(jTable2.getSelectedRowCount()==1){
             //if single row is selected then delete
             model.removeRow(jTable2.getSelectedRow());
             jcode.setText("");
             jbname.setText("");
             jprice.setText("");
             quantity.setValue(0);
              
            //to subtract the total cost from the deleted sales
            int numrow = jTable2.getRowCount();
            double tot = 0;
            double tot1 = 0;
            
            for (int i = 0; i<numrow; i++){
                double val = Double.valueOf(jTable2.getValueAt(i, 3).toString());
                double val1 = Double.valueOf(jTable2.getValueAt(i, 2).toString());
                tot += val;   
                tot1 += val1; 
            }          
            jtcost.setText(Double.toString(tot));
            jtquantity.setText(Double.toString(tot1));
            jtotalc.setText(Double.toString(tot));
            jtotalq.setText(Double.toString(tot1));
            
            JOptionPane.showMessageDialog(this, "Order Cancelled!!");
           
            }else{
             if(jTable2.getRowCount()==0){
                 //if table is not empty(no data) than display message
                 JOptionPane.showMessageDialog(this, "Table is empty.");
             }else{
                 //if table is not empty but row is not selected or multiple row is selected
                 
                  JOptionPane.showMessageDialog(this, "Please select a single row to delete.");
             }
         }
       
    }//GEN-LAST:event_jButtondeleteActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
               jcode.setText("");
               jbname.setText("");
               jprice.setText("");
               quantity.setValue(0);
             
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcodeActionPerformed

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        // TODO add your handling code here:
               System.exit(0);
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        // TODO add your handling code here:
        
         LoginUsers main = new  LoginUsers();
        main.setVisible(true);
        main.pack();
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
        
         LoginUsers cashregis = new LoginUsers();
        cashregis.setVisible(true);
        cashregis.pack();
        cashregis.setLocationRelativeTo(null);
        cashregis.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jtcostMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtcostMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtcostMouseClicked

    private void jTable7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable7MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
        try {
            Statement st = con.createStatement();
            String query1 = "select * from `products`";
            ResultSet rs1 = st.executeQuery(query1);

            while(rs1.next()){
                //data wil added until finished..

                String bid = rs1.getString("barcode");
                String bookn = rs1.getString("product_name");
                String des = rs1.getString("description");
                String qty = rs1.getString("quantity");
               
                  String price2 = rs1.getString("sellingprice");
                   String image = rs1.getString("productImage");

                //string array for store data into jtable..
                String tbData[] = {bid,bookn,des,qty,price2,image};
                DefaultTableModel tblModel = (DefaultTableModel)jTable7.getModel();

                //add string array data into jtable..

                tblModel.addRow(tbData);

            }

//            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(adduser.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }//GEN-LAST:event_jLabel14MouseClicked

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
            java.util.logging.Logger.getLogger(bookshop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(bookshop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(bookshop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(bookshop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new bookshop().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField date;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtondelete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable7;
    private javax.swing.JButton jadd;
    private javax.swing.JTextField jbalance;
    private javax.swing.JTextField jbname;
    private javax.swing.JLabel jcategory;
    private javax.swing.JTextField jcode;
    private javax.swing.JTextField jpay;
    private javax.swing.JTextField jprice;
    private javax.swing.JTextArea jreciept;
    private javax.swing.JTextField jtcost;
    private javax.swing.JTextField jtotalc;
    private javax.swing.JTextField jtotalq;
    private javax.swing.JTextField jtquantity;
    private javax.swing.JLabel jusername;
    private javax.swing.JSpinner quantity;
    private javax.swing.JTextField time;
    // End of variables declaration//GEN-END:variables

//    private void SelectedCat() {
//       try{
//        con = DriverManager.getConnection("jdbc:mysql://localhost/bebieinventorysystem", "root", "");
//        st= con.createStatement();
//        RS=st.executeQuery("select * from `sales_products`");
//        jTable2.setModel(bookshop.resultSetToTableModel(RS));
//       }catch(SQLException e){
//           e.printStackTrace();
//             
//       }
//    }
}

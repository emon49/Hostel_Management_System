
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class Student_Bill_details extends javax.swing.JFrame {

    Connection conn;
   Statement statement;
   ResultSet resultSet,resultSet2;
    String Stu_id;
    public Student_Bill_details() {
        initComponents();
    }
     public Student_Bill_details(String idd) {
        initComponents();
        OracleConnection OC=new OracleConnection();
        String[] stringArray = OC.connection();
        
        try{
           conn=DriverManager.getConnection(stringArray[0],stringArray[1],stringArray[2]);
            if(conn!=null)
            {
                
                System.out.println("Connection Sucessful in Student_Bill_details");
                statement=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            }
            
            
        }catch(SQLException e){
            System.out.println("Connection failed");
        }
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
        Stu_id=idd;
        
        Showdata();
    }

     void Showdata()
     {
          String query=String.format("select S_Name  from Student_Information where S_ID='%s'",Stu_id);
        try {
            resultSet=statement.executeQuery(query);
            resultSet.next();
            String name=resultSet.getString("S_Name");
            namelabel.setText(name);
            
            String query2=String.format("select * from Bills where S_ID='%s'",Stu_id);
            resultSet2=statement.executeQuery(query2);
            resultSet2.next();
           
                int hallbill=resultSet2.getInt("Hall_Bill");
                int messbill=resultSet2.getInt("Mess_Bill");
                int laundrybill=resultSet2.getInt("Laundary_Bill");
                int fine=resultSet2.getInt("Fine");
                int addbill=resultSet2.getInt("Additional_Bill");
                int total=hallbill+messbill+laundrybill+fine+addbill;
                
                
                hallbilllabel.setText(hallbill+"");
                messbilllabel.setText(messbill+"");
                laundrybilllabel.setText(laundrybill+"");
                finalabel.setText(fine+"");
                additionalbill.setText(addbill+"");
                totalbilllabel.setText(total+"");
                
                
           
           } catch (SQLException ex) {
            Logger.getLogger(Student_Bill_details.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel1 = new javax.swing.JLabel();
        namelabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        hallbilllabel = new javax.swing.JLabel();
        messbilllabel = new javax.swing.JLabel();
        laundrybilllabel = new javax.swing.JLabel();
        additionalbill = new javax.swing.JLabel();
        finalabel = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        totalbilllabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        imagefield = new javax.swing.JTextField();
        choosebtn = new javax.swing.JButton();
        seechoosedreciptbtn = new javax.swing.JButton();
        imagelabel = new javax.swing.JLabel();
        Pay = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(770, 470));

        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Bill  Details of ");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 70, 100, 40);

        namelabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        namelabel.setText("name");
        jPanel1.add(namelabel);
        namelabel.setBounds(120, 70, 230, 40);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Hall Bill");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(90, 170, 70, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Mess Bill");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(90, 214, 70, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Laundry Bill");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(90, 264, 100, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Additional Bill");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(90, 314, 100, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Fine");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(90, 360, 70, 20);

        hallbilllabel.setText("hb");
        jPanel1.add(hallbilllabel);
        hallbilllabel.setBounds(210, 170, 50, 20);

        messbilllabel.setText("mb");
        jPanel1.add(messbilllabel);
        messbilllabel.setBounds(210, 220, 60, 30);

        laundrybilllabel.setText("lb");
        jPanel1.add(laundrybilllabel);
        laundrybilllabel.setBounds(220, 270, 50, 20);

        additionalbill.setText("ad");
        jPanel1.add(additionalbill);
        additionalbill.setBounds(220, 310, 60, 30);

        finalabel.setText("fin");
        jPanel1.add(finalabel);
        finalabel.setBounds(220, 360, 50, 30);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Total");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(90, 404, 60, 20);

        totalbilllabel.setText("to");
        jPanel1.add(totalbilllabel);
        totalbilllabel.setBounds(220, 400, 80, 30);

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel7);
        jLabel7.setBounds(352, 70, 2, 660);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Pay Bill :");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(370, 30, 70, 20);
        jPanel1.add(imagefield);
        imagefield.setBounds(370, 80, 230, 30);

        choosebtn.setText("Choose File");
        choosebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choosebtnActionPerformed(evt);
            }
        });
        jPanel1.add(choosebtn);
        choosebtn.setBounds(610, 90, 130, 20);

        seechoosedreciptbtn.setText("See Choosed Receipt");
        seechoosedreciptbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seechoosedreciptbtnActionPerformed(evt);
            }
        });
        jPanel1.add(seechoosedreciptbtn);
        seechoosedreciptbtn.setBounds(380, 130, 200, 23);
        jPanel1.add(imagelabel);
        imagelabel.setBounds(380, 170, 340, 618);

        Pay.setText("Pay");
        Pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PayActionPerformed(evt);
            }
        });
        jPanel1.add(Pay);
        Pay.setBounds(600, 130, 130, 23);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void choosebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choosebtnActionPerformed
        JFileChooser choose=new JFileChooser();
        choose.showOpenDialog(null);
        File fl=choose.getSelectedFile();
        String filename=fl.getAbsolutePath();
        imagefield.setText(filename);
    }//GEN-LAST:event_choosebtnActionPerformed

    private void seechoosedreciptbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seechoosedreciptbtnActionPerformed
         String filename=imagefield.getText();
         ImageIcon image = new ImageIcon(filename);
         imagelabel.setIcon(image);
    }//GEN-LAST:event_seechoosedreciptbtnActionPerformed

    private void PayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PayActionPerformed
        String filename=imagefield.getText();
        if(filename.equals(""))
        {
            JFrame f=new JFrame();  
            JOptionPane.showMessageDialog(f,"Please Choose the Receipt First!"); 
        }
        else
        {
            int response = JOptionPane.showConfirmDialog(null, "Are you Confirm?", "Confirm",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                try {
                    PreparedStatement pstmt = conn.prepareStatement("update Bills set RECEIPT=? where S_ID=?");
                    InputStream in = new FileInputStream(filename);
                    pstmt.setBlob(1, in);
                    pstmt.setString(2, Stu_id);
                    pstmt.execute();
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Student_Bill_details.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Student_Bill_details.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_PayActionPerformed

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
            java.util.logging.Logger.getLogger(Student_Bill_details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Student_Bill_details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Student_Bill_details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Student_Bill_details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Student_Bill_details().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Pay;
    private javax.swing.JLabel additionalbill;
    private javax.swing.JButton choosebtn;
    private javax.swing.JLabel finalabel;
    private javax.swing.JLabel hallbilllabel;
    private javax.swing.JTextField imagefield;
    private javax.swing.JLabel imagelabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel laundrybilllabel;
    private javax.swing.JLabel messbilllabel;
    private javax.swing.JLabel namelabel;
    private javax.swing.JButton seechoosedreciptbtn;
    private javax.swing.JLabel totalbilllabel;
    // End of variables declaration//GEN-END:variables
}

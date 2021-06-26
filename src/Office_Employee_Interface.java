
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Office_Employee_Interface extends javax.swing.JFrame {

    
   Connection conn;
   Statement statement;
   ResultSet resultSet,resultSet2,resultSet5,resultSet6,resultSet7;
   String St_id,stuff_id;
   int total_cost=0,total_breakfast=0,total_lunch=0,total_dinner=0;
     public Office_Employee_Interface() {
        initComponents();
     }
    public Office_Employee_Interface(String idd) {
        initComponents();
        OracleConnection OC=new OracleConnection();
        String[] stringArray = OC.connection();
        
        try{
           conn=DriverManager.getConnection(stringArray[0],stringArray[1],stringArray[2]);
            if(conn!=null)
            {
                
                System.out.println("Connection Sucessful in Office_Employee_Interface");
                statement=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            }
            
            
        }catch(SQLException e){
            System.out.println("Connection failed");
        }
        stuff_id=idd;
        showdata();
    }

     void showdata()
    {
        ///For Mill tabele
        DefaultTableModel model,model2;
        model=(DefaultTableModel) MillDetailTable.getModel(); 
         model.setRowCount(0);
        String query=String.format("select * from Student_Mill");
       try {
           resultSet = statement.executeQuery(query);
          
           int BS,LS,DS,total;
           while(resultSet.next())
           {
               St_id=resultSet.getString("S_ID");
               total=resultSet.getInt("Total");
               BS=resultSet.getInt("Breakfast_Status");
               LS=resultSet.getInt("Launch_Status");
               DS=resultSet.getInt("Dinner_Status");
                String B_take="                         ✘",L_take="                         ✘",D_take="                         ✘";
               if(BS==1)
               {
                   B_take="                         ✓";
                   total_breakfast++;
               }
               if(LS==1)
               {
                   L_take="                         ✓";
                   total_lunch++;
               }
               if(DS==1)
               {
                   D_take="                        ✓";
                   total_dinner++;
               }
               total_cost+=total;
                model.insertRow(model.getRowCount(),new Object[]{St_id,total,B_take,L_take,D_take});
               
           }
            model.insertRow(model.getRowCount(),new Object[]{"Total Calculation",total_cost,total_breakfast+"",total_lunch+"",total_dinner+""});
           
       } catch (SQLException ex) {
           Logger.getLogger(Office_Employee_Interface.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       
       //For Seat cancel table 
         model2=(DefaultTableModel) seatcanceltable.getModel();
         model2.setRowCount(0);
         String query2=String.format("select S_ID,Issue_Descr from Response_Issue where Stuff_ID='%s' and Issue_Status=1 and Issue_Name='Seat Cancel'",stuff_id);
         
       try {
           resultSet = statement.executeQuery(query2);
           String s_id,issue_des;
            while(resultSet.next())
           {
               s_id=resultSet.getString("S_ID");
               issue_des=resultSet.getString("Issue_Descr");
               model2.insertRow(model2.getRowCount(),new Object[]{s_id,issue_des,false});
           }
          
       } catch (SQLException ex) {
           Logger.getLogger(Office_Employee_Interface.class.getName()).log(Level.SEVERE, null, ex);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        MillDetailTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        seatcanceltable = new javax.swing.JTable();
        studentID_Search_field = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        mealdispatchLabel = new javax.swing.JLabel();
        UpdateseatcancelLabel = new javax.swing.JLabel();
        searchIDLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        totalbillshow = new javax.swing.JLabel();
        paidbillLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        ProfileBackLabel = new javax.swing.JLabel();
        assignedworkstatusBtnLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(24, 44, 97));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Student Mill Details");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(80, 110, 200, 40);

        MillDetailTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Total Cost", "Breakfast", "Lunch", "Dinner"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        MillDetailTable.setRowHeight(30);
        jScrollPane1.setViewportView(MillDetailTable);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(70, 170, 870, 220);

        jLabel2.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Seat Calcellation Requests:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(70, 470, 290, 40);

        seatcanceltable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student_ID", "Reason", "Approved"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        seatcanceltable.setRowHeight(30);
        jScrollPane2.setViewportView(seatcanceltable);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(60, 520, 490, 150);
        jPanel1.add(studentID_Search_field);
        studentID_Search_field.setBounds(660, 520, 180, 40);

        jLabel3.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Show Bill Details");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(660, 470, 160, 40);

        mealdispatchLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mealdispatch.png"))); // NOI18N
        mealdispatchLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mealdispatchLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mealdispatchLabelMouseClicked(evt);
            }
        });
        jPanel1.add(mealdispatchLabel);
        mealdispatchLabel.setBounds(450, 410, 110, 40);

        UpdateseatcancelLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pendingUpdate.png"))); // NOI18N
        UpdateseatcancelLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        UpdateseatcancelLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UpdateseatcancelLabelMouseClicked(evt);
            }
        });
        jPanel1.add(UpdateseatcancelLabel);
        UpdateseatcancelLabel.setBounds(240, 680, 110, 30);

        searchIDLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/searchid.png"))); // NOI18N
        searchIDLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchIDLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchIDLabelMouseClicked(evt);
            }
        });
        jPanel1.add(searchIDLabel);
        searchIDLabel.setBounds(900, 520, 95, 34);

        jLabel5.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Pending Bill:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(680, 610, 140, 40);

        totalbillshow.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        totalbillshow.setForeground(new java.awt.Color(255, 255, 255));
        totalbillshow.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(totalbillshow);
        totalbillshow.setBounds(900, 610, 140, 40);

        paidbillLabel.setForeground(new java.awt.Color(255, 255, 255));
        paidbillLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paidbills.png"))); // NOI18N
        paidbillLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        paidbillLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paidbillLabelMouseClicked(evt);
            }
        });
        jPanel1.add(paidbillLabel);
        paidbillLabel.setBounds(900, 670, 120, 40);

        jLabel4.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("See Paid Bill Receipts");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(680, 680, 180, 30);

        jPanel2.setBackground(new java.awt.Color(27, 124, 161));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Your Assigned Work");

        ProfileBackLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/profile2.png"))); // NOI18N
        ProfileBackLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ProfileBackLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProfileBackLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 709, Short.MAX_VALUE)
                .addComponent(ProfileBackLabel)
                .addGap(28, 28, 28))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ProfileBackLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 15, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 1110, 90);

        assignedworkstatusBtnLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/workstatus.png"))); // NOI18N
        assignedworkstatusBtnLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        assignedworkstatusBtnLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                assignedworkstatusBtnLabelMouseClicked(evt);
            }
        });
        jPanel1.add(assignedworkstatusBtnLabel);
        assignedworkstatusBtnLabel.setBounds(900, 730, 180, 50);

        jLabel7.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Assigned Work Status");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(680, 734, 200, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1103, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mealdispatchLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mealdispatchLabelMouseClicked
        int response = JOptionPane.showConfirmDialog(null, "Are you Confirm?", "Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            DefaultTableModel model;
            model=(DefaultTableModel) MillDetailTable.getModel(); 
            model.setRowCount(0);
            String query5=String.format("Delete from Student_Mill");
            try {
                resultSet6=statement.executeQuery(query5);
            } catch (SQLException ex) {
                Logger.getLogger(Office_Employee_Interface.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_mealdispatchLabelMouseClicked

    private void UpdateseatcancelLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpdateseatcancelLabelMouseClicked
         int response = JOptionPane.showConfirmDialog(null, "Are you Confirm?", "Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
        String query3=String.format("select count(*) as c from Response_Issue where Stuff_ID='%s' and Issue_Status=1 and Issue_Name='Seat Cancel'",stuff_id);
       try {
           resultSet = statement.executeQuery(query3);
           resultSet.next();
           int no_of_row=resultSet.getInt("c");
           
           for(int i=0;i<no_of_row;i++)
            {
               
                String stuid= ((String)seatcanceltable.getValueAt(i,0)).toString();
                
                 Boolean chk= ((Boolean)seatcanceltable.getValueAt(i,2)).booleanValue();
                 if(chk)
                 {
                      String query4=String.format("update Response_Issue set Issue_Status=4 where S_ID='%s'",stuid);
                      resultSet2 = statement.executeQuery(query4);
                 }
                 else
                 {
                      String query5=String.format("select * from Bills where S_ID='%s'",stuid);
                      resultSet5=statement.executeQuery(query5);
                      resultSet5.next();
           
                      int hallbill=resultSet5.getInt("Hall_Bill");
                      int messbill=resultSet5.getInt("Mess_Bill");
                      int laundrybill=resultSet5.getInt("Laundary_Bill");
                      int fine=resultSet5.getInt("Fine");
                      int addbill=resultSet5.getInt("Additional_Bill");
                      int total=hallbill+messbill+laundrybill+fine+addbill;

                      String query4=String.format("update Response_Issue set Issue_Status=3,Issue_Cost='%d' where S_ID='%s'",total,stuid);
                      resultSet2 = statement.executeQuery(query4);
                 }
                 
                 
            }
           JFrame f=new JFrame();  
           JOptionPane.showMessageDialog(f,"Updated!!!!!!!!!");  
       } catch (SQLException ex) {
           Logger.getLogger(Office_Employee_Interface.class.getName()).log(Level.SEVERE, null, ex);
       }
      }
        showdata();
    }//GEN-LAST:event_UpdateseatcancelLabelMouseClicked

    private void searchIDLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchIDLabelMouseClicked
        String Stu_id=studentID_Search_field.getText();
     
        try {
          
           String query2=String.format("select * from Bills where S_ID='%s'",Stu_id);
           resultSet7=statement.executeQuery(query2);
           resultSet7.next();
           
           int hallbill=resultSet7.getInt("Hall_Bill");
           int messbill=resultSet7.getInt("Mess_Bill");
           int laundrybill=resultSet7.getInt("Laundary_Bill");
           int fine=resultSet7.getInt("Fine");
           int addbill=resultSet7.getInt("Additional_Bill");
           int total=hallbill+messbill+laundrybill+fine+addbill;
           String totalbill=total+"";
           totalbillshow.setText(totalbill);
       } catch (SQLException ex) {
           Logger.getLogger(Office_Employee_Interface.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_searchIDLabelMouseClicked

    private void paidbillLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paidbillLabelMouseClicked
         PaidReceipts PR=new PaidReceipts(stuff_id);
         this.setVisible(false);
        PR.setVisible(true);
    }//GEN-LAST:event_paidbillLabelMouseClicked

    private void ProfileBackLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileBackLabelMouseClicked
      Stuff_Interface SI=new Stuff_Interface(stuff_id);
      this.setVisible(false);
      SI.setVisible(true);
    }//GEN-LAST:event_ProfileBackLabelMouseClicked

    private void assignedworkstatusBtnLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_assignedworkstatusBtnLabelMouseClicked
        Paid_receipt aws=new Paid_receipt(stuff_id);
        this.setVisible(false);
        aws.setVisible(true);
    }//GEN-LAST:event_assignedworkstatusBtnLabelMouseClicked

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
            java.util.logging.Logger.getLogger(Office_Employee_Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Office_Employee_Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Office_Employee_Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Office_Employee_Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Office_Employee_Interface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable MillDetailTable;
    private javax.swing.JLabel ProfileBackLabel;
    private javax.swing.JLabel UpdateseatcancelLabel;
    private javax.swing.JLabel assignedworkstatusBtnLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel mealdispatchLabel;
    private javax.swing.JLabel paidbillLabel;
    private javax.swing.JLabel searchIDLabel;
    private javax.swing.JTable seatcanceltable;
    private javax.swing.JTextField studentID_Search_field;
    private javax.swing.JLabel totalbillshow;
    // End of variables declaration//GEN-END:variables
}

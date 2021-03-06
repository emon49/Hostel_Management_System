
import java.awt.Image;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Paid_receipt extends javax.swing.JFrame {

    Connection conn;
   Statement statement;
   ResultSet resultSet,resultSet2,resultSet3,resultSet4,resultSet5,resultSet6,resultSet7,resultSet8,resultSet9;
    ResultSetMetaData resultsetMetaData;
    int rowcount;
    String stuff_passed_id;
    public Paid_receipt() {
        initComponents();
    }
    public Paid_receipt(String Stuff_id) {
        initComponents();
        OracleConnection OC=new OracleConnection();
        String[] stringArray = OC.connection();
        
        try{
           conn=DriverManager.getConnection(stringArray[0],stringArray[1],stringArray[2]);
            if(conn!=null)
            {
                
                System.out.println("Connection Sucessful in Assigned_Work_Status_interface");
                statement=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                
            }
            
            
        }catch(SQLException e){
            System.out.println("Connection failed");
        }
        no_Receipt.setVisible(false);
        imageLabel.setVisible(false);
        stuff_passed_id=Stuff_id;
         setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
         showData();
    }
    
    void showData()
    {
       
        DefaultTableModel model;
        model=(DefaultTableModel) pending_complete_table.getModel(); 
        model.setRowCount(0);
        no_Receipt.setVisible(false);
        imageLabel.setVisible(false);
        String  query=String.format("select * from Response_Issue where Issue_Status!=0 and Issue_Name!='Seat Cancel'");
        String issue_name,stu_id,stuff_id,Issue_des;
        int Issue_status,issue_cost;
       try {
           resultSet = statement.executeQuery(query);
            while(resultSet.next())
           {
                issue_name=resultSet.getString("Issue_Name");
                stu_id=resultSet.getString("S_ID");
                stuff_id=resultSet.getString("Stuff_ID");
                Issue_status=resultSet.getInt("Issue_Status");
                Issue_des=resultSet.getString("Issue_Descr");
                issue_cost=resultSet.getInt("Issue_Cost");
                Blob bb=resultSet.getBlob("RECEIPT");
                if(Issue_status==1)
                {
                    
                    model.insertRow(model.getRowCount(),new Object[]{issue_name,stu_id,stuff_id,"Pending",Issue_des,issue_cost,"--  No Receipt  --",false});
                }
                else if(Issue_status==2)
                {
                    if(!(bb == null))
                    {
                        int blobLength = (int) bb.length();  
                        byte[] blobAsBytes = bb.getBytes(1, blobLength);
                          model.insertRow(model.getRowCount(),new Object[]{issue_name,stu_id,stuff_id,"Done",Issue_des,issue_cost,blobAsBytes,false});
                    }
                    else
                    {
                        model.insertRow(model.getRowCount(),new Object[]{issue_name,stu_id,stuff_id,"Done",Issue_des,issue_cost,"--  No Receipt  --",false});
                    }
                        
                     
                }
                /*
                else if(Issue_status==3)
                {
                     model.insertRow(model.getRowCount(),new Object[]{issue_name,stu_id,stuff_id,"Payment Not clear",Issue_des,issue_cost,"--  No Receipt  --",false});
                }
                else if(Issue_status==4)
                {
                    model.insertRow(model.getRowCount(),new Object[]{issue_name,stu_id,stuff_id,"Payment Clear",Issue_des,issue_cost,"--  No Receipt  --",false});
                } 
                */
           }
       } catch (SQLException ex) {
           Logger.getLogger(Paid_receipt.class.getName()).log(Level.SEVERE, null, ex);
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
        pending_complete_table = new javax.swing.JTable();
        updateBtnLabel = new javax.swing.JLabel();
        imageLabel = new javax.swing.JLabel();
        crossbtn = new javax.swing.JLabel();
        no_Receipt = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1400, 840));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(27, 124, 161));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Pending Or Completed List");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(290, 40, 370, 62);

        pending_complete_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Issue Name", "Student ID", "Stuff Id", "Issue Status", "Issue Descr", "Issue Cost", "Image", "Issue Checked"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        pending_complete_table.setGridColor(new java.awt.Color(255, 255, 255));
        pending_complete_table.setRowHeight(30);
        pending_complete_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pending_complete_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(pending_complete_table);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(20, 130, 938, 275);

        updateBtnLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pendingUpdate.png"))); // NOI18N
        updateBtnLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateBtnLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateBtnLabelMouseClicked(evt);
            }
        });
        jPanel1.add(updateBtnLabel);
        updateBtnLabel.setBounds(400, 430, 110, 41);
        jPanel1.add(imageLabel);
        imageLabel.setBounds(990, 130, 340, 618);

        crossbtn.setFont(new java.awt.Font("Tempus Sans ITC", 1, 24)); // NOI18N
        crossbtn.setForeground(new java.awt.Color(255, 255, 255));
        crossbtn.setText("X");
        crossbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        crossbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                crossbtnMouseClicked(evt);
            }
        });
        jPanel1.add(crossbtn);
        crossbtn.setBounds(1355, 11, 16, 33);

        no_Receipt.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        no_Receipt.setForeground(new java.awt.Color(255, 255, 255));
        no_Receipt.setText("No Receipt Was Uploaded");
        jPanel1.add(no_Receipt);
        no_Receipt.setBounds(1030, 200, 260, 40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void pending_complete_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pending_complete_tableMouseClicked
        no_Receipt.setVisible(false);
        imageLabel.setVisible(false);
        DefaultTableModel model=(DefaultTableModel) pending_complete_table.getModel();
        int present_row=pending_complete_table.getSelectedRow();
        Object img= (pending_complete_table.getValueAt(present_row,6));
        if(img.equals("--  No Receipt  --"))
        {
            no_Receipt.setVisible(true);
        }
        else
        {
            byte[] blobAsBytes =(byte[]) pending_complete_table.getValueAt(present_row, 6);
            ImageIcon image = new ImageIcon(blobAsBytes);
            Image im = image.getImage();
            Image myImg = im.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(),Image.SCALE_SMOOTH);
            ImageIcon newImage = new ImageIcon(myImg);
            imageLabel.setIcon(newImage);
            imageLabel.setVisible(true);
        }
    }//GEN-LAST:event_pending_complete_tableMouseClicked

    private void updateBtnLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateBtnLabelMouseClicked
        int response = JOptionPane.showConfirmDialog(null, "Are you Confirm?", "Confirm",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {

            String query=String.format("select count(*) as c from Response_Issue where Issue_Status!=0");
            try {
                resultSet9 = statement.executeQuery(query);
                resultSet9.next();
                rowcount=resultSet9.getInt("c");
                for(int i=0;i<rowcount;i++)
                {
                    String issue_name=((String)pending_complete_table.getValueAt(i,0)).toString();
                    String studidd= ((String)pending_complete_table.getValueAt(i,1)).toString();
                    String stuffid= ((String)pending_complete_table.getValueAt(i,2)).toString();
                    String issue_st= ((String)pending_complete_table.getValueAt(i,3)).toString();
                    Boolean chk= ((Boolean)pending_complete_table.getValueAt(i,7)).booleanValue();

                    if(chk)
                    {
                        if((issue_name.equals("Clean Issue"))||(issue_name.equals("Food Issue"))||(issue_name.equals("Electricity Issue"))||(issue_name.equals("Internet Issue"))||(issue_name.equals("Laundry Issue")))
                        {
                            int issue_cost=((Integer)pending_complete_table.getValueAt(i,5));
                            String query7=String.format("select Additional_Bill from Bills where S_ID='%s'",studidd);
                            resultSet8= statement.executeQuery(query7);
                            resultSet8.next();
                            int addi_bill=resultSet8.getInt("Additional_Bill");
                            addi_bill+=issue_cost;
                            query7=String.format("update Bills set Additional_Bill='%d' where S_ID='%s'",addi_bill,studidd);
                            resultSet8= statement.executeQuery(query7);
                              System.out.println("Hello");
                            String query2=String.format("Delete from Response_Issue where S_ID='%s' and Stuff_ID='%s'",studidd,stuffid);
                            resultSet2 = statement.executeQuery(query2);
                        }
                        /*
                        else if(issue_name.equals("Seat Cancel"))
                        {
                            if(issue_st.equals("Payment Clear"))
                            {
                                // Send seat cancel confirmation mail
                                String query5=String.format("select S_Name,S_Email from Student_Information where S_ID='%s'",studidd);
                                resultSet6= statement.executeQuery(query5);
                                resultSet6.next();
                                String s_name=resultSet6.getString("S_Name");
                                String mail=resultSet6.getString("S_Email");

                                String message="Hello Dear "+s_name+",\n"+"Your Application for hall seat cancellation has been approved. Vacant the room within this week."
                                +"\nThanks \nBest Regards\nPseudo Hall Authority";
                                String sub="Seat Cancellation Confirm";
                                SendEmail SE=new SendEmail();
                                SE.main(mail,sub,message);

                                ///took the room no of cancelled student
                                String queryforseat=String.format("Select Room_No from Student_Information where S_ID='%s'",studidd);
                                resultSet3=statement.executeQuery(queryforseat);
                                resultSet3.next();
                                String room_no=resultSet3.getString("Room_No");
                                ///Deleting from all table except roon_seat
                                String query3=String.format("Delete from Student_Information where S_ID='%s'",studidd);
                                resultSet4 = statement.executeQuery(query3);

                                //cur seat of room
                                String query4=String.format("select Cur_No_Seat from Room_Information where Room_No='%s'",room_no);
                                resultSet5= statement.executeQuery(query4);
                                resultSet5.next();
                                int count_seat=resultSet5.getInt("Cur_No_Seat");
                                CallableStatement cstmt = null;
                                try {
                                    //String SQL = "{call incre (?, ?)}";
                                    String SQL = "{call seatdecr (?, ?)}";
                                    cstmt = conn.prepareCall (SQL);
                                    cstmt.setString(1,room_no);
                                    cstmt.setInt(2,count_seat);
                                   
                                    cstmt.execute();

                                }
                                catch (SQLException e) {

                                }
                                finally {
                                    try {
                                        cstmt.close();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(ManagerInterface.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                }

                            }
                            else if(issue_st.equals("Payment Not clear"))
                            {
                                // Send seat can not cancel mail
                                int issue_cost=((Integer)pending_complete_table.getValueAt(i,5));
                                String query5=String.format("select S_Name,S_Email from Student_Information where S_ID='%s'",studidd);
                                resultSet6= statement.executeQuery(query5);
                                resultSet6.next();
                                String s_name=resultSet6.getString("S_Name");
                                String mail=resultSet6.getString("S_Email");

                                String message="Hello Dear "+s_name+",\n"+"Your Application for hall seat cancellation is declined.Your due : "+issue_cost+" taka. Please clear the due first and apply again."
                                +"\nThanks \nBest Regards\nPseudo Hall Authority";
                                String sub="Seat Cancellation Confirm";
                                SendEmail SE=new SendEmail();
                                SE.main(mail,sub,message);

                                String query6=String.format("Delete from Response_Issue where S_ID='%s'",studidd);
                                resultSet7= statement.executeQuery(query6);

                            }

                        }
                       */

                    }

                }
                 JFrame f=new JFrame();
                 JOptionPane.showMessageDialog(f,"Checked!");
            } catch (SQLException ex) {
                Logger.getLogger(Paid_receipt.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        showData();
    }//GEN-LAST:event_updateBtnLabelMouseClicked

    private void crossbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crossbtnMouseClicked
        Office_Employee_Interface OEI=new Office_Employee_Interface(stuff_passed_id);
        this.setVisible(false);
        OEI.setVisible(true);
    }//GEN-LAST:event_crossbtnMouseClicked

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
            java.util.logging.Logger.getLogger(Paid_receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Paid_receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Paid_receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Paid_receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Paid_receipt().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel crossbtn;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel no_Receipt;
    private javax.swing.JTable pending_complete_table;
    private javax.swing.JLabel updateBtnLabel;
    // End of variables declaration//GEN-END:variables
}

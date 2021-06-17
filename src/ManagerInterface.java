

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class ManagerInterface extends javax.swing.JFrame {
    
   Connection conn;
   Statement statement,statement2;
   ResultSet resultSet,resultSet2,resultSet3,resultSet6,resultSet4,resultSet7,resultSet5;
   
   ResultSetMetaData resultsetMetaData;
   int rowcount;
   
    public ManagerInterface() {
        initComponents();
    }

    public ManagerInterface(String ID) {
        initComponents();
        OracleConnection OC=new OracleConnection();
        String[] stringArray = OC.connection();
        
        try{
           conn=DriverManager.getConnection(stringArray[0],stringArray[1],stringArray[2]);
            if(conn!=null)
            {
                
                System.out.println("Connection Sucessful in Manager Interface");
                statement=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                statement2=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            }
            
            
        }catch(SQLException e){
            System.out.println("Connection failed");
        }
        
        showInfo();
        
    }
    
    void showInfo()
    {     // Show Info for apllicant
       String query=String.format("select count(*) as c from Student_Information where S_Status='0'");
       try {
           resultSet = statement.executeQuery(query);
           resultSet.next();
           rowcount=resultSet.getInt("c");
           DefaultTableModel model;
           model=(DefaultTableModel) pendinglist.getModel(); 
           model.setRowCount(0);
           query=String.format("select * from student_information where S_Status='0'");
           resultSet = statement.executeQuery(query);
           
           
           
           String name,id,email,dept,regularity,father,mother,gender,address,DOB_String;
           while(resultSet.next())
           {
                name=resultSet.getString("S_Name");
                id=resultSet.getString("S_ID");
                email=resultSet.getString("S_Email");
                dept=resultSet.getString("S_Dept");
                regularity=resultSet.getString("S_Regularity_Status");
                father=resultSet.getString("S_Father_Name");
                mother=resultSet.getString("S_Mother_Name");
                gender=resultSet.getString("S_Gender");
                address=resultSet.getString("S_Home_Address");
                
                java.sql.Date sDate=resultSet.getDate("S_DOB");
                java.util.Date utilDate = new java.util.Date(sDate.getTime());
                SimpleDateFormat sdf1 = new SimpleDateFormat("MMM dd,yyyy");
                
                DOB_String = sdf1.format(utilDate);
                
                model.insertRow(model.getRowCount(),new Object[]{id,name,email,dept,regularity,father,mother,DOB_String,gender,address,"--Select Room--",false});
           }
           
           TableColumn stuffColumn = pendinglist.getColumnModel().getColumn(10);
           JComboBox comboBox = new JComboBox();
            String query4=String.format("select Room_No from Room_Information where Cur_No_Seat>0 and Room_No!='NA'");
               resultSet5 = statement2.executeQuery(query4);
               while(resultSet5.next())
               {
                   String room_no=resultSet5.getString("Room_No");
                  // System.out.println(stuff_name);
                   comboBox.addItem(room_no);
               }
                stuffColumn.setCellEditor(new DefaultCellEditor(comboBox));
           

       } catch (SQLException ex) {
           Logger.getLogger(ManagerInterface.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       
       
       // Show info for issue
       DefaultTableModel model2;
       model2=(DefaultTableModel) issueTable.getModel(); 
       model2.setRowCount(0);
       String query2=String.format("select RI.S_ID,RI.Issue_Name,RI.Issue_Descr,SI.Room_No from Student_Information SI inner join Response_Issue RI on SI.S_ID=RI.S_ID and ri.issue_status=0");
       
   //    String query2=String.format("select count(*) as c from Student_Information SI inner join Response_Issue RI on SI.S_ID=RI.S_ID and ri.issue_status=0");
       
       try {
          resultSet4 = statement2.executeQuery(query2);
          //int rowcnt=resultSet4.getInt("c");
           //System.out.println(rowcnt);
            String stuid,issuename,issuedescription,roomno;
            TableColumn stuffColumn = issueTable.getColumnModel().getColumn(4);
             JComboBox comboBox = new JComboBox();
           while(resultSet4.next())
           {    
               //Taking for stuff combolist
           //    TableColumn stuffColumn = issueTable.getColumnModel().getColumn(4);
              
               
               stuid=resultSet4.getString("S_ID");
               issuename=resultSet4.getString("Issue_Name");
               issuedescription=resultSet4.getString("Issue_Descr");
               roomno=resultSet4.getString("Room_No");
               model2.insertRow(model2.getRowCount(),new Object[]{stuid,roomno,issuename,issuedescription,"     --Select Stuff-- "});
               
               /*
               //Considering on Issue Status and room no extracting suff
               String query4 = null;
               
               if(issuename.equals("Seat Cancel"))
               {
                  query4=String.format("select Stuff_Name from Stuff_Information where Stuff_Rank='Office Employee' and ('%s' between Assigned_Room_Lower and Assigned_Room_Upper)",roomno);
               }
               else if(issuename.equals("Clean Issue"))
               {
                   query4=String.format("select Stuff_Name from Stuff_Information where Stuff_Rank='Cleaner' and ('%s' between Assigned_Room_Lower and Assigned_Room_Upper)",roomno);
               }
                else if(issuename.equals("Food Issue"))
               {
                   query4=String.format("select Stuff_Name from Stuff_Information where Stuff_Rank='Cook' and ('%s' between Assigned_Room_Lower and Assigned_Room_Upper)",roomno);
               }
                else if(issuename.equals("Electricity Issue"))
               {
                   query4=String.format("select Stuff_Name from Stuff_Information where Stuff_Rank='Electrician' and ('%s' between Assigned_Room_Lower and Assigned_Room_Upper)",roomno);
               }
                else if(issuename.equals("Internet Issue"))
               {
                   query4=String.format("select Stuff_Name from Stuff_Information where Stuff_Rank='ISP' and ('%s' between Assigned_Room_Lower and Assigned_Room_Upper)",roomno);
               }
               //else if(issuename.equals("Laundry Issue"))
               else
                {
                   query4=String.format("select Stuff_Name from Stuff_Information where Stuff_Rank='Laundry Man' and ('%s' between Assigned_Room_Lower and Assigned_Room_Upper)",roomno);
               }
               
              resultSet5= statement.executeQuery(query4);
               
             //  String stuff_name;
               while(resultSet5.next())
               {
                   String stuff_name=resultSet5.getString("Stuff_Name");
                   System.out.println(stuff_name);
                   comboBox.addItem(stuff_name);
               }
               stuffColumn.setCellEditor(new DefaultCellEditor(comboBox));
               */
               String query4=String.format("select Stuff_ID from Stuff_Information where Manager_ID='101914004'");
               resultSet5 = statement.executeQuery(query4);
               while(resultSet5.next())
               {
                   String stuff_name=resultSet5.getString("Stuff_ID");
                  // System.out.println(stuff_name);
                   comboBox.addItem(stuff_name);
               }
                stuffColumn.setCellEditor(new DefaultCellEditor(comboBox));
           }
          
           
           
           
       } catch (SQLException ex) {
           //Logger.getLogger(ManagerInterface.class.getName()).log(Level.SEVERE, null, ex);
           System.out.println("Resultset4");
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
        pendinglist = new javax.swing.JTable();
        addStdBtn = new javax.swing.JButton();
        vacantRoomButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        assignworkbtn = new javax.swing.JButton();
        stufflistbtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        issueTable = new javax.swing.JTable();
        assignedworklistbtn = new javax.swing.JButton();
        mothlybilladdbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        jLabel1.setText("Seat Applicant List");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 100, 130, 40);

        pendinglist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Email", "Dept", "Regularity", "Father's Name", "Mother's Name", "Date of Birth", "Gender", "Home Address", "Assigned Room No", "Granted"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(pendinglist);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(0, 150, 1180, 300);

        addStdBtn.setText("Add Selected Students");
        addStdBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStdBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addStdBtn);
        addStdBtn.setBounds(500, 490, 190, 23);

        vacantRoomButton.setText("Vacant Rooms");
        vacantRoomButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vacantRoomButtonMouseClicked(evt);
            }
        });
        jPanel1.add(vacantRoomButton);
        vacantRoomButton.setBounds(310, 490, 160, 23);

        jLabel2.setText("Student Complain Or Seat Cancellation");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(90, 520, 220, 20);

        assignworkbtn.setText("Assign Work");
        assignworkbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignworkbtnActionPerformed(evt);
            }
        });
        jPanel1.add(assignworkbtn);
        assignworkbtn.setBounds(540, 880, 110, 23);

        stufflistbtn.setText("Show Stuff List");
        stufflistbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stufflistbtnActionPerformed(evt);
            }
        });
        jPanel1.add(stufflistbtn);
        stufflistbtn.setBounds(340, 880, 120, 23);

        issueTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Room No", "Issue Name", "Issue Description", "Stuff ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(issueTable);

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(40, 560, 930, 280);

        assignedworklistbtn.setText("Assigned Work Status");
        assignedworklistbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignedworklistbtnActionPerformed(evt);
            }
        });
        jPanel1.add(assignedworklistbtn);
        assignedworklistbtn.setBounds(730, 881, 180, 30);

        mothlybilladdbtn.setText("Add Bills");
        mothlybilladdbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mothlybilladdbtnActionPerformed(evt);
            }
        });
        jPanel1.add(mothlybilladdbtn);
        mothlybilladdbtn.setBounds(460, 30, 90, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1182, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 939, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //Add All the approved students
    private void addStdBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStdBtnActionPerformed
       
       
        for(int i=0;i<rowcount;i++)
        {
            String idd= ((String)pendinglist.getValueAt(i,0)).toString();
            String S_name= ((String)pendinglist.getValueAt(i,1)).toString();
             String mail= ((String)pendinglist.getValueAt(i,2)).toString();
             System.out.println(mail);
            String room_noo=((String)pendinglist.getValueAt(i,10)).toString();
            //room_noo=room_noo.substring(1);  // Removing extra space for the front of the string
            Boolean chk= ((Boolean)pendinglist.getValueAt(i,11)).booleanValue();
            int count_seat=0;
            if(chk && !room_noo.equals("--Select Room--"))
            {   
                
                int response = JOptionPane.showConfirmDialog(null, "Are you Confirm?", "Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                
            
                // Updating student information table
                String query=String.format("update student_information set S_Status='1',Room_No='%s',S_Pass='%s' where S_ID='%s'",room_noo,idd,idd);
              
                try {
                    resultSet = statement.executeQuery(query);
                    // Fetching current seat no of assigned room
                      String query2=String.format("select Cur_No_Seat from Room_Information where Room_No='%s'",room_noo);
                      resultSet2 = statement.executeQuery(query2);
                      resultSet2.next();
                    count_seat=resultSet2.getInt("Cur_No_Seat");
                    System.out.println(count_seat);
//                    count_seat++;
//                    String query3=String.format("update Room_Information set Cur_No_Seat='%d' where Room_No='%s'",count_seat,room_noo);
//                    resultSet3 = statement.executeQuery(query3);
                    
                        // Using procedure to update room information table
                         CallableStatement cstmt = null;
                           try {
                                 String SQL = "{call incre (?, ?)}";
                                 cstmt = conn.prepareCall (SQL);
                                cstmt.setString(1,room_noo);
                                cstmt.setInt(2,count_seat);
                                System.out.println("Executing stored procedure..." );
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
                         
                           
                         //Add the id to bill table
                         String query3="insert into Bills values(?,?,?,?,?,?,?)";
                         PreparedStatement ps=conn.prepareStatement(query3);
                         ps.setString(1,idd);
                         ps.setInt(2,0);
                         ps.setInt(3,0);
                         ps.setInt(4,0);
                         ps.setInt(5,0);
                         ps.setInt(6,0);
                         ps.setInt(7,0);
                         ps.executeUpdate();
                        JFrame f=new JFrame();  
                        JOptionPane.showMessageDialog(f,"Added To Database");  
                    
                    
                      String message="Hello Dear "+S_name+",\n"+"Congratulations.Your Application for hall seat has been approved.\nYour login id : "+idd
                                +"\nYour Login pass : "+idd+"\nYour Room No: "+room_noo
                                 +"\nYou can change your pass later\nThanks \nBest Regards\nPseudo Hall Authority";
                      String sub="Seat Allotment Confirmation";
                      SendEmail SE=new SendEmail();
                      SE.main(mail,sub,message);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(ManagerInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            }
            else if(((chk==false) && !room_noo.equals("--Select Room--"))||((chk==true) && room_noo.equals("--Select Room--")))
            {
               JFrame f;
               f=new JFrame();  
               JOptionPane.showMessageDialog(f,"Select the fields properly.","Alert",JOptionPane.WARNING_MESSAGE);
            }
        }
      
      showInfo();
       
    }//GEN-LAST:event_addStdBtnActionPerformed

    private void vacantRoomButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vacantRoomButtonMouseClicked
        VacantRoomList vr=new VacantRoomList();
        vr.setVisible(true);
    }//GEN-LAST:event_vacantRoomButtonMouseClicked

    private void stufflistbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stufflistbtnActionPerformed
        StuffListInterface sl=new StuffListInterface();
        sl.setVisible(true);
    }//GEN-LAST:event_stufflistbtnActionPerformed

    private void assignworkbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assignworkbtnActionPerformed
      int response = JOptionPane.showConfirmDialog(null, "Are you Confirm?", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
        
        String query=String.format("select count(*) as c from Response_Issue where Issue_Status=0");
       try {
           resultSet = statement.executeQuery(query);
            resultSet.next();
           int no_of_row=resultSet.getInt("c");
           System.out.println(no_of_row);
           for(int i=0;i<no_of_row;i++)
           {
               
               String stuid= ((String)issueTable.getValueAt(i,0)).toString();
            //System.out.println(stuid);
            
            String stuffid=((String)issueTable.getValueAt(i,4)).toString();
            //stuffid=stuffid.substring(1);
            if(!stuffid.equals("     --Select Stuff-- "))
             {
                System.out.println(stuffid);
                String query5=String.format("update Response_Issue set Issue_Status=1,Stuff_ID='%s' where S_ID='%s'",stuffid,stuid);
                resultSet6 = statement.executeQuery(query5);
                JFrame f=new JFrame();  
                JOptionPane.showMessageDialog(f,"Assigned To Stuff");  

             }
           }
           
       } catch (SQLException ex) {
           Logger.getLogger(ManagerInterface.class.getName()).log(Level.SEVERE, null, ex);
       }
     } 
     showInfo();     
        
    }//GEN-LAST:event_assignworkbtnActionPerformed

    private void assignedworklistbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assignedworklistbtnActionPerformed
       Assigned_Work_Status_interface aws=new Assigned_Work_Status_interface();
       aws.setVisible(true);
    }//GEN-LAST:event_assignedworklistbtnActionPerformed

    private void mothlybilladdbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mothlybilladdbtnActionPerformed
        MonthlyBillAdd MDA=new MonthlyBillAdd();
        this.setVisible(false);
        MDA.setVisible(true);
    }//GEN-LAST:event_mothlybilladdbtnActionPerformed

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
            java.util.logging.Logger.getLogger(ManagerInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addStdBtn;
    private javax.swing.JButton assignedworklistbtn;
    private javax.swing.JButton assignworkbtn;
    private javax.swing.JTable issueTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton mothlybilladdbtn;
    private javax.swing.JTable pendinglist;
    private javax.swing.JButton stufflistbtn;
    private javax.swing.JButton vacantRoomButton;
    // End of variables declaration//GEN-END:variables
}

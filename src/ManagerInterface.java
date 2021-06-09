
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


public class ManagerInterface extends javax.swing.JFrame {
    
   Connection conn;
   Statement statement;
   ResultSet resultSet,resultSet2,resultSet3;
   
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
            }
            
            
        }catch(SQLException e){
            System.out.println("Connection failed");
        }
        
        showInfo();
        
    }
    
    void showInfo()
    {
         String query=String.format("select count(*) as c from Student_Information where S_Status='0'");
       try {
           resultSet = statement.executeQuery(query);
           resultSet.next();
           rowcount=resultSet.getInt("c");
           DefaultTableModel model;
           model=(DefaultTableModel) pendinglist.getModel(); 
           query=String.format("select * from student_information where S_Status='0'");
           resultSet = statement.executeQuery(query);
           
           resultsetMetaData =resultSet.getMetaData();
           
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
                
                model.insertRow(model.getRowCount(),new Object[]{id,name,email,dept,regularity,father,mother,DOB_String,gender,address," ",false});
           }

       } catch (SQLException ex) {
           Logger.getLogger(ManagerInterface.class.getName()).log(Level.SEVERE, null, ex);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        jLabel1.setText("Seat Applicant List");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(40, 40, 130, 40);

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
        addStdBtn.setBounds(500, 490, 190, 21);

        vacantRoomButton.setText("Vacant Rooms");
        vacantRoomButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vacantRoomButtonMouseClicked(evt);
            }
        });
        jPanel1.add(vacantRoomButton);
        vacantRoomButton.setBounds(90, 590, 160, 21);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1176, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //Add All the approved students
    private void addStdBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStdBtnActionPerformed
        
        for(int i=0;i<rowcount;i++)
        {
            String idd= ((String)pendinglist.getValueAt(i,0)).toString();
            String room_noo=((String)pendinglist.getValueAt(i,10)).toString();
            room_noo=room_noo.substring(1);  // Removing extra space for the front of the string
            Boolean chk= ((Boolean)pendinglist.getValueAt(i,11)).booleanValue();
            int count_seat=0;
            if(chk)
            {
                
                String query=String.format("update student_information set S_Status='1',Room_No='%s' where S_ID='%s'",room_noo,idd);
              
                try {
                    resultSet = statement.executeQuery(query);
                      String query2=String.format("select Cur_No_Seat from Room_Information where Room_No='%s'",room_noo);
                      resultSet2 = statement.executeQuery(query2);
                      resultSet2.next();
                    count_seat=resultSet2.getInt("Cur_No_Seat");
                    System.out.println(count_seat);
                    count_seat++;
                    String query3=String.format("update Room_Information set Cur_No_Seat='%d' where Room_No='%s'",count_seat,room_noo);
                    resultSet3 = statement.executeQuery(query3);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(ManagerInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_addStdBtnActionPerformed

    private void vacantRoomButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vacantRoomButtonMouseClicked
        VacantRoomList vr=new VacantRoomList();
        vr.setVisible(true);
    }//GEN-LAST:event_vacantRoomButtonMouseClicked

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable pendinglist;
    private javax.swing.JButton vacantRoomButton;
    // End of variables declaration//GEN-END:variables
}

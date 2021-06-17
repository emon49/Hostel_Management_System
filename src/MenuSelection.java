
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class MenuSelection extends javax.swing.JFrame {

   Connection conn;
   Statement statement;
   ResultSet resultSet,resultSet2,resultSet3,resultSet4;
   ResultSetMetaData resultsetMetaData;
   int break_cost,launch_cost,dinner_cost;
   String id,next_day;
   
   public MenuSelection() {
        initComponents();
   }
    public MenuSelection(String idd) {
        initComponents();
        OracleConnection OC=new OracleConnection();
        String[] stringArray = OC.connection();
        
        try{
           conn=DriverManager.getConnection(stringArray[0],stringArray[1],stringArray[2]);
            if(conn!=null)
            {
                
                System.out.println("Connection Sucessful in VacantRoomList Interface");
                statement=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            }
            
            
        }catch(SQLException e){
            System.out.println("Connection failed");
        }
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
        id=idd;
        showInfo();
        
    }
    void showInfo()
    {
        ArrayList<String> day = new ArrayList<String>(Arrays.asList("Saturday","Sunday","Monday","Tuesday","Wednesday","Thursday","Friday"));
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String current_day=new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
        int pos=day.indexOf(current_day);
        pos=(pos+1)%7;
        next_day=day.get(pos);
        System.out.println(next_day);
        tomorrowtitle.setText(next_day+"'s");
        
        // For showing tomorrow's menu and cost
         String query=String.format("select * from Mill_Information where Day='%s'",next_day);
       try {
           resultSet = statement.executeQuery(query);
           resultSet.next();
           String break_men=resultSet.getString("Breakfast_Menu");
           String launch_men=resultSet.getString("Lunch_Menu");
           String dinner_men=resultSet.getString("Dinner_Menu");
           break_cost=resultSet.getInt("Breakfast_Cost");
           launch_cost=resultSet.getInt("Lunch_Cost");
           dinner_cost=resultSet.getInt("Dinner_Cost");
           
           break_menu_label.setText(break_men);
           launch_menu_label.setText(launch_men);
           dinner_menu_label.setText(dinner_men);
           
           cost_breakfast.setText(break_cost+"");
           cost_launch.setText(launch_cost+"");
           cost_dinner.setText(dinner_cost+"");
           
           
       } catch (SQLException ex) {
           Logger.getLogger(MenuSelection.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       // Checking if Meal is perviously selected
        String query4=String.format("select * from Student_Mill where S_ID='%s'",id);
        
       try {
           resultSet4 = statement.executeQuery(query4);
           if(resultSet4.next())
           {
               System.out.println("kaj kore");
               int bs=0,ls=0,ds=0;
               bs=resultSet4.getInt("Breakfast_Status");
               ls=resultSet4.getInt("Launch_Status");
               ds=resultSet4.getInt("Dinner_Status");
               if(bs==1)
               {
                   break_check.setSelected(true);
                   
               }
               if(ls==1)
               {
                   launch_check.setSelected(true);
               }
               if(ds==1)
               {
                   dinner_check.setSelected(true);
               }
               break_check.setEnabled(false);
               launch_check.setEnabled(false);
               dinner_check.setEnabled(false);
               add_mill_btn.setEnabled(false);
           }
           else
           {
               System.out.println("kaj kore na");
           }
       } catch (SQLException ex) {
           Logger.getLogger(MenuSelection.class.getName()).log(Level.SEVERE, null, ex);
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
        tomorrowtitle = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        break_menu_label = new javax.swing.JLabel();
        launch_menu_label = new javax.swing.JLabel();
        dinner_menu_label = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cost_breakfast = new javax.swing.JLabel();
        cost_launch = new javax.swing.JLabel();
        cost_dinner = new javax.swing.JLabel();
        break_check = new javax.swing.JCheckBox();
        launch_check = new javax.swing.JCheckBox();
        dinner_check = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        add_mill_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(502, 381));

        jPanel1.setLayout(null);

        tomorrowtitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tomorrowtitle.setText("Tom");
        jPanel1.add(tomorrowtitle);
        tomorrowtitle.setBounds(41, 36, 110, 22);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText(" Menu & Cost");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(150, 40, 120, 17);

        jLabel1.setText("Breakfast:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(40, 130, 70, 22);

        jLabel3.setText("Launch:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(41, 188, 50, 14);

        jLabel4.setText("Dinner:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(41, 251, 40, 14);

        break_menu_label.setText("breakfast menu");
        jPanel1.add(break_menu_label);
        break_menu_label.setBounds(120, 130, 170, 20);

        launch_menu_label.setText("Launch Menu");
        jPanel1.add(launch_menu_label);
        launch_menu_label.setBounds(120, 190, 180, 20);

        dinner_menu_label.setText("dinner Menu");
        jPanel1.add(dinner_menu_label);
        dinner_menu_label.setBounds(110, 250, 190, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Time");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(40, 84, 50, 20);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Menu");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(130, 80, 50, 20);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Cost");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(300, 84, 50, 20);

        cost_breakfast.setText("c-b");
        jPanel1.add(cost_breakfast);
        cost_breakfast.setBounds(300, 130, 30, 14);

        cost_launch.setText("c-l");
        jPanel1.add(cost_launch);
        cost_launch.setBounds(300, 190, 30, 14);

        cost_dinner.setText("c-d");
        jPanel1.add(cost_dinner);
        cost_dinner.setBounds(300, 250, 30, 14);
        jPanel1.add(break_check);
        break_check.setBounds(420, 120, 20, 23);
        jPanel1.add(launch_check);
        launch_check.setBounds(420, 180, 21, 21);
        jPanel1.add(dinner_check);
        dinner_check.setBounds(420, 240, 21, 21);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Taken");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(410, 80, 60, 17);

        add_mill_btn.setText("Add");
        add_mill_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_mill_btnActionPerformed(evt);
            }
        });
        jPanel1.add(add_mill_btn);
        add_mill_btn.setBounds(210, 300, 70, 23);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void add_mill_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_mill_btnActionPerformed
        int response = JOptionPane.showConfirmDialog(null, "Are you Confirm?", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
           break_check.setSelected(false);
           launch_check.setSelected(false);
           dinner_check.setSelected(false);
       }
        else if (response == JOptionPane.YES_OPTION) {
        try {                                             
            int cost=0,break_st=0,launch_st=0,dinner_st=0;
            if(break_check.isSelected())
            {
                cost+=break_cost;
                break_st=1;
            }
            
            if(launch_check.isSelected())
            {
                cost+=launch_cost;
                launch_st=1;
            }
            
            if(dinner_check.isSelected())
            {
                cost+=dinner_cost;
                dinner_st=1;
            }
            
            try {
                String query="insert into Student_Mill values(?,?,?,?,?,?)";
                PreparedStatement ps=conn.prepareStatement(query);
                ps.setString(1,id);
                ps.setString(2,next_day);
                ps.setInt(3,cost);
                ps.setInt(4,break_st);
                ps.setInt(5,launch_st);
                ps.setInt(6,dinner_st);
                ps.executeUpdate();
                
            } catch (SQLException ex) {
                Logger.getLogger(ComplainInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String query2=String.format("select Mess_Bill from Bills where S_ID='%s'",id);
            resultSet2 = statement.executeQuery(query2);
            resultSet2.next();
            int prev_mess_bill=resultSet2.getInt("Mess_Bill");
            prev_mess_bill+=cost;
            
            String query3=String.format("update Bills set Mess_Bill='%d' where S_ID='%s'",prev_mess_bill,id);
            resultSet3= statement.executeQuery(query3);
            
        } catch (SQLException ex) {
            Logger.getLogger(MenuSelection.class.getName()).log(Level.SEVERE, null, ex);
        }
       JFrame f=new JFrame();  
       JOptionPane.showMessageDialog(f,"Meal Added"); 
       break_check.setEnabled(false);
       launch_check.setEnabled(false);
       dinner_check.setEnabled(false);
       add_mill_btn.setEnabled(false); 
       }
    }//GEN-LAST:event_add_mill_btnActionPerformed

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
            java.util.logging.Logger.getLogger(MenuSelection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuSelection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuSelection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuSelection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuSelection().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_mill_btn;
    private javax.swing.JCheckBox break_check;
    private javax.swing.JLabel break_menu_label;
    private javax.swing.JLabel cost_breakfast;
    private javax.swing.JLabel cost_dinner;
    private javax.swing.JLabel cost_launch;
    private javax.swing.JCheckBox dinner_check;
    private javax.swing.JLabel dinner_menu_label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox launch_check;
    private javax.swing.JLabel launch_menu_label;
    private javax.swing.JLabel tomorrowtitle;
    // End of variables declaration//GEN-END:variables
}

package votingSystemPackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class frameAdminAccess extends JFrame implements ActionListener {
    
    private JTextArea txtaSummary;
    private JLabel hdrAdmin,hdrAdd,hdrRemove,lblAddName,lblAddParty,lblAddPosition
            ,lblRemoveID,hdrElectionDate,lblStartElection,lblEndElection;
    private JComboBox cmbAddPosition;
    private String []position = {"Select Position:","President","Vice President","Secretary","Senator"} ;
    private JTextField txtfAddName,txtfAddParty,txtfRemoveID,txtfRemoveName,txtfRemovePosition,txtfRemoveParty,
            txtfDateStartElection,txtfDateEndElection, txtfTimeStartElection,txtfTimeEndElection;
    private JButton btnAddCandidate, btnClearCandidate,btnRemoveCandidate,btnSearchCandidate,
            btnSetElection,btnForceEndElection,btnCurrentTime,btnSignOut;
    private JScrollPane spSummary;
       
    private DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");
    
//    setTimeElection setElection = new setTimeElection();
    
    frameAdminAccess(){
        setSize(800,600);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
//Adding Components
        hdrAdmin = new JLabel("Admin Page");
        hdrAdmin.setBounds(10,0,150,30);
        hdrAdmin.setFont(new Font("Helvetica", Font.BOLD, 25));
        add(hdrAdmin);
        
//Add Candidate Function
        hdrAdd = new JLabel("Add Candidate");
        hdrAdd.setBounds(20,30,150,50);
        hdrAdd.setFont(new Font("Arial", Font.ITALIC, 18));
        add(hdrAdd);
        
        lblAddName = new JLabel("Name: ");
        lblAddName.setBounds(20,75,50,50);
        add(lblAddName);
        
        txtfAddName = new JTextField();
        txtfAddName.setBounds(75,80,200,30);
        add(txtfAddName);
                
        lblAddParty = new JLabel("Partylist: ");
        lblAddParty.setBounds(20,125,150,50);
        add(lblAddParty);
        
        txtfAddParty = new JTextField();
        txtfAddParty.setBounds(75,130,200,30);
        add(txtfAddParty);
        
        lblAddPosition = new JLabel("Enter Position: ");
        lblAddPosition.setBounds(20,175,150,50);
        add(lblAddPosition);
        
        cmbAddPosition = new JComboBox(position);
        cmbAddPosition.setBounds(125,180,150,30);
        add(cmbAddPosition);
        
        btnAddCandidate = new JButton("Add Candidate");
        btnAddCandidate.setBounds(325,225,150,30);
        //btnAddCandidate.setBounds(100,225,150,30);
        add(btnAddCandidate);
        
        btnClearCandidate = new JButton("Clear");
        btnClearCandidate.setBounds(230, 225, 75, 30);
        //btnClearCandidate.setBounds(20, 225, 75, 30);
        add(btnClearCandidate);

//Remove Candidate Function
        hdrRemove = new JLabel("Remove Candidate");
        hdrRemove.setBounds(20,250,200,50);
        hdrRemove.setFont(new Font("Arial", Font.ITALIC, 18));
        add(hdrRemove);
        
        lblRemoveID = new JLabel("Enter Candidate ID: ");
        lblRemoveID.setBounds(20, 290,150,50);
        add(lblRemoveID);
        
        txtfRemoveID = new JTextField();
        txtfRemoveID.setBounds(135,305,50,20);
        add(txtfRemoveID);
        
        btnSearchCandidate = new JButton("Search");
        btnSearchCandidate.setBounds(195, 305,80,20);
        add(btnSearchCandidate);
        
        txtfRemoveName = new JTextField();
        txtfRemoveName.setEditable(false);
        txtfRemoveName.setBounds(20,335,225,20);
        add(txtfRemoveName);
        
        txtfRemoveParty = new JTextField();
        txtfRemoveParty.setEditable(false);
        txtfRemoveParty.setBounds(250,335,125,20);
        add(txtfRemoveParty);
        
        txtfRemovePosition= new JTextField();
        txtfRemovePosition.setBounds(380,335,100,20);
        txtfRemovePosition.setEditable(false);
        add(txtfRemovePosition);
        
        btnRemoveCandidate = new JButton("Remove Candidate");
        btnRemoveCandidate.setBounds(325,370,150,30);
        add(btnRemoveCandidate);
       
        txtaSummary = new JTextArea();
        txtaSummary.setEditable(false);
        
       spSummary = new JScrollPane(txtaSummary);
       spSummary.setLayout(new ScrollPaneLayout());
       spSummary.setBounds(490,10,280,390);
       spSummary.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       add(spSummary);
               
        
        //Election Date:Start Election
        
       hdrElectionDate = new JLabel("Election Date Configuration");
        hdrElectionDate.setBounds(20,400,250,30);
        hdrElectionDate.setFont(new Font("Helvetica", Font.ITALIC,18));
        add(hdrElectionDate);
        
        lblStartElection = new JLabel("Start of Election: ");
        lblStartElection.setBounds(20,465,150,30);
        add(lblStartElection);
        
        JLabel lblDateForm = new JLabel("MM/DD/YY");
        lblDateForm.setBounds(135, 440,80, 30);
        add(lblDateForm);
        
        JLabel lblTimeForm = new JLabel("HH:MM");
        lblTimeForm.setBounds(240, 440,80, 30);
        add(lblTimeForm);
        
        txtfDateStartElection = new JTextField();
        txtfDateStartElection.setBounds(125,465,80,30);
        add(txtfDateStartElection);
        
       txtfTimeStartElection = new JTextField();
        txtfTimeStartElection.setBounds(225,465,80,30);
        add(txtfTimeStartElection);



        //End Election        
         lblEndElection = new JLabel("End of Election: ");
        lblEndElection.setBounds(20,515,150,30);
        add(lblEndElection);
        
        txtfDateEndElection = new JTextField();
        txtfDateEndElection.setBounds(125,515,80,30);
        add(txtfDateEndElection);

        txtfTimeEndElection = new JTextField();
        txtfTimeEndElection.setBounds(225,515,80,30);
        add(txtfTimeEndElection);

        btnCurrentTime = new JButton("Get Current Time");
        btnCurrentTime.setBounds(325, 465, 150, 30);
        add(btnCurrentTime);
                
        btnSetElection = new JButton("Set Election Date");
        btnSetElection.setBounds(325,515,150,30);
        add(btnSetElection);
        
        btnForceEndElection = new JButton("Force Stop");
        btnForceEndElection.setBounds(550,465,150,30);
        btnForceEndElection.setBackground(Color.RED);
        add(btnForceEndElection);
        
        btnSignOut = new JButton("Sign Out");
        btnSignOut.setBounds(550,515,150,30);
        add(btnSignOut);
        
        btnClearCandidate.addActionListener(this);
        btnCurrentTime.addActionListener(this);
        btnAddCandidate.addActionListener(this);
        btnSignOut.addActionListener(this);
        btnRemoveCandidate.addActionListener(this);
        btnSetElection.addActionListener(this);
        
//        setTimeElection setTime = new setTimeElection();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //button for sign out
        if(e.getSource() == btnSignOut){
            int resultSignOut = JOptionPane.showConfirmDialog(this, "Are you sure you want to leave the Admin Page?", "Log Out", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
            
            if(resultSignOut == JOptionPane.YES_OPTION){
                dispose();    
            }else{
                
            }
            
        }
        
        //Button for Getting current time
        if(e.getSource()== btnCurrentTime){
            
            LocalDate currDate = LocalDate.now();
            String formattedCurrDate = currDate.format(formatDate);
            txtfDateStartElection.setText(formattedCurrDate);
            
            LocalTime currTime = LocalTime.now();
            String formattedCurrTime = currTime.format(formatTime);
            txtfTimeStartElection.setText(formattedCurrTime);
        }
        //Adding Candidates
        if(e.getSource()==btnAddCandidate){
            if(!txtfAddName.getText().isBlank() || !txtfAddParty.getText().isBlank() ){
                if(cmbAddPosition.getSelectedIndex() == 0){
                        JOptionPane.showMessageDialog(this, "Select necessary position.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                else{
                        //ENTER ADD CANDIDATE CODE HERE
                    
                        
                        
                        
                        
                         txtfAddName.setText("");
                        txtfAddParty.setText("");
                        cmbAddPosition.setSelectedIndex(0);
                    }
            }
            else{
                    JOptionPane.showMessageDialog(this, "Candidate Information is required.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        
       //Remove Candidates
       if(e.getSource()==btnRemoveCandidate){
            if(!txtfRemoveID.getText().isBlank()){
                int ans = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the selected candidate?", "Are you sure?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if(ans == 0){
                      txtfRemoveName.setText("");
                      txtfRemoveID.setText("");
                      txtfRemovePosition.setText("");
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "No Candidate Selected to remove.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
       
       //Clear Add Candidates
       if(e.getSource() == btnClearCandidate){
           txtfAddName.setText("");
           txtfAddParty.setText("");
           cmbAddPosition.setSelectedIndex(0);
       }
       
       //Select End Date
       if(e.getSource()==btnSetElection){
           if(!txtfDateEndElection.getText().isBlank() || !txtfTimeEndElection.getText().isBlank() || !txtfDateStartElection.getText().isBlank() || !txtfDateStartElection.getText().isBlank()){
               
               
               String StartDate = txtfDateStartElection.getText();
               String StartTime = txtfTimeStartElection.getText();
               
               String EndDate = txtfDateEndElection.getText();
               String EndTime = txtfTimeEndElection.getText();
               
//               setTimeElection = new setTimeElection.isWithinTime(StartDate,StartTime,EndDate,EndTime);
               
               JOptionPane.showMessageDialog(this, "Election dates set, and is now running.", "Date and Time Confirmed", JOptionPane.INFORMATION_MESSAGE);
            }else{
               JOptionPane.showMessageDialog(this, "Date of Election Required.","Error",JOptionPane.ERROR_MESSAGE);
                }
       }
        
//        try{
//            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/dbadmin","andre","Andurehhh619");
//            
//            PreparedStatement ps = (PreparedStatement) con.prepareStatement("INSERT INTO candidate (candidateName, candidateParty, candidatePosition) VALUES(?,?,?)");
//            
//            if(e.getSource() == btnAddCandidate){
//            //ps.setInt(1,1);
//            ps.setString(1, txtfAddName.getText());
//            ps.setString(2,txtfAddParty.getText());
//            ps.setString(3,(String)cmbAddPosition.getSelectedItem());
//            
//                ps.executeUpdate();
//                
//                System.out.println("Success");
                
//                if(ps.next()){
//                    JOptionPane.showMessageDialog(btnAddCandidate, "Successfully Added");
//                }else{
//                    JOptionPane.showMessageDialog(btnAddCandidate,"Unable to Add to Database.","Error",JOptionPane.ERROR_MESSAGE);
//                }
        
            
    //}
            
          
            
        //} 
        //catch(Exception err){
            
        //}
        
//        catch (SQLException sqlException){
//            sqlException.printStackTrace();
//        }
        
        
    }
}
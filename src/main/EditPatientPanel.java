package main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.TableModel;

public class EditPatientPanel extends JPanel
{
	static final long serialVersionUID = 12L;
	JTextField patientID,patientLocationX,patientLocationY,patientStatus,assignedAmbulance;
	PatientsPanel parentFrame;
	JLabel idLabel,locationLabel;
	JComboBox<String> statusComboBox;
	JComboBox<Object> ambulanceComboBox;
	PatientsControl patientsControl;
	TableModel tabModel;
	ArrayList<String> ambulance;
	Object[] newData;
	int row;
	JButton cancelButton,saveButton;
	JLabel title;
	String lastRow;
	
	public EditPatientPanel(PatientsControl patientsControl, int row,PatientsPanel parent)
	{
		setParentFrame(parent);
		setRow(row);
		patientID = new JTextField();
		patientLocationX = new JTextField();
		patientLocationY = new JTextField();
		this.patientsControl = patientsControl;
		newData = new Object[5];
		
		patientID.setEnabled(false);
		tabModel = this.patientsControl.getTableModel();
		lastRow = (String)tabModel.getValueAt(row, 0);
		int lastRow2 = Integer.parseInt(lastRow);
		lastRow = ""+lastRow2;
		patientID.setText(lastRow);
		
		title = new JLabel();
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Sans Serif",Font.BOLD,24));
		
		cancelButton = new JButton("Cancel");
		saveButton = new JButton("Save");
		
		cancelButton.setMaximumSize(new Dimension(150,50));
		saveButton.setMaximumSize(new Dimension(150,50));
		
		String[] status = {"Pending", "Assigned", "Transporting", "Completed"};
		statusComboBox = new JComboBox<String>(status);
		statusComboBox.setEditable(false);
		statusComboBox.setSelectedItem(tabModel.getValueAt(row, 2));
		
		ambulance = new ArrayList<String>();
		ambulance.add("None");
		
		for (int i=0;i<tabModel.getRowCount();i++)
		{
			String data =  (String) tabModel.getValueAt(i, 3);
			if (data != null)
			{
				if (!ambulance.contains(data))
				{
					ambulance.add((String) tabModel.getValueAt(i, 3));
				}
			}
		}
		
		ambulanceComboBox = new JComboBox<Object>(ambulance.toArray());
		ambulanceComboBox.setEditable(false);
		ambulanceComboBox.setSelectedItem(tabModel.getValueAt(row, 3));
		
		patientID.setMaximumSize(new Dimension(200,30));
		newData[0] = lastRow;
		
		patientLocationX.setMaximumSize(new Dimension(100,30));
		String loc = ((String)tabModel.getValueAt(row, 2)).replaceAll("[^0-9]", "");
		patientLocationX.setText(loc);
		patientLocationX.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
				{
					String word = patientLocationX.getText();
					newData[1] = word;
				}
		});
		
		patientLocationY.setMaximumSize(new Dimension(100,30));
		patientLocationY.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
				{
					String word = patientLocationY.getText();
					newData[2] = word;
				}
		});
		
		statusComboBox.setMaximumSize(new Dimension(200,30));
		statusComboBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
				{
					String word = (String) statusComboBox.getSelectedItem();
					newData[3] = word;
				}
		});
		
		ambulanceComboBox.setMaximumSize(new Dimension(200,30));
		ambulanceComboBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
				{
					String word = (String) ambulanceComboBox.getSelectedItem();
					if (!word.equals(""))
						newData[4] = word;
					else
						newData[4] = "";
				}
		});
			
		JPanel firstPanel = new JPanel();
		JPanel secondPanel = new JPanel();
		JPanel thirdPanel = new JPanel();
		JPanel fourthPanel = new JPanel();
		JPanel fifthPanel = new JPanel();
		JPanel sixthPanel = new JPanel();
		
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		add(Box.createRigidArea(new Dimension(0,20)));
		firstPanel.setLayout(new BoxLayout(firstPanel,BoxLayout.X_AXIS));
		firstPanel.add(title);
		add(firstPanel);
		add(Box.createRigidArea(new Dimension(0,20)));
		
		secondPanel.setLayout(new BoxLayout(secondPanel,BoxLayout.X_AXIS));
		secondPanel.add(new JLabel("ID"));
		secondPanel.add(Box.createRigidArea(new Dimension(30,0)));
		secondPanel.add(patientID);
		add(secondPanel);
		add(Box.createRigidArea(new Dimension(0,20)));
		
		thirdPanel.setLayout(new BoxLayout(thirdPanel,BoxLayout.X_AXIS));
		thirdPanel.add(new JLabel("Location"));
		thirdPanel.add(Box.createRigidArea(new Dimension(30,0)));
		thirdPanel.add(patientLocationX);
		thirdPanel.add(Box.createRigidArea(new Dimension(30,0)));
		thirdPanel.add(patientLocationY);
		add(thirdPanel);
		add(Box.createRigidArea(new Dimension(0,20)));
		
		fourthPanel.setLayout(new BoxLayout(fourthPanel,BoxLayout.X_AXIS));
		fourthPanel.add(new JLabel("Status"));
		fourthPanel.add(Box.createRigidArea(new Dimension(30,0)));
		fourthPanel.add(statusComboBox);
		add(fourthPanel);
		add(Box.createRigidArea(new Dimension(0,20)));
		
		fifthPanel.setLayout(new BoxLayout(fifthPanel,BoxLayout.X_AXIS));
		fifthPanel.add(new JLabel("Ambulance"));
		fifthPanel.add(Box.createRigidArea(new Dimension(30,0)));
		fifthPanel.add(ambulanceComboBox);
		add(fifthPanel);
		add(Box.createRigidArea(new Dimension(0,20)));
		
		sixthPanel.setLayout(new BoxLayout(sixthPanel,BoxLayout.X_AXIS));
		sixthPanel.add(cancelButton);
		sixthPanel.add(Box.createRigidArea(new Dimension(30,0)));
		sixthPanel.add(saveButton);
		add(sixthPanel);
		add(Box.createRigidArea(new Dimension(0,20)));
		
	}
	
	public JLabel getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title.setText(title);
	}

	public Object[] getNewData() {
		return newData;
	}

	public void setNewData(Object[] newData) {
		this.newData = newData;
	}

	public JTextField getPatientID() {
		return patientID;
	}

	public void setPatientID(JTextField patientID) {
		this.patientID = patientID;
	}

	public JTextField getPatientLocationX() {
		return patientLocationX;
	}

	public void setPatientLocationX(JTextField patientLocationX) {
		this.patientLocationX = patientLocationX;
	}

	public JTextField getPatientLocationY() {
		return patientLocationY;
	}

	public void setPatientLocationY(JTextField patientLocationY) {
		this.patientLocationY = patientLocationY;
	}

	public JTextField getPatientStatus() {
		return patientStatus;
	}

	public void setPatientStatus(JTextField patientStatus) {
		this.patientStatus = patientStatus;
	}

	public JTextField getAssignedAmbulance() {
		return assignedAmbulance;
	}

	public void setAssignedAmbulance(JTextField assignedAmbulance) {
		this.assignedAmbulance = assignedAmbulance;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	public JButton getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(JButton saveButton) {
		this.saveButton = saveButton;
	}
	
	public PatientsPanel getParentFrame()
	{
		return parentFrame;
	}
	
	public void setParentFrame(PatientsPanel parent)
	{
		this.parentFrame = parent;
	}

	public PatientsControl getPatientsControl() {
		return patientsControl;
	}

	public void setPatientsControl(PatientsControl patientsControl) {
		this.patientsControl = patientsControl;
		
	}
	public void setRow(int row)
	{
		this.row = row;
	}
}


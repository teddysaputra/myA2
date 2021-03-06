package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

public class AddNewPatientControl 
{
	AddNewPatientPanel addNewPatientPanel;
	public AddNewPatientControl(AddNewPatientPanel panel)
	{
		this.addNewPatientPanel = panel;
		
		this.addNewPatientPanel.getCancelButton().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainMenuFrame mainMenuFrame = addNewPatientPanel.getParentFrame().getParentFrame();
				MainMenuFrame newMainMenuFrame = new MainMenuFrame();
				mainMenuFrame.dispose();
				newMainMenuFrame.add(newMainMenuFrame.getPatientsPanel(),BorderLayout.CENTER);
				newMainMenuFrame.setVisible(true);
			}
		});
		
		this.addNewPatientPanel.getSaveButton().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				PatientsControl patientsControl = addNewPatientPanel.getPatientsControl();
				MainMenuFrame mainMenuFrame = addNewPatientPanel.getParentFrame().getParentFrame();
				Object[] newData = addNewPatientPanel.getNewData();
				try
				{
					if (newData[1] == null && newData[2] == null)
					{
						JOptionPane.showMessageDialog(null, "both location is invalid");
					}
					else if (newData[1] == null)
					{
						JOptionPane.showMessageDialog(null, "x-location is invalid");
					}
					else if (newData[2] == null)
					{
						JOptionPane.showMessageDialog(null, "y-location is invalid");
					}
					else
					{
						patientsControl.getPatientCSVDataModel().savePatient(newData);
						MainMenuFrame newMainMenuFrame = new MainMenuFrame();
						mainMenuFrame.dispose();
						newMainMenuFrame.add(newMainMenuFrame.getPatientsPanel(),BorderLayout.CENTER);
						newMainMenuFrame.setVisible(true);
					}
					
				}
				catch(IOException ioe)
				{
					ioe.printStackTrace();
				}
			}
		});
	}
}

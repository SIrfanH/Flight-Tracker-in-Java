import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class AddCapital extends JPanel 
{
	
	private JTextField textField;


	/**
	 * Create the panel.
	 */
	public AddCapital() 
	{
		setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("Capital Name :");
		lblNewLabel_6.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_6.setBounds(187, 143, 160, 30);
		add(lblNewLabel_6);
		
		JButton btnNewButton_1 = new JButton("Add Capital");
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 20));
		
		btnNewButton_1.setBounds(384, 236, 160, 35);
		add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 15));
		textField.setBounds(384, 147, 184, 30);
		add(textField);
		textField.setColumns(10);
		

		btnNewButton_1.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e) 
			{
				
				String capitalName = textField.getText();
					
				Capitals capital = new Capitals();
					
				List<Capitals> capitalList = null;
				try 
				{
					capitalList = capital.getCapitals();
					//System.out.println("inside Try");
				} 
				catch (Exception e2) 
				{
					// TODO Auto-generated catch block
					//System.out.println("Exception");
					e2.printStackTrace();
				}
					
				for(Capitals eachCapital : capitalList)
				{
					if( textField.getText().equalsIgnoreCase(eachCapital.getName()))
					{
						JOptionPane.showMessageDialog(null, "Capital Already Exist");
						System.out.println("For Loop");
						return;
					}
					
					System.out.println("outside if and inside for");
					
				}
					
				capital.setName(textField.getText());
					
				if (!capitalName.isEmpty()) 
				{
					
					try 
					{
						capital.addCapital();
					} 
					catch (IOException e1) 
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						
					JOptionPane.showMessageDialog(null, "Capital Successfuly Added");
					} 
				else 
				{

					JOptionPane.showMessageDialog(null, "Please Enter The Required Fields");

				}

			}
		});
	}
}

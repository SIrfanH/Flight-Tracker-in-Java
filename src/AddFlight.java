import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;

public class AddFlight extends JPanel 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	JComboBox<String> comboBox ;
	JComboBox<String> comboBox_1;

	/**
	 * Create the panel.
	 * @throws Exception 
	 */
	
	public AddFlight() throws Exception 
	{
		setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Airline Name :");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel.setBounds(190, 100, 128, 44);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Flight Number :");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(180, 154, 138, 44);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Departure City :");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(172, 208, 146, 44);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Arrival City :");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(205, 262, 113, 44);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Departure Time :");
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(158, 316, 160, 44);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Arrival Time :");
		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(190, 370, 128, 44);
		add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 20));
		textField.setBounds(406, 111, 160, 30);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Arial", Font.PLAIN, 20));
		textField_1.setBounds(406, 165, 160, 30);
		add(textField_1);
		textField_1.setColumns(10);
		
		comboBox = new JComboBox<String>();
		comboBox.setMaximumRowCount(15);
		comboBox.setFont(new Font("Arial", Font.PLAIN, 20));
		comboBox.setBounds(406, 218, 160, 30);
		add(comboBox);
		
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setMaximumRowCount(15);
		comboBox_1.setFont(new Font("Arial", Font.PLAIN, 20));
		comboBox_1.setBounds(406, 272, 160, 30);
		add(comboBox_1);
		
		long milliseconds = Calendar.getInstance().getTimeInMillis();
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerDateModel(new Date(milliseconds), null, null, Calendar.YEAR));
		spinner.setFont(new Font("Arial", Font.PLAIN, 15));
		spinner.setBounds(406, 327, 160, 30);
		add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerDateModel(new Date(milliseconds), null, null, Calendar.DAY_OF_YEAR));
		spinner_1.setFont(new Font("Arial", Font.PLAIN, 15));
		spinner_1.setBounds(406, 381, 160, 30);
		add(spinner_1);
		
		Capitals capital = new Capitals();
		String[] capitalsList = new String[capital.getCapitals().size()+1];
		int counter = 1;
		
		capitalsList[0] = "Select";
		
		for(Capitals eachCapital: capital.getCapitals())
		{
			capitalsList[counter] = eachCapital.getName();
			counter++;
		}
		
		JButton btnNewButton = new JButton("Add Flight");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				if ( !textField.getText().isEmpty() && !textField_1.getText().isEmpty() 
						&& (comboBox.getSelectedItem().toString() != comboBox_1.getSelectedItem().toString()) ) {

					try
					{
					
						Capitals capital = new Capitals();
						Capitals arrivalCity = capital.findByName(comboBox.getSelectedItem().toString());
						Capitals departureCity = capital.findByName(comboBox_1.getSelectedItem().toString());
						String departureTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(spinner.getValue());
						String arrivalTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(spinner_1.getValue());
					
						Flights flight = new Flights();
						flight.setAirlineName(textField.getText());
						flight.setFlightNumber(textField_1.getText());
						flight.setDepartureCity(departureCity.getName());
						flight.setArrivalCity(arrivalCity.getName());
						flight.setDepartureTime(departureTime);
						flight.setArrivalTime(arrivalTime);
						flight.setDelay("No Delay");
						flight.setTakeoffTime("Not Started");
						flight.setLandingTime("Not Arrived");
						flight.setStatus("Flight Not Started");
						flight.addToFile();
					
						System.out.println("Flight was added");
						JOptionPane.showMessageDialog(null,	"Flight Added Successfuly");
					}
					catch(Exception e1)
					{
						System.out.println("Exception."+ e1.getMessage());
					}
					
				} 
				else if( comboBox.getSelectedItem().toString() == comboBox_1.getSelectedItem().toString())
				{
					JOptionPane.showMessageDialog(null, "Departure and Arrival Cities can't be same");
				}
				else {

					JOptionPane.showMessageDialog(null, "Please Enter The Required Fields");

				}
				
			}
		});
		
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton.setBounds(406, 445, 160, 35);
		add(btnNewButton);
		
		

	}
	
	public void updateComboBox() {
		
		try 
		{
			Capitals capital = new Capitals();
			String[] capitalsList = new String[capital.getCapitals().size()+1];
			int counter = 1;
			
			capitalsList[0] = "Select";
			
			for(Capitals eachCapital: capital.getCapitals())
			{
				capitalsList[counter] = eachCapital.getName();
				counter++;
			}
			
			comboBox.setModel(new DefaultComboBoxModel<String>(capitalsList));
			comboBox_1.setModel(new DefaultComboBoxModel<String>(capitalsList));
			
		}
		catch (Exception e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

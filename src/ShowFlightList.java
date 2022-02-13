import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ShowFlightList extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtIncompletedFlightsList;

	/**
	 * Create the panel.
	 * @throws FileNotFoundException 
	 */
	public ShowFlightList() throws FileNotFoundException 
	{
		setLayout(null);
		
		Flights flight = new Flights();
		Object[][] flightList = new Object[flight.getList().size()][11];
		int counter = 0;

		for(Flights eachFlight: flight.getList())
		{
			flightList[counter][0] = eachFlight.getId();
			flightList[counter][1] = eachFlight.getAirlineName();
			flightList[counter][2] = eachFlight.getFlightNumber();
			flightList[counter][3] = eachFlight.getDepartureCity();
			flightList[counter][4] = eachFlight.getArrivalCity();
			flightList[counter][5] = eachFlight.getDepartureTime();
			flightList[counter][6] = eachFlight.getArrivalTime();
			flightList[counter][7] = eachFlight.getDelay();
			flightList[counter][8] = eachFlight.getTakeoffTime();
			flightList[counter][9] = eachFlight.getLandingTime();
			flightList[counter][9] = eachFlight.getStatus();
			counter++;
		}
		
		Object[] columns = { "ID", "Airline Name", "Flight Number", "Departure City", 
							"Arrival City", "Departure Time", "Arrival Time", "Delay", 
							"Take off Time", "Landing Time", "Status" };
		
		table = new JTable(flightList, columns);
		table.setBounds(23, 26, 708, 388);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 38, 780, 376);
		add(scrollPane);
		
		JButton btnNewButton = new JButton("Delete Selected Flight");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				deleteFlight();
			}
		});
		
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 15));
		btnNewButton.setBounds(271, 447, 204, 42);
		add(btnNewButton);
		
		txtIncompletedFlightsList = new JTextField();
		txtIncompletedFlightsList.setEditable(false);
		txtIncompletedFlightsList.setText("Incompleted Flights List");
		txtIncompletedFlightsList.setBounds(10, 10, 149, 19);
		add(txtIncompletedFlightsList);
		txtIncompletedFlightsList.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Refresh List");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateFlightsTable();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(637, 468, 114, 21);
		add(btnNewButton_1);
		
	}
	
	public void updateFlightsTable() throws FileNotFoundException 
	{
		
		Flights flight = new Flights();
		Object[][] flightList = new Object[flight.getList().size()][11];
		int counter = 0;

		for(Flights eachFlight: flight.getList())
		{
			flightList[counter][0] = eachFlight.getId();
			flightList[counter][1] = eachFlight.getAirlineName();
			flightList[counter][2] = eachFlight.getFlightNumber();
			flightList[counter][3] = eachFlight.getDepartureCity();
			flightList[counter][4] = eachFlight.getArrivalCity();
			flightList[counter][5] = eachFlight.getDepartureTime();
			flightList[counter][6] = eachFlight.getArrivalTime();
			flightList[counter][7] = eachFlight.getDelay();
			flightList[counter][8] = eachFlight.getTakeoffTime();
			flightList[counter][9] = eachFlight.getLandingTime();
			flightList[counter][10] = eachFlight.getStatus();
			counter++;
		}
		
		Object[] columns = { "ID", "Airline Name", "Flight Number", "Departure City", 
				"Arrival City", "Departure Time", "Arrival Time", "Delay", 
				"Take off Time", "Landing Time", "Status" };
		
		table.setModel(new DefaultTableModel(flightList, columns));
		
	}
	
	public void deleteFlight() 
	{
		
		DefaultTableModel table1 = (DefaultTableModel)table.getModel();
        int SelectedIndex = table.getSelectedRow();
        
        int id = (int) table1.getValueAt(SelectedIndex, 0);
        
        try
        {
        	
        	Flights flight = new Flights();
        	List <Flights> flightList = flight.getList();
    		flight.blankFile();

    		for(Flights eachFlight: flightList)
    		{
    			if (id != eachFlight.getId())
    			{
    				eachFlight.addToFile();
    				
    			}
    		}
    		
    		updateFlightsTable();
    		JOptionPane.showMessageDialog(null,	"Flight Deleted Successfuly");
    		
        }
		catch(Exception e)
        {
            System.out.println(e);
            System.out.println("EXception");
        }
        
        
	}
}

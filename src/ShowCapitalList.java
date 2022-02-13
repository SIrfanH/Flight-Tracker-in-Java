
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowCapitalList extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTable table;

	/**
	 * Create the panel.
	 * @throws Exception 
	 */
	public ShowCapitalList() throws Exception 
	{
		setLayout(null);
		
		Capitals capital = new Capitals();
		Object[][] capitalsList = new Object[capital.getCapitals().size()][2];
		int counter = 0;

		for(Capitals eachCapital: capital.getCapitals())
		{
			
			capitalsList[counter][0] = eachCapital.getId();
			capitalsList[counter][1] = eachCapital.getName();
			counter++;
			
		}
		
		String[] columns = { "ID", "Capital" };

		table = new JTable(capitalsList, columns);
		table.setBounds(23, 26, 708, 388);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 38, 741, 376);
		add(scrollPane);
		
		JButton btnNewButton = new JButton("Delete Selected Capital");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				deleteCapital();
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(293, 454, 170, 37);
		add(btnNewButton);
		

	}
	
	public void updateTable() throws Exception 
	{
		
		Capitals capital = new Capitals();
		Object[][] capitalsList = new Object[capital.getCapitals().size()][2];
		int counter = 0;

		for(Capitals eachCapital: capital.getCapitals())
		{
			capitalsList[counter][0] = eachCapital.getId();
			capitalsList[counter][1] = eachCapital.getName();
			counter++;
		}
		String[] columns = { "ID", "Capital" };
		table.setModel(new DefaultTableModel(capitalsList, columns));
		
	}
	
	public void deleteCapital() 
	{
		
		DefaultTableModel table1 = (DefaultTableModel)table.getModel();
        int SelectedIndex = table.getSelectedRow();
        
        int id = (int) table1.getValueAt(SelectedIndex, 0);
        
        try{
        	
        	Capitals capital = new Capitals();
        	List <Capitals> capitalList = capital.getCapitals();
    		capital.blankFile();

    		for(Capitals eachCapital: capitalList)
    		{
    			if (id != eachCapital.getId()) {
    				eachCapital.addCapital();
    				
    			}
    		}
    		
    		updateTable();
    		JOptionPane.showMessageDialog(null,	"Capital Deleted Successfuly");
    		
        }
		catch(Exception e){
            System.out.println(e);
            System.out.println("EXception");
        }
        
	}
	
}

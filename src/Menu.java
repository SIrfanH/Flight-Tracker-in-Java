import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;

public class Menu 
{

	private JFrame frame;
	private AddCapital panel;
	private AddFlight panel_1;
	private ShowCapitalList panel_2;
	private ShowFlightList panel_3;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Menu window = new Menu();
					window.frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public Menu() throws Exception 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	
	private void initialize() throws Exception 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 816, 571);
		frame.getContentPane().add(tabbedPane);
		
		panel = new AddCapital();
		tabbedPane.addTab("Add Capital", null, panel, null);
		panel.setBounds(12, 12, 790, 500);
		
		panel_1 = new AddFlight();
		tabbedPane.addTab("Add Flight", null, panel_1, null);
		panel_1.setBounds(12, 12, 790, 500);
		
		panel_2 = new ShowCapitalList();
		tabbedPane.addTab("Capitals List", null, panel_2, null);
		panel_2.setBounds(12, 12, 790, 500);
		
		panel_3 = new ShowFlightList();
		tabbedPane.addTab("Flights List", null, panel_3, null);
		panel_3.setBounds(12, 12, 790, 500);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("System");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Start System");
		mnNewMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				MainThread.state=1;		 
			}
		});
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Pause System");
		mnNewMenu.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				MainThread.state=2;
				JOptionPane.showMessageDialog(null,	"Flight Stats Are Saved At Flight Reports.txt");
			}
		});
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Resume System");
		mnNewMenu.add(mntmNewMenuItem_2);
		mntmNewMenuItem_2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				MainThread.state=1;		 
			}
		});
		
		JMenu systemTime = new JMenu("SystemTime");
		menuBar.add(systemTime);
		
		MainThread mainThread = new MainThread(systemTime);
		Thread thread = new Thread(mainThread);
		thread.start();
		
		ChangeListener changeListener = new ChangeListener() 
		{
			
			public void stateChanged(ChangeEvent e) 
			{
				
				System.out.println("Focus Gained");
				panel_1.updateComboBox();
				
				try 
				{
					
					panel_2.updateTable();
					panel_3.updateFlightsTable();
					
				} 
				catch (Exception e1)
				{
					
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
			}
		};
		
		tabbedPane.addChangeListener(changeListener);
		
	}
}

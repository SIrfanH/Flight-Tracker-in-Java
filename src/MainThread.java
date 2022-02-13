import javax.swing.JMenu;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

public class MainThread implements Runnable 
{
	
		private long miliseconds;
		private JMenu systemTime;
		public static int state = 2;

		/**
		 * @wbp.parser.entryPoint
		 */
		public MainThread(JMenu systemTime) 
		{
			
			this.systemTime = systemTime;
			this.setMiliseconds(Calendar.getInstance().getTimeInMillis());
		}

		@Override
		public void run() 
		{
			
			JMenu systemTime = this.getSystemTime();
			long miliseconds = Calendar.getInstance().getTimeInMillis();
			Timer timer = new Timer();
			
			TimerTask task = new TimerTask() {

				long milliseconds_time = miliseconds;
				@Override
				public void run() 
				{

					if (state == 1) 
					{
						
						Date time = new Date(milliseconds_time);
						String timeToText = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(time);
						systemTime.setText(timeToText + "(Hour : Minute)");
						milliseconds_time += TimeUnit.MINUTES.toMillis(1);
						
						Flights flight = new Flights();
						List<Flights> results2 = null;
						
						try 
						{
							
							results2 = flight.flightsDepartureTime(timeToText);
							for (Flights eachFlight : results2)
							{
								if (results2.size() > 0)
								{
									System.out.print("Working");
									ControlTower ft = new ControlTower(eachFlight);
									Thread t = new Thread(ft);
									t.start();
								}
							}
						} 
						catch (FileNotFoundException e) 
						{
							// TODO Auto-generated catch block
							System.out.println("Exception :"+e.getMessage());
						}

					}
				}
			};

			timer.schedule( task, 0, 1000 );

		}

		public JMenu getSystemTime() 
		{
			return systemTime;
		}

		public void setSystemTime(JMenu systemTime) 
		{
			this.systemTime = systemTime;
		}

		public long getMiliseconds() 
		{
			return miliseconds;
		}

		public void setMiliseconds(long miliseconds) 
		{
			this.miliseconds = miliseconds;
		}

	};


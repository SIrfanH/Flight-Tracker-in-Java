
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ControlTower implements Runnable 
{
	
	private Flights flight;

	public ControlTower(Flights flight) 
	{

		setFlight(flight);
		
	}

	@Override
	public void run() 
	{
		
		System.out.println("Yep it's working");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date departureTime = null;
		try 
		{
			departureTime = format.parse(flight.getDepartureTime());
		} catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date arrivalTime = null;
		try {
			arrivalTime = format.parse(flight.getArrivalTime());
		} catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long time = (arrivalTime.getTime()-departureTime.getTime())/(60);
		
		System.out.println(time);
		
		flight.setTakeoffTime(flight.getDepartureTime());
		flight.setDelay("On Time");
		flight.setStatus("Started");
		
		try 
		{
			Thread.sleep(time);
		} catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//long currentTime = Calendar.getInstance().getTimeInMillis();
		flight.setLandingTime(flight.getArrivalTime());
		flight.setStatus("Landed");
		
		FileWriter file = null;
		try 
		{
			file = new FileWriter("Flight Reports.txt", true);
			file.write("Airlines Name: "+flight.getAirlineName()+" ,");
			file.write("Flight Number: "+flight.getFlightNumber()+" ,"); 
			file.write("Departure: "+flight.getDepartureCity()+" ,");
			file.write("Arrive : "+flight.getArrivalCity()+" ,");
			file.write("Departure Time : "+flight.getDepartureTime()+" ,");
			file.write("Arrival Time : "+flight.getArrivalTime()+" ,");
			file.write("Take off Time : "+flight.getTakeoffTime()+" ,");
			file.write("Landing Time : "+flight.getLandingTime()+" ,");
			file.write("Status : "+flight.getStatus()+" "); 
			//file.write("Time taken: "+ currentTime);
			file.write("\r\n");
			file.write("\r\n");
			file.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done");
		
	}

	
	public Flights getFlight() 
	{
		return flight;
	}

	public void setFlight(Flights flight) 
	{
		this.flight = flight;
	}

}

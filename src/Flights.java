import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Flights 
{
	
	private int id;
	private String flightNumber;
	private String airlineName;
	private String departureCity;
	private String arrivalCity;
	private String departureTime;
	private String arrivalTime;
	private String takeoffTime;
	private String landingTime;
	private String status;
	private String file = "Flights.txt";
	private String delay;
	
	
	public Flights() 
	{
		
		File myfile = new File(file);
		try 
		{
			myfile.createNewFile();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public String getTakeoffTime() 
	{
		return takeoffTime;
	}


	public void setTakeoffTime(String takeoffTime) 
	{
		this.takeoffTime = takeoffTime;
	}


	public String getLandingTime() 
	{
		return landingTime;
	}


	public void setLandingTime(String landingTime) 
	{
		this.landingTime = landingTime;
	}


	public String getStatus() 
	{
		return status;
	}


	public void setStatus(String status) 
	{
		this.status = status;
	}


	public int getId() 
	{
		return id;
	}


	public void setId(int id) 
	{
		this.id = id;
	}


	public String getFlightNumber() 
	{
		return flightNumber;
	}


	public void setFlightNumber(String flightNumber) 
	{
		this.flightNumber = flightNumber;
	}


	public String getAirlineName() 
	{
		return airlineName;
	}


	public void setAirlineName(String airlineName) 
	{
		this.airlineName = airlineName;
	}


	public String getDepartureCity() 
	{
		return departureCity;
	}


	public void setDepartureCity(String departureCity) 
	{
		this.departureCity = departureCity;
	}


	public String getArrivalCity() 
	{
		return arrivalCity;
	}


	public void setArrivalCity(String arrivalCity) 
	{
		this.arrivalCity = arrivalCity;
	}


	public String getDepartureTime() 
	{
		return departureTime;
	}


	public void setDepartureTime(String departureTime) 
	{
		this.departureTime = departureTime;
	}


	public String getArrivalTime() 
	{
		return arrivalTime;
	}


	public void setArrivalTime(String arrivalTime) 
	{
		this.arrivalTime = arrivalTime;
	}


	public String getDelay() 
	{
		return delay;
	}


	public void setDelay(String delay) 
	{
		this.delay = delay;
	}
	
	
	
	public boolean addToFile() throws IOException 
	{
		
		FileWriter file_1 = new FileWriter(file, true);
		
		file_1.write((lastFlightID() + 1) + "#" + getAirlineName() + "#"+ getFlightNumber() + "#" + getDepartureCity()+ "#" + getArrivalCity() + 
				"#" + getDepartureTime()+ "#" + getArrivalTime()+ "#" + getDelay() + "#" + getTakeoffTime() + "#" + 
				getLandingTime() + "#" + getStatus());
		
		file_1.write("\r\n");
		file_1.close();
				
		return true;
				
	}
	
	public List<Flights> flightsDepartureTime(String time) throws FileNotFoundException 
	{
		
		Scanner scanner = new Scanner(new FileInputStream(file));
		List<Flights> list = new ArrayList<Flights>();
		String[] item = null;
		
		while (scanner.hasNextLine()) 
		{
			
			item = scanner.nextLine().split("#");
			if (item[5].equals(time)) 
			{
				
				System.out.println(item[1]);
				Flights flight = new Flights();
				flight.setId(Integer.parseInt(item[0]));
				flight.setAirlineName(item[1]);
				flight.setFlightNumber(item[2]);
				flight.setDepartureCity(item[3]);
				flight.setArrivalCity(item[4]);
				flight.setDepartureTime(item[5]);
				flight.setArrivalTime(item[6]);
				flight.setDelay(item[7]);
				flight.setTakeoffTime(item[8]);
				flight.setLandingTime(item[9]);
				flight.setStatus(item[10]);
				list.add(flight);
				
			}
			
		}
		
		scanner.close();
		return list;

	}
	
	public int lastFlightID() throws FileNotFoundException 
	{
		Scanner scanner = new Scanner(new FileInputStream(file));
		String[] lastLine = null;
		int flightCount = 0;
		
		while (scanner.hasNextLine()) 
		{
			lastLine = scanner.nextLine().split("#");
			flightCount++;
		}
		scanner.close();
		
		if ( flightCount == 0 ) 
		{
			return 0;
		} 
		else 
		{
			return Integer.parseInt(lastLine[0]);
		}

	}
	
	public List<Flights> getList() throws FileNotFoundException 
	{
		Scanner scanner = new Scanner(new FileInputStream(file));
		List<Flights> flightList = new ArrayList<Flights>();
		String[] item = null;
		
		while (scanner.hasNextLine()) 
		{
			item = scanner.nextLine().split("#");
			Flights flight = new Flights();
			flight.setId(Integer.parseInt(item[0]));
			flight.setAirlineName(item[1]);
			flight.setFlightNumber(item[2]);
			flight.setDepartureCity(item[3]);
			flight.setArrivalCity(item[4]);
			flight.setDepartureTime(item[5]);
			flight.setArrivalTime(item[6]);
			flight.setDelay(item[7]);
			flight.setTakeoffTime(item[8]);
			flight.setLandingTime(item[9]);
			flight.setStatus(item[10]);

			flightList.add(flight);
		}
		scanner.close();

		return flightList;

	}
	
	public void blankFile() throws IOException
	{
		
		FileWriter file_1 = new FileWriter(file);
		file_1.write("");
		file_1.close();
		
	}
	
	public boolean updateFlight() throws IOException 
	{
		
		FileWriter file_1 = new FileWriter(file, true);
		file_1.write((getId()) + "#" + getAirlineName() + "#"+ getFlightNumber() + "#" + getDepartureCity()+ "#" + getArrivalCity() + "#" 
				+ getDepartureTime() + "#" + getArrivalTime()+ "#" +getDelay() + "#" + getTakeoffTime() + "#"+ getLandingTime() + "#" + getStatus());
		file_1.write("\r\n");
		file_1.close();
		return true;
		
	}
	
	
	
	
	
	
	
	

}

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Capitals {

	private int id;
	private String name;
	private String file = "Capitals.txt";
	
	
	public Capitals() {
		File myfile = new File(file);
		try {
			myfile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int lastCapitalID() throws FileNotFoundException 
	{
		
		Scanner scanner = new Scanner(new FileInputStream(file));
		String[] lastLine = null;
		int capitalCount = 0;
		while (scanner.hasNextLine()) 
		{
			lastLine = scanner.nextLine().split("-");
			capitalCount++;
		}
		scanner.close();
		
		if ( capitalCount == 0 )
		{
			return 0;
		} 
		else 
		{
			return Integer.parseInt(lastLine[0]);
		}
	}

	public boolean addCapital() throws IOException
	{
		FileWriter fw=new FileWriter(file,true);
		fw.write((lastCapitalID()+1)+"-"+getName()) ;
		fw.write("\r\n");
		fw.close();
		return true;
	}

	public boolean updateCapital() throws IOException{
		FileWriter fw=new FileWriter(file,true);
		fw.write((getId())+"-"+getName());
		fw.write("\r\n");
		fw.close();
		return true;
	}
	
	public Capitals findById(int id) throws FileNotFoundException
	{
		Scanner scanner = new Scanner(new FileInputStream(file));
		String[] currentLine = null;
		while (scanner.hasNextLine()) 
		{
			currentLine = scanner.nextLine().split("-");
		    if(currentLine[0].equals(Integer.toString(id)))
		    {
		        Capitals capital=new Capitals();
				capital.setId(Integer.parseInt(currentLine[0]));
				capital.setName(currentLine[1]);
				scanner.close();
		        return capital;
		    }
		   
		}
		scanner.close();
		return null;
	}
	
	public List<Capitals> getCapitals() throws Exception
	{
		Scanner scanner = new Scanner(new FileInputStream(file));
		List<Capitals>list = new ArrayList<Capitals>();
		String[] item = null;
			while( scanner.hasNextLine() )
			{
				item = scanner.nextLine().split("-");
				Capitals capital = new Capitals();
				capital.setId(Integer.parseInt(item[0]));
				capital.setName(item[1]);
				list.add(capital);
			}
			
		scanner.close();
		return list;
		
	}
	
	public void blankFile() throws IOException
	{
		FileWriter fw=new FileWriter(file);
		fw.write("");
		fw.close();
		
	}
	
	public Capitals findByName(String name) throws Exception
	{
		File myfile = new File("Capitals.txt");
		myfile.createNewFile();
		Scanner scanner = new Scanner(new FileInputStream(file));
		String[] currentLine = null;
		while ( scanner.hasNextLine() ) 
		{
			currentLine = scanner.nextLine().split("-");
			if(currentLine[1].equals(name))
			{
				Capitals capital=new Capitals();
				capital.setId(Integer.parseInt(currentLine[0]));
				capital.setName(currentLine[1]);
				scanner.close();
		        return capital;
			}
		   
		 }
		
		scanner.close();
		return null;
	}
}

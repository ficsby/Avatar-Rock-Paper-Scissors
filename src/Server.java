import java.net.*;
import java.io.*;

public class Server 
{
	public static void main(String[] args)
	{
		File pokeFile = new File("Poke.dat");
		Computer comp = new Computer();
		String attackPattern = "";
		if(pokeFile.exists())
		{ 
			try
			{
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pokeFile));
				comp = (Computer) ois.readObject();
				attackPattern = ois.readUTF();
				ois.close();
			}catch(IOException err){
				System.out.println("IO Exception error.");
				err.printStackTrace();
			}catch(ClassNotFoundException err)
			{
				System.out.println("Incorrect class.");
				err.printStackTrace();
			}
		}
		try
		{
			while(true)
			{
				ServerSocket serverS = new ServerSocket(1025);
				System.out.println("Waiting...");
				Socket s = serverS.accept();
				System.out.println("Connected");
				BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				PrintStream out = new PrintStream(s.getOutputStream());
				//GET CLIENT INPUT
				String attack = in.readLine();
				System.out.println("Server Receiving Data...\nAttack: " + attack);
				
				if(attack.equals("Q"))
				{
					writeFile(pokeFile, comp, attackPattern);
					serverS.close();
				}
				
				int compPredict = comp.makePrediction(attackPattern); 
				//PROCESS INPUT
				attackPattern += attack;
				System.out.println("PATTERN: " + attackPattern);
				if(attackPattern.length()>= 3)
				{
					if(attackPattern.length() == 4){
						attackPattern = attackPattern.substring(1);
					}
					comp.storePattern(attackPattern);
					
				}
								
				//SEND RESULT BACK TO CLIENT
				System.out.println("Server Sending Result...");
				out.println(compPredict);
				out.flush();
				serverS.close();
			}			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Writes the file to be saved to allow the player to continue
	 * @param pokeFile: The file written into (to be saved)
	 * @param comp: Computer opponent
	 */
	public static void writeFile(File pokeFile, Computer comp, String attackPattern)
	{
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pokeFile));
			oos.writeObject(comp);
			oos.writeUTF(attackPattern);
			oos.close();
		}catch(IOException err)
		{
			System.out.println("IOException Error. server");
		}	
	}	
}


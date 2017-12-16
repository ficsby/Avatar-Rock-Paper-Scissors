import java.io.Serializable;
import java.util.HashMap;
/**
 * 
 * @author Christine
 * Represents computer opponent
 */
public class Computer implements Serializable
{
	/**
	 * Declares the hashmap of the attack patterns; key type: pattern, value type: integer
	 */
	private HashMap<Pattern, Integer> pokeHash;
	/**
	 * Constructs the computer class
	 */
	public Computer()
	{
		pokeHash = new HashMap<Pattern, Integer>();
	}
	
	/**
	 * Returns a pokemon move type from predictions based on previous patterns
	 * @param atkPattern
	 * @return pokemon move type (1: fire; 2: water, 3: grass)
	 */
	public int makePrediction(String atkPattern)
	{
		Pattern predictPat = new Pattern(atkPattern);
		int fireFrequency = 0;
		int waterFrequency = 0;
		int grassFrequency = 0;
		
		if(atkPattern.length() == 3)
		{
			Pattern predictFire = new Pattern(predictPat.getPattern().substring(1) + "F");
			Pattern predictWater = new Pattern(predictPat.getPattern().substring(1) + "W");
			Pattern predictGrass = new Pattern(predictPat.getPattern().substring(1) + "G");
	
			if(pokeHash.containsKey(predictPat))
			{
				if(pokeHash.containsKey(predictFire))
				{
					fireFrequency = pokeHash.get(predictFire);
				}
				if(pokeHash.containsKey(predictWater))
				{
					waterFrequency = pokeHash.get(predictWater);
				}
				if(pokeHash.containsKey(predictGrass))
				{
					grassFrequency = pokeHash.get(predictGrass);
				}
				if(fireFrequency == waterFrequency && fireFrequency == grassFrequency)
				{
					return(int)(Math.random()*3+1);
				}
				else if(fireFrequency > waterFrequency && fireFrequency > grassFrequency)
				{
					return 2;
				}
				else if(waterFrequency > fireFrequency && waterFrequency > grassFrequency)
				{
					return 3;
				}
				else if(grassFrequency > fireFrequency && grassFrequency > waterFrequency)
				{
					return 1;
				}
			}
		}
		return(int)(Math.random()*3+1);
	}
	
	/**
	 * Stores the pattern:
	 * If exists then increments pattern's value
	 * If does not exist yet, then stores a new pattern (value: 1)
	 * @param storeAtk
	 */
	public void storePattern(String storeAtk)
	{
		Pattern pokePat = new Pattern(storeAtk); 
		if(pokeHash.containsKey(pokePat))
		{
			pokeHash.put(pokePat,pokeHash.get(pokePat)+1);
		}
		else
		{
			pokeHash.put(pokePat,1);
		}
		System.out.println("POKE HASH: " + pokeHash.get(storeAtk));
	}

}

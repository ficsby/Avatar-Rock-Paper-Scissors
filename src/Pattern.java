import java.io.Serializable;

/**
 * @author Christine
 * Represents pattern key
 *
 */
public class Pattern implements Serializable
{
	/**
	 * Represents pattern (String)
	 */
	private String pattern;
	/**
	 * Contructs the pattern
	 * @param p
	 */	
	public Pattern(String p)
	{
		pattern = p;
	}
	
	/**
	 * Gets the pattern (String)
	 * @return pattern
	 */
	public String getPattern()
	{
		return pattern;
	}
	
	/**
	 * Checks to see if two patterns are equal (after confirming object is a pattern)
	 * Returns true if equal patterns
	 * Returns false if not equal patterns
	 */
	@Override
	public boolean equals(Object p)
	{
		if(p instanceof Pattern)
		{
			Pattern pat = (Pattern) p;
			return pat.pattern.equals(pattern);
		}
		return false;
	}
	
	/**
	 * Returns the hashcode of a pattern
	 */
	@Override
	public  int hashCode()
	{
		return pattern.hashCode();
	}
	
}

 package hegmanns.it.de.junit.basisklassen.komposite.ergebnis;

import java.util.HashMap;
import java.util.Map;

public class Konverterfactory {

	Map<String, ValueKonverter<?>> konverterMap;
	
	public Konverterfactory()
	{
		this.konverterMap = new HashMap<>();
	}
	
	public <T> ValueKonverter<T> locateValuekonverter(String s)
	{
		ValueKonverter<T> valueKonverter = (ValueKonverter<T>) konverterMap.get(s);
		if (valueKonverter == null)
		{
			Object instanz = null;
			try {
				Class<?> klasse = Class.forName(s);
				instanz = klasse.newInstance();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			konverterMap.put(s, (ValueKonverter<T>)instanz);
		}
		
		return valueKonverter;
	}
}

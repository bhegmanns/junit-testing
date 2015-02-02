package hegmanns.it.de.junit.testing.abstractservice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractService {

	public final int ermittleAnzahl(String wert)
	{
		if (wert == null || wert.isEmpty())
		{
			return 0;
		}
		
		int ergebnis1 = findeErgebnis1(wert);
		int ergebnis2 = findeErgebnis2(wert, ergebnis1);
		
		
		if (ergebnis1 != ergebnis2)
		{
			HashSet<Integer> ergebnisse = new HashSet<Integer>(Arrays.asList(ergebnis1, ergebnis2));
			aggregiere(ergebnisse);
			return auswaehlen(ergebnisse);
		}
		else
		{
			return ergebnis1;
		}
		
	}
	
	protected abstract int findeErgebnis1(String wert);
	
	protected abstract int findeErgebnis2(String wert, int zwischenergebnis);
	
	protected abstract void aggregiere(Set<Integer> ergebnisse);
	
	protected abstract int auswaehlen(Set<Integer> ergebnis);
}

package hegmanns.it.de.junit.testing.theorien;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * Ein kleines Beispiel fuer die Kombination von Theories und Assume, um auch bei sehr vielen
 * moeglichen Eingangsparametern / Kombinationen nur spezielle Aspekte in einem Test zu kontrollieren.
 * 
 * Freilich sollte moeglichst sicher gestellt werden, dass dennoch alle noetigen Varianten getestet
 * werden koennen.
 * 
 * @author B. Hegmanns
 */
@RunWith(Theories.class)
public class DurchdividierenTest {

	@DataPoints
	public static Integer[] ZAEHLER = erstelleArray(0, 20);
	
	@DataPoints
	public static Integer[] NENNER = erstelleArray(0, 50);
	
	private static Integer[] erstelleArray(int von, int bis)
	{
		List<Integer> zahlen = new ArrayList<>(bis+1);
		for (int i = von ; i < bis+1 ; i++){
			zahlen.add(i);
		}
		
		return zahlen.toArray(new Integer[zahlen.size()]);
	}
	
	@Theory
	public void divisionDurchNullErzeugtNumericException(Integer zaehler, Integer nenner)
	{
		Assume.assumeTrue(nenner == 0);
		double quotient = 99999;
		try{
			quotient = zaehler / nenner;
			Assert.fail("Darf nicht auftreten");
		}catch(ArithmeticException e)
		{
			
		}
		assertThat("", quotient, comparesEqualTo(99999.0));
	}
	
	@Theory
	public void divisionMitZaehlerNullErgibtImmerNull(Integer zaehler, Integer nenner)
	{
		Assume.assumeTrue(zaehler == 0);
		Assume.assumeTrue(nenner != 0);
		
		int quotient = zaehler / nenner;
		assertThat("", quotient, is(0));
	}
}

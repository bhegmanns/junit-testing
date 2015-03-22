package hegmanns.it.de.junit.basisklassen;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Eine Sammlung betrachteter Konten, im Sinne eines Portfolios oder "Asset under Management".
 *  
 * @author B. Hegmanns
 *
 */
public class KontoAccount {

	private List<Konto> konten;
	
	public KontoAccount(Konto ... konten)
	{
		this.konten = Arrays.asList(konten);
	}
	
	/**
	 * Gibt die Gesamt-Kreditlinie zurueck.
	 * 
	 * @return die Gesamt-Kreditlinie
	 */
	public Geldbetrag getGesamtKreditlinie()
	{
		Geldbetrag gesamtKreditlinie = Geldbetrag.createInEuro(BigDecimal.ZERO);
		
		for (Konto konto : konten)
		{
			gesamtKreditlinie = gesamtKreditlinie.add(konto.getKreditlinie());
		}
		
		return gesamtKreditlinie;
	}
	
	/**
	 * den Gesamt-Verfuegbaren Betrag aller betrachteten Konten.
	 * 
	 * @return den Gesamt-verfuegbaren Betrag
	 */
	public Geldbetrag getGesamtVerfuegbarerBetrag()
	{
		Geldbetrag gesamtVerfuegbarerBetrag = Geldbetrag.createInEuro(BigDecimal.ZERO);
		
		for (Konto konto : konten)
		{
			gesamtVerfuegbarerBetrag = gesamtVerfuegbarerBetrag.add(konto.getAktuellVerfuegbarerBetrag());
		}
		
		return gesamtVerfuegbarerBetrag;
	}
}

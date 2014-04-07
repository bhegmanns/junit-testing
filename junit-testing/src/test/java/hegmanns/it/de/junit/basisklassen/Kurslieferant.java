package hegmanns.it.de.junit.basisklassen;

import java.math.BigDecimal;

public interface Kurslieferant {

	public BigDecimal getFaktor(Waehrung quellwaehrung, Waehrung zielwaehrung);
}

package hegmanns.it.de.junit.basisklassen;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Abbildung eines Konto, hier recht banal realisiert.
 * 
 * @author B. Hegmanns
 */
public class Konto
{

    /**
     * Die Kontonummer.
     */
    private String kontonummer;

    /**
     * Die Kontoart.
     */
    private Kontoart kontoart;

    /**
     * Das zum {@link #zeitpunktAktualisierung Akualisierungszeitpunkt}
     * vorhandene Kontosaldo.
     */
    private BigDecimal saldo;

    /**
     * Die Kreditlinie dieses Kontos.
     * 
     * Kennzeichnet den maximalen negativen Betrag dieses Kontos. Sofern keine
     * Kreditlinie existiert, steht hier {@link BigDecimal#ZERO}.
     */
    private BigDecimal kreditlinie;

    /**
     * Zeitpunkt bzw. Bezugszeitpunkt der letzten Aktualisierung dieses Kontos.
     * 
     * <p>
     * Im Allgemeinen werden die Konten durch naechtliche Ablaeufe (Batche)
     * aktualisiert. In diesem Fall wuerde hier ein Bezugsdatum stehen.
     * </p>
     */
    private Date zeitpunktAktualisierung;

    /**
     * Standard-Konstruktor
     */
    public Konto()
    {
        super();
    }

    /**
     * Konstruktor.
     * 
     * @param kontonummer
     *            die Kontonummer
     * @param kontoart
     *            die Kontoart, {@link Kontoart}
     * @param saldo
     *            das aktuelle Saldo zum Erstellungszeitpunkt
     * @param kreditlinie
     *            die Kreditlinie
     */
    public Konto( String kontonummer, Kontoart kontoart, BigDecimal saldo, BigDecimal kreditlinie )
    {
        super();
        this.kontonummer = kontonummer;
        this.kontoart = kontoart;
        this.saldo = saldo;
        this.kreditlinie = kreditlinie;
        this.zeitpunktAktualisierung = new Date();
    }

    /**
     * Getter fuer die Kontonummer
     * 
     * @return die Kontonummer als String
     */
    public String getKontonummer()
    {
        return kontonummer;
    }

    /**
     * Setter fuer die Kontonummer
     * 
     * @param kontonummer
     *            Kontonummer
     */
    public void setKontonummer( String kontonummer )
    {
        this.kontonummer = kontonummer;
    }

    /**
     * Getter fuer die Kontoart.
     * 
     * @return Kontoart
     */
    public Kontoart getKontoart()
    {
        return kontoart;
    }

    /**
     * Setter fuer die Kontoart.
     * 
     * @param kontoart
     *            Kontoart
     */
    public void setKontoart( Kontoart kontoart )
    {
        this.kontoart = kontoart;
    }

    /**
     * Getter fuer das Saldo.
     * 
     * @return Saldo
     */
    public BigDecimal getSaldo()
    {
        return saldo;
    }

    /**
     * Setter fuer das Saldo.
     * 
     * @param saldo
     *            Saldo
     */
    public void setSaldo( BigDecimal saldo )
    {
        this.saldo = saldo;
    }

    /**
     * Getter fuer die Kreditlinie.
     * 
     * @return Kreditlinie
     */
    public BigDecimal getKreditlinie()
    {
        return kreditlinie;
    }

    /**
     * Setter fuer die Kreditlinie.
     * 
     * @param kreditlinie
     *            Kreditlinie
     */
    public void setKreditlinie( BigDecimal kreditlinie )
    {
        this.kreditlinie = kreditlinie;
    }

    /**
     * Getter fuer den Zeitpunkt der letzten Aktualisierung.
     * 
     * @return Zeitpunkt der letzten Aktualisierung als Date
     */
    public Date getZeitpunktAktualisierung()
    {
        return zeitpunktAktualisierung;
    }

    /**
     * Setter fuer den Zeitpunkt der letzten Aktualisierung.
     * 
     * @param zeitpunktAktualisierung
     *            letzer Aktualisierungszeitpunkt
     */
    public void setZeitpunktAktualisierung( Date zeitpunktAktualisierung )
    {
        this.zeitpunktAktualisierung = zeitpunktAktualisierung;
    }

}

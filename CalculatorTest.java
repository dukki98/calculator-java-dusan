import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Jedinični test za metodu Calculate u klasi Calculator.
 * Pošto je Calculate private, testira se posredno kroz javnu metodu Run().
 * Svaki test proverava jedan aspekt ponašanja kalkulatora.
 */
public class CalculatorTest {

    // --- Osnove operacije ---

    @Test
    public void testSabiranje() {
        assertEquals("9.0", Calculator.Run("4+5"));
    }

    @Test
    public void testOduzimanje() {
        assertEquals("7.0", Calculator.Run("10-3"));
    }

    @Test
    public void testMnozenje() {
        assertEquals("42.0", Calculator.Run("6*7"));
    }

    @Test
    public void testDeljenje() {
        assertEquals("5.0", Calculator.Run("10/2"));
    }

    // --- Prioritet operacija ---

    @Test
    public void testPrioritetMnozenjePreSabiranja() {
        // 2 + (3*4) = 14, a ne (2+3)*4 = 20
        assertEquals("14.0", Calculator.Run("2+3*4"));
    }

    @Test
    public void testKompleksanIzrazSaPrioritetom() {
        // 10 + (5*4) + 3 = 10 + 20 + 3 = 33
        assertEquals("33.0", Calculator.Run("10+5*4+3"));
    }

    @Test
    public void testDeljenjeIPa() {
        // (10/2) + (3*4) = 5 + 12 = 17
        assertEquals("17.0", Calculator.Run("10/2+3*4"));
    }

    // --- Predznak i negativni brojevi ---

    @Test
    public void testNegativniBrojNaPocetku() {
        assertEquals("-5.0", Calculator.Run("-5+0"));
    }

    @Test
    public void testNegativniRezultat() {
        assertEquals("-2.0", Calculator.Run("-5+3"));
    }

    // --- Greške i nevažeći ulaz ---

    @Test
    public void testNevazaciTekst() {
        assertEquals("ERROR", Calculator.Run("abc"));
    }

    @Test
    public void testIzrazKojiZavrsvaSaOperatorom() {
        assertEquals("ERROR", Calculator.Run("5+"));
    }

    // --- Poznate greške (očekuje se da testovi PADNU dok se greške ne isprave) ---

    @Test
    public void testDeljenjeNulom_trebaVratitiGresku() {
        // BUG: trenutno vraća "Infinity" umesto "ERROR"
        assertEquals("ERROR", Calculator.Run("5/0"));
    }

    @Test
    public void testPrazanString_trebaVratitiGresku() {
        // BUG: trenutno baca StringIndexOutOfBoundsException
        assertEquals("ERROR", Calculator.Run(""));
    }

    @Test
    public void testNegativniOperandUSrediIzraza_trebaRaditi() {
        // BUG: trenutno vraća "ERROR" jer split razdvaja "-" od broja
        assertEquals("-15.0", Calculator.Run("5*-3"));
    }

    @Test
    public void testRazmaciUIsrazu_trebaRaditi() {
        // BUG: trenutno vraća "ERROR" jer razmaci nisu tretirani
        assertEquals("8.0", Calculator.Run("5 + 3"));
    }
}

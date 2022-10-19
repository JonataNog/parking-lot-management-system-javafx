package testes;

import modelo.entidades.Veiculo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VeiculoTest {

    @Test
    void testEquals() {
        Veiculo v1 = new Veiculo();
        Veiculo v2 = new Veiculo();
        String foo = "foo";
        v1.setPlaca("15");
        v2.setPlaca("15");
        assertTrue(v1.equals(v2));
        v2.setPlaca("0");
        assertFalse(v1.equals(v2));
        assertFalse(v1.equals(foo));
    }
}
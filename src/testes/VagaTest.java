package testes;

import modelo.entidades.Vaga;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VagaTest {

    @Test
    void testEquals() {
        Vaga vaga = new Vaga(1);
        Vaga vaga2 = new Vaga(1);
        Vaga vaga3 = new Vaga(2);
        String foo = "foo";
        assertTrue(vaga.equals(vaga2));
        assertFalse(vaga.equals(vaga3));
        assertFalse(vaga.equals(null));
        assertFalse(vaga.equals(foo));
    }
}
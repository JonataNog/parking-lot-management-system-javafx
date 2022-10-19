package testes;

import modelo.entidades.Cliente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void testEquals() {
        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();
        String foo = "foo";
        cliente2.setCpf("15");
        cliente1.setCpf("15");
        assertTrue(cliente1.equals(cliente2));
        cliente2.setCpf("0");
        assertFalse(cliente1.equals(cliente2));
        assertFalse(cliente1.equals(foo));

    }
}
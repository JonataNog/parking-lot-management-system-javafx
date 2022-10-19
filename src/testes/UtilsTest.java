package testes;

import gui.util.Utils;
import modelo.entidades.Vaga;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void tryParseToInt() {
        Assertions.assertEquals(12, Utils.tryParseToInt("12"));
        assertNull(Utils.tryParseToInt("12foo"));
    }
}
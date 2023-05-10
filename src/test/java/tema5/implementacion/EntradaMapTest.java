package tema5.implementacion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntradaMapTest {
    @Test
    void equals(){
        EntradaMap<String, Integer> a = new EntradaMap<>("A", 3);
        EntradaMap<String, Integer> b = new EntradaMap<>("A", null);
        assertTrue(a.equals(b));
    }
}
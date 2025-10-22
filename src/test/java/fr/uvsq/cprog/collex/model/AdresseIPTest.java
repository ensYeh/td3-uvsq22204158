package fr.uvsq.cprog.collex.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class AdresseIPTest {
    @Test
    public void testValidIp() {
        AdresseIP ip = new AdresseIP("192.168.0.1");
        assertEquals("192.168.0.1", ip.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidIpTooHigh() {
        new AdresseIP("999.168.0.1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidIpFormat() {
        new AdresseIP("192.168.0");
    }

    @Test
    public void testEquality() {
        AdresseIP ip1 = new AdresseIP("192.168.0.1");
        AdresseIP ip2 = new AdresseIP("192.168.0.1");
        assertEquals(ip1, ip2);
    }
}

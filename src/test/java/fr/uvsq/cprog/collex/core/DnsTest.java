package fr.uvsq.cprog.collex.core;

import fr.uvsq.cprog.collex.model.AdresseIP;
import fr.uvsq.cprog.collex.model.NomMachine;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

public class DnsTest {
    @Test
    public void testAddAndGet() throws IOException {
        Path tmp = Files.createTempFile("dns", ".txt");
        Dns dns = new Dns(tmp);

        dns.addItem(new AdresseIP("192.168.0.10"), new NomMachine("test.local"));
        assertTrue(dns.getItem(new NomMachine("test.local")).isPresent());
        assertTrue(dns.getItem(new AdresseIP("192.168.0.10")).isPresent());
    }
}

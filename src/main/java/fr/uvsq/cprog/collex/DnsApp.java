package fr.uvsq.cprog.collex;

import fr.uvsq.cprog.collex.core.Dns;
import fr.uvsq.cprog.collex.ui.Commande;
import fr.uvsq.cprog.collex.ui.DnsTUI;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;
import java.util.Scanner;

/**
 * Application principale du simulateur DNS.
 */
public class DnsApp {

    private Dns dns;
    private DnsTUI tui;

    /** Initialise le DNS à partir du fichier de propriétés. */
    private void init() throws IOException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);
        }

        String fichier = props.getProperty("dns.data.file");
        if (fichier == null) {
            throw new IllegalArgumentException("Propriété 'dns.data.file' manquante dans config.properties");
        }

        dns = new Dns(Path.of(fichier));
        tui = new DnsTUI(dns);
    }

    /** Boucle principale. */
    public void run() throws IOException {
        init();
        System.out.println("=== Simulateur DNS ===");
        System.out.println("Tapez 'quit' pour quitter.");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            Commande cmd = tui.nextCommande(input);
            String result = cmd.execute();
            tui.affiche(result);

            if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("exit")) {
                break;
            }
        }

        System.out.println("Au revoir 👋");
    }

    public static void main(String[] args) {
        try {
            new DnsApp().run();
        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }
}

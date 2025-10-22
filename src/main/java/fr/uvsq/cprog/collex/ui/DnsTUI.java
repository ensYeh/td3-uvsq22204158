package fr.uvsq.cprog.collex.ui;

import fr.uvsq.cprog.collex.command.*;
import fr.uvsq.cprog.collex.core.Dns;

import java.util.Scanner;

/**
 * Interface en ligne de commande pour le DNS.
 */
public class DnsTUI {
    private final Dns dns;
    private final Scanner scanner = new Scanner(System.in);

    public DnsTUI(Dns dns) {
        this.dns = dns;
    }

    /**
     * Analyse la saisie utilisateur et retourne la commande correspondante.
     */
    public Commande nextCommande(String input) {
        input = input.trim();

        if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("exit")) {
            return new CmdQuit();
        }

        if (input.startsWith("ls ")) {
            String[] parts = input.split("\\s+");
            boolean sortByIp = false;
            String domaine;

            if (parts[1].equals("-a")) {
                sortByIp = true;
                domaine = parts[2];
            } else {
                domaine = parts[1];
            }

            return new CmdListDomain(dns, domaine, sortByIp);
        }

        if (input.startsWith("add ")) {
            String[] parts = input.split("\\s+");
            if (parts.length != 3) {
                return () -> "ERREUR : Format attendu -> add adresse.IP nom.qualifie";
            }
            return new CmdAdd(dns, parts[1], parts[2]);
        }

        // Si c’est un nom de machine (contient des lettres)
        if (input.matches(".*[a-zA-Z].*")) {
            return new CmdLookupByName(dns, input);
        }

        // Si c’est une adresse IP
        if (input.matches("(\\d{1,3}\\.){3}\\d{1,3}")) {
            return new CmdLookupByIp(dns, input);
        }

        return () -> "Commande inconnue.";
    }

    public void affiche(String message) {
        System.out.println(message);
    }
}

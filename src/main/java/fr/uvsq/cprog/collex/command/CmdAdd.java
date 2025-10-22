package fr.uvsq.cprog.collex.command;

import fr.uvsq.cprog.collex.core.Dns;
import fr.uvsq.cprog.collex.model.AdresseIP;
import fr.uvsq.cprog.collex.model.NomMachine;
import fr.uvsq.cprog.collex.ui.Commande;

import java.io.IOException;

public class CmdAdd implements Commande {
    private final Dns dns;
    private final String ip;
    private final String nom;

    public CmdAdd(Dns dns, String ip, String nom) {
        this.dns = dns;
        this.ip = ip;
        this.nom = nom;
    }

    @Override
    public String execute() {
        try {
            dns.addItem(new AdresseIP(ip), new NomMachine(nom));
            return "Ajout effectué.";
        } catch (IllegalArgumentException e) {
            return "ERREUR : " + e.getMessage();
        } catch (IOException e) {
            return "ERREUR : Impossible d’écrire dans le fichier.";
        }
    }
}

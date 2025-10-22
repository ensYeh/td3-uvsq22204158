package fr.uvsq.cprog.collex.command;

import fr.uvsq.cprog.collex.core.Dns;
import fr.uvsq.cprog.collex.model.NomMachine;
import fr.uvsq.cprog.collex.ui.Commande;

public class CmdLookupByName implements Commande {
    private final Dns dns;
    private final String nom;

    public CmdLookupByName(Dns dns, String nom) {
        this.dns = dns;
        this.nom = nom;
    }

    @Override
    public String execute() {
        return dns.getItem(new NomMachine(nom))
                .map(it -> it.getAdresse().toString())
                .orElse("ERREUR : Nom inconnu !");
    }
}

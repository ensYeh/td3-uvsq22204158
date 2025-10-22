package fr.uvsq.cprog.collex.command;

import fr.uvsq.cprog.collex.core.Dns;
import fr.uvsq.cprog.collex.model.AdresseIP;
import fr.uvsq.cprog.collex.ui.Commande;

public class CmdLookupByIp implements Commande {
    private final Dns dns;
    private final String ip;

    public CmdLookupByIp(Dns dns, String ip) {
        this.dns = dns;
        this.ip = ip;
    }

    @Override
    public String execute() {
        return dns.getItem(new AdresseIP(ip))
                .map(it -> it.getNom().toString())
                .orElse("ERREUR : Adresse IP inconnue !");
    }
}

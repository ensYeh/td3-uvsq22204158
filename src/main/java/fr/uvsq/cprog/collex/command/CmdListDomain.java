package fr.uvsq.cprog.collex.command;

import fr.uvsq.cprog.collex.core.Dns;
import fr.uvsq.cprog.collex.model.DnsItem;
import fr.uvsq.cprog.collex.ui.Commande;

import java.util.List;
import java.util.stream.Collectors;

public class CmdListDomain implements Commande {
    private final Dns dns;
    private final String domaine;
    private final boolean sortByIp;

    public CmdListDomain(Dns dns, String domaine, boolean sortByIp) {
        this.dns = dns;
        this.domaine = domaine;
        this.sortByIp = sortByIp;
    }

    @Override
    public String execute() {
        List<DnsItem> items = dns.getItems(domaine, sortByIp);
        if (items.isEmpty()) return "Aucune machine trouvée.";
        return items.stream()
                .map(it -> it.getAdresse() + " " + it.getNom())
                .collect(Collectors.joining("\n"));
    }
}

package fr.uvsq.cprog.collex.core;

import fr.uvsq.cprog.collex.model.AdresseIP;
import fr.uvsq.cprog.collex.model.DnsItem;
import fr.uvsq.cprog.collex.model.NomMachine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Gère la base de données DNS.
 */
public class Dns {
    private final Path dataFile;
    private final Map<AdresseIP, DnsItem> byIp = new HashMap<>();
    private final Map<String, DnsItem> byName = new HashMap<>();

    /**
     * Construit un Dns en chargeant les données depuis un fichier texte.
     * Chaque ligne du fichier doit être de la forme :
     * nom.qualifie adresse.IP
     *
     * @param dataFile chemin du fichier
     * @throws IOException si le fichier ne peut pas être lu
     */
    public Dns(Path dataFile) throws IOException {
        this.dataFile = dataFile;
        load();
    }

    /** Charge les données du fichier en mémoire. */
    private void load() throws IOException {
        if (Files.notExists(dataFile)) {
            Files.createFile(dataFile);
        }

        List<String> lines = Files.readAllLines(dataFile);
        for (String line : lines) {
            String trimmed = line.trim();
            if (trimmed.isEmpty()) continue;

            String[] parts = trimmed.split("\\s+");
            if (parts.length != 2) continue; // ligne mal formée

            NomMachine nom = new NomMachine(parts[0]);
            AdresseIP ip = new AdresseIP(parts[1]);
            DnsItem item = new DnsItem(ip, nom);

            byIp.put(ip, item);
            byName.put(nom.getFullName(), item);
        }
    }

    /** Recherche un item par adresse IP. */
    public Optional<DnsItem> getItem(AdresseIP ip) {
        return Optional.ofNullable(byIp.get(ip));
    }

    /** Recherche un item par nom de machine. */
    public Optional<DnsItem> getItem(NomMachine nom) {
        return Optional.ofNullable(byName.get(nom.getFullName()));
    }

    /**
     * Liste les machines d’un domaine.
     * @param domaine le nom du domaine (ex: "uvsq.fr")
     * @param sortByIp true = tri par IP, false = tri par nom
     */
    public List<DnsItem> getItems(String domaine, boolean sortByIp) {
        List<DnsItem> list = byName.values().stream()
                .filter(it -> it.getNom().getDomain().equals(domaine))
                .collect(Collectors.toList());

        if (sortByIp) {
            list.sort(Comparator.comparing(DnsItem::getAdresse));
        } else {
            list.sort(Comparator.comparing(it -> it.getNom().getMachineName()));
        }

        return list;
    }

    /**
     * Ajoute un nouvel item dans la base.
     * @throws IllegalArgumentException si l’adresse ou le nom existent déjà
     */
    public void addItem(AdresseIP ip, NomMachine nom) throws IOException {
        if (byIp.containsKey(ip)) {
            throw new IllegalArgumentException("L’adresse existe déjà !");
        }
        if (byName.containsKey(nom.getFullName())) {
            throw new IllegalArgumentException("Le nom de machine existe déjà !");
        }

        DnsItem item = new DnsItem(ip, nom);
        byIp.put(ip, item);
        byName.put(nom.getFullName(), item);
        persist();
    }

    /** Sauvegarde la base dans le fichier. */
    private void persist() throws IOException {
        List<String> lines = byName.values().stream()
                .sorted(Comparator.comparing(it -> it.getNom().getFullName()))
                .map(it -> it.getNom().getFullName() + " " + it.getAdresse())
                .collect(Collectors.toList());
        Files.write(dataFile, lines);
    }
}

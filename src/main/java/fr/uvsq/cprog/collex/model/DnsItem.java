package fr.uvsq.cprog.collex.model;

import java.util.Objects;

/**
 * Association entre un nom de machine et une adresse IP.
 */
public class DnsItem {
    private final AdresseIP adresse;
    private final NomMachine nom;

    public DnsItem(AdresseIP adresse, NomMachine nom) {
        this.adresse = adresse;
        this.nom = nom;
    }

    public AdresseIP getAdresse() {
        return adresse;
    }

    public NomMachine getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return adresse + " " + nom;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DnsItem)) return false;
        DnsItem other = (DnsItem) o;
        return adresse.equals(other.adresse) && nom.equals(other.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adresse, nom);
    }
}

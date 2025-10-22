package fr.uvsq.cprog.collex.model;

import java.util.Objects;

/**
 * Représente une adresse IPv4 simple.
 */
public class AdresseIP implements Comparable<AdresseIP> {
    private final int[] octets = new int[4];

    public AdresseIP(String ip) {
        if (ip == null) {
            throw new IllegalArgumentException("IP null");
        }
        String[] parts = ip.trim().split("\\.");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Format IP invalide: " + ip);
        }
        for (int i = 0; i < 4; i++) {
            int v;
            try {
                v = Integer.parseInt(parts[i]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Octet non numérique: " + parts[i]);
            }
            if (v < 0 || v > 255) {
                throw new IllegalArgumentException("Octet hors borne: " + parts[i]);
            }
            octets[i] = v;
        }
    }

    @Override
    public String toString() {
        return octets[0] + "." + octets[1] + "." + octets[2] + "." + octets[3];
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AdresseIP)) {
            return false;
        }
        AdresseIP other = (AdresseIP) o;
        for (int i = 0; i < 4; i++) {
            if (octets[i] != other.octets[i]) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(octets[0], octets[1], octets[2], octets[3]);
    }

    @Override
    public int compareTo(AdresseIP o) {
        for (int i = 0; i < 4; i++) {
            int cmp = Integer.compare(this.octets[i], o.octets[i]);
            if (cmp != 0) return cmp;
        }
        return 0;
    }
}

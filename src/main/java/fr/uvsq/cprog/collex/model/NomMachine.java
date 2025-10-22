package fr.uvsq.cprog.collex.model;

import java.util.Objects;

/**
 * Représente un nom qualifié de machine : nom.domaine.
 */
public class NomMachine {
    private final String fullName;
    private final String machineName;
    private final String domain;

    public NomMachine(String fullName) {
        if (fullName == null || !fullName.contains(".")) {
            throw new IllegalArgumentException("Nom complet invalide: " + fullName);
        }
        this.fullName = fullName.trim();
        int idx = this.fullName.indexOf('.');
        this.machineName = this.fullName.substring(0, idx);
        this.domain = this.fullName.substring(idx + 1);
    }

    public String getFullName() {
        return fullName;
    }

    public String getMachineName() {
        return machineName;
    }

    public String getDomain() {
        return domain;
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof NomMachine)) return false;
        return fullName.equals(((NomMachine) o).fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName);
    }
}

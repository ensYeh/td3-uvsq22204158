package fr.uvsq.cprog.collex.command;

import fr.uvsq.cprog.collex.ui.Commande;

public class CmdQuit implements Commande {
    @Override
    public String execute() {
        return "Fermeture du DNS.";
    }
}

package sportsclub;

public abstract class AbstractMember {
    /**
     * berechnet die gesamten Einnahmen, die der Verein durch dieses
     * Mitglied pro Jahr erzielt. */
    abstract double getIncome();

    /**
     * berechnet die gesamten Ausgaben, die dieses Mitglied pro Jahr
     * verursacht.
     */
    abstract double getCosts();

    /**
     * berechnet den finanziellen Überschuss, den dieses Mitglied dem
     * Verein pro Jahr bringt.
     * */
    abstract double getSurplus();

    /**
     * gibt ordentlich formatiert und strukturiert (mit
     * Einrückungen im Fall einer Sektion) den Namen sowie Einnahmen, Ausgaben und
     * Überschuss des Mitglieds (der Mitglieder im Fall einer Sektion) zurück. Wenn ascending
     * true ist, soll die Ausgabe aufsteigend sortiert, ansonsten absteigend sortiert erfolgen. Die
     * */
    abstract String toString(boolean ascending);

    /** Default-Methode String toString() soll eine Ausgabe in aufsteigender Reihenfolge erzeugen.
     * */
    //TODO check how to implement this

}

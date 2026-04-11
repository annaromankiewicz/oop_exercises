package sportsclub;

public class ChairMember extends AbstractMember implements Comparable<ChairMember>{

    /** Vorstandsmitglieder haben einen
    ganzzahligen Kompetenzwert im Bereich von 0 und 10. Ein Vorstandsmitglied erzeugt durch
    das Lukrieren von Sponsorengeldern und Förderungen Jahreseinnahmen von Kompetenz *
            100 € und verursacht Ausgaben, indem es 20% Provision für lukrierte Einnahmen erhält.
     */

    protected int competenceValue;

    public ChairMember(int competenceValue) {
        if (competenceValue >= 0 && competenceValue <= 10) {
            this.competenceValue = competenceValue;
        }
    }

    @Override
    double getIncome() {
        return competenceValue *100;
    }

    @Override
    double getCosts() {
        return getIncome()*0.2;
    }

    @Override
    double getSurplus() {
        return getIncome()-getCosts();
    }

    @Override
    String toString(boolean ascending) {
        return "";
    }

    @Override
    public int compareTo(ChairMember o) {
        return this.competenceValue-o.competenceValue;
    }
}

package sportsclub;

public class ActiveMember extends AbstractMember implements Comparable<ActiveMember>{

//    Aktive Mitglieder (Klasse ActiveMember): Diese Mitglieder besitzen einen ganzzahligen
//    Aktivitätsgrad im Bereich von 0 bis 10, und gliedern sich in:
//    o Spitzensportler (Klasse TopAthlete): Monatlicher Beitrag: €10,-, Ausgaben in € pro
//    Monat: Aktivitätsgrad * 5
//    o Amateure (Klasse AmateurAthlete): Monatlicher Beitrag: €25,- Ausgaben in € pro
//    Monat: Aktivitätsgrad * 2,5
//    o Trainer (Klasse Trainer): Monatlicher Beitrag: €10,- Ausgaben in € pro Monat:
//    Aktivitätsgrad * 40

    protected int activityLevel;

    protected ActiveMember(int activityLevel) {
        if (activityLevel >= 0 && activityLevel <= 10) {
            this.activityLevel = activityLevel;
        }
    }

    @Override
    double getIncome() {
        return 0;
    }

    @Override
    double getCosts() {
        return 0;
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
    public int compareTo(ActiveMember o) {
        return this.activityLevel-o.activityLevel;
    }
}

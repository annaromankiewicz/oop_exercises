package sportsclub;

public class TopAthlete extends ActiveMember {
    protected TopAthlete(int activityLevel) {
        super(activityLevel);
    }

    //    Aktive Mitglieder (Klasse ActiveMember): Diese Mitglieder besitzen einen ganzzahligen
//    Aktivitätsgrad im Bereich von 0 bis 10, und gliedern sich in:
//    o Spitzensportler (Klasse TopAthlete): Monatlicher Beitrag: €10,-, Ausgaben in € pro
//    Monat: Aktivitätsgrad * 5

    @Override
    double getIncome() {
        return 10;
    }

    @Override
    double getCosts() {
        return activityLevel * 5;
    }

    @Override
    String toString(boolean ascending) {
        return "";
    }


}

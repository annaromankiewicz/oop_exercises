package sportsclub;

public class Trainer extends ActiveMember {
    protected Trainer(int activityLevel) {
        super(activityLevel);
    }

    //    Aktive Mitglieder (Klasse ActiveMember): Diese Mitglieder besitzen einen ganzzahligen
//    Aktivitätsgrad im Bereich von 0 bis 10, und gliedern sich in:

//    o Trainer (Klasse Trainer): Monatlicher Beitrag: €10,- Ausgaben in € pro Monat:
//    Aktivitätsgrad * 40

    @Override
    double getIncome() {
        return 10;
    }

    @Override
    double getCosts() {
        return activityLevel * 40;
    }


    @Override
    String toString(boolean ascending) {
        return "";
    }

}

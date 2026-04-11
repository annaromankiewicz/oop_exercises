package sportsclub;

public class SupportingMember extends AbstractMember {

//    Unterstützende Mitglieder (Klasse SupportingMember): Diese Mitglieder bezahlen einen
//    Jahresbeitrag von €100,- und verursachen bei Vereinsfesten Ausgaben von durchschnittlich
//    €15,- pro Jahr.

    @Override
    double getIncome() {
        return 100;
    }

    @Override
    double getCosts() {
        return 15;
    }

    @Override
    double getSurplus() {
        return getIncome()-getCosts();
    }

    @Override
    String toString(boolean ascending) {
        return "";
    }

    public String toString() {
        return toString(true);
    }

}

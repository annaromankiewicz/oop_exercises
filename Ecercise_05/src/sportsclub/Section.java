package sportsclub;

import binarySearchTree.BinarySearchTree;

public class Section extends AbstractMember{
    BinarySearchTree section = new BinarySearchTree<Section>();


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
        return 0;
    }

    @Override
    String toString(boolean ascending) {
        return "";
    }

    boolean addMember(AbstractMember m) {}
     boolean removeMember(AbstractMember m) {}
     boolean isMember(AbstractMember m) {}

}

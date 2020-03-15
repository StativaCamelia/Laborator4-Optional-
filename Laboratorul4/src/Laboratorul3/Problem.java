package Laboratorul3;

public class Problem<E1, E2> {
    Partition<E1, E2> listOne = new Partition<>();
    Partition<E2, E1> listTwo = new Partition<>();

    public Problem(){

    }

    public Problem(Partition<E1, E2> listOne, Partition<E2, E1> listTwo)
    {
        for(Element<E1, E2> e1: listOne.getSetOfElements())
            this.listOne.addToPartition(e1);
        for(Element<E2, E1> e2: listTwo.getSetOfElements())
            this.listTwo.addToPartition(e2);
    }

    @Override
    public String toString() {
        return "Elementele din prima partitie:" + listOne.toString() + "Elementele din a doua partitie:" + listTwo.toString();
    }

    public Partition getListOne() {
        return listOne;
    }

    public void setListOne(Partition listOne) {
        this.listOne = listOne;
    }

    public Partition getListTwo() {
        return listTwo;
    }

    public void setListTwo(Partition listTwo) {
        this.listOne = listTwo;
    }

}

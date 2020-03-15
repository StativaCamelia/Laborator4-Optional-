package Laboratorul3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class Partition<E1, E2>{
    private List<Element<E1, E2>> setOfElements = new ArrayList<>();

    Partition(){
    }

    Partition(List<Element<E1, E2>> setOfElements){
        this.setOfElements = setOfElements;
    }

    Partition(Element[] arrayElements){
        for(Element e: arrayElements){
            setOfElements.add(e);
        }
    }

    public void setElementsList(List<Element<E1, E2>> elementsList) {
        this.setOfElements = elementsList;
    }

    public void addToPartition(Element<E1, E2> newElement){
       setOfElements.add(newElement);
    }

    public List<Element<E1, E2>> getSetOfElements() {
        return setOfElements;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return setOfElements.toString();
    }
}

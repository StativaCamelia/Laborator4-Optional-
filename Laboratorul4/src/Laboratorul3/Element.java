package Laboratorul3;

public class Element<E1, E2>{
    private E1 problemElement;
    private Partition<E2, E1> preferences = new Partition<>();

    Element(E1 element){
        this.problemElement = element;
    }
    Element(){
    }
    public E1 getProblemElement() {
        return problemElement;
    }

    public void setProblemElement(E1 problemElement) {
        this.problemElement = problemElement;
    }

    public String toString(){
        return problemElement.toString();
    }

    public void setPreferences(Partition<E2, E1> preferences) {
        this.preferences = preferences;
    }

    public Partition<E2, E1> getPreferences() {
        return preferences;
    }

    /**
     * Adauga un element in lista de preferinte a altui element
     * @param: elementul nou care trebuie adaugat la partitia de preferinte a altului elemet
     */
    public void addPreference(Element<E2, E1> e){
        preferences.addToPartition(e);
    }

    public void printPreferences(){
        System.out.println(preferences.toString());
    }

    /**
     * Fiind dat un element de un tip se cauta indexul acestuia in lista de preferinte a celuilalt tip de element implicat in problema
     * Daca elementul nu este gasit se returneaza dimensiunea preferintelor + 1 pentru a semnaliza ca nu se aflat in aceasta lista
     * @param:  element pentru care incerc sa obtin pozitia in lista de preferinte
     * @return index-ul elementului in lista de preferinte a altui element
     */
    public int getPositionInPreferences(Element<E2, E1> elem){
        int i = 0;
        for(Element<E2, E1> elInd: this.preferences.getSetOfElements()){
            if(elInd.getProblemElement().equals(elem.getProblemElement())){
                return i;
            }
        }
        return preferences.getSetOfElements().size()+1;
    }

}

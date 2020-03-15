package Laboratorul3;

import sun.font.TrueTypeFont;

import java.util.ArrayList;
import java.util.List;

public class Hospital implements Comparable<Hospital>{
    private String name;
    private List<Resident> preferences = new ArrayList<>();
    int capacity;

    public Hospital(){

    }

    public int compareTo(Hospital h){
        return this.name.compareTo(h.name);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Hospital(String name, int capacity)
    {
        this.name = name;
        this.capacity = capacity;
    }
    public Hospital(String name){
        this.name = name;
    }
    public List<Resident> getPreferances() {
        return preferences;
    }

    public void setPreferances(List<Resident> newPreferances) {
        for(Resident newPreference: preferences)
            this.addPreference(newPreference);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Hospital))
            return false;
        Hospital other = (Hospital) obj;
        return name.equals(other.name);
    }

    public void addPreference(Resident newPreference){
        for(Resident preference: preferences)
        {
            if(newPreference.equals(preference)) {
                System.out.println("Nu se poate adauga de doua ori acelasi spital in lista de preferinte");
                return;
            }
        }
        preferences.add(newPreference);
    }

    public void addPreferences(Resident[] newPreferences) {
        for (Resident newPreference : newPreferences) {
            this.addPreference(newPreference);
        }
    }

    public void printPreferences(){
        System.out.println(this.preferences.toString());
    }
    public String toString(){
        return this.name;
    }

}

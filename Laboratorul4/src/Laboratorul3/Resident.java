package Laboratorul3;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class Resident {
    private String name;
    private List<Hospital> preferencesHospitals = new ArrayList<>();

    public Resident(){
    }

    public Resident(String name)
    {
        this.name = name;
    }

    public List<Hospital> getPreferences() {
        return preferencesHospitals;
    }

    public void setPreferences(List<Hospital> preferences) {
        for(Hospital newPreference: preferences)
            this.addPreference(newPreference);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPreference(Hospital newPreference){
        for(Hospital preference: preferencesHospitals)
        {
            if(newPreference.equals(preference)) {
                System.out.println("Nu se poate adauga de doua ori acelasi spital in lista de preferinte");
                return;
            }
        }
        preferencesHospitals.add(newPreference);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Resident))
            return false;
        Resident other = (Resident) obj;
        return name.equals(other.name);
    }

    public String toString(){
        return this.name;
    }

    public void printPreferences(){
        System.out.println(this.preferencesHospitals.toString());
    }
    public void addPreferences(Hospital[] newPreferences) {
        for (Hospital newPreference : newPreferences) {
           this.addPreference(newPreference);
        }
    }
    public boolean returnTopPreferences(Hospital h) {
        return h.equals(this.preferencesHospitals.get(0));
    }
}

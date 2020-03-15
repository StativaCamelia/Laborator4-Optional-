package Laboratorul3;

import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;

public class Matching {
    Problem<Resident, Hospital> problem = new Problem<>();
    Map<Element<Resident, Hospital>, Element<Hospital, Resident>> match = new HashMap<>();

    Matching(Problem<Resident, Hospital> newProblem) {
        this.problem = newProblem;
    }

    public Problem<Resident, Hospital> getProblem() {
        return problem;
    }

    public void setProblem(Problem<Resident, Hospital> problem) {
        this.problem = problem;
    }

    @Override
    public String toString() {
        return match.toString();
    }

    public Map<Element<Resident, Hospital>, Element<Hospital, Resident>> getMatch() {
        return match;
    }

    public void setMatch(Map<Element<Resident, Hospital>, Element<Hospital, Resident>> match) {
        this.match = match;
    }

    public void calculateMatch() {
        int hospitalCapacity;
        for (Element<Resident, Hospital> resident : problem.listOne.getSetOfElements())
            for (Element<Hospital, Resident> hospital : resident.getPreferences().getSetOfElements()) {
                hospitalCapacity = hospital.getProblemElement().getCapacity();
                if (hospitalCapacity > 0) {
                    match.put(resident, hospital);
                    hospital.getProblemElement().setCapacity(hospitalCapacity - 1);
                    break;
                }
            }
    }


    private Partition<Hospital, Resident> listOfPrefferMoreElements(Element<Resident, Hospital> res){
        Partition<Hospital, Resident> prefferedMore = new Partition<>();
        for(Element<Hospital, Resident> hos: res.getPreferences().getSetOfElements()){
            if(hos.getProblemElement().equals(match.get(res).getProblemElement())){
                break;
            }
            else{
                prefferedMore.addToPartition(hos);
            }
        }
        return prefferedMore;
    }

    private Partition<Resident, Hospital> getSelectedResidents(Element<Hospital, Resident> hospital){
        Partition<Resident, Hospital> selectedResidents = new Partition<>();
        for(Element<Resident, Hospital> res : match.keySet())
            if(match.get(res).getProblemElement().equals(hospital)){
                selectedResidents.addToPartition(res);
            }
        return selectedResidents;
    }


    private boolean prefersMoreThatSelectedOne(Element<Hospital, Resident> hospital, Element<Resident, Hospital> resident){
        for(Element<Resident, Hospital> res: getSelectedResidents(hospital).getSetOfElements()){
            if(hospital.getPositionInPreferences(resident) > hospital.getPositionInPreferences(res)){
                return false;
            }
        }
        return true;
    }


    public boolean checkStableMatch() {
        for(Element<Resident, Hospital> resident: problem.listOne.getSetOfElements()){
            for(Element<Hospital, Resident> hospital: listOfPrefferMoreElements(resident).getSetOfElements()){
                if(prefersMoreThatSelectedOne(hospital, resident)){
                    return false;
                }
            }
        }
        return true;
    }
}
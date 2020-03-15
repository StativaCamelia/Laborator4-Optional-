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

    /**
     * Avand cele doua partitii respectiv cea cu Spitale si cea cu Rezidenti incearca sa asigneze fiecarui Rezisent primul Spital din Lista de preferinte
     * Daca capacitatea acestuia este deja atinsa, incearca sa il asigneze urmatorului spital de pe lista de preferinte.
     */
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


    /**
     *
     * @param res
     * @return O partitie de rezidenti pe care spitalul le prefera mai mult decat pe rezidentul dat ca parametru
     */
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

    /**
     * Returneaza lista de rezidenti asignati unui spital in urma algoritmului de match
     * @param hospital:spitalul pentru care se doreste sa se returneze lista de rezidenti
     * @return: o partitie cu rezidentii asignati spitalului
     */
    private Partition<Resident, Hospital> getSelectedResidents(Element<Hospital, Resident> hospital){
        Partition<Resident, Hospital> selectedResidents = new Partition<>();
        for(Element<Resident, Hospital> res : match.keySet())
            if(match.get(res).getProblemElement().equals(hospital)){
                selectedResidents.addToPartition(res);
            }
        return selectedResidents;
    }

    /**
     * Returneaza false daca Spitalul dat ca parametru prefera rezidentul dat ca parametru mai mult decat unul dintre rezidentii deja selectati
     * True se va returna in cazul in care rezidentul dat ca parametru nu este mai sus in lista de preferinte decat un rezident ales deja
     * @param hospital
     * @param resident
     * @return
     */

    private boolean prefersMoreThatSelectedOne(Element<Hospital, Resident> hospital, Element<Resident, Hospital> resident){
        for(Element<Resident, Hospital> res: getSelectedResidents(hospital).getSetOfElements()){
            if(hospital.getPositionInPreferences(resident) > hospital.getPositionInPreferences(res)){
                return false;
            }
        }
        return true;
    }

    /**
     * Pentru fiecare rezident dat in problema verificam spitalele pe care le prefera mai mult decat spitalul la care a fost asignat
     * Daca unul din aceste spitale il prefera la randul sau mai mult decat pe unul din rezidenti asignati inseaman ca match-ul nu este stabil.
     * Daca nu exista nici o situatie de genul se va intoarce True(match-ul este stabil)
     * @return
     */
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
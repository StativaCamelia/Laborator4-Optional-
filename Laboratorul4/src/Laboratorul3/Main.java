package Laboratorul3;
import com.github.javafaker.Faker;
import java.util.Random;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Resident[] arrayOfResidents = IntStream.rangeClosed(0, 3).mapToObj(i -> new Resident("R" + i)).toArray(Resident[]::new);
        Hospital[] arrayOfHospitals = IntStream.rangeClosed(0, 2).mapToObj(i -> new Hospital("H" + i)).toArray(Hospital[]::new);

        for (Resident resident : arrayOfResidents) {
            System.out.print(resident + ":");
            resident.printPreferences();
        }

        for (Hospital hospital : arrayOfHospitals) {
            System.out.print(hospital + ":");
            hospital.printPreferences();
        }

        List<Resident> residentList = new ArrayList<>();
        for (Resident res : arrayOfResidents)
            residentList.add(res);
        Collections.sort(residentList, (r1, r2) -> r1.getName().compareTo(r2.getName()));

        Set<Hospital> hospitalsSet = new TreeSet<>();
        for (Hospital hos : arrayOfHospitals)
            hospitalsSet.add(hos);

        Map<Resident, List<Hospital>> resPrefMap = new HashMap<>();

        resPrefMap.put(arrayOfResidents[0], Arrays.asList(arrayOfHospitals[0], arrayOfHospitals[1], arrayOfHospitals[2]));
        arrayOfResidents[0].setPreferences(Arrays.asList(arrayOfHospitals[0], arrayOfHospitals[1], arrayOfHospitals[2]));

        resPrefMap.put(arrayOfResidents[1], Arrays.asList(arrayOfHospitals[0], arrayOfHospitals[1], arrayOfHospitals[2]));
        arrayOfResidents[1].setPreferences(Arrays.asList(arrayOfHospitals[0], arrayOfHospitals[1], arrayOfHospitals[2]));

        resPrefMap.put(arrayOfResidents[2], Arrays.asList(arrayOfHospitals[0], arrayOfHospitals[1]));
        arrayOfResidents[2].setPreferences(Arrays.asList(arrayOfHospitals[0], arrayOfHospitals[1]));

        arrayOfResidents[3].setPreferences(Arrays.asList(arrayOfHospitals[0], arrayOfHospitals[2]));
        resPrefMap.put(arrayOfResidents[3], Arrays.asList(arrayOfHospitals[0], arrayOfHospitals[2]));

        Map<Hospital, List<Resident>> hosPrefMap = new TreeMap<>();

        hosPrefMap.put(arrayOfHospitals[0], Arrays.asList(arrayOfResidents[3], arrayOfResidents[0], arrayOfResidents[1], arrayOfResidents[2]));
        hosPrefMap.put(arrayOfHospitals[1], Arrays.asList(arrayOfResidents[0], arrayOfResidents[2], arrayOfResidents[1]));
        hosPrefMap.put(arrayOfHospitals[2], Arrays.asList(arrayOfResidents[0], arrayOfResidents[1], arrayOfResidents[3]));

        System.out.println(hosPrefMap);
        System.out.println(resPrefMap);

        System.out.println("Rezidenti care prefera H0:");
        residentList.stream().filter(res -> resPrefMap.get(res).contains(arrayOfHospitals[0])).forEach(System.out::println);

        List<Hospital> target = Arrays.asList(arrayOfHospitals[0], arrayOfHospitals[2]);

        System.out.println("Rezidentii care prefera H0 si H2:");

        List<Resident> result = residentList.stream().filter(res -> resPrefMap.get(res).containsAll(target)).collect(Collectors.toList());
        System.out.println(result);

        System.out.println("Spitalele care prefera R0:");
        List<Hospital> result1 = hospitalsSet.stream().filter(hos -> hosPrefMap.get(hos).get(0).equals(arrayOfResidents[0])).collect(Collectors.toList());
        System.out.println(result1);

        arrayOfHospitals[0].setCapacity(1);
        arrayOfHospitals[1].setCapacity(2);
        arrayOfHospitals[2].setCapacity(2);

        /**
         * OPTIONAL
         */

        Partition<Resident, Hospital> partitionRes = new Partition();
        Partition<Hospital, Resident> partitionHos = new Partition();

        for (int i = 0; i < 10; i++) {
            Faker faker = new Faker();
            partitionRes.addToPartition(new Element<Resident, Hospital>(new Resident(faker.name().fullName())));
            partitionHos.addToPartition(new Element<Hospital, Resident>(new Hospital(faker.company().name(),2)));
        }


        for(Element<Resident, Hospital> resident: partitionRes.getSetOfElements()){
            for(Element<Hospital, Resident> hospital: partitionHos.getSetOfElements()) {
                Random option = new Random();
                if(option.nextBoolean() == true){
                    resident.addPreference(hospital);
                }
            }
        }

        for(Element<Hospital, Resident> hospital: partitionHos.getSetOfElements()){
            for(Element<Resident, Hospital> resident: partitionRes.getSetOfElements()) {
                Random option = new Random();
                if (option.nextBoolean() == true) {
                    hospital.addPreference(resident);
                }
            }
        }

        Problem newProblemFake = new Problem(partitionRes, partitionHos);
        Matching newMatch =  new Matching(newProblemFake);
        newMatch.calculateMatch();
        System.out.println(newMatch);
        if(newMatch.checkStableMatch())
            System.out.println("Stable Match");
        else
            System.out.println("No Stable Match");
    }
}
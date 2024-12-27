package explore.code._2024;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Day2 {
    private static Integer IsSafeWithoutDampener(ArrayList<ArrayList<String>> reports) {
        int safeReport = 0;
        for(ArrayList<String> report:reports){
            if(isSafe(report)){
                safeReport++;
            }
        }
        return safeReport;
    }

    private static Boolean isSafe(ArrayList<String> report){
        String op = "";
        int lvlDiff = Integer.parseInt(report.get(1))-Integer.parseInt(report.get(0));
        if(lvlDiff > 0){
            op = "+";
        }else if(lvlDiff < 0){
            op = "-";
        }

        for(int lvl=0;lvl<report.size()-1;lvl++){
            lvlDiff = Integer.parseInt(report.get(lvl+1))-Integer.parseInt(report.get(lvl));
            if(Math.abs(lvlDiff) > 3 || lvlDiff == 0){
                return false;
            }else{
                if((op.equals("+") && lvlDiff < 0) || (op.equals("-") && lvlDiff > 0)){
                    return false;
                }
            }
        }
        return true;
    }

    private static Integer IsSafeWithDampener(ArrayList<ArrayList<String>> reports){
        int safeReport = 0;
        for(ArrayList<String> report:reports){
            ArrayList<String> reportDup = new ArrayList<>(report);
            if(isSafe(reportDup)){
                safeReport++;
            }else {
                for(int lvl=0;lvl<report.size();lvl++) {
                    reportDup.remove(lvl);
                    if (isSafe(reportDup)) {
                        safeReport++;
                        break;
                    }
                    reportDup = new ArrayList<>(report);
                }
            }
        }
        return safeReport;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Day2_input.txt"));


        ArrayList<ArrayList<String>> reports = new ArrayList<>();

        String line;
        while((line = reader.readLine()) != null){
            reports.add(new ArrayList<String>(Arrays.asList(line.split(" "))));
        }

        System.out.println("Number of Reports Classified Safe without Dampener: " + IsSafeWithoutDampener(reports));
        System.out.println("Number of Reports Classified Safe with Dampener: " + IsSafeWithDampener(reports));
    }
}

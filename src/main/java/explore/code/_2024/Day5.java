package explore.code._2024;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day5 {
    private static Boolean isCorrectlyOrderedList(HashMap<Integer,ArrayList<Integer>> pageOrderingRule, ArrayList<Integer> arr){
        for(int i=0;i<arr.size()-1;i++){
            if (pageOrderingRule.get(arr.get(i)) == null || !pageOrderingRule.get(arr.get(i)).contains(arr.get(i+1))){
                return false;
            }
        }
        return true;
    }

    private static Integer sumMiddleElementPart1(HashMap<Integer,ArrayList<Integer>> pageOrderingRule,  ArrayList<ArrayList<Integer>> pageUpdates){
        int sum = 0;
        for(ArrayList<Integer> arr:pageUpdates){
            if(isCorrectlyOrderedList(pageOrderingRule, arr)){
                sum += arr.get(arr.size()/2);
            }
        }
        return sum;
    }

    private static Integer sumMiddleElementPart2(HashMap<Integer,ArrayList<Integer>> pageOrderingRule,  ArrayList<ArrayList<Integer>> pageUpdates){
        int sum = 0;
        for(ArrayList<Integer> arr:pageUpdates){
            if(isWronglyOrderedListCorrect(pageOrderingRule, arr)) {
                sum += arr.get(arr.size() / 2);
            }
        }
        return sum;
    }

    private static Boolean isWronglyOrderedListCorrect(HashMap<Integer,ArrayList<Integer>> pageOrderingRule, ArrayList<Integer> arr){
        int temp;
        for(int i=0;i<arr.size()-1;i++){
            if (pageOrderingRule.get(arr.get(i)) == null || !pageOrderingRule.get(arr.get(i)).contains(arr.get(i+1))){
                temp = arr.get(i+1);
                arr.set(i+1,arr.get(i));
                arr.set(i,temp);
                if(isCorrectlyOrderedList(pageOrderingRule, arr)) {
                    return true;
                }else{
                    return isWronglyOrderedListCorrect(pageOrderingRule, arr);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Day5_input.txt"));

        HashMap<Integer,ArrayList<Integer>> pageOrderingRule = new HashMap<>();
        ArrayList<ArrayList<Integer>> pageUpdates = new ArrayList<>();

        String line;
        while((line = reader.readLine()) != null) {
            if(line.contains("|")){
                int temp1 = Integer.parseInt(line.split("\\|")[0]);
                int temp2 = Integer.parseInt(line.split("\\|")[1]);

                if(pageOrderingRule.get(temp1) == null) {
                    pageOrderingRule.put(temp1, new ArrayList<>());
                    pageOrderingRule.get(temp1).add(temp2);
                }else{
                    pageOrderingRule.get(temp1).add(temp2);
                }

            }else if(line.contains(",")){
                pageUpdates.add(Arrays.stream(line.split(","))
                        .mapToInt(Integer::parseInt)
                        .boxed()
                        .collect(Collectors.toCollection(ArrayList::new))
                );
            }
        }

        System.out.println("Sum of the Middle Elements of the Correctly Ordered List: " + sumMiddleElementPart1(pageOrderingRule, pageUpdates));

        System.out.println("Sum of the Middle Elements of the corrected Wrongly Ordered List: " + sumMiddleElementPart2(pageOrderingRule, pageUpdates));

    }
}

package explore.code._2024;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;

public class Day1 {

    // Sorted the
    private static Integer SortedDiffCount(ArrayList<Integer> LeftInput, ArrayList<Integer> RightInput){
        LeftInput.sort(null);
        RightInput.sort(null);

        ArrayList<Integer> LeftRightDiff = new ArrayList<>();
        IntStream.range(0,LeftInput.size())
                .forEach(
                        i-> LeftRightDiff.add(Math.abs(LeftInput.get(i) - RightInput.get(i)))
                );
        return (LeftRightDiff.stream().flatMapToInt(IntStream::of).sum());
    }

    private static Integer SimilarityScore(ArrayList<Integer> LeftInput, ArrayList<Integer> RightInput){
        HashMap<Integer, Integer> RightInputCount = new HashMap<>();
        RightInput.forEach(num->{
            if(RightInputCount.get(num) == null){
                RightInputCount.put(num,1);
            }else{
                RightInputCount.replace(num, RightInputCount.get(num)+1);
            }
        });
        return (
                LeftInput.stream().parallel().mapToInt(num -> {
                    if (RightInputCount.get(num) == null) {
                        return 0;
                    } else {
                        return num * RightInputCount.get(num);
                    }
                }).sum()
        );
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Day1_input.txt"));

        ArrayList<Integer> LeftInput = new ArrayList<>();
        ArrayList<Integer> RightInput = new ArrayList<>();

        String line;
        while((line = reader.readLine()) != null){
            LeftInput.add(Integer.valueOf(line.split(" {3}")[0]));
            RightInput.add(Integer.valueOf(line.split(" {3}")[1]));
        }

        // Part 1
        System.out.println("Count of the difference btw sorted Left and Right: " + SortedDiffCount(LeftInput, RightInput));

        // Part 2
        System.out.println("Similarity Score: " + SimilarityScore(LeftInput, RightInput));

        reader.close();
    }
}

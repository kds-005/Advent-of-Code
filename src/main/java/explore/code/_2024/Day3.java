package explore.code._2024;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    private static long ValidInstructionSum(String corruptedMemorySegment){
        long sum = 0L;

        Matcher getValidInstructions = Pattern.compile("mul\\([0-9]+,[0-9]+\\)").matcher(corruptedMemorySegment);
        while (getValidInstructions.find()) {
            String validInstructions = getValidInstructions.group(0);
            validInstructions = validInstructions.substring(4, validInstructions.length() - 1);
            sum += Long.parseLong(validInstructions.split(",")[0]) * Long.parseLong(validInstructions.split(",")[1]);

        }
        return sum;
    }

    private static long getNoDont(String corruptedMemorySegment){
        StringBuilder onlyDo = new StringBuilder();
        onlyDo.append(corruptedMemorySegment.split("don't\\(\\)")[0]);
        for(String ins:corruptedMemorySegment.split("don't\\(\\)")){
            if(ins.contains("do()")){
                onlyDo.append(ins.split("do\\(\\)",2)[1]);
            }
        }
        return ValidInstructionSum(onlyDo.toString());
    }

    public static void main(String[] args) throws IOException {
        String corruptedMemorySegment = Files.readString(Path.of("src/main/resources/Day3_input.txt"));

        System.out.println("The results of the Computer Program without Instructions: " + ValidInstructionSum(corruptedMemorySegment));

        System.out.println("The results of the Computer Program with Instructions: " + getNoDont(corruptedMemorySegment));

    }
}

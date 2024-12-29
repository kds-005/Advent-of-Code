package explore.code._2024;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day4 {
    private static int countXMAS(ArrayList<ArrayList<String>> input){
        int count = 0;

        for(int i=0;i<input.size();i++){
            for(int j=0;j<input.get(i).size();j++){
                // For Top Down
                try {
                    String topDown = input.get(i).get(j) + input.get(i+1).get(j) +
                            input.get(i+2).get(j) + input.get(i+3).get(j);
                    if(topDown.equals("XMAS")){
                        count++;
                    }
                } catch (IndexOutOfBoundsException e) {

                }

                // For Bottom Up
                try {
                    String bottomUp = input.get(i).get(j) + input.get(i-1).get(j) +
                            input.get(i-2).get(j) + input.get(i-3).get(j);
                    if(bottomUp.equals("XMAS")){
                        count++;
                    }
                }catch (IndexOutOfBoundsException e){

                }

                // For Left to Right
                try {
                    String leftRight = input.get(i).get(j) + input.get(i).get(j+1) +
                            input.get(i).get(j+2) + input.get(i).get(j+3);
                    if(leftRight.equals("XMAS")){
                        count++;
                    }
                }catch (IndexOutOfBoundsException e){

                }

                // For Right to Left
                try {
                    String rightLeft = input.get(i).get(j) + input.get(i).get(j-1) +
                            input.get(i).get(j-2) + input.get(i).get(j-3);
                    if(rightLeft.equals("XMAS")){
                        count++;
                    }
                } catch (IndexOutOfBoundsException e) {

                }

                // For Top Down Right Diagonal
                try {
                    String topDownRight = input.get(i).get(j) + input.get(i+1).get(j+1) +
                            input.get(i+2).get(j+2) + input.get(i+3).get(j+3);
                    if(topDownRight.equals("XMAS")){
                        count++;
                    }
                } catch (IndexOutOfBoundsException e) {

                }

                // For Top Down Left Diagonal
                try {
                    String topDownleft = input.get(i).get(j) + input.get(i+1).get(j-1) +
                            input.get(i+2).get(j-2) + input.get(i+3).get(j-3);
                    if(topDownleft.equals("XMAS")){
                        count++;
                    }
                } catch (IndexOutOfBoundsException e) {

                }

                // For Bottom Up Right Diagonal
                try {
                    String bottomUpRight = input.get(i).get(j) + input.get(i-1).get(j+1) +
                            input.get(i-2).get(j+2) + input.get(i-3).get(j+3);
                    if(bottomUpRight.equals("XMAS")){
                        count++;
                    }
                } catch (IndexOutOfBoundsException e) {

                }

                // For Bottom Up Right Diagonal
                try {
                    String bottomUpLeft = input.get(i).get(j) + input.get(i-1).get(j-1) +
                            input.get(i-2).get(j-2) + input.get(i-3).get(j-3);
                    if(bottomUpLeft.equals("XMAS")){
                        count++;
                    }
                } catch (IndexOutOfBoundsException e) {

                }
            }
        }
        return count;
    }

    private static int countMAS_likeX(ArrayList<ArrayList<String>> input){
        int countX_MAS = 0;

        for(int i=0;i<input.size();i++) {
            for (int j = 0; j < input.get(i).size(); j++) {
                try{
                    if((input.get(i).get(j).equals("M") || input.get(i).get(j).equals("S")) &&
                            (input.get(i).get(j+2).equals("M") || input.get(i).get(j+2).equals("S"))){

                        String topDownRightx = input.get(i).get(j) + input.get(i+1).get(j+1) + input.get(i+2).get(j+2);
                        String topDownLeftx = input.get(i).get(j+2) + input.get(i+1).get(j+1) + input.get(i+2).get(j);

                        if((topDownRightx.equals("MAS") || topDownRightx.equals("SAM")) &&
                                (topDownLeftx.equals("MAS") || topDownLeftx.equals("SAM"))){
                            countX_MAS++;
                            input.get(i+1).set(j+1,"X");
                        }
                    }
                } catch (IndexOutOfBoundsException e) {

                }
            }
        }
        return countX_MAS;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Day4_input.txt"));

        ArrayList<ArrayList<String>> input = new ArrayList<>();

        String line;
        while((line = reader.readLine()) != null){
            input.add(new ArrayList<>(Arrays.asList(line.split(""))));
        }

        System.out.println("XMAS occurs " + countXMAS(input) + " times");

        System.out.println("MAS occurs like X " + countMAS_likeX(input) + " times");
    }
}

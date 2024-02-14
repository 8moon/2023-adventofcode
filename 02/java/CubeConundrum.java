import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CubeConundrum {

    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part1(){
        List<String> input = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get("02\\java\\input1.txt"))) {
            input = br.lines().collect(Collectors.toList());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        int total = 0;
        for (String line : input) {
            List<String> valdiationList = new ArrayList<>();
            String gameId = line.substring(5, line.indexOf(":")).trim();
            String reveals = line.substring(line.indexOf(":") + 1).trim();
            String[] reveal = reveals.split(";");
            for (String revealString : reveal) {
                String[] colours = revealString.split(",");
                for (String colour : colours) {  
                    valdiationList.add("valid");
                    if (colour.contains("red")) {                        
                        String redAmount = colour.replace("red", "").trim();
                        if (Integer.parseInt(redAmount) > 12) {
                            valdiationList.add("invalid");
                        }
                    } else if (colour.contains("green")) {
                        String greenAmount = colour.replace("green", "").trim();
                        if (Integer.parseInt(greenAmount) > 13) {
                            valdiationList.add("invalid");
                        }
                    } else if (colour.contains("blue")) {
                        String blueAmount = colour.replace("blue", "").trim();
                        if (Integer.parseInt(blueAmount) > 14) {
                            valdiationList.add("invalid");
                        }
                    }
                }
            }
            if (valdiationList.contains("invalid") == true) {} else {
                total += Integer.parseInt(gameId);
            }
        }
        System.out.println(total);
    }

    private static void part2(){
        List<String> input = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get("02\\java\\example2.txt"))) {
            input = br.lines().collect(Collectors.toList());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        int total = 0;
        for (String line : input) {
            List<String> valdiationList = new ArrayList<>();
            String gameId = line.substring(5, line.indexOf(":")).trim();
            String reveals = line.substring(line.indexOf(":") + 1).trim();
            String[] reveal = reveals.split(";");
            for (String revealString : reveal) {
                String[] colours = revealString.split(",");
                for (String colour : colours) {
                    // Instead of valid and invalid game check find the highest amount of each colour
/*
Create a Map with colour:amount
starting amount = 0
check amount in map with amount from current ; substring
if substring amount > map amount -> write substring amount into map 
*/ 
                    valdiationList.add("valid");
                    if (colour.contains("red")) {                        
                        String redAmount = colour.replace("red", "").trim();
                        if (Integer.parseInt(redAmount) > 12) {
                            valdiationList.add("invalid");
                        }
                    } else if (colour.contains("green")) {
                        String greenAmount = colour.replace("green", "").trim();
                        if (Integer.parseInt(greenAmount) > 13) {
                            valdiationList.add("invalid");
                        }
                    } else if (colour.contains("blue")) {
                        String blueAmount = colour.replace("blue", "").trim();
                        if (Integer.parseInt(blueAmount) > 14) {
                            valdiationList.add("invalid");
                        }
                    }
                }
            }
            // Multiply the cube amounts with each other <-> game id
/*
 * read colour amounts out of map and multiply them
 */
            // sum the multiplication results instead of game id
            if (valdiationList.contains("invalid") == true) {} else {
                total += Integer.parseInt(gameId);
            }
        }
        System.out.println(total);
    }

}
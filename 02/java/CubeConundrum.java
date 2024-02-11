import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CubeConundrum {

    public static void main(String[] args) {
        part1();
    }

    private static void part1(){
        /*
         * 3 colours of cubes: red, green, blue
         * Game: x amount of cubes goes into bag, x reveals of x cubes per game.
         * 12 red cubes, 13 green cubes, 14 blue cubes
         * Question: Which games are possible? -> ID sum of possible games
         */
        // Map for cubes
        Map<String, Integer> cubesMap = Map.of(
            "red", 0,
            "green", 0,
            "blue", 0
        );
        
        // Read input file with lines to list
        List<String> input = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get("example1.txt"))) {
            input = br.lines().collect(Collectors.toList());
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        // Iterate through each line
        int total = input.stream()
            .map(m -> {
                cubesMap.forEach((key, value) -> {
                    cubesMap.put(key, 0);
                });
                ArrayList<Integer> validGameIdList = new ArrayList<>();
                // Create substring for each reveal
                String reveals = m.substring(m.indexOf(":")-1).trim();
                String[] reveal = reveals.split(";");
                Arrays.stream(reveal).forEach(revealString -> {
                    // 3 blue, 4 red, 2 green -> get each colour value, if colour not present value = 0
                    String[] colours = revealString.split(",");
                    Arrays.stream(colours).forEach(colourString -> {
                        //int redTotalRevealed, blueTotalRevealed, greenTotalRevealed = 0;
                        if (colourString.contains("red")) {
                            String redAmount = colourString.replace("red", "").trim();
                            //redTotalRevealed += Integer.parseInt(redAmount);
                            cubesMap.put("red", cubesMap.get("red") + Integer.parseInt(redAmount));
                        } else if (colourString.contains("blue")) {
                            String blueAmount = colourString.replace("blue", "").trim();
                            //blueTotalRevealed += Integer.parseInt(blueAmount);
                            cubesMap.put("blue", cubesMap.get("blue") + Integer.parseInt(blueAmount));
                        } else if (colourString.contains("green")) {
                            String greenAmount = colourString.replace("green", "").trim();
                            //greenTotalRevealed += Integer.parseInt(greenAmount);
                            cubesMap.put("green", cubesMap.get("green") + Integer.parseInt(greenAmount));
                        }
                    });
                });
                // Check if max amount of shown cubes is bigger than existing amount of cubes
                if (cubesMap.get("red") <= 12 && cubesMap.get("blue") <= 14 && cubesMap.get("green") <= 13) {
                    // Add game id to map as int
                    String gameId = m.substring(4, m.indexOf(":")).trim();
                    validGameIdList.add(Integer.parseInt(gameId));
                }
                return Integer.parseInt(m.substring(4, m.indexOf(":")).trim());
            })
            .mapToInt(m -> m)
            .sum();
        System.out.println("total sum" + total);
    }

}
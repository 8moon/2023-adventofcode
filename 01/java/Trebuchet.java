import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Trebuchet {
    public static void main(String[] args){
        Map<String, String> numbers = Map.of(
                "1", "one",
                "2", "two",
                "3", "three",
                "4", "four",
                "5", "five",
                "6", "six",
                "7", "seven",
                "8", "eight",
                "9", "nine"
        );

        List<String> list = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get("input2.txt"))) {
            list = br.lines().collect(Collectors.toList());
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        int totalSum = list.stream()
            .map(n -> {
                TreeMap<Integer, String> listFirstLast = new TreeMap<>();
                numbers.forEach((digit, word)->{
                    listFirstLast.put(n.indexOf(word), digit);
                    listFirstLast.put(n.lastIndexOf(word), digit);
                    listFirstLast.put(n.indexOf(digit), digit);
                    listFirstLast.put(n.lastIndexOf(digit), digit);
                });
                listFirstLast.remove(-1);
                return Integer.parseInt(listFirstLast.firstEntry().getValue() + listFirstLast.lastEntry().getValue());
            })
            .mapToInt(n -> n)
            .sum();
        System.out.println("total sum: " + totalSum);
    }
}
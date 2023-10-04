import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1){
            System.out.println("zadejte budto cisla oddelena mezerou, nebo cestu k souboru odkud cisla nacist, nebo muzete pridat soubor kam vysledek ulozit ");
        }

    }

    public static List<Integer> processNumbersFromCLI(String[] ar){
        boolean number = false;
        for (String x : ar){

        }
    }

    public static void writeNumbersToFile(List<Integer> numbers, String relativepath){
        Path filePath = Paths.get(relativepath);
        List<String> content = new ArrayList<>();
        for (int x : numbers){
            content.add(String.valueOf(x));
        }
        try {
            Files.write(filePath,content); //re-write file content, was unclear if we wanna keep origin in case of conflict
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static List<Integer> readNumbersFromFile(String relativepath) {
        Path filePath = Paths.get(relativepath);
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(filePath);
        } catch (IOException e) {
            System.out.println("File does not exist");
            System.out.println(e.getMessage());
            return null;
        }
        List<Integer> numbersList = new ArrayList<>();
        for (String line : lines) {
            String[] numbers = line.split(","); // numbers must be split by comma in file, i can add some replace if necessary for other separators
            for (String numStr : numbers) {
                StringBuilder sb = new StringBuilder();
                for (char c : numStr.toCharArray()) {
                    if (Character.isDigit(c)) {
                        sb.append(c);
                    }
                }
                if (sb.length() > 0) {
                    int num = Integer.parseInt(sb.toString());
                    numbersList.add(num);
                }
            }
        }
        return numbersList;
    }
    public static List<Integer> processedList(List<Integer> ilist){
        List<Integer> nmbrsToPrintSave = new ArrayList<>();
        if (ilist.size() % 2 == 0){
            for (int x : ilist){
                if (x % 2 == 0){
                    nmbrsToPrintSave.add(x);
                }
            }
        }else {
            for (int x : ilist){
                if (x % 2 == 1){
                    nmbrsToPrintSave.add(x);
                }
            }
        }
        return nmbrsToPrintSave;
    }
}
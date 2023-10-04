import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NumberProcesser {

    public static void processArgsFromCLI(String[] ar) {
        List<Integer> numbers = new ArrayList<>();
        if (ar.length > 2) {
            for (String str : ar) {
                for (char c : str.toCharArray()) {
                    if (!Character.isDigit(c)) {
                        System.out.println("zadavejte pouze cisla nebo jen adresu zdroje, popripade muzete pridat adresu vystupu");
                        return;
                    }
                }
                numbers.add(Integer.parseInt(str));
            }
            List<Integer> processedNumbers = processList(numbers);
            System.out.println(processedNumbers);
        } else if (ar.length == 1) {
            boolean isItNumber = true;
            for (char c : ar[0].toCharArray()) {
                if (!Character.isDigit(c)) {
                    isItNumber = false;
                    break;
                }
            }
            if (isItNumber) {
                System.out.println(ar[0]);
            } else {
                List<Integer> fileInput = processList(Objects.requireNonNull(readNumbersFromFile(ar[0])));
                System.out.println(fileInput);
            }
        } else if (ar.length == 2) {
            boolean isFirstNumber = true;
            boolean isSecondNumber = true;
            for (char c : ar[0].toCharArray()) {
                if (!Character.isDigit(c)) {
                    isFirstNumber = false;
                    break;
                }
            }
            for (char c : ar[1].toCharArray()) {
                if (!Character.isDigit(c)) {
                    isSecondNumber = false;
                    break;
                }
            }
            if (isFirstNumber && isSecondNumber) {
                List<Integer> printNums = new ArrayList<>();
                printNums.add(Integer.parseInt(ar[0]));
                printNums.add(Integer.parseInt(ar[1]));
                System.out.println(processList(printNums));
                return;
            }
            if (isFirstNumber || isSecondNumber) {
                System.out.println("invalid arguments");
                return;
            }
            List<Integer> unprocessed = readNumbersFromFile(ar[0]);
            assert unprocessed != null;
            List<Integer> fileInput = processList(unprocessed);
            if (fileInput == null) {
                return;
            }
            if (unprocessed.size() % 2 == 0 || !ar[1].contains(".")) {  // in task instructions, it says if amount of read numbers is even, allways just print
                System.out.println("Neulozeno, jen vytisknuto, pocet vstupu je sudy");
                System.out.println(fileInput);
            } else {
                writeNumbersToFile(fileInput, ar[1]);
                System.out.println("Your file is at: src/resources/" + ar[1]);
            }
        }
    }

    public static void writeNumbersToFile(List<Integer> numbers, String filename) {
        Path filePath = Paths.get("src\\resources\\" + filename);
        List<String> content = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int x : numbers) {
            sb.append(x);
            sb.append(",");
        }
        content.add(sb.substring(0, sb.length() - 1));
        try {
            Files.write(filePath, content); //re-write file content, was unclear if we wanna rewrite origin in case of conflict or append
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Integer> readNumbersFromFile(String filename) {
        Path filePath = Paths.get("src\\resources\\" + filename);
        List<String> lines;
        try {
            lines = Files.readAllLines(filePath);
        } catch (Exception e) {
            System.out.println("404 File not found");
            System.out.println(e.getMessage());
            return null;
        }
        List<Integer> numbersList = new ArrayList<>();
        for (String line : lines) {
            String[] numbers = line.split(","); // numbers must be split by comma in file, i can add some replace if necessary for other separators
            for (String numStr : numbers) {
                StringBuilder sb = new StringBuilder();
                for (char c : numStr.toCharArray()) { // adding numbers only
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

    public static List<Integer> processList(List<Integer> ilist) {
        List<Integer> nmbrsToPrintSave = new ArrayList<>();
        if (ilist == null) {
            return null;
        }
        if (ilist.size() % 2 == 0) {
            for (int x : ilist) {
                if (x % 2 == 0) {
                    nmbrsToPrintSave.add(x);
                }
            }
        } else {
            for (int x : ilist) {
                if (x % 2 == 1) {
                    nmbrsToPrintSave.add(x);
                }
            }
        }
        return nmbrsToPrintSave;
    }
}
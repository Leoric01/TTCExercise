import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class NumberProcesser2 {

    public static void processArgsFromCLI(String[] args) throws Exception {
        switch (args.length) {
            case 1 -> {
                if (hasChars(args)) {
                    onlyInput(args);
                } else {
                    onlyNumbers(args);
                }
            }
            case 2 -> {
                if (hasChars(args)) {
                    inputAndOutput(args);
                } else {
                    onlyNumbers(args);
                }
            }
            default -> {
                if (hasChars(args)) {
                    System.out.println("Invalid input");
                } else {
                    onlyNumbers(args);
                }
            }
        }
    }

    public static boolean hasChars(String[] args) {
        for (String str : args) {
            for (char ch : str.toCharArray()) {
                if (Character.isLetter(ch)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void onlyNumbers(String[] args) {
        String result = "";
        for (String str : args) {
            if (args.length % 2 == 0) {
                if (Integer.parseInt(str) % 2 == 0) {
                    result += str + ",";
                }
            } else {
                if (Integer.parseInt(str) % 2 != 0) {
                    result += str + ",";
                }
            }
        }
        System.out.println(result.length() > 1 ? result.substring(0, result.length()-1) : "");
    }

    public static void onlyInput(String[] args) throws Exception {
        Path path = Paths.get("src\\resources\\" + args[0]);
        try {
            List<String> lines = Files.readAllLines(path);
            for (String str : lines) {
                onlyNumbers(str.split(","));
            }
        } catch (Exception e) {
            throw new Exception("Invalid input");
        }
    }

    public static void inputAndOutput(String[] args) throws Exception {
        Path inputPath = Paths.get("src\\resources\\" + args[0]);
        Path outputPath = Paths.get("src\\resources\\" + args[1]);
        String result = "";

        try {
            List<String> lines = Files.readAllLines(inputPath);
            for (String str : lines) {
                for (char ch : str.toCharArray()) {
                    if (Character.isDigit(ch)) {
                        if (str.trim().split(",").length % 2 == 0) {
                            if (Character.getNumericValue(ch) % 2 == 0) {
                                result += ch + ",";
                            }
                        } else {
                            if (Character.getNumericValue(ch) % 2 != 0) {
                                result += ch + ",";
                            }
                        }
                    }
                }
            }
            if (result.length() > 1){
                Files.write(outputPath, result.substring(0, result.length() - 1).getBytes());
            }
        } catch (Exception e) {
            throw new Exception("Invalid input");
        }
    }

}

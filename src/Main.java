
public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("zadejte budto cisla oddelena mezerou, nebo cestu k souboru odkud cisla nacist, nebo muzete pridat soubor kam vysledek ulozit ");
        }
        NumberProcesser.processArgsFromCLI(args);
    }
}
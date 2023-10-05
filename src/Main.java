
public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("zadejte budto cisla oddelena mezerou, nebo cestu k souboru odkud cisla nacist, nebo muzete pridat soubor kam vysledek ulozit ");
        } else {
//            NumberProcesser.processArgsFromCLI(args);
            NumberProcesser2.processArgsFromCLI(args);
        }
    }
}
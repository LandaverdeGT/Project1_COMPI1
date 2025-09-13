package Generated;
import java.io.File;

public class GeneradorScanner {
    public static void main(String [] args){
        jflex.Main.generate(new File("Project1_Compi1/src/Analyze/Scanner.jflex"));
    }
}
package Generated;

public class GeneradorParser {
    public static void main(String [] args){
        try{
            java_cup.Main.main(
                    new String[] {
                            "-destdir",
                            "Project1_Compi1/src/Analyze",
                            "-symbols",
                            "Terminal",
                            "-parser",
                            "Parser",
                            "Project1_Compi1/src/Analyze/Parser.cup"
                    }
            );
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
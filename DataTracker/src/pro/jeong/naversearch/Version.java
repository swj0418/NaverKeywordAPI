package pro.jeong.naversearch;

public class Version {
    public static String version = "0.0.1";
    public static String author = "Sangwon Jeong";
    public static String lastModified = "2018-07-15";

    public static void printVersion() {
        System.out.println("================================== Version ==================================");
        System.out.println("|\t\t\t" + version      + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+ "|");
        System.out.println("|\t\t\t" + author       + "\t\t\t\t\t\t\t\t\t\t\t\t\t"    + "|");
        System.out.println("|\t\t\t" + lastModified + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t"  + "|");
        System.out.println("=============================================================================");

    }
}

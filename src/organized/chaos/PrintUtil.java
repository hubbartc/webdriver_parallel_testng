package organized.chaos;

public class PrintUtil {

    public static void tPrint(String str) {
    	System.out.println(Thread.currentThread().getId() + ": " + str);
    }
    
}

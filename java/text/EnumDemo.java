import java.util.Scanner;

/**
 * * Enum Class test
 * 
 */
public class EnumDemo{
    public static void main(String ... args){
        System.out.println("????");
        Size2 s = Enum.valueOf(Size2.class, "LARGE");
        String str = Size2.SMALL.toString();
        Size2[] val = Size2.values();
        for(Size2 i : val) System.out.println(i);
        System.out.println(Size2.EXTRA_LARGE.ordinal());
    
    
        /**
         * TODO test Enum
         */
        Scanner in = new Scanner(System.in);
        System.out.println("ENTER(SMALL, MEDIUM, LARGE, EXTRA_LARGE):");
        String input = in.next().toUpperCase();
        Size2 si = Enum.valueOf(Size2.class, input);
        System.out.println("size = " + si);
        System.out.println("abbabbreviation = " + si.getAbbreviation());
        
    }   
}

enum Size{
    SMALL, MEDIUM, LARGE, EXTRA_LARGE;
};
enum Size2{
    SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("E");
    public String abbreviation;
    private Size2(String abbabbreviation) {
        this.abbreviation = abbabbreviation;
    } 
    public String getAbbreviation(){
        return this.abbreviation;
    }
};


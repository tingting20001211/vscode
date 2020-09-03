/**
 * vesion:1.0
 * author Mr.Yu
 */

/**
 * ! error
 * * forget
 * ? question
 * todo forget
 */
public class Demo1{
    
    public static final double pi = 3.1415926;

    public static void main(String[] args){
        double salary = 3.14159265357;
        int a = 1, b = 2;
        System.out.println(a+b);
        System.out.println("hello");
        System.out.println("Demo1"+pi);

        /**
         * TODO  ting :test hashCode() 
         */
        String s = "Ok";
        StringBuilder sb = new StringBuilder(s);
        System.out.println(s.hashCode() + "= ? =" + sb.hashCode()); // 2556= ? =1878246837
        String t = new String("Ok");
        StringBuilder tb = new StringBuilder(t);
        System.out.println(t.hashCode() + "= ? =" + tb.hashCode()); // 2556= ? =1523554304
    }
}

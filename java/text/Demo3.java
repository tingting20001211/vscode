/*************************************************************************
  > File Name   : Demo3.java
  > Author      : ting
  > Mail        : 1213728568@qq.com
  > Created Time: Sun 02 Aug 2020 02:04:47 PM CST
 ************************************************************************/

//import static java.lang.Math.*

public class Demo3{
    public static void main(String[] args){
        double x = 9.97;
        int nx =(int)Math.round(x); // MAth.round return type is long
        System.out.println(nx);

        int a = 26; // 0b11010
        System.out.println((a&(1<<3))>>3);
        System.out.println((a&(1<<3))>>>3); // >>> 运算符的作用是将高位填充0, 而不存在<<<运算符
        
        // enum error?
        // enum Size {SMALL, MEDIUM, LARGE, EXTRA_LARGE};
        // Size s = Size.MEDIUM, b = null;    
        
        //String
        String e = "";
        String greeting = "hello";

        String subs = greeting.substring(1, 3); // index = [1, 3) 
        System.out.println(subs);

        //使用+拼接字符串
        String expletive = "Expletive";
        String PG13 = "delete";
        String message = expletive + PG13;
        System.out.println(message);
        
        int age = 21;
        String outage = "age"+21;  // 字符串+非字符串 (会首先把非字符串转化为字符串)
        System.out.println(outage); // age21

        //静态join方法
        String all = String.join(", ", "string1", "string2", "string3", "...");
        System.out.println(all);
        

        String a1 = "hello";
        String a2 = "world";
        String b = a1;
        String a3 = a1.substring(0, 2) + a2;
        System.out.println("a = " + a3 + "  b = " + b );
        
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        /*****************************************************/
        //遍历字符串
        String word = "我草泥马";
        int leng1 = word.length();
        System.out.println("第一个字符串.length()方法求得的长度为"+leng1);
        
        int[] code = word.codePoints().toArray(); // codePoints 方法会产生一个int值得流,每个int值对应一个码点.
        for(int i=0;i<code.length;i++){
            System.out.println(code[i]+"---->");
        }

        //将码点数组转化为字符串
        String word2 = new String(code, 0, code.length);
        System.out.println(word2);
        
        /********************************************************/

        //要想获得字符串中的字符的个数，应当使用
        String aString = "我草泥马";
        int leng2 = aString.codePointCount(0, aString.length());
        System.out.println("使用codePointCount得到的字符串长度"+leng2);
        
        //要想获得指定位置处的字符，使用
        System.out.println(aString.codePointAt(3));
        //需要注意codePointAt的返回值，是int而非char。

        
        //枚举字符串的正确方法：
        for (int i = 0; i < aString.length();) {
            int character = aString.codePointAt(i);
            if (Character.isSupplementaryCodePoint(character)) 
                i += 2;
            else 
                ++i;
        }
        //将codePoint转换为char[]可调用Character.toChars方法，然后可进一步转换为字符串：

        
        // 构造器 StringBuilder
        String am = "fuch" ;
        String bm = "hello";
        StringBuilder builder = new StringBuilder();
        builder.append(am); 
        builder.append(bm);

        String completedString = builder.toString();
        System.out.println(builder);
        System.out.println(completedString);

    }
}


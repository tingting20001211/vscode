/*************************************************************************
  > File Name   : Demo2.java
  > Author      : ting
  > Mail        : 1213728568@qq.com
  > Created Time: Sat 01 Aug 2020 10:47:10 PM CST
 ************************************************************************/

import static java.lang.Math.*;

public class Demo2{
    public static void main(String[] args){
        
        //System.out.println(Demo.pi);// 使用别的java文件中的public static final double 常量
        
        double x = 4.0;
        double y = Math.sqrt(x); //sqrt
        System.out.println(y); // output 2.0

        double z = pow(x, 3);  // 由于导入Math包,可以省略前面的Math
        System.out.println(z);  //println方法处理System.out对象
        
        System.out.println(sqrt(PI));  //直接使用Math中的静态方法
        
        int a = 105, b = 202;
        int c = b%a;
        System.out.println(c);
        
        int d = Math.floorMod(b, a); // floorMod 同样也是求余运算
        System.out.println(d);
        
        System.out.println(123);

    }
}

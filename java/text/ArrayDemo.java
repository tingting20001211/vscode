import java.util.ArrayList;
import java.util.Arrays;

/*************************************************************************
  > File Name   : array.java
  > Author      : ting
  > Mail        : 1213728568@qq.com
  > Created Time: Mon 03 Aug 2020 11:29:09 AM CST
 ************************************************************************/

public class ArrayDemo{
    public static void main(String[] args){
        // first method
        int[] a = new int[5];
        for(int i : a)
            System.out.print(i + "  ");
        System.out.println();
                
        // second method
        int[] b = {1,2,3,4,5};
        for(int i : b)
            System.out.print(i + "  ");
        System.out.println();

        //third method
        int[] c;
        c = new int[] {9,8,7,6,5};
        for(int i : c)
            System.out.print(i + "  ");
        System.out.println();
        
        System.out.println(Arrays.toString(new int[]{11,22,33,44,55,66,77,88,99}));
    

        ArrayList<Student> stu = new ArrayList<Student>();
        stu.add(new Student("first", 10, "Computer-Science", 100.0));
        stu.add(new Student("second", 20, "Computer-Science", 90.0)); 
        stu.add(new Student("third", 30, "Computer-Science", 80.0));
        for(Student stui : stu)
            System.out.println(stui.toString());

        System.out.println(stu.size());
        stu.set(2, new Student("four",40,"doctor",70.0));
        System.out.println(stu.get(2));
        System.out.println("=================================================");
        System.out.println(Arrays.toString(stu.toArray()));
    }
}

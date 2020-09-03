import java.util.Objects;
import java.lang.String;
// package text  

/*************************************************************************
  > File Name   : Student.java
  > Author      : ting
  > Mail        : 1213728568@qq.com
  > Created Time: Wed 02 Sep 2020 10:43:25 PM CST
 ************************************************************************/


public class Student extends Person{
    private String major;
    private double grade;
    public Student(){
        super(); 
        this.major = "";
        this.grade = 0.0;
    }
    public Student(final String name, final int age, final String major, final double grade){
        super(name, age);
        this.major = major;
        this.grade = grade;
    }
    public Student(final String name, final int age){
        this(name, age, "", 0.0);
    }
    
    public void setMajor(final String major){ this.major = major; }
    public void setGrade(final double grade){ this.grade = grade; }
    public String getMajor() {return this.major;}
    public double getGrade() {return this.grade;}

    //first
    // @Override
    // public String info(){
    //     return super.info() + ", " +
    //            "major: " + this.major + ", " + 
    //            "grade: " + this.grade;
    // }

    // second
    // public String info2(){
    //     return "name: "  + super.getName() + ", " +
    //            "age: "   + super.getAge()  + ", " +
    //            "major: " + this.major      + ", " + 
    //            "grade: " + this.grade;
    // }

    // public String info3(){
    //     return "name: "  + getName()   + ", " + 
    //            "age: "   + getAge()    + ", " +
    //            "major: " + this.major  + ", " + 
    //            "grade: " + this.grade ;
    // }
    @Override
    public String toString(){
        return super.toString() + "[" + 
               "major=" + this.major  + ", " + 
               "grade=" + String.format("%.2f",this.grade) + "]"; 
    }
    @Override
    public boolean equals(final Object other){
        if(!super.equals(other)) return false;
        final Student otherStudent = (Student)other;
        return Objects.equals(this.major, otherStudent.getMajor())&&
               otherStudent.getGrade() == this.grade;
    }
    public static void main(final String[] args){
        final Student stu = new Student("yujie", 21, "CS", 4.8);
        final Person per = new Student("yujie",21,"CS",4.8);
        Person person = new Person("dj",25);
        // System.out.println("per: " + per.info());
        // System.out.println("stu: " + stu.info());
        // System.out.println("stu: " + stu.info2());
        // System.out.println("stu: " + stu.info3());
        System.out.println("per==stu? -> " + stu.equals(per));

        System.out.println(stu.toString());
        System.out.println(person.toString());

    }
}

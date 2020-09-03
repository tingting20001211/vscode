import java.util.Objects;

/*************************************************************************
  > File Name   : Employee.java
  > Author      : ting
  > Mail        : 1213728568@qq.com
  > Created Time: Wed 02 Sep 2020 10:10:00 PM CST
 ************************************************************************/

public class Person{
    private final String name;
    private int age;
    public Person(){
        this("",0);
    }
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }
    public void setAge(int age){this.age = age;}
    public int getAge(){ return this.age; }
    public String getName(){ return this.name; } 
    // public String info(){
    //     return "name: " + this.name + ", " +
    //            "age: "  + this.age  ;
    // }

    @Override
    public boolean equals(Object other){
        if(this==other) return true;
        if(other == null) return false;
        if(this.getClass()!=other.getClass()) return false;
        Person otherPerson = (Person)other;
        return Objects.equals(this.name, otherPerson.getName())&&
               this.age == otherPerson.getAge();
    }
    @Override
    public String toString(){
        return getClass().getName() + "[" +
               "name=" + this.name + ", " +
               "age="  + this.age  + "]";
    }

}




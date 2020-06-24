public class Student {

    private String name;
    private int age;

    public Student(String stuName, int stuAge) {
        name = stuName;
        age = stuAge;
    }

    @Override
    public String toString() {
        return String.format("Student(name: %s, age: %d)", name, age);
    }

    public static void main(String[] args) {

        Array<Student> arr = new Array<>();
        arr.addLast(new Student("Jack", 10));
        arr.addLast(new Student("Bob", 12));
        arr.addLast(new Student("Randy", 6));

        System.out.println(arr);
    }
}

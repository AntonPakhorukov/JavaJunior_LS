public class User {
    String name;
    int age;
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Name is " + this.name + " and him "+this.age + " age";
    }
}

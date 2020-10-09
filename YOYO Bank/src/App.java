import models.User;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        User user1 = new User();
        user1.setName("Keshav");
        System.out.println(user1.getName());
    }
}

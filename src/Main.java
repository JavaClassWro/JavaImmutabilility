import java.lang.reflect.Field;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        // code below prevents us from using reflection
        System.setSecurityManager(new SecurityManager());
        //---------------------------------------------

        System.out.println("Hello Java Immutability!");
        Person kate = Person.create("Kate", LocalDateTime.now());
        Person peter = Person.create(kate, "Peter", LocalDateTime.now());
        System.out.println("Peter's mom:"+peter.getMother().getName());
        peter.getMother().setName("Ann");
        System.out.println("Peter's mom:"+peter.getMother().getName());
        kate.setName("Ann");
        System.out.println("Peter's mom:"+peter.getMother().getName());
        System.out.println("Kate's name:"+kate.getName());
        System.out.println("---[ Mutability through reflection]---");

        Field name = peter.getClass().getDeclaredField("name");
        name.setAccessible(true);
        name.set(peter, "John");
        System.out.println("Peter's name:"+peter.getName());
        name.set(kate, "Alice");
        System.out.println("Kate's name:"+kate.getName());
        System.out.println("Peter's mom name:"+peter.getMother().getName());

        Field mom = peter.getClass().getDeclaredField("mother");
        mom.setAccessible(true);
        Person petersMom = (Person) mom.get(peter);
        name.set(petersMom, "Alice");
        System.out.println("Peter's mom:"+peter.getMother().getName());
    }
}

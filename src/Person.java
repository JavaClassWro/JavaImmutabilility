import java.time.LocalDateTime;

public final class Person {
    private final String name;
    private final LocalDateTime birthday;
    private final Person mother;

    public static Person create(Person mother, String name, LocalDateTime birthday) {
        return new Person(mother, name, birthday); // <- static factory
    }

    public static Person create(String name, LocalDateTime birthday) {
        return create(null, name, birthday); // <- static factory
    }

    private Person(Person mother, String name, LocalDateTime birthday) {
        this.name = name;
        this.birthday = birthday;
        // this.mother = mother <- not safe for immutability
        this.mother = mother != null ? new Person(mother) : null;
    }

    private Person(String name, LocalDateTime birthday) {
        this(null, name, birthday);
    }

    private Person(Person p) {
        this(p.mother, p.name, p.birthday);
    }

    public String getName() {
        return name;
    }

    public Person getMother() {
        //return mother; <- not safe for immutability
        return mother != null ? new Person(mother) : null;
    }

    public Person setName(String name) {
        // this.name = name <- not safe for immutability
        return new Person(getMother(), name, this.birthday);
    }
}

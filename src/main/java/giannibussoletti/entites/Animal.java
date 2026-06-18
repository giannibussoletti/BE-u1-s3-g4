package giannibussoletti.entites;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "tipo_animale")
@Table(name = "animals")
public abstract class Animal {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private int age;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Owners owner;

    public Animal(String name, int age, Owners owner) {
        this.name = name;
        this.age = age;
        this.owner = owner;
    }

    protected Animal() {
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", owner=" + owner +
                '}';
    }
}

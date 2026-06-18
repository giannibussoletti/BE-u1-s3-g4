package giannibussoletti.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "owners")
public class Owners {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String surname;

    public Owners(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    protected Owners() {
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "Owners{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}

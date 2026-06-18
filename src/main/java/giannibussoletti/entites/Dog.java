package giannibussoletti.entites;

import jakarta.persistence.Entity;

@Entity
//@DiscriminatorValue("Cane")
public class Dog extends Animal {
    private double maxSpeed;

    public Dog(String name, int age, double maxSpeed, Owners owner) {
        super(name, age, owner);
        this.maxSpeed = maxSpeed;
    }

    protected Dog() {
    }


    public double getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "maxSpeed=" + maxSpeed +
                "} " + super.toString();
    }
}

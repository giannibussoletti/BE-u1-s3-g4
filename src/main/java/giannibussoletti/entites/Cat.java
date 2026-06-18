package giannibussoletti.entites;

import jakarta.persistence.Entity;

@Entity
//@DiscriminatorValue("Gatto")
public class Cat extends Animal {
    private double maxJumpHeight;

    public Cat(String name, int age, double maxJumpHeight, Owners owner) {
        super(name, age, owner);
        this.maxJumpHeight = maxJumpHeight;

    }

    protected Cat() {
    }

    public double getMaxJumpHeight() {
        return maxJumpHeight;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "maxJumpHeight=" + maxJumpHeight +
                "} " + super.toString();
    }
}

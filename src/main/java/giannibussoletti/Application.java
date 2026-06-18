package giannibussoletti;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("beu1s3g4pu");

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}

package giannibussoletti.dao;

import giannibussoletti.entites.Animal;
import giannibussoletti.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class AnimalsDAO {
    private final EntityManager em;

    public AnimalsDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Animal newAnimal) {
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        em.persist(newAnimal);
        transaction.commit();
        System.out.println("Animale salvato");

    }

    public Animal findById(String animalId) {
        Animal found = em.find(Animal.class, UUID.fromString(animalId));
        if (found != null) return found;
        else throw new NotFoundException(animalId);
    }


}

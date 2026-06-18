package giannibussoletti.dao;

import giannibussoletti.entites.Owners;
import giannibussoletti.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class OwnersDAO {

    private final EntityManager em;

    public OwnersDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Owners newOwners) {
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        em.persist(newOwners);
        transaction.commit();
        System.out.println("Padrone salvato");

    }

    public Owners findById(String ownerId) {
        Owners found = em.find(Owners.class, UUID.fromString(ownerId));
        if (found != null) return found;
        else throw new NotFoundException(ownerId);
    }


}

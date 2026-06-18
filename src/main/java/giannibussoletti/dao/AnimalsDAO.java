package giannibussoletti.dao;

import giannibussoletti.entites.Animal;
import giannibussoletti.entites.Dog;
import giannibussoletti.entites.Owners;
import giannibussoletti.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;
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

    public List<Animal> getAllAnimals() {
        TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
        return query.getResultList();
    }

    public List<Dog> getAllDogs() {
        TypedQuery<Dog> query = em.createQuery("SELECT d FROM Dog d", Dog.class);
        return query.getResultList();

    }

    public List<Animal> getYoungerThen(int age) {
        TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a WHERE a.age < :param", Animal.class);
        query.setParameter("param", age);
        return query.getResultList();
    }

    public List<Animal> getNameAnimalStartsWith(String partial) {
        TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a WHERE LOWER(a.name) LIKE LOWER(:param)", Animal.class);
        query.setParameter("param", partial + "%");
        return query.getResultList();
    }

    public void findByNameAndUpdate(String currentName, String newName) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Query query = em.createQuery("UPDATE Animal a SET a.name = :newName WHERE a.name = :currentName");
        // Siccome questa non è una lettura ma una scrittura bisogna utilizzare una transazione
        query.setParameter("newName", newName);
        query.setParameter("currentName", currentName);
        query.executeUpdate(); // <-- Questa riga eseqgue la query nella transazione;
        transaction.commit();

    }

    public void findByNameAndDelete(String name) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Query query = em.createQuery("DELETE FROM Animal a WHERE a.name = :name");
        // siccome questa non è una lettura ma una scrittura bisogna utilizzare una transazione
        query.setParameter("name", name);
        query.executeUpdate(); // <-- Questa riga esegue la query nella transazione;
        transaction.commit();

    }

    public List<Animal> findByOwnersName(String name) {
        TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a WHERE a.owner.name = :name", Animal.class);
        // Grazie a JPQL posso attraversare le relazioni tramite la dotnotation sull'attributo che si riferisce
        // all'altra entità
        query.setParameter("name", name);
        return query.getResultList();
    }

    public List<Animal> findByOwner(Owners owner) {
        TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a WHERE a.owner = :owner", Animal.class);
        // Grazie a JPQL posso anche usare OGGETTI come parametri (in SQL non si può fare)
        query.setParameter("owner", owner);
        return query.getResultList();
    }
}

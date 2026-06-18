package giannibussoletti;

import giannibussoletti.dao.AnimalsDAO;
import giannibussoletti.entites.Animal;
import giannibussoletti.entites.Cat;
import giannibussoletti.entites.Dog;
import giannibussoletti.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("beu1s3g4pu");

    public static void main(String[] args) {
        // EREDITARIETÀ DELLE CLASSI E COME INTEGRARLA NELLE TABELLE
        // L'ereditarietà in Hibernate e JPA può essere rappresentata in tre modi:
        // - Single table: una singola tabella che contiene padri e figli
        // - Joined: una tabella per il padre e una per ciascuno dei figli, ma i figli contengono solo gli attributi non in
        //   comune con il padre
        // - Table-per-class: tabelle complete per ogni classe concreta


        // SINGLE TABLE
        // Se abbiamo un una classe padre e due figli con la single table verranno tutte accomunate
        // in una singola tabella. Si usa @Inheritance(strategy = InheritanceType.SINGLE_TABLE).
        // Il pro è che si gestisce tutto in un unica tabella, e quando dobbiamo cercare qualcosa
        // posso trovare tutto in maniera molto semplice.
        // Il grande contro di questo metodo è il fatto che la singola tabella si riempe di null
        // Questo impedisce di creare dei campi obbligatori andando a peggiorare la robustezza della tabella
        // questi check vanno fatti tramite codice

        EntityManager em = emf.createEntityManager();

        AnimalsDAO ad = new AnimalsDAO(em);

        Dog rex = new Dog("Rex", 10, 1.2);
        Cat felix = new Cat("Felix", 3, 2.3);

//        ad.save(rex);
//        ad.save(felix);

        try {
            Animal animal = ad.findById("444db5e1-7438-47d6-87e9-be7954cd2466");
            System.out.println(animal);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
}

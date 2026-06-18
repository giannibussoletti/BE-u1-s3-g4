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
            Animal animal = ad.findById("ceb3bb44-45f7-4d31-b9d0-1ba2667d108d");
            System.out.println(animal);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    // JOINED
    // Vengono create più tabelle, una tabella (anche astratta) con i parametri del padre
    // e poi per ogni classe figlio verrà creerà una tabella con solo gli attributi delle classi figlio
    // In questo caso verranno create 3 tabelle
    // Animal
    // Cat
    // Dog
    // Il pro è che le tabelle saranno molto più pulite
    // Il contro è che per recuperare i dati ci vorrà più tempo a causa del fatto che il programma dovrà fare un Join per ogni ricerca

    // TABLE PER (CONCRETE) CLASS
    // Questo metodo andrà a creare tabelle distinte solo delle classi concrete, quindi non classi astratte
    // Questo porta al fatto che se per qualsiasi cosa dobbiamo confrontare le 2 tabelle il lavoro del database sarà molto di più
    // Qui creare delle relazioni è molto difficile se no addirittura impossibile non avendo cose che uniscano le due tabelle
    

}

package giannibussoletti;

import giannibussoletti.dao.AnimalsDAO;
import giannibussoletti.dao.OwnersDAO;
import giannibussoletti.entites.Cat;
import giannibussoletti.entites.Dog;
import giannibussoletti.entites.Owners;
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
        OwnersDAO od = new OwnersDAO(em);

        Owners aldoFromDB = od.findById("2563eec1-1809-4f49-a376-20b5d622bbfe");
        Owners giovanniFromDB = od.findById("36d3024e-1f52-4e50-938a-fe01e3b843f6");

        Dog rex = new Dog("Rex", 10, 1.2, aldoFromDB);
        Cat felix = new Cat("Felix", 3, 2.3, aldoFromDB);
        Dog ringhio = new Dog("Ringhio", 5, 90.5, giovanniFromDB);
        Cat pantera = new Cat("Pantera", 4, 5.3, giovanniFromDB);
        Owners aldo = new Owners("Aldo", "Baglio");
        Owners giovanni = new Owners("Giovanni", "Storti");
//        od.save(giovanni);

//        ad.save(rex);
//        ad.save(felix);
//        ad.save(ringhio);
//        ad.save(pantera);

        ad.findByOwner(giovanniFromDB).forEach(System.out::println);
//
//        try {
//            Animal animal = ad.findById("2904d46a-31eb-453c-8e7d-f0eeca480e5b");
//            System.out.println(animal);
//        } catch (NotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//
//        ad.getAllAnimals().forEach(System.out::println);
//        ad.getAllDogs().forEach((System.out::println));
//        ad.getYoungerThen(11).forEach(System.out::println);
//        ad.getNameAnimalStartsWith("r").forEach(System.out::println);
//        ad.findByNameAndUpdate("Rex", "Ringhio");
//        ad.findByNameAndDelete("Ringhio");
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
    // Il vantaggio principale è che se dobbiamo cercare solo per esempio, dog o cat
    // possiamo creare dei metodi a posta per poter trovare solo i cani o i gatti

    // Java Persistence Query Language - JPQL
    // Tipo di query supportate:
    // - Named query: sono tipicamente utilizzate per eseguire le operazioni più comuni, vengono infatti memorizzate e
    // riutilizzate quando è necessario
    // - Dynamic query: vengono create secondo le necessità applicative del momento
    // Entrambe le query possono essere query normali o typed query, queste ultime hanno il vantaggio di fornire un
    // controllo sul tipo e quindi restituire risultati di un tipo specifico (ad esempio una lista di oggetti Automobile)


}

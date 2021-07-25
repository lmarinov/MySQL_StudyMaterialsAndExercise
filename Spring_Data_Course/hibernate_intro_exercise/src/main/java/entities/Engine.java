package entities;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Engine {
    private final EntityManager entityManager;
    private BufferedReader bufferedReader;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run(){
        System.out.println("Select exercise number:");

        try {
           int exNum = Integer.parseInt(bufferedReader.readLine());

           switch (exNum){
               case 2 -> changeCasing();
//               case 3 -> changeCasing();
//               case 4 -> changeCasing();
//               case 5 -> changeCasing();
//               case 6 -> changeCasing();
//               case 7 -> changeCasing();
//               case 8 -> changeCasing();
//               case 9 -> changeCasing();
//               case 10 -> changeCasing();
//               case 11 -> changeCasing();
//               case 12 -> changeCasing();
//               case 13 -> changeCasing();
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeCasing() {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Town t SET t.name = UPPER(t.name) WHERE LENGTH(t.name) <= 5 ");

        System.out.println(query.executeUpdate());

        entityManager.getTransaction().commit();
    }
}

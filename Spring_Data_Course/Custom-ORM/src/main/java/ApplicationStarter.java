import customerOrmFramework.core.connector.MyConnector;
import customerOrmFramework.core.entityManager.EntityManager;
import customerOrmFramework.core.entityManager.impl.EntityManagerImpl;
import entities.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class ApplicationStarter {
    public static void main(String[] args) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MyConnector.createConnection("root", "1253", "demo_orm_db");

        Connection connection = MyConnector.getConnection();
        EntityManager<User> entityManager = new EntityManagerImpl<>(connection);

        User user = new User("Pesho","Pesho123", 40, LocalDate.of(2021, 6, 20));

        entityManager.persist(user);

        User userFound = entityManager.findFirst(User.class, "age > 30");
    }
}

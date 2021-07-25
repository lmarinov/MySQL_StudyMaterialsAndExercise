package customerOrmFramework.core.entityManager;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface EntityManager<E> {

    boolean persist(E entity) throws IllegalAccessException, SQLException;

    Iterable<E> find(Class<E> table);

    Iterable<E> find(Class<E> table, String where);

    E findFirst(Class<E> table);

    E findFirst(Class<E> table, String where) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}

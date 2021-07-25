package customerOrmFramework.core.entityManager.impl;

import customerOrmFramework.annotations.Column;
import customerOrmFramework.annotations.Entity;
import customerOrmFramework.annotations.Id;
import customerOrmFramework.core.entityManager.EntityManager;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class EntityManagerImpl<E> implements EntityManager<E> {

    private Connection connection;

    public EntityManagerImpl(Connection connection) {
        this.connection = connection;
    }

    private Field getId(Class<?> entity) {
        return Arrays.stream(entity.getDeclaredFields())
                .filter(x -> x.isAnnotationPresent(Id.class))
                .findFirst()
                .orElse(Arrays
                        .stream(entity
                                .getSuperclass()
                                .getDeclaredFields())
                        .filter(field -> field.isAnnotationPresent(Id.class))
                        .findFirst()
                        .orElseThrow(() -> new UnsupportedOperationException("Entity does not have primary key.")));
    }

    @Override
    public boolean persist(E entity) throws IllegalAccessException, SQLException {
        Field primaryKey = getId(entity.getClass());
        primaryKey.setAccessible(true);
        Object value = primaryKey.get(entity);

        if (value == null || (long) value <= 0) {
            return doInsert(entity);
        }

        return doUpdate(entity, primaryKey);
    }

    private boolean doUpdate(E entity, Field primary) throws SQLException {

        String tableName = getTableName(entity.getClass());
        String fieldsNamesAndValues = getFieldAndValuesAsMap(entity)
                .entrySet()
                .stream()
                .map(kvp -> String.format(" %s = %s ", kvp.getKey(), kvp.getValue()))
                .collect(Collectors.joining(", "));

        String query = String.format("UPDATE %s SET %s WHERE id = ? ", tableName, fieldsNamesAndValues);

        return connection.prepareStatement(query).execute();
    }

    private Map<String, String> getFieldAndValuesAsMap(E entity) {
        Map<String, String> resultMap = new LinkedHashMap<>();

        Arrays.stream(entity
                .getClass()
                .getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .forEach(field -> {
                    field.setAccessible(true);
                    String fieldName = field.getAnnotation(Column.class).name();
                    String fieldValue = null;
                    try {
                        fieldValue = getValueToString(field, entity);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    resultMap.put(fieldName, fieldValue);
                });

        return resultMap;
    }

    private String getFieldValuesAsString(E entity) {
        return Arrays.stream(entity
                .getClass()
                .getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .map(field -> {
                    try {
                        return getValueToString(field, entity);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.joining(", "));
    }

    private String getValueToString(Field field, E entity) throws IllegalAccessException {
        field.setAccessible(true);
        boolean isString = field.getType().isAssignableFrom(String.class);
        if (isString) {
            try {
                return String.format("'%s'", field.get(entity));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return String.format("%s", field.get(entity));
    }

    private String getColumnNamesByEntity(E entity) {
        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(e -> e.isAnnotationPresent(Column.class))
                .map(f -> f.getAnnotation(Column.class).name())
                .collect(Collectors.joining(", "));
    }

    private String getTableName(Class<?> aClass) {
        return aClass
                .getAnnotation(Entity.class)
                .name();
    }

    private boolean doInsert(E entity) throws SQLException {
        String tableName = this.getTableName(entity.getClass());
        String columns = getColumnNamesByEntity(entity);
        String values = getFieldValuesAsString(entity);

        String query = String.format("INSERT INTO %s (%s) VALUES (%s) ", tableName, columns, values);

        return connection.prepareStatement(query).execute();
    }

    @Override
    public Iterable<E> find(Class<E> table) {
        return null;
    }

    @Override
    public Iterable<E> find(Class<E> table, String where) {
        return null;
    }

    @Override
    public E findFirst(Class<E> table) {
        return null;
    }

    @Override
    public E findFirst(Class<E> table, String where) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Statement statement = connection.createStatement();
        String tableName = getTableName(table);

        String query = String.format("SELECT * FROM %s %s LIMIT 1", tableName, where != null ? " WHERE " + where : "");
        ResultSet resultSet = statement.executeQuery(query);

        E entity = table.getDeclaredConstructor().newInstance();
        resultSet.next();
        fillEntity(table, resultSet, entity);

        return entity;
    }

    private void fillEntity(Class<E> table, ResultSet resultSet, E entity) throws SQLException, IllegalAccessException {
        Field[] declaredFields = Arrays.stream(table.getDeclaredFields()).toArray(Field[]::new);

        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            fillField(declaredField, resultSet, entity);
        }
    }

    private void fillField(Field declaredField, ResultSet resultSet, E entity) throws SQLException, IllegalAccessException {
        declaredField.setAccessible(true);

        if (declaredField.getType() == int.class || declaredField.getType() == long.class){
            declaredField.set(entity, resultSet.getInt(declaredField.getName()));
        }else if(declaredField.getType() == LocalDate.class){
            declaredField.set(entity, LocalDate.parse(resultSet.getString(declaredField.getName())));
        }else{
            declaredField.set(entity, resultSet.getString(declaredField.getName()));
        }
    }
}

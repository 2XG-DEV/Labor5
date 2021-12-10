package repos;

import java.util.List;

public interface ICrudRepo<T> {
    /**
     * Adds object to database
     * @param obj object to be added
     * @return added object if succesful null otherwise
     */
    T create(T obj);

    /**
     * Gets all objects in the database
     * @return list of all objects in database
     */
    List<T> getAll();

    /**
     * Updates values of an object
     * @param obj object with updated values
     * @return object with the updated values
     */
    T update(T obj);

    /**
     * Removes object from database
     * @param obj object to be deleted
     */
    void delete(T obj);
}

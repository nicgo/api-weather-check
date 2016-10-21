package persistence;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nico on 13/10/2016.
 */
public interface DataBaseAccess_Prototype<T,S> {

    public void inserInto(T persistence) throws SQLException;

    public void deleteFrom(T persistence) throws SQLException;

    public T selectFromWhere(S id) throws SQLException;

    public T select(T pesistence) throws SQLException;

    public List<T> selectAllFrom() throws SQLException;

    public List<T> selectAllFromWhere(S id) throws SQLException;


}
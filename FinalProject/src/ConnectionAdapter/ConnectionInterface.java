/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionAdapter;

import Criteria.Criteria;
import Criteria.CriteriaBuilder;
import java.sql.SQLException;

/**
 *
 * @author DangTrinh
 */
public interface ConnectionInterface {
    public abstract boolean createConnection(String url, String user, String password);
    public abstract boolean insert(Object values) throws IllegalArgumentException, IllegalAccessException, SQLException;
    public abstract boolean update(Object values) throws IllegalArgumentException, IllegalAccessException, SQLException;
    public abstract boolean delete(Object values) throws IllegalArgumentException, IllegalAccessException, SQLException;
    public abstract boolean select(Criteria crit);
}

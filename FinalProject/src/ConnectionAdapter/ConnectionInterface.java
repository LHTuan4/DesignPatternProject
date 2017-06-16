/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionAdapter;

/**
 *
 * @author DangTrinh
 */
public interface ConnectionInterface {
    public abstract boolean createConnection(String url, String user, String password);
    public abstract boolean insert(Object values);
    public abstract boolean update(Object values);
}

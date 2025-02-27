package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;

public interface RepositoryInterface<T> {
    static int id = 0;

    T create(T productType);
    Iterator<T> findAll();
    T findById(String id);
    void update(T productType, String id);
    void delete(String id);
}

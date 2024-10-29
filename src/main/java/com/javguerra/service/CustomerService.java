package com.javguerra.service;

import com.javguerra.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    /**
     * Devuelve la lista de clientes
     *
     * @return List<Customer>
     */
    List<Customer> getAll();

    /**
     * Devuelve el número de clientes totales
     *
     * @return long
     */
    long count();

    /**
     * Devuelve un cliente por su id dada
     *
     * @param id long
     * @return Optional<Customer>
     */
    Optional<Customer> findById(long id);

    /**
     * Agrega un cliente
     *
     * @param customer Customer
     * @return Customer
     */
    Customer save(Customer customer);

    /**
     * Agrega una lista de clientes
     *
     * @param customers List<Customer>
     */
    void saveAll(List<Customer> customers);

    /**
     * Elimina un cliente por su id dada
     * Si no existe el cliente, no hace nada
     *
     * @param id long
     */
    void deleteById(long id);

    /**
     * Elimina todos los clientes
     */
    void deleteAll();
}
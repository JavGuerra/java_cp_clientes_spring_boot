package com.javguerra.services;

import com.javguerra.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    /**
     * Devuelve la lista de clientes
     *
     * @return List<Customer>
     */
    List<Customer> getAllCustomers();

    /**
     * Devuelve el número de clientes totales
     *
     * @return long
     */
    long countCustomers();

    /**
     * Devuelve un cliente por su id dada
     *
     * @param id long
     * @return Optional<Customer>
     */
    Optional<Customer> findACustomerById(long id);

    /**
     * Agrega un cliente
     *
     * @param customer Customer
     * @return Customer
     */
    Customer saveACustomer(Customer customer);

    /**
     * Elimina un cliente por su id dada
     * Si no existe el cliente, no hace nada
     *
     * @param id long
     */
    void removeACustomerById(long id);

    /**
     * Elimina todos los clientes
     */
    void removeAllCustomers();

}
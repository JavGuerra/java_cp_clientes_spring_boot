package com.javguerra.controller;

import com.javguerra.model.Customer;
import com.javguerra.service.CustomerService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerUTest {

    @InjectMocks
    private CustomerController customerController;
    @Mock
    private CustomerService customerService;
    @Mock
    private Model model;

    @Test
    @DisplayName("findAll() utilizando mocks se prueba la interacción con el repositorio y el modelo.")
    void findAll() {
        Customer man1 = Customer.builder().id(1L).build();
        Customer man2 = Customer.builder().id(2L).build();
        List<Customer> customers = List.of(man1, man2);
        when(customerService.getAllCustomers()).thenReturn(customers);

        String view = customerController.findAll(model);

        assertEquals("/customer/list", view);
        verify(customerService).getAllCustomers();
        verify(model).addAttribute("customers", customers);
    }

    @Test
    @DisplayName("findById que tiene cliente con id")
    void findById_WhenCustomerExists() {
        Customer customer1 = Customer.builder().id(1L).nombre("c1").build();
        Optional<Customer> customerOpt = Optional.of(customer1);
        when(customerService.findACustomerById(1L)).thenReturn(customerOpt);

        String view = customerController.findById(model, 1L);

        assertEquals("/customer/detail", view);
        verify(customerService).findACustomerById(1L);
        verify(model).addAttribute("customer", customer1);
    }

    @Test
    @DisplayName("findById que no tiene cliente con id")
    void findById_WhenCustomerNotExists() {
        when(customerService.findACustomerById(1L)).thenReturn(Optional.empty());

        String view = customerController.findById(model, 1L);

        //assertEquals("/error", view); // TODO
        verify(customerService).findACustomerById(1L);
    }

    @Test
    @DisplayName("Método que te desplaza al formulario de customer/form vacío")
    void getFormToCreate() {
        String view = customerController.getFormToCreate(model);

        assertEquals("/customer/form", view);
        verify(model).addAttribute(eq("customer"), any(Customer.class));
    }

    @Test
    @DisplayName("Método que te desplaza al formulario de customer/form relleno")
    void getFormToUpdate_Exists() {
        Customer customer1 = Customer.builder().id(1L).nombre("c1").build();
        Optional<Customer> customerOpt = Optional.of(customer1);
        when(customerService.findACustomerById(1L)).thenReturn(customerOpt);

        String view = customerController.getFormToUpdate(model, 1L);

        assertEquals("/customer/form", view);
        verify(customerService).findACustomerById(1L);
        verify(model).addAttribute("customer", customer1);
    }

    @Test
    @DisplayName("Método que te desplaza a la página de error si no se encuentra el cliente")
    void getFormToUpdate_NotExists() {
        when(customerService.findACustomerById(1L)).thenReturn(Optional.empty());

        String view = customerController.getFormToUpdate(model, 1L);

        // assertEquals("/error", view); // TODO
        verify(customerService).findACustomerById(1L);
    }

    @Test
    @DisplayName("Método que guarda un cliente nuevo en el repositorio")
    void save_New() {
        Customer customer1 = Customer.builder().
                nombre("c1").apellido("a1").email("e1").edad(1).build();

        // Simula el guardado del cliente en el repositorio y añade el id
        doAnswer(invocation -> {
            Customer customerToSave = invocation.getArgument(0);
            customerToSave.setId(1L);
            return null;
        }).when(customerService).saveACustomer(customer1);

        String view = customerController.save(model, customer1);
        assertEquals("redirect:/customers/1", view);
    }

    @Test
    @DisplayName("Método que guarda un cliente existente en el repositorio")
    void save_Exists() {
        Customer customer1 = Customer.builder().id(1L).
                nombre("c1").apellido("a1").email("e1").edad(1).build();

        String view = customerController.save(model, customer1);

        assertEquals("redirect:/customers/1", view);
    }

    @Test
    @DisplayName("Método que te desplaza a la página de error si no existe el cliente")
    void save_NotExist() {
        Customer customer1 = Customer.builder().id(1L).
                nombre("c1").apellido("a1").email("e1").edad(1).build();
        when(customerService.findACustomerById(1L)).thenReturn(Optional.empty());

        String view = customerController.save(model, customer1);

        //assertEquals("/error", view); // TODO
    }

    @Test
    @DisplayName("Método que borra un cliente del repositorio")
    void deleteById_Exists() {
        Customer customer1 = Customer.builder().id(1L).build();
        when(customerService.findACustomerById(1L)).
                thenReturn(Optional.of(customer1));

        String view = customerController.deleteById(model, 1L);
        assertEquals("redirect:/customers", view);
        verify(customerService).removeACustomerById(1L);
    }

    @Test
    @DisplayName("Método que te desplaza a la página de error si no se borra el cliente")
    void deleteById_NotExists() {
        when(customerService.findACustomerById(1L)).thenReturn(Optional.empty());

        String view = customerController.deleteById(model, 1L);
        //assertEquals("/error", view); // TODO
    }

    @Test
    @DisplayName("Método que borra todos los clientes del repositorio")
    void deleteAll() {
        String view = customerController.deleteAll(model);
        assertEquals("redirect:/customers", view);
        verify(customerService).removeAllCustomers();
    }

    @Test
    @DisplayName("Método que te desplaza a la página de error si no se borran los clientes")
    void deleteAll_NotDeleted() {
        when(customerService.countCustomers()).thenReturn(1L);
        String view = customerController.deleteAll(model);
        assertEquals("/error", view);
        verify(customerService).removeAllCustomers();
    }

}
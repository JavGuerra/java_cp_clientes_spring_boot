package com.javguerra.controller;

import com.javguerra.model.Customer;
import com.javguerra.service.CustomerService;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.NoSuchElementException;

import static com.javguerra.util.Validation.*;

@Controller
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    private final String idMsg = "Falta el id o no es un entero positivo";
    private final String clientIdMsg = "El cliente no existe";
    private final String dataMsg = "Los datos recibidos no son válidos";
    private final String errMsg = "Error al borrar todos los clientes";

    /**
     * Lista todos los clientes
     *
     * @param model Model
     * @return String
     */
    @GetMapping("customers")
    public String findAll(Model model) {
        model.addAttribute("customers", customerService.getAll());
        return "customer/list";
    }

    /**
     * Muestra un cliente por su id
     *
     * @param id    Long
     * @param model Model
     * @return String
     */
    @GetMapping("customers/{id}")
    public String findById(Model model, @PathVariable Long id) {
        if (invalidIntPosNumber(id) || id == 0)
            throw new NoSuchElementException(idMsg);

        return customerService.findById(id).map(customer -> {
            model.addAttribute("customer", customer);
            return "customer/detail";
// Deprecated
//        }).orElseGet(() -> {
//            model.addAttribute("message", clientIdMsg);
//            return "error";
//        });
        }).orElseThrow(() -> new NoSuchElementException(clientIdMsg));

// Deprecated
//        if (invalidIntPosNumber(id) || id == 0) {
//        customerService.findACustomerById(id).ifPresentOrElse(
//                customer -> model.addAttribute("customer", customer),
//                () -> model.addAttribute("message", clientIdMsg)
//        );
//        if (model.containsAttribute("message")) return "/error";
//        return "/customer/detail";
    }

    /**
     * Obtiene el formulario vacío para poder crear un cliente desde cero
     *
     * @param model Model
     * @return String
     */
    @GetMapping("customers/new")
    public String getFormToCreate(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/form";
    }

    /**
     * Obtiene el formulario relleno para poder actualizar un cliente
     *
     * @param model Model
     * @param id    Long
     * @return String
     */
    @GetMapping("customers/update/{id}")
    public String getFormToUpdate(Model model, @PathVariable Long id) {
        if (invalidIntPosNumber(id) || id == 0)
            throw new NoSuchElementException(idMsg);

        return customerService.findById(id).map(customer -> {
            model.addAttribute("customer", customer);
            return "customer/form";
        }).orElseThrow(() -> new NoSuchElementException(clientIdMsg));
    }

    /**
     * Agrega un cliente
     *
     * @param customer Customer
     * @param model    Model
     * @return String
     */
    @PostMapping("customers")
    public String save(Model model, @ModelAttribute Customer customer) {
        if (customer == null) throw new NoSuchElementException(dataMsg);
        String error = formValidation(customer);
        if (error != null) throw new NoSuchElementException(error);

// Deprecated
//        if (customer.getId() == null) { // crear
//            customerService.saveACustomer(customer);
//        } else { // editar
//            customerService.findACustomerById(customer.getId()).ifPresentOrElse(
//                optCustomer -> {
//                    BeanUtils.copyProperties(customer, optCustomer);
//                    customerService.saveACustomer(optCustomer);
//                },
//                () -> model.addAttribute("message", clientIdMsg)
//            );
//        }

        if (customer.getId() == null) { // crear
            customerService.save(customer);
            return "redirect:/customers/" + customer.getId();
        } else { // editar
            return customerService.findById(customer.getId()).map(optCustomer -> {
                BeanUtils.copyProperties(customer, optCustomer);
                customerService.save(optCustomer);
                return "redirect:/customers/" + optCustomer.getId();
            }).orElseThrow(() -> new NoSuchElementException(clientIdMsg));
        }
    }

    /**
     * Elimina un cliente por su id
     *
     * @param id    Long
     * @param model Model
     * @return String
     */
    @GetMapping("customers/delete/{id}")
    public String deleteById(Model model, @PathVariable Long id) {
        if (invalidIntPosNumber(id) || id == 0)
            throw new NoSuchElementException(idMsg);
        return customerService.findById(id).map(customer -> {
            customerService.deleteById(customer.getId());
            return "redirect:/customers";
        }).orElseThrow(() -> new NoSuchElementException(clientIdMsg));
    }

    /**
     * Elimina todos los clientes
     *
     * @param model Model
     * @return String
     */
    @GetMapping("customers/delete")
    public String deleteAll(Model model) {
        customerService.deleteAll();
        if (customerService.count() != 0)
            throw new NoSuchElementException(errMsg);
        return "redirect:/customers";
    }

}
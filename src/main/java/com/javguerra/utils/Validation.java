package com.javguerra.utils;

import com.javguerra.entities.Customer;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Validation {

    /**
     * Comprueba si una cadena está vacía
     *
     * @param str String
     * @return boolean
     */
    public static boolean stringIsEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Comprueba si un número no es un número entero positivo
     *
     * @param num Long
     * @return boolean
     */
    public static boolean invalidIntPosNumber(Long num) {
        return num == null || num < 0L;
    }

    /**
     * Validación del formulario
     *
     * @param customer Customer
     * @return String
     */
    public static String formValidation(Customer customer) {
        if (stringIsEmpty(customer.getNombre())) return "Falta el nombre";
        if (stringIsEmpty(customer.getApellido())) return "Faltan los apellidos";
        if (stringIsEmpty(customer.getEmail())) return "Falta el email";
        if (invalidIntPosNumber((long) customer.getEdad()))
            return "Falta la edad o no es un número entero positivo";
        if (customer.getEdad() <= 0) return "La edad debe ser mayor que cero";
        return null;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaseguridad;

/**
 *
 * @author fabri
 */
public class TextProcessor {
    
    public static String reverseAndCapitalize(String input) {
        // ESCENARIO 2: Nulo o Vacío
        if (input == null) {
            throw new IllegalArgumentException("El texto no puede ser nulo");
        }
        
        // ESCENARIO 4 (Implícito): Solo espacios o vacío
        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException("El texto no puede estar vacío ni contener solo espacios");
        }

        // ESCENARIO 3: Límite de 1000 caracteres
        if (input.length() > 1000) {
            throw new IllegalArgumentException("El texto excede el límite de 1000 caracteres");
        }

        // LÓGICA DE TRANSFORMACIÓN: Trim -> Reverse -> UpperCase
        
        // 1. Eliminar espacios inicio/fin
        String trimmed = input.trim();
        
        // 2. Invertir (Usando StringBuilder que es eficiente)
        StringBuilder reversed = new StringBuilder(trimmed).reverse();
        
        // 3. Convertir a Mayúsculas y retornar
        return reversed.toString().toUpperCase();
    }
    
}

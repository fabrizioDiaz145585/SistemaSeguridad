/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaseguridad;

/**
 *
 * @author fabri
 */
public class PasswordValidator {
    
        public boolean esValida(String contrasena) {
        // Escenario 1: Null o Vacío -> Excepción
        if (contrasena == null || contrasena.trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede ser nula o vacía");
        }

        // Requisito: Longitud mínima 8 y máxima 64
        if (contrasena.length() < 8 || contrasena.length() > 64) {
            return false;
        }

        // Requisito: Espacios
        if (contrasena.startsWith(" ") || contrasena.endsWith(" ")) {
            return false;
        }
        if (contrasena.contains("  ")) {
            return false;
        }

        // Verificación de complejidad
        boolean tieneMayuscula = false;
        boolean tieneMinuscula = false;
        boolean tieneDigito = false;
        boolean tieneEspecial = false;
        
        String caracteresEspeciales = "!@#$%^&*()_-+={}[]|\\:;\"'<>,.?/";

        for (char c : contrasena.toCharArray()) {
            if (Character.isUpperCase(c)) {
                tieneMayuscula = true;
            } else if (Character.isLowerCase(c)) {
                tieneMinuscula = true;
            } else if (Character.isDigit(c)) {
                tieneDigito = true;
            } else if (caracteresEspeciales.contains(String.valueOf(c))) {
                tieneEspecial = true;
            } else if (c == ' ') {
                continue; 
            } else {
                return false;
            }
        }

        return tieneMayuscula && tieneMinuscula && tieneDigito && tieneEspecial;
    }
    
}

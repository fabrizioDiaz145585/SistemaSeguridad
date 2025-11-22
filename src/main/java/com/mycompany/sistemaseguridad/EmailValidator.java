/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaseguridad;

/**
 *
 * @author fabri
 */
public class EmailValidator {
    
    public boolean isValidEmail(String email) {
        // ESCENARIO 1: Nulo o Vacío
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo no puede ser nulo o vacío");
        }

        // Longitud total (RFC 5321)
        if (email.length() < 6 || email.length() > 254) {
            return false;
        }

        // Debe contener un arroba '@'
        int arrobaIndex = email.indexOf('@');
        if (arrobaIndex == -1) {
            return false; // No tiene @
        }
        
        // Solo puede haber UN arroba
        if (email.indexOf('@', arrobaIndex + 1) != -1) {
            return false; // Tiene más de un @
        }

        // Separar parte local y dominio
        String localPart = email.substring(0, arrobaIndex);
        String domainPart = email.substring(arrobaIndex + 1);

        // VALIDACIONES PARTE LOCAL
        if (localPart.isEmpty()) return false;
        if (localPart.startsWith(".") || localPart.endsWith(".")) return false; // No punto al inicio/fin
        if (localPart.contains("..")) return false; // No puntos consecutivos

        // Caracteres permitidos en local (Letras, números, punto, guion, guion bajo)
        for (char c : localPart.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != '.' && c != '-' && c != '_') {
                return false; // Caracter inválido
            }
        }

        // VALIDACIONES DOMINIO
        if (domainPart.isEmpty()) return false;
        if (!domainPart.contains(".")) return false; // Debe tener punto
        if (domainPart.contains("..")) return false; // No puntos consecutivos
        
        // El último punto no puede estar al final ni al principio del dominio
        if (domainPart.startsWith(".") || domainPart.endsWith(".")) return false;

        // Validar extensión (parte después del último punto)
        int lastDotIndex = domainPart.lastIndexOf('.');
        String extension = domainPart.substring(lastDotIndex + 1);
        
        // Extensión debe tener al menos 2 caracteres (ej: .co, .com)
        if (extension.length() < 2) return false;

        return true; // Si pasó todo, es válido
    }
    
}

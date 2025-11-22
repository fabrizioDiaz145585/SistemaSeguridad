/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaseguridad;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author fabri
 */
public class EmailValidatorTest {
    
    EmailValidator validador = new EmailValidator();

    // CASO 1: Correo Válido (Happy Path)
    // [VIDEO: MANTENER DESCOMENTADO] - Esta es la prueba básica que siempre debe estar.
    @Test
    @DisplayName("Debe retornar true para un correo estándar válido")
    public void testCorreoValido() {
        assertTrue(validador.isValidEmail("cliente123@tienda.com"));
    }

    // CASO 2: Correo Nulo (Escenario 1)
    // [VIDEO: COMENTAR ESTO] - Para mostrar que no cubres excepciones al principio
    @Test
    @DisplayName("Debe lanzar excepción si el correo es nulo")
    public void testCorreoNulo() {
        Exception excepcion = assertThrows(IllegalArgumentException.class, () -> {
            validador.isValidEmail(null);
        });
        assertEquals("El correo no puede ser nulo o vacío", excepcion.getMessage());
    }

    // CASO 3: Correo Vacío (Escenario 1 - Variación)
    // [VIDEO: COMENTAR ESTO]
    @Test
    @DisplayName("Debe lanzar excepción si el correo está vacío")
    public void testCorreoVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            validador.isValidEmail("");
        });
    }

    // CASO 4: Falta Arroba o Dominio (Escenario 2)
    // [VIDEO: MANTENER DESCOMENTADO] - Prueba básica de formato
    @Test
    @DisplayName("Debe retornar false si falta el arroba")
    public void testFaltaArroba() {
        assertFalse(validador.isValidEmail("usuario.gmail.com"));
    }

    // CASO 5: Dominio Inválido (Escenario 2 - Variación)
    // [VIDEO: COMENTAR ESTO] - Para bajar coverage en lógica de dominios
    @Test
    @DisplayName("Debe retornar false si el dominio no tiene punto o extensión válida")
    public void testDominioInvalido() {
        assertAll("Validación de dominios rotos",
            () -> assertFalse(validador.isValidEmail("user@com"), "Falta punto en dominio"), // "usuario@com"
            () -> assertFalse(validador.isValidEmail("user@tienda."), "Termina en punto"),
            () -> assertFalse(validador.isValidEmail("user@tienda.c"), "Extensión muy corta")
        );
    }

    // CASO 6: Validaciones complejas de Parte Local (Puntos consecutivos, inicio, fin)
    // [VIDEO: COMENTAR ESTO] - Esta cubre muchas ramas del 'if' complejos
    @Test
    @DisplayName("Debe validar reglas estrictas de la parte local (puntos, caracteres)")
    public void testParteLocalInvalida() {
        assertAll("Errores en parte local",
            () -> assertFalse(validador.isValidEmail(".user@tienda.com"), "No punto al inicio"),
            () -> assertFalse(validador.isValidEmail("user.@tienda.com"), "No punto al final de local"),
            () -> assertFalse(validador.isValidEmail("us..er@tienda.com"), "No puntos consecutivos"),
            () -> assertFalse(validador.isValidEmail("us*er@tienda.com"), "Caracter prohibido (*)")
        );
    }
    
}

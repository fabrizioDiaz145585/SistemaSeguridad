package com.mycompany.sistemaseguridad;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author fabri
 */
public class PasswordValidatorTest {
    
    PasswordValidator validador = new PasswordValidator();

    // CASO 1
    @Test
    @DisplayName("Debe lanzar excepción si la contraseña es null")
    public void testContrasenaNula() {
        Exception excepcion = assertThrows(IllegalArgumentException.class, () -> {
            validador.esValida(null);
        });
        assertEquals("La contraseña no puede ser nula o vacía", excepcion.getMessage());
    }

    // CASO 2
    @Test
    @DisplayName("Debe lanzar excepción si la contraseña está vacía")
    public void testContrasenaVacia() {
        assertThrows(IllegalArgumentException.class, () -> {
            validador.esValida("");
        });
    }

    // CASO 3
    @Test
    @DisplayName("Debe retornar true para una contraseña segura correcta")
    public void testContrasenaValida() {
        boolean resultado = validador.esValida("Segura123!");
        assertTrue(resultado, "La contraseña debería ser válida");
    }
    
    // EXTRA: Espacio simple
    @Test
    @DisplayName("Debe permitir un espacio simple entre caracteres")
    public void testContrasenaConEspacioValido() {
        boolean resultado = validador.esValida("Segura 123!");
        assertTrue(resultado, "Debería aceptar un espacio simple intermedio");
    }

    // CASO 4
    @Test
    @DisplayName("Debe retornar false si faltan mayúsculas, números o especiales")
    public void testComplejidadInvalida() {
        boolean resultado = validador.esValida("nosecura");
        assertFalse(resultado, "La contraseña debería ser rechazada");
    }

    // CASO 5
    @Test
    @DisplayName("Debe retornar false si la longitud es menor a 8")
    public void testLongitudMuyCorta() {
        boolean resultado = validador.esValida("Abc123!");
        assertEquals(false, resultado, "La contraseña es muy corta");
    }

    // CASO 6
    @Test
    @DisplayName("Validar reglas estrictas de espacios")
    public void testReglasDeEspacios() {
        assertAll("Validación de espacios incorrectos",
            () -> assertFalse(validador.esValida(" Segura123!"), "No espacios al inicio"),
            () -> assertFalse(validador.esValida("Segura123! "), "No espacios al final"),
            () -> assertFalse(validador.esValida("Segura  123!"), "No doble espacio")
        );
    }
    
}

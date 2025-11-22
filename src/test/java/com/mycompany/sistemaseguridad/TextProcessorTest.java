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
public class TextProcessorTest {
    
     // CASO 1: Flujo normal (Happy Path)
    // Entrada: "hola" -> Trim "hola" -> Reverse "aloh" -> Upper "ALOH"
    // [VIDEO: MANTENER DESCOMENTADO]
    @Test
    @DisplayName("Debe invertir, limpiar espacios y convertir a mayúsculas")
    public void testProcesamientoCorrecto() {
        String resultado = TextProcessor.reverseAndCapitalize(" hola ");
        assertEquals("ALOH", resultado);
    }

    // CASO 2: Texto Nulo (Escenario 2)
    // [VIDEO: COMENTAR ESTO]
    @Test
    @DisplayName("Debe lanzar excepción si el texto es null")
    public void testTextoNulo() {
        Exception excepcion = assertThrows(IllegalArgumentException.class, () -> {
            TextProcessor.reverseAndCapitalize(null);
        });
        assertEquals("El texto no puede ser nulo", excepcion.getMessage());
    }

    // CASO 3: Texto con solo espacios (Inválido según requerimiento)
    // [VIDEO: COMENTAR ESTO]
    @Test
    @DisplayName("Debe lanzar excepción si el texto tiene solo espacios")
    public void testSoloEspacios() {
        assertThrows(IllegalArgumentException.class, () -> {
            TextProcessor.reverseAndCapitalize("   ");
        });
    }

    // CASO 4: Texto vacío ""
    // [VIDEO: COMENTAR ESTO]
    @Test
    @DisplayName("Debe lanzar excepción si el texto está vacío")
    public void testTextoVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            TextProcessor.reverseAndCapitalize("");
        });
    }

    // CASO 5: Límite de caracteres excedido (Escenario 3)
    // [VIDEO: COMENTAR ESTO]
    @Test
    @DisplayName("Debe lanzar excepción si supera 1000 caracteres")
    public void testLongitudExcedida() {
        // Generamos un string de 1001 letras 'a'
        String textoLargo = "a".repeat(1001); 
        
        Exception excepcion = assertThrows(IllegalArgumentException.class, () -> {
            TextProcessor.reverseAndCapitalize(textoLargo);
        });
        assertEquals("El texto excede el límite de 1000 caracteres", excepcion.getMessage());
    }
    
    // CASO 6: Texto palíndromo (se lee igual al revés)
    // [VIDEO: MANTENER DESCOMENTADO] - Caso interesante para probar lógica
    @Test
    @DisplayName("Debe procesar correctamente palíndromos con espacios")
    public void testPalindromo() {
        // " anita lava la tina " -> "ANIT AL AVAL ATINA" (invertido y mayusc)
        String input = " anita lava la tina ";
        String esperado = "ANIT AL AVAL ATINA";
        assertEquals(esperado, TextProcessor.reverseAndCapitalize(input));
    }
    
}

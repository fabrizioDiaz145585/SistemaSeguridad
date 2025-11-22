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
public class InventoryManagerTest {
    
     // Instancia fresca para cada test
    InventoryManager gestor = new InventoryManager();

    // CASO 1: Agregar ítem nuevo (Happy Path)
    // Requerimiento: Escenario 1
    @Test
    @DisplayName("Debe agregar un producto correctamente al inventario")
    public void testAgregarItemNuevo() {
        gestor.addItem("Laptop", 5);
        // Verificamos que el stock sea 5
        assertEquals(5, gestor.getStock("Laptop"), "El stock de Laptop debería ser 5");
        // Verificamos que el producto exista con assertTrue
        assertTrue(gestor.hasProduct("Laptop"));
    }

    // CASO 2: Agregar cantidad negativa
    // Requerimiento: Escenario 2
    @Test
    @DisplayName("Debe lanzar excepción si la cantidad es negativa o cero")
    public void testCantidadNegativa() {
        assertThrows(IllegalArgumentException.class, () -> {
            gestor.addItem("Mouse", -1);
        });
    }

    // CASO 3: Agregar ítem con nombre vacío
    // Requerimiento: Escenario 3
    @Test
    @DisplayName("Debe lanzar excepción con mensaje específico si el nombre está vacío")
    public void testNombreVacio() {
        Exception excepcion = assertThrows(IllegalArgumentException.class, () -> {
            gestor.addItem("", 5);
        });
        // Validamos el mensaje exacto que pedía el requerimiento
        assertEquals("El nombre del producto no puede estar vacío", excepcion.getMessage());
    }

    // CASO 4: Sumar cantidad a producto existente
    // Requerimiento: "Se permite agregar el mismo producto más de una vez"
    @Test
    @DisplayName("Debe sumar la cantidad si el producto ya existe")
    public void testAcumularStock() {
        gestor.addItem("Teclado", 10);
        gestor.addItem("Teclado", 5); // Agregamos más
        
        // Esperamos 15
        assertEquals(15, gestor.getStock("Teclado"), "El stock debería sumarse (10+5)");
    }

    // CASO 5: Límite superior de cantidad
    // Requerimiento: Máximo permitido 1000
    @Test
    @DisplayName("Debe lanzar excepción si la cantidad supera 1000")
    public void testCantidadExcesiva() {
        assertThrows(IllegalArgumentException.class, () -> {
            gestor.addItem("Monitor", 1001);
        });
    }

    // CASO 6: Validaciones de longitud de nombre (Bordes)
    // Requerimiento: Min 2, Max 50 caracteres
    @Test
    @DisplayName("Validar longitud mínima y máxima del nombre")
    public void testLongitudNombre() {
        String nombreMuyLargo = "Este es un nombre de producto extremadamente largo que supera los cincuenta caracteres";
        
        assertAll("Validación de longitud de nombres",
            // Nombre de 1 letra (Inválido)
            () -> assertThrows(IllegalArgumentException.class, () -> gestor.addItem("A", 5)),
            
            // Nombre muy largo (Inválido)
            () -> assertThrows(IllegalArgumentException.class, () -> gestor.addItem(nombreMuyLargo, 5)),
            
            // Nombre de 2 letras (Válido - Borde inferior)
            () -> assertDoesNotThrow(() -> gestor.addItem("TV", 1))
        );
    }
    
}

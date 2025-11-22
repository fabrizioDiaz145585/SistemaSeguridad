/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaseguridad;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author fabri
 */
public class InventoryManager {
    
    // Mapa para guardar el inventario: "NombreProducto" -> Cantidad
    private Map<String, Integer> inventory = new HashMap<>();

    public void addItem(String item, int quantity) {
        // VALIDACIÓN 1: Nombre nulo o vacío (Escenario 3)
        if (item == null || item.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío");
        }

        // VALIDACIÓN 2: Longitud del nombre (Requerimiento: 2 a 50 caracteres)
        if (item.length() < 2 || item.length() > 50) {
            throw new IllegalArgumentException("El nombre debe tener entre 2 y 50 caracteres");
        }

        // VALIDACIÓN 3: Cantidad (Escenario 2 y Requerimiento Max 1000)
        if (quantity < 1 || quantity > 1000) {
            throw new IllegalArgumentException("La cantidad debe ser válida (entre 1 y 1000)");
        }

        // LÓGICA PRINCIPAL: Sumar si existe, crear si no
        if (inventory.containsKey(item)) {
            int actual = inventory.get(item);
            inventory.put(item, actual + quantity);
        } else {
            inventory.put(item, quantity);
        }
    }

    // Método auxiliar necesario para las pruebas (consultar el stock)
    public int getStock(String item) {
        // getOrDefault devuelve 0 si el producto no existe
        return inventory.getOrDefault(item, 0);
    }
    
    // Método auxiliar para verificar si existe (útil para aserciones)
    public boolean hasProduct(String item) {
        return inventory.containsKey(item);
    }
    
}

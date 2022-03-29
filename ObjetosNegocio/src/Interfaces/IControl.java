/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Clases.Producto;

/**
 *
 * @author Abrahan Barrios
 */
public interface IControl {
    
    public Producto obtenProducto(Producto producto) throws Exception;
    
    public void agregar(Producto producto) throws Exception;

    public void actualizar(Producto producto) throws Exception;

    public void eliminar(Producto producto) throws Exception;
    
}

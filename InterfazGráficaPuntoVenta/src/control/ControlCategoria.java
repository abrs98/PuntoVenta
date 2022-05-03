/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import entidades.Categoria;
import persistencia.CategoriaDAO;

/**
 *
 * @author Abrahan Barrios
 */
public class ControlCategoria {
    CategoriaDAO categoria= new CategoriaDAO();
    
    
    public boolean guardarCategoria(Categoria categoria) {
        return this.categoria.insert(categoria);
    }

    public boolean actualizarCategoria(Categoria categoria ) {
        return this.categoria.update(categoria);
    }

    public boolean eliminarCategoria(Categoria categoria) {
        return this.categoria.remove(categoria);
    }

    public Categoria consultarPorIdCategoria(Integer id) {
        return this.categoria.findById(id);
    }
    
    public Categoria consultarPorNombreCategoria(String nombre) {
        return this.categoria.findByName(nombre);
    }

    public List<Categoria> consultarCategorias(String busqueda) {
        return this.categoria.find(busqueda);
    }
}


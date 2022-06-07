/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import tienda.entidades.Producto;

/**
 *
 * @author Jime
 */
public class ProductoDao extends DAO {

    public Collection<Producto> listarProductosBD() throws Exception {
        try {
            String sql = "select * from producto";
            consultarBase(sql);

            Producto producto = null;
            Collection<Producto> productos = new ArrayList();

            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));
                productos.add(producto);
            }
            return productos;
        } catch (Exception ex) {
            ex.printStackTrace();
            desconectarBase();
            throw new Exception("Error del sistema");
        }
    }

    public Collection<Producto> listarProductosPorRango() throws Exception {
        Collection<Producto> productos = new ArrayList();
        Producto producto;
        try {
            String sql = "select nombre, precio from producto where precio<202 and precio>120";
            consultarBase(sql);

            while (resultado.next()) {
                producto = new Producto();
                producto.setNombre(resultado.getString(1));
                producto.setPrecio(resultado.getDouble(2));
                productos.add(producto);
            }
            return productos;
        } catch (Exception ex) {
            ex.printStackTrace();
            desconectarBase();
            throw new Exception("Error del sistema");
        }

    }

    public Collection<Producto> listarProductosPorNombre(String nombre) throws Exception {
        Collection<Producto> productos = new ArrayList();
        Producto producto;
        try {
            String sql = "select nombre, precio from producto where nombre like '%" + nombre + "%'";
            //select nombre, precio from producto where nombre like '%Portatil%';
            consultarBase(sql);

            while (resultado.next()) {
                producto = new Producto();
                producto.setNombre(resultado.getString(1));
                producto.setPrecio(resultado.getDouble(2));
                productos.add(producto);
            }
            return productos;
        } catch (Exception ex) {
            ex.printStackTrace();
            desconectarBase();
            throw new Exception("Error del sistema");
        }
    }

    public Producto buscarProductosPorCodigo(int codigo) throws Exception {
        try {
            String sql = "select * from producto where codigo = " + codigo + "";
            //select nombre, precio from producto where nombre like '%Portatil%';
            consultarBase(sql);

            while (resultado.next()) {
                Producto producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));
                return producto;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            desconectarBase();
            throw new Exception("Error del sistema");
        }
        return null;
     }

    public Collection<Producto> listarProductosPrecioBarato() throws Exception {
        Collection<Producto> productos = new ArrayList();
        Producto producto;
        try {
            String sql = "select nombre, MIN(precio) from producto";
            //select nombre, precio, MIN(precio) from producto;
            consultarBase(sql);

            while (resultado.next()) {
                producto = new Producto();
                producto.setNombre(resultado.getString(1));
                producto.setPrecio(resultado.getDouble(2));
                productos.add(producto);
            }
            return productos;
        } catch (Exception ex) {
            ex.printStackTrace();
            desconectarBase();
            throw new Exception("Error del sistema");
        }

    }

    public void guardarProducto(Producto producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("Debe indicar un producto");
            }
            String sql = "INSERT INTO producto (nombre, precio, codigo_fabricante)VALUES ('" + producto.getNombre() + "'," + producto.getCodigo() + "," + producto.getCodigoFabricante() + ")";
            //INSERT INTO producto (nombre, precio, codigo_fabricante) VALUES ('notebook jayala',34,2);
            insertarModificarEliminar(sql);

        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarProducto(Producto producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("Debe indicar un producto");
            }
            String sql = "UPDATE producto SET nombre='" + producto.getNombre()
                    + "', precio=" + producto.getPrecio()
                    + ",codigo_fabricante=" + producto.getCodigoFabricante() + " WHERE codigo=" + producto.getCodigo();

            
            insertarModificarEliminar(sql);

        } catch (Exception e) {
            throw e;
        }
    }

}

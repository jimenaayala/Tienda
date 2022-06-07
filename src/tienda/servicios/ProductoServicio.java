/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.servicios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import tienda.entidades.Producto;

/**
 *
 * @author Jime
 */
public class ProductoServicio {

    public ProductoServicio() {
    }
    public Producto crearProducto(){
        Scanner leer = new Scanner (System.in);
        Producto producto = new Producto();
        System.out.println("Ingrese Codigo, Nombre, Precio, Codigo del fabricante");
        producto.setCodigo(leer.nextInt());
        producto.setNombre(leer.next());
        producto.setPrecio(leer.nextDouble());
        producto.setCodigoFabricante(leer.nextInt());
        return producto;
    }
    //public Producto modificarProducto(int codigo){
        
    //return producto;
    //}
    public void consultarObjetosProductos(Collection<Producto> productos, String atributo) {
        if (atributo.equalsIgnoreCase("nombre")) {
            productos.forEach((producto) -> {
                System.out.println("Producto: " + producto.getNombre());
            });
        }
        if (atributo.equalsIgnoreCase("nombrePrecio")) {
            productos.forEach((producto) -> {
                System.out.println("Nombre del producto: " + producto.getNombre() + "  |  Precio: $" + producto.getPrecio());
            });
        }
        if (atributo.equalsIgnoreCase("todo")) {
            productos.forEach((producto) -> {
                System.out.println("Codigo: "+ producto.getCodigo()+ 
                        "  | Nombre del producto: " + producto.getNombre() + 
                        "  |  Precio: $" + producto.getPrecio() + 
                        "  |  Codigo del fabricante: " + producto.getCodigoFabricante());
            });
        }
    }
}

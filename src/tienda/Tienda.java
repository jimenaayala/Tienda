/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import tienda.entidades.Producto;
import tienda.persistencia.ProductoDao;
import tienda.servicios.ProductoServicio;

/**
 *
 * @author Jime
 */
public class Tienda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner leer = new Scanner(System.in);
        char op = 'c';
        while (op != 'S') {
            System.out.println(" ");
            System.out.println("**********MENU************");
            System.out.println("C - Consultar nombre productos");
            System.out.println("P - Consultar nombre y precios productos");
            System.out.println("L - Listar aquellos productos que su precio esté entre 120 y 202");
            System.out.println("F - Buscar y listar todos los Portátiles de la tabla producto.");
            System.out.println("B - Listar el nombre y el precio del producto más barato.");
            System.out.println("I - Ingresar un producto a la base de datos.");
            System.out.println("Z - Ingresar un fabricante a la base de datos");
            System.out.println("W - Editar un producto con datos a elección.");
            System.out.println("S - Salir");
            System.out.println("**************************");
            System.out.println("");
            System.out.println("Ingrese opcion: ");
            op = leer.next().charAt(0);
            ProductoServicio ps = new ProductoServicio();
            Collection<Producto> productos = new ArrayList();
            ProductoDao pd = new ProductoDao();

            switch (op) {
                case 'C':
                    try {
                        productos = pd.listarProductosBD();
                        System.out.println("");
                        System.out.println("_____PRODUCTOS POR NOMBRE__________");
                        ps.consultarObjetosProductos(productos, "nombre");
                        System.out.println("");
                        break;
                    } catch (Exception exe) {
                        System.out.println("Error: " + exe.getMessage());
                    }

                case 'L':
                    try {
                        productos = pd.listarProductosPorRango();
                        System.out.println("");
                        System.out.println("_____PRODUCTOS POR RANGO__________");
                        ps.consultarObjetosProductos(productos, "nombrePrecio");
                        System.out.println("");
                        break;
                    } catch (Exception exe) {
                        System.out.println("Error: " + exe.getMessage());

                    }

                case 'P':
                    try {
                        productos = pd.listarProductosBD();
                        System.out.println("");
                        System.out.println("_____PRODUCTOS POR NOMBRE Y PRECIO__________");
                        ps.consultarObjetosProductos(productos, "nombrePrecio");
                        System.out.println("");
                        break;
                    } catch (Exception exe) {
                        System.out.println("Error: " + exe.getMessage());
                    }
                case 'F':
                    try {
                        productos = pd.listarProductosPorNombre("Portatil");
                        System.out.println("");
                        System.out.println("_____PRODUCTOS POR NOMBRE Y PRECIO__________");
                        ps.consultarObjetosProductos(productos, "nombrePrecio");
                        System.out.println("");
                        break;
                    } catch (Exception exe) {
                        System.out.println("Error: " + exe.getMessage());
                    }
                case 'B':
                    try {
                        productos = pd.listarProductosPrecioBarato();
                        System.out.println("");
                        System.out.println("_____PRODUCTOS POR NOMBRE Y PRECIO__________");
                        ps.consultarObjetosProductos(productos, "nombrePrecio");
                        System.out.println("");
                        break;
                    } catch (Exception exe) {
                        System.out.println("Error: " + exe.getMessage());
                    }
                case 'I':
                    try {
                        Producto producto = ps.crearProducto();
                        pd.guardarProducto(producto);
                        System.out.println("El producto fue agregado con exito");
                        break;
                    } catch (Exception exe) {
                        System.out.println("Error: " + exe.getMessage());
                    }
                case 'Z':
                    try {
//                        Producto producto = ps.crearProducto();
//                        pd.guardarProducto(producto);
//                        System.out.println("El producto fue agregado con exito");
                        break;
                    } catch (Exception exe) {
                        System.out.println("Error: " + exe.getMessage());
                    }
                case 'W':
                    try {
                        productos = pd.listarProductosBD();
                        System.out.println("");
                        System.out.println("_____PRODUCTOS POR NOMBRE Y PRECIO__________");
                        ps.consultarObjetosProductos(productos, "todo");
                        System.out.println("");

                        Scanner leerproducto = new Scanner(System.in);
                        System.out.println("Ingrese el nombre del producto que desea modificar");
                        Producto producto = pd.buscarProductosPorCodigo(leerproducto.nextInt());
                        //Producto productoModificado = ps.modificarObjetoProducto(producto);
                        System.out.println("Ingrese el nombre del nuevo producto");
                        producto.setNombre(leerproducto.next());
                        pd.modificarProducto(producto);
                        productos = pd.listarProductosBD();
                        System.out.println("");
                        System.out.println("_____PRODUCTOS POR NOMBRE Y PRECIO__________");
                        ps.consultarObjetosProductos(productos, "todo");
                        System.out.println("");

                        break;
                    } catch (Exception exe) {
                        System.out.println("Error: " + exe.getMessage());
                    }
                case 'S':
                    break;

            }
        }

    }
}

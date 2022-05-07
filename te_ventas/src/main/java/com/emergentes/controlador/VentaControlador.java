package com.emergentes.controlador;

import com.emergentes.dao.ClienteDAO;
import com.emergentes.dao.ClienteDAOimpl;
import com.emergentes.dao.ProductoDAO;
import com.emergentes.dao.ProductoDAOimpl;
import com.emergentes.dao.VentaDAO;
import com.emergentes.dao.VentaDAOimpl;
import com.emergentes.modelo.Cliente;
import com.emergentes.modelo.Producto;
import com.emergentes.modelo.Venta;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gualbert
 */
@WebServlet(name = "VentaControlador", urlPatterns = {"/VentaControlador"})
public class VentaControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            VentaDAO dao = new VentaDAOimpl();
            ProductoDAO daoProducto = new ProductoDAOimpl();
            ClienteDAO daoCliente = new ClienteDAOimpl();
            int id;
            List<Producto> lista_productos = null;
            List<Cliente> lista_clientes = null;
            Venta v = new Venta();

            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {
                case "add":
                    //lenar la lista de prodcutos
                    lista_productos=daoProducto.getAll();
                    lista_clientes=daoCliente.getAll();
                    request.setAttribute("lista_productos", lista_productos);
                    request.setAttribute("lista_clientes", lista_clientes);
                    
                    request.setAttribute("venta", v);
                    request.getRequestDispatcher("frmVenta.jsp").forward(request, response);
                    break;

                case "edit":
                    //
                    lista_productos=daoProducto.getAll();
                    lista_clientes=daoCliente.getAll();
                    request.setAttribute("lista_productos", lista_productos);
                    request.setAttribute("lista_clientes", lista_clientes);
                    
                    id = Integer.parseInt(request.getParameter("id"));
                    v = dao.getById(id);
                    request.setAttribute("venta", v);
                    request.getRequestDispatcher("frmVenta.jsp").forward(request, response);
                    break;

                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("VentaControlador");
                    break;

                case "view":
                    //obtener la lista de registros(objetos)
                    List<Venta> lista = dao.getAll();
                    request.setAttribute("ventas", lista);
                    request.getRequestDispatcher("ventas.jsp").forward(request, response);
                    break;
            }

        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int cliente_id=Integer.parseInt(request.getParameter("cliente_id"));
        int producto_id=Integer.parseInt(request.getParameter("producto_id"));
        String fecha=request.getParameter("fecha");
       
        Venta venta = new Venta();
        venta.setId(id);
        venta.setCliente_id(cliente_id);
        venta.setProducto_id(producto_id);
        venta.setFecha(convierteFecha(fecha));
        VentaDAO dao = new VentaDAOimpl();
        
        if(id==0){
            try {
                //nuevo registro

                dao.insert(venta);
            } catch (Exception ex) {
                System.out.println("Error al Insertar."+ex.getMessage());            }
        }else{
            try {
                //edicion de registro
                dao.update(venta);
            } catch (Exception ex) {
                System.out.println("Error al EDITAR."+ex.getMessage());            }
        }
        response.sendRedirect("VentaControlador");
        
        
        
    }
    
    public Date convierteFecha(String fecha){
        Date fechaBD=null;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date fechaTMP;
        try {
            fechaTMP = formato.parse(fecha);
            fechaBD = new Date(fechaTMP.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(VentaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return fechaBD;
    }

}


package com.emergentes.dao;

import com.emergentes.modelo.Venta;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gualbert
 */
public class VentaDAOimpl extends ConexionDB implements VentaDAO{

    @Override
    public void insert(Venta venta) throws Exception {
        try {
            String sql = "insert into ventas(producto_id,cliente_id,fecha) values (?,?,?)";
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, venta.getProducto_id());
            ps.setInt(2, venta.getCliente_id());
            ps.setDate(3, venta.getFecha());

            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Venta venta) throws Exception {
        try {
            String sql = "update ventas set producto_id=?,cliente_id=?,fecha=? where id=?";
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, venta.getProducto_id());
            ps.setInt(2, venta.getCliente_id());
            ps.setDate(3, venta.getFecha());
            ps.setInt(4, venta.getId());

            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            String sql = "delete from ventas where id =?";
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Venta getById(int id) throws Exception {
        Venta ven = new Venta();
     
        try {
            String sql = "select * from ventas where id =?";
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ven.setId(rs.getInt("id"));
                ven.setProducto_id(rs.getInt("producto_id"));
                ven.setCliente_id(rs.getInt("cliente_id"));
                ven.setFecha(rs.getDate("fecha"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return ven;
    }

    @Override
    public List<Venta> getAll() throws Exception {
        List<Venta> lista = null;
        try {
            String sql = "SELECT v.*,p.nombre as Producto, c.nombre as Cliente FROM ventas v ";
            sql +="LEFT JOIN productos p ON v.producto_id = p.id ";
            sql +="LEFT JOIN clientes c ON v.cliente_id = c.id ";
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            lista = new ArrayList<Venta>();
            while (rs.next()) {
                Venta ven = new Venta();
                ven.setId(rs.getInt("id"));
                ven.setProducto_id(rs.getInt("producto_id"));
                ven.setCliente_id(rs.getInt("cliente_id"));
                ven.setFecha(rs.getDate("fecha"));
                ven.setCliente(rs.getString("cliente"));
                ven.setProducto(rs.getString("producto"));

                lista.add(ven);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }
    
}

package com.example.demo.repository;

import com.example.demo.modelo.DetalleVenta;

public interface IDetalleVentaRepository {
	
	public void insertar(DetalleVenta detalleVenta);
	
	public DetalleVenta buscar(Integer id);
	
	public void actualizar(DetalleVenta detalleVenta);
	
	public void eliminar(Integer id);

}

package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.modelo.Producto;
import com.example.demo.modelo.ReporteVenta;

public interface IProductoService {

	public void insertar(Producto producto);
	
	public Producto buscarPorCodigoBarras(String codigo);

	public Producto buscarPorCodigoBarrasCriteria(String codigo);
	public Producto buscar(Integer id);
	
	public List<Producto> buscarTodos();
	
	public void actualizar(Producto producto);
	
	public List<ReporteVenta> buscarProducto(LocalDateTime fecha, String categoria, Integer cantidad);
}

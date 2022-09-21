package com.example.demo.service;

import java.util.List;

import com.example.demo.modelo.Producto;

public interface IGestorSupermercadoService  {

	public void realizarVenta(List<Producto> lista,Integer cantidad,String cedula,String numeroVenta);
	
}

package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.modelo.Producto;
import com.example.demo.service.IGestorSupermercadoService;
import com.example.demo.service.IProductoService;

@Service
@RequestMapping("/supermercados/")
public class GestorSuperMercadoController {

	@Autowired
	private IGestorSupermercadoService iGestorSupermercadoServic;
	
	@Autowired
	private IProductoService iProductoService;

	//buscar todos los productos
	@GetMapping("listaProductos")
	public String listaProductos(Producto producto, Model modelo) {
		List<Producto> lista = this.iProductoService.buscarTodos();
		modelo.addAttribute("productos", lista);
		return "vistalistaProductos";
	}
	
	//ingresar productos
	@PostMapping("ingresar")
	public String ingresar(Producto producto) {
		this.iProductoService.insertar(producto);
		return "redirect:/supermercados/listaProductos";
	}
	
	@GetMapping("nuevoProducto")
	public String insertarNuevoProducto(Producto producto) {
		return "vistaNuevoProducto";
	}
	
	//consultar stock
	
	// pagina para ingresar el codigo de barras a buscar
		@GetMapping("buscarProductoPorCodigo")
		public String buscarProductoPorCodigo(Producto producto) {
			return "vistaBuscarProductoPorCodigo";
		}
	@GetMapping("productoEncontrado")
	public String buscarPorCodigo(Producto prod, Model modelo) {
		Producto producto = this.iProductoService.buscarPorCodigoBarrasCriteria(prod.getCodigoBarras());
		modelo.addAttribute("producto",producto);
		return "vistaVisualizarStock";
	}


}

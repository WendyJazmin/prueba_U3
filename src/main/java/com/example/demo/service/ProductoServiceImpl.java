package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.Producto;
import com.example.demo.modelo.ReporteVenta;
import com.example.demo.repository.IProductoRepository;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private IProductoRepository iProductoRepository;

	@Override
	@Transactional(value=TxType.REQUIRED)
	public void insertar(Producto producto) {
		// TODO Auto-generated method stub
		Producto prod = this.iProductoRepository.buscarPorCodigoBarras(producto.getCodigoBarras());
		if(prod == null) {
			this.iProductoRepository.insertar(producto);
			
		}else {
			prod.setStock(prod.getStock()+producto.getStock());
			this.iProductoRepository.actualizar(prod);
		}
	}

	@Override
	public Producto buscarPorCodigoBarras(String codigo) {
		// TODO Auto-generated method stub
		return this.iProductoRepository.buscarPorCodigoBarras(codigo);
	}

	@Override
	public Producto buscar(Integer id) {
		// TODO Auto-generated method stub
		return this.iProductoRepository.buscar(id);
	}

	@Override
	public List<Producto> buscarTodos() {
		// TODO Auto-generated method stub
		return this.iProductoRepository.buscarTodos();
	}

	@Override
	@Transactional(value=TxType.REQUIRED)
	public void actualizar(Producto producto) {
		// TODO Auto-generated method stub
		this.iProductoRepository.actualizar(producto);
	}

	@Override
	
	public List<ReporteVenta> buscarProducto(LocalDateTime fecha, String categoria, Integer cantidad) {
		// TODO Auto-generated method stub
		return this.iProductoRepository.buscarProducto(fecha, categoria, cantidad);
	}

	@Override
	public Producto buscarPorCodigoBarrasCriteria(String codigo) {
		// TODO Auto-generated method stub
		return this.iProductoRepository.buscarPorCodigoBarrasCriteria(codigo);
	}
}

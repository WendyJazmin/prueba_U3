package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.DetalleVenta;
import com.example.demo.modelo.Producto;
import com.example.demo.modelo.Venta;
import com.example.demo.repository.IDetalleVentaRepository;
import com.example.demo.repository.IProductoRepository;
import com.example.demo.repository.IVentaRepository;

@Service
public class GestorSupermercadoServiceImpl implements IGestorSupermercadoService{
	
	@Autowired
	private IProductoRepository IProductoRepository;
	
	@Autowired
	private IVentaRepository iVentaRepository;
	
	@Autowired
	private IDetalleVentaRepository iDetalleVentaRepository;
	
	

	


	@Override
	@Transactional(value=TxType.REQUIRES_NEW)
	public void realizarVenta(List<Producto> lista, Integer cantidad, String cedula, String numeroVenta) {
		// TODO Auto-generated method stub
		for(Producto i : lista) {
			Producto p = this.IProductoRepository.buscarPorCodigoBarras(i.getCodigoBarras());
			if(cantidad<=p.getStock()) {
				Venta v = new Venta();
				v.setCedulaCliente(cedula);
				v.setFecha(LocalDateTime.now());
				v.setNumero(numeroVenta);
				
				
				List<DetalleVenta> listaVenta = new ArrayList();
				DetalleVenta d = new DetalleVenta();
				d.setVenta(v);
				v.setDetallVentasV(listaVenta);
				d.setCantidad(cantidad);
				d.setProducto(i);
				d.setPrecioUnitario(i.getPrecio());
				d.setSubtotal(p.getPrecio().add(new BigDecimal(i.getStock())));
				listaVenta.add(d);
				
				v.setDetallVentasV(listaVenta);
				this.iVentaRepository.insertar(v);
				this.iDetalleVentaRepository.insertar(d);
			}else {
				throw new RuntimeException();
			}
		}
	}

}

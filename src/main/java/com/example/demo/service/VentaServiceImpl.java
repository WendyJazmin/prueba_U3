package com.example.demo.service;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.Venta;
import com.example.demo.repository.IVentaRepository;

@Service
public class VentaServiceImpl implements IVentaService {
	@Autowired
	private IVentaRepository iVentaRepository;

	@Override
	@Transactional(value=TxType.REQUIRED)
	public void insertar(Venta venta) {
		// TODO Auto-generated method stub
		this.iVentaRepository.insertar(venta);
	}

}

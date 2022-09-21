package com.example.demo.modelo;

public class ProductoDTO {

	private String codigoBarras;
	
	private String nombre;
	
	private Integer stock;
	
	public ProductoDTO() {}

	public ProductoDTO(String codigoBarras, String nombre, Integer stock) {
		
		this.codigoBarras = codigoBarras;
		this.nombre = nombre;
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "ProductoDTO [codigoBarras=" + codigoBarras + ", nombre=" + nombre + ", stock=" + stock + "]";
	}

	
	//GET Y SET
	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	
}

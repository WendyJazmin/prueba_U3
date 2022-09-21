package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Producto;
import com.example.demo.modelo.ReporteVenta;

@Repository
@Transactional
public class ProductoRepositoryImpl implements IProductoRepository{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(value=TxType.MANDATORY)
	public void insertar(Producto producto) {
		// TODO Auto-generated method stub
		this.entityManager.persist(producto);
	}

	@Override
	@Transactional(value=TxType.NOT_SUPPORTED)
	public Producto buscarPorCodigoBarras(String codigo) {
		// TODO Auto-generated method stub
		TypedQuery<Producto> myQuery = this.entityManager.createQuery("SELECT p FROM Producto p WHERE p.codigoBarras =:datoCodigo",Producto.class);
		myQuery.setParameter("datoCodigo", codigo);
		
		try {
			return myQuery.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
		
	}

	@Override
	@Transactional(value=TxType.NOT_SUPPORTED)	
	public Producto buscar(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Producto.class, id);
	}

	@Override
	@Transactional(value=TxType.NOT_SUPPORTED)
	public List<Producto> buscarTodos() {
		// TODO Auto-generated method stub
		TypedQuery<Producto> myQuery = this.entityManager.createQuery("SELECT p FROM Producto p", Producto.class);
		
		return myQuery.getResultList();
	}

	@Override
	@Transactional(value=TxType.MANDATORY)
	public void actualizar(Producto producto) {
		// TODO Auto-generated method stub
		this.entityManager.merge(producto);
	}

	@Override
	@Transactional(value=TxType.NOT_SUPPORTED)
	public Producto buscarPorCodigoBarrasCriteria(String codigo) {
		// TODO Auto-generated method stub
		CriteriaBuilder myBuilder = this.entityManager.getCriteriaBuilder();//-
		
		//se espcecifca el retorno del sql
		CriteriaQuery<Producto> myQuery = myBuilder.createQuery(Producto.class);//-
		
		//se empieza a construir el sql
		//SELECT p FROM Producto p WHERE p.codigoBarras =: datoCodigo
		//Root FROM
		Root<Producto> productoFrom = myQuery.from(Producto.class);//-
		
		//las condiciones WHERE en CRITERIA API se los conoce como preidcados
		Predicate p = myBuilder.equal(productoFrom.get("codigoBarras"), codigo);//p.codigoBarras =: datoCodigo//-
		
		CriteriaQuery<Producto> myQueryCompleto=myQuery.select(productoFrom).where(p);//-
		TypedQuery<Producto> myQueryFinal = this.entityManager.createQuery(myQueryCompleto);
		
		try {
			return myQueryFinal.getSingleResult();//
		}catch(NoResultException e){
			return null;
			
		}
		
	}

	@Override
	@Transactional(value=TxType.NOT_SUPPORTED)
	public List<ReporteVenta> buscarProducto(LocalDateTime fecha, String categoria, Integer cantidad) {
		// TODO Auto-generated method stub
		TypedQuery<ReporteVenta> myQuery = this.entityManager.createQuery(""
				+ "SELECT NEW com.example.demo.modelo.ReporteVenta(p.codigoBarras,p.nombre,d.cantidad,d.precioUnitario,d.subtotal) "
				+ "FROM Producto p JOIN p.detalleVentasP d JOIN d.venta v "
				+ "WHERE v.fecha =: datoFecha AND p.categoria =: datoCategoria AND d.cantidad > :datoCantidad",ReporteVenta.class);
		myQuery.setParameter("datoFecha", fecha);
		myQuery.setParameter("datoCategoria", categoria);
		myQuery.setParameter("datoCantidad", cantidad);
		return myQuery.getResultList();
	}
}

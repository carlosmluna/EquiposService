package com.mx.lbg.bimbo.equiposservice.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.mx.lbg.bimbo.equiposservice.model.EquipoEntity;
import com.mx.lbg.bimbo.equiposservice.model.dto.EquipoBusquedaDTO;

@Component
public class EquipoRepositoryImpl {
	
	@PersistenceContext
	private EntityManager entityManager;

	
	public List<EquipoEntity> findEquipoPorDTO(EquipoBusquedaDTO equipoBuscar) throws SQLException {
		CriteriaBuilder critBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<EquipoEntity> query = critBuilder.createQuery(EquipoEntity.class);
		Root<EquipoEntity> root = query.from(EquipoEntity.class);

		List<Predicate> predicates = new ArrayList<>();
		Predicate preEstatus 	 	= null;
		Predicate predRegion 	 	= null;
		Predicate preLocalidad 	 	= null;
		Predicate predbodega 	 	= null;
		Predicate preRazon_social   = null;
		Predicate preModelo_nuevo 	= null;
		Predicate preAccion 	 	= null;
		Predicate predProgramacion 	= null;
		Predicate predProveedor 	= null;
		
		// Preparamos los criterios de busqueda
		if (equipoBuscar.getEstatus()!=null && !equipoBuscar.getEstatus().isEmpty()) {			
			preEstatus = critBuilder.equal(root.get("estatus"), (String) equipoBuscar.getEstatus());
			predicates.add(preEstatus);			
		}
		if (equipoBuscar.getRegion()!=null && !equipoBuscar.getRegion().isEmpty()) {			
			predRegion = critBuilder.equal(root.get("region"), (String) equipoBuscar.getRegion());
			predicates.add(predRegion);			
		}
		if (equipoBuscar.getLocalidad()!=null && !equipoBuscar.getLocalidad().isEmpty()) {			
			preLocalidad = critBuilder.equal(root.get("localidad"), (String) equipoBuscar.getLocalidad());
			predicates.add(preLocalidad);			
		}
		if (equipoBuscar.getBodega()!=null && !equipoBuscar.getBodega().isEmpty()) {			
			predbodega = critBuilder.equal(root.get("bodega"), (String) equipoBuscar.getBodega());
			predicates.add(predbodega);			
		}
		if (equipoBuscar.getRazon_social()!=null && !equipoBuscar.getRazon_social().isEmpty()) {			
			preRazon_social = critBuilder.equal(root.get("bodega"), (String) equipoBuscar.getRazon_social());
			predicates.add(preRazon_social);			
		}
		if (equipoBuscar.getModelo_uevo()!=null && !equipoBuscar.getModelo_uevo().isEmpty()) {			
			preModelo_nuevo = critBuilder.equal(root.get("bodega"), (String) equipoBuscar.getModelo_uevo());
			predicates.add(preModelo_nuevo);			
		}
		if (equipoBuscar.getAccion()!=null && !equipoBuscar.getAccion().isEmpty()) {			
			preAccion = critBuilder.equal(root.get("accion"), (String) equipoBuscar.getAccion());
			predicates.add(preAccion);			
		}
		if (equipoBuscar.getProgramacion()!=null && !equipoBuscar.getProgramacion().isEmpty()) {			
			predProgramacion = critBuilder.equal(root.get("accion"), (String) equipoBuscar.getProgramacion());
			predicates.add(predProgramacion);			
		}
		if (equipoBuscar.getProveedor()!=null && !equipoBuscar.getProveedor().isEmpty()) {			
			predProveedor = critBuilder.equal(root.get("accion"), (String) equipoBuscar.getProgramacion());
			predicates.add(predProveedor);			
		}

		query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));

		return entityManager.createQuery(query).getResultList();
	}
}

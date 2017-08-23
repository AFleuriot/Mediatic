package fr.dta.configuration;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import fr.dta.configuration.IoEntity;
import fr.dta.databasehelper.JsonHelper;

public abstract class AbstractJpaRepository<T extends IoEntity> {

	protected Class<T> entityClass;

	@PersistenceContext
	EntityManager em;
	@Autowired
	protected JsonHelper jsonHelper;

	@PostConstruct
	public void init() {
		entityClass = getEntityClass();
	}

	protected abstract Class<T> getEntityClass();

	protected Session getSession() {
		return em.unwrap(Session.class);
	}
// Les méthodes Transactional sont rollback à la fin de chaque test! @Rollback(false)
	@Transactional
	public T save(T entity) {
		if (isNew(entity)) {
			em.persist(entity);
			return entity;
		} else if (!em.contains(entity)) {
			return em.merge(entity);
		}

		return entity;
	}
	
	@Transactional
	public T update(T entity) {
			return em.merge(entity);
	}

	@Transactional
	public T findOne(Integer id) {
		return em.find(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<T> findAll() {
		return getSession().createCriteria(entityClass).list();
	}

	@Transactional
	public void delete(T entity) {
		if (!getSession().contains(entity)) {
			em.remove(getSession().merge(entity));
		} else {
			em.remove(entity);
		}

	}

	public boolean isNew(T entity) {
		return entity.getId() == null;
	}

}

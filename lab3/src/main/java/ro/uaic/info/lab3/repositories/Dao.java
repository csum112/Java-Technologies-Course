package ro.uaic.info.lab3.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public abstract class Dao<E> {
    @PersistenceContext
    protected EntityManager em;

    private final Class<E> type;

    /**
     * Resolve generic type
     * https://stackoverflow.com/questions/18707582/get-actual-type-of-generic-type-argument-on-abstract-superclass
     */
    @SuppressWarnings("unchecked")
    public Dao() {
        //
        Type type = getClass().getGenericSuperclass();
        while (!(type instanceof ParameterizedType) || ((ParameterizedType) type).getRawType() != Dao.class) {
            if (type instanceof ParameterizedType) {
                type = ((Class<?>) ((ParameterizedType) type).getRawType()).getGenericSuperclass();
            } else {
                type = ((Class<?>) type).getGenericSuperclass();
            }
        }
        this.type = (Class<E>) ((ParameterizedType) type).getActualTypeArguments()[0];
    }

    public void save(E e) {
        em.persist(e);
    }

    public List<E> findAll() {
        String ALL_QUERY = "SELECT e FROM %s e";
        return em.createQuery(String.format(ALL_QUERY, type.getSimpleName()), type).getResultList();
    }

    public Optional<E> findById(Object id) {
        String ID_QUERY = "SELECT e FROM %s e WHERE e.id = :key";
        return em.createQuery(String.format(ID_QUERY, type.getSimpleName()), type)
                .setParameter("key", id)
                .getResultStream()
                .map(obj -> (E) obj)
                .findFirst();
    }
}

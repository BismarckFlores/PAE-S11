package ni.edu.uam.practicaorm.repository;

import ni.edu.uam.practicaorm.config.HibernateUtil;
import ni.edu.uam.practicaorm.model.Estudiante;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

public class EstudianteRepository {

    public void guardar(Estudiante estudiante) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(estudiante);
            tx.commit();
        } catch (ConstraintViolationException e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw new CorreoDuplicadoException(estudiante.getCorreo(), e);
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw e;
        }
    }

    public List<Estudiante> listar() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Estudiante", Estudiante.class)
                    .getResultList();
        }
    }

    public List<Estudiante> buscarPorCarrera(String carrera) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "FROM Estudiante e WHERE e.carrera = :carrera", Estudiante.class)
                    .setParameter("carrera", carrera)
                    .getResultList();
        }
    }
}
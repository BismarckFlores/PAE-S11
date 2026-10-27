package ni.edu.uam.practicaorm.config;

import io.github.cdimascio.dotenv.Dotenv;
import ni.edu.uam.practicaorm.model.Estudiante;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public final class HibernateUtil {

    private static final SessionFactory FACTORY = crear();

    private HibernateUtil() {}

    private static SessionFactory crear() {
        try (var in = HibernateUtil.class.getClassLoader()
                .getResourceAsStream("hibernate.properties")) {
            if (in == null) throw new IllegalStateException("Falta hibernate.properties");

            Properties p = new Properties();
            p.load(in);

            Dotenv env = Dotenv.configure().ignoreIfMissing().load();
            p.setProperty("hibernate.connection.url", "jdbc:postgresql://"
                    + env.get("DB_HOST", "localhost") + ":"
                    + env.get("DB_PORT", "5432") + "/"
                    + env.get("DB_NAME", "gestionacademica"));
            p.setProperty("hibernate.connection.username", env.get("DB_USER"));
            p.setProperty("hibernate.connection.password", env.get("DB_PASSWORD"));

            return new Configuration().addProperties(p)
                    .addAnnotatedClass(Estudiante.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() { return FACTORY; }
    }

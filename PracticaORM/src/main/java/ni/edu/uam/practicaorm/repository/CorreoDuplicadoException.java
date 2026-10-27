package ni.edu.uam.practicaorm.repository;

public class CorreoDuplicadoException extends RuntimeException {
    public CorreoDuplicadoException(String correo, Throwable causa) {
        super("Ya existe un estudiante registrado con el correo " + correo, causa);
    }
}

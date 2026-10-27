package ni.edu.uam.practicaorm.model;

import java.util.regex.Pattern;

public final class EstudianteValidator {

    private static final Pattern CORREO = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    private static final Pattern TELEFONO = Pattern.compile("^\\d{1,20}$");

    private EstudianteValidator() {}

    public static Estudiante crear(String nombres, String apellidos, String correo,
                                   String carrera, String telefono) {
        String n = obligatorio(nombres, "Los nombres", 100);
        String a = obligatorio(apellidos, "Los apellidos", 100);
        String c = obligatorio(correo, "El correo electrónico", 150);
        String ca = obligatorio(carrera, "La carrera", 100);

        if (!CORREO.matcher(c).matches()) {
            throw new IllegalArgumentException("El correo electrónico no tiene un formato válido.");
        }

        String t = telefono == null ? "" : telefono.trim();
        if (!t.isEmpty() && !TELEFONO.matcher(t).matches()) {
            throw new IllegalArgumentException("El teléfono debe contener solo dígitos (máximo 20).");
        }

        Estudiante estudiante = new Estudiante(n, a, c.toLowerCase(), ca);
        estudiante.setTelefono(t.isEmpty() ? null : t);
        return estudiante;
    }

    private static String obligatorio(String valor, String campo, int max) {
        String v = valor == null ? "" : valor.trim();
        if (v.isEmpty()) {
            throw new IllegalArgumentException(campo + " es obligatorio.");
        }
        if (v.length() > max) {
            throw new IllegalArgumentException(campo + " no puede superar " + max + " caracteres.");
        }
        return v;
    }
}

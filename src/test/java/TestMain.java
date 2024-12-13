
import com.iessanalberto.dam1.models.Usuario;
import com.iessanalberto.dam1.models.UsuarioDAO;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


import java.sql.*;

import java.util.List;



public class TestMain {



        private UsuarioDAO usuarioDAO;

        @BeforeEach
        public void setUp () throws SQLException {
            usuarioDAO = new UsuarioDAO();
            usuarioDAO.createTable();  // Crear la tabla antes de cada prueba.
        }

        @Test
        public void testInsertar () throws SQLException {
            Usuario usuario = new Usuario(0, "Juan Perez", "juan@example.com", null);
            usuarioDAO.insertar(usuario);

            List<Usuario> usuarios = usuarioDAO.obtenerTodos();
            assertEquals(1, usuarios.size(), "Debe haber un usuario insertado");
        }

        @Test
        public void testObtenerTodos () throws SQLException {
            Usuario usuario1 = new Usuario(0, "Juan Perez", "juan@example.com", null);
            Usuario usuario2 = new Usuario(0, "Ana Garcia", "ana@example.com", null);
            usuarioDAO.insertar(usuario1);
            usuarioDAO.insertar(usuario2);

            List<Usuario> usuarios = usuarioDAO.obtenerTodos();
            assertEquals(2, usuarios.size(), "Debe haber dos usuarios");
        }
        @Test
        public void testActualizar () throws SQLException {
            // Insertamos un usuario inicial
            Usuario usuario = new Usuario(0, "Juan Perez", "juan@example.com", null);
            usuarioDAO.insertar(usuario);

            // Obtenemos el usuario insertado
            List<Usuario> usuariosAntes = usuarioDAO.obtenerTodos();
            Usuario usuarioOriginal = usuariosAntes.getFirst();

            // Creamos un nuevo usuario con datos actualizados
            usuarioOriginal.setNombre("Juan Actualizado");
            usuarioOriginal.setEmail("juan.actualizado@example.com");

            // Actualizamos el usuario
            usuarioDAO.actualizar(usuarioOriginal);

            // Verificamos que el usuario haya sido actualizado
            List<Usuario> usuariosDespues = usuarioDAO.obtenerTodos();
            Usuario usuarioActualizado = usuariosDespues.getFirst();

            assertEquals("Juan Actualizado", usuarioActualizado.getNombre(), "El nombre del usuario no se actualizó");
            assertEquals("juan.actualizado@example.com", usuarioActualizado.getEmail(), "El email del usuario no se actualizó");
        }
        @Test
        public void testEliminar () throws SQLException {
            Usuario usuario = new Usuario(0, "Juan Perez", "juan@example.com", null);
            usuarioDAO.insertar(usuario);

            List<Usuario> usuariosAntes = usuarioDAO.obtenerTodos();
            assertEquals(1, usuariosAntes.size(), "Debe haber un usuario antes de eliminar");

            usuarioDAO.eliminar(usuariosAntes.get(0).getId());

            List<Usuario> usuariosDespues = usuarioDAO.obtenerTodos();
            assertEquals(0, usuariosDespues.size(), "La tabla debe estar vacía después de eliminar");
        }

        @AfterEach
        public void limpiar () throws SQLException {
            usuarioDAO.limpiarTabla();  // Limpiar la tabla después de cada prueba
        }
    }

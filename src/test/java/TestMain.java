
import com.iessanalberto.dam1.models.Usuario;
import com.iessanalberto.dam1.models.UsuarioDAO;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class TestMain {
  /*  private static Connection connection;
    private UsuarioDAO usuarioDAO;
    private static Savepoint savepoint;



    @BeforeEach
    public void setUp() throws SQLException {


connection = UsuarioDAO.getConnection();
        connection.setAutoCommit(false);
        usuarioDAO = new UsuarioDAO();
        savepoint = connection.setSavepoint();
    }
    @AfterEach
            public void tearDown() throws SQLException {
        connection.rollback(savepoint);

        // Cerrar la conexión
        connection.close();
    }
   @Test
    @Order(1)
    public void testCrearUsuario() {
        boolean resultado = usuarioDAO.crearUsuario("Juan Perez", "juan.perez@example.com");
        assertTrue(resultado);
    }

    @Test
    @Order(2)
    public void testObtenerUsuario() {
        // Asumimos que el id 1 existe
        Usuario usuario = usuarioDAO.obtenerUsuario(1);
        assertNotNull(usuario);
        assertEquals(1, usuario.getId());
    }

    @Test
    @Order(3)
    public void testActualizarUsuario() {
        // Asumimos que el id 1 existe
        boolean resultado = usuarioDAO.actualizarUsuario(1, "Juan Perez Actualizado", "juan.perez.actualizado@example.com");
        assertTrue(resultado);

        Usuario usuarioActualizado = usuarioDAO.obtenerUsuario(1);
        assertEquals("Juan Perez Actualizado", usuarioActualizado.getNombre());
        assertEquals("juan.perez.actualizado@example.com", usuarioActualizado.getEmail());
    }

    @Test
    @Order(4)
    public void testEliminarUsuario() {

        // Ahora, elimina el usuario recién creado
        boolean resultado = usuarioDAO.eliminarUsuario(1);
        assertTrue(resultado);


    }*/


          private UsuarioDAO usuarioDAO;

          @BeforeEach
          public void setUp() throws SQLException {
              usuarioDAO = new UsuarioDAO();
              usuarioDAO.createTable();  // Crear la tabla antes de cada prueba
          }

          @Test
          public void testInsertar() throws SQLException {
              Usuario usuario = new Usuario(0, "Juan Perez", "juan@example.com", null);
              usuarioDAO.insertar(usuario);

              List<Usuario> usuarios = usuarioDAO.obtenerTodos();
              assertEquals(1, usuarios.size(), "Debe haber un usuario insertado");
          }

          @Test
          public void testObtenerTodos() throws SQLException {
              Usuario usuario1 = new Usuario(0, "Juan Perez", "juan@example.com", null);
              Usuario usuario2 = new Usuario(0, "Ana Garcia", "ana@example.com", null);
              usuarioDAO.insertar(usuario1);
              usuarioDAO.insertar(usuario2);

              List<Usuario> usuarios = usuarioDAO.obtenerTodos();
              assertEquals(2, usuarios.size(), "Debe haber dos usuarios");
          }
    @Test
    public void testActualizar() throws SQLException {
        // Insertamos un usuario inicial
        Usuario usuario = new Usuario(0, "Juan Perez", "juan@example.com", null);
        usuarioDAO.insertar(usuario);

        // Obtenemos el usuario insertado
        List<Usuario> usuariosAntes = usuarioDAO.obtenerTodos();
        Usuario usuarioOriginal = usuariosAntes.get(0);

        // Creamos un nuevo usuario con datos actualizados
        usuarioOriginal.setNombre("Juan Actualizado");
        usuarioOriginal.setEmail("juan.actualizado@example.com");

        // Actualizamos el usuario
        usuarioDAO.actualizar(usuarioOriginal);

        // Verificamos que el usuario haya sido actualizado
        List<Usuario> usuariosDespues = usuarioDAO.obtenerTodos();
        Usuario usuarioActualizado = usuariosDespues.get(0);

        assertEquals("Juan Actualizado", usuarioActualizado.getNombre(), "El nombre del usuario no se actualizó");
        assertEquals("juan.actualizado@example.com", usuarioActualizado.getEmail(), "El email del usuario no se actualizó");
    }
          @Test
          public void testEliminar() throws SQLException {
              Usuario usuario = new Usuario(0, "Juan Perez", "juan@example.com", null);
              usuarioDAO.insertar(usuario);

              List<Usuario> usuariosAntes = usuarioDAO.obtenerTodos();
              assertEquals(1, usuariosAntes.size(), "Debe haber un usuario antes de eliminar");

              usuarioDAO.eliminar(usuariosAntes.get(0).getId());

              List<Usuario> usuariosDespues = usuarioDAO.obtenerTodos();
              assertEquals(0, usuariosDespues.size(), "La tabla debe estar vacía después de eliminar");
          }

          @AfterEach
          public void limpiar() throws SQLException {
              usuarioDAO.limpiarTabla();  // Limpiar la tabla después de cada prueba
          }
}
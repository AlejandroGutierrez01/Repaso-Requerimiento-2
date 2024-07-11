import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;

public class Form1 {
    private JTextField dirtxt;
    private JTextField correotxt;
    private JTextField edadtxt;
    private JTextField nota2txt;
    private JTextField notatxt;
    private JTextField nombretxt;
    private JTextField cedulatxt;
    private JButton enviarButton;
    public JPanel mainPanel;

    public Form1() {
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3307/estudiantes2024a"; //el puerto 3306 ya estaba usado en mi caso
                String user = "root";
                String password = "123456";

                String cedula = cedulatxt.getText();
                String nombre = nombretxt.getText();
                Float b1 = parseFloat(notatxt.getText());
                Float b2 = parseFloat(nota2txt.getText());
                String edad = edadtxt.getText();
                String correo = correotxt.getText();
                String direccion = dirtxt.getText();

                Estudiantes estudiantes= new Estudiantes(cedula, nombre, b1, b2, edad, correo, direccion);
                String sql = "INSERT INTO estudiantes (cedula, nombre, b1, b2, edad, correo, direccion) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try(Connection connection= DriverManager.getConnection(url,user,password)) {
                    System.out.println("Conexion exitosa");

                    PreparedStatement cadenaPreparada = connection.prepareStatement(sql);
                    cadenaPreparada = connection.prepareStatement(sql);
                    cadenaPreparada.setString(1, estudiantes.getCedula());
                    cadenaPreparada.setString(2, estudiantes.getNombre());
                    cadenaPreparada.setFloat(3, estudiantes.getB1());
                    cadenaPreparada.setFloat(4, estudiantes.getB2());
                    cadenaPreparada.setString(5, estudiantes.getEdad());
                    cadenaPreparada.setString(6, estudiantes.getCorreo());
                    cadenaPreparada.setString(7, estudiantes.getDireccion());

                    cadenaPreparada.executeUpdate();


                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}

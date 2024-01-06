package com.example.tes;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ResourceBundle;


public class ControlerStart implements Initializable {

    public Button editmenu;
    public Label typelaundrypop;
    public Button tombolbackinput;
    @FXML
    private ChoiceBox<String> pilihantype;

    private Connection connection;
    private PaketHargaDAO paketHargaDAO;

    private VBox vboxHarga;

    public ControlerStart() {
        connection = DatabaseConnection.getConnection();
        paketHargaDAO = new PaketHargaDAO();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (pilihantype != null) {
            pilihantype.setItems(FXCollections.observableArrayList("Santai", "Jogging", "Sprint", "Reguler", "Express", "Kilat"));
            pilihantype.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    typelaundrypop.setText(newValue);
                    // Memanggil metode untuk menampilkan data paket harga
                    tampilkanDataPaketHarga(newValue);
                }
            });
        }

    }


    @FXML
    private void TombolStart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void TombolMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DaftarHarga.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Tombolback(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Tombolinput(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InputMenu.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void backinput(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Tombolcekpesanan(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CekPesanan.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    private void saveinput(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("popuptersimpan.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    private void Okkemenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    private void tampilkanDataPaketHarga(String typeLaundry) {

        ResultSet resultSet = paketHargaDAO.ambilSemuaPaketHarga();


        vboxHarga.getChildren().clear();


        try {
            while (resultSet.next()) {
                String nama = resultSet.getString("nama");
                int harga = resultSet.getInt("harga");
                String deskripsi = resultSet.getString("deskripsi");


                Label label = new Label(nama + ": Rp " + harga + " - " + deskripsi);
                vboxHarga.getChildren().add(label);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
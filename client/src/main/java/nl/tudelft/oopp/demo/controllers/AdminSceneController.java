package nl.tudelft.oopp.demo.controllers;

<<<<<<< HEAD
=======
import static nl.tudelft.oopp.demo.controllers.MainSceneController.buildingTP;
import static nl.tudelft.oopp.demo.controllers.MainSceneController.restaurantsTP;
import static nl.tudelft.oopp.demo.controllers.MainSceneController.roomsTP;

import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
>>>>>>> development
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.converter.IntegerStringConverter;
import nl.tudelft.oopp.demo.communication.BuildingCommunication;
import nl.tudelft.oopp.demo.communication.OpenTimeCommunication;
import nl.tudelft.oopp.demo.communication.RestaurantCommunication;
import nl.tudelft.oopp.demo.communication.RoomCommunication;
import nl.tudelft.oopp.demo.helperclasses.*;

import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static nl.tudelft.oopp.demo.controllers.MainSceneController.*;

public class AdminSceneController implements Initializable {

    static final Button updateButtonBuilding = new Button("Update");
    static final Button deleteButtonBuilding = new Button("Delete");
    static final Button updateTimeBuilding = new Button("Update");
    static final Button deleteTimeBuilding = new Button("Delete");
    static final Button updateButtonRoom = new Button("Update");
    static final Button deleteButtonRoom = new Button("Delete");
    static final Button updateButtonRestaurant = new Button("Update");
    static final Button deleteButtonRestaurant = new Button("Delete");
    public static final TableView<Building> tableBuilding = new TableView<>();
    public static final TableView<OpenTime> tableBuildingTime = new TableView<>();
    public static final TableView<Room> tableRoom = new TableView<>();
    public static final TableView<Restaurant> tableRestaurant = new TableView<>();

    /**
     * The method below is implemented for the update button under the building section in the admin scene.
     * When the user double clicks on a specific section of a row one will be able to change the details, and
     * after changing the details one will have to press on the update button to update the database.
     */
    public static void updateBuildingButtonClicked() {
        Building building = tableBuilding.getSelectionModel().getSelectedItem();
        BuildingCommunication.updateBuilding(building.getId(), building.getName(), building.getOpenTime(), building.getCloseTime(), building.getStreetName(), building.getStreetNumber(),
            building.getZipCode(), building.getCity());
    }

    /**
     * The method below is implemented for the delete button under the building section in the admin scene.
     * When the user selects a row in the building table it will be deleted from the database.
     */
    public static void deleteBuildingButtonClicked() {
        ObservableList<Building> allBuildings;
        allBuildings = tableBuilding.getItems();
        Building building = tableBuilding.getSelectionModel().getSelectedItem();

        allBuildings.remove(building);
        BuildingCommunication.removeBuilding(building.getId());
    }

    /**
     * The method below is implemented for the update button under the rooms section in the admin scene.
     * When the user double clicks on a specific section of a row one will be able to change the details, and
     * after changing the details one will have to press on the update button to update the database.
     */
    public static void updateRoomButtonClicked() {
        Room room = tableRoom.getSelectionModel().getSelectedItem();
        RoomCommunication.updateRoom(room.getId(), room.getName(), room.getCapacity(), room.getBuilding().getId());
    }

    /**
     * The method below is implemented for the delete button under the rooms section in the admin scene.
     * When the user selects a row in the rooms table it will be deleted from the database.
     */
    public static void deleteRoomButtonClicked() {
        ObservableList<Room> allRooms;
        allRooms = tableRoom.getItems();
        Room room = tableRoom.getSelectionModel().getSelectedItem();

        allRooms.remove(room);
        RoomCommunication.removeRoom(room.getId());
    }

    /**
     * The method below is implemented for the update button under the time section in the admin scene.
     * When the user double clicks on a specific section of a row one will be able to change the details, and
     * after changing the details one will have to press on the update button to update the database.
     */
    public static void updateTimeButtonClicked() {
        OpenTime openTime = tableBuildingTime.getSelectionModel().getSelectedItem();
        OpenTimeCommunication.updateOpenTime(openTime.getId(), openTime.getDay(), openTime.getOpenTime(), openTime.getCloseTime(), openTime.getBuilding().getId());
    }

    /**
     * The method below is implemented for the delete button under the time section in the admin scene.
     * When the user selects a row in the rooms table it will be deleted from the database.
     */
    public static void deleteTimeButtonClicked() {
        ObservableList<OpenTime> allTimes;
        allTimes = tableBuildingTime.getItems();
        OpenTime openTime = tableBuildingTime.getSelectionModel().getSelectedItem();

        allTimes.remove(openTime);
        OpenTimeCommunication.removeOpenTime(openTime.getId());
    }

    /**
     * The method below is implemented for the update button under the restaurant section in the admin scene.
     * When the user double clicks on a specific section of a row one will be able to change the details, and
     * after changing the details one will have to press on the update button to update the database.
     */
    public static void updateButtonRestaurantClicked() {
        Restaurant restaurant = tableRestaurant.getSelectionModel().getSelectedItem();
        RestaurantCommunication.updateRestaurant(restaurant.getId(), restaurant.getName(), restaurant.getBuilding().getId(), restaurant.getTimeClose(), restaurant.getTimeOpen());
    }

    /**
     * The method below is implemented for the delete button under the restaurant section in the admin scene.
     * When the user selects a row in the rooms table it will be deleted from the database.
     */
    public static void deleteButtonRestaurantClicked() {
        ObservableList<Restaurant> allRestaurants;
        allRestaurants = tableRestaurant.getItems();
        Restaurant restaurant = tableRestaurant.getSelectionModel().getSelectedItem();

        allRestaurants.remove(restaurant);
        RestaurantCommunication.removeRestaurant(restaurant.getId());
    }

    /**
     * The method below is implemented for the building table which is loaded on the main accordion element in
     * the admin scene.
     */
    public static void BuildingView() {
        //clearing any previous tableview to avoid multiplication
        tableBuilding.getColumns().clear();

        // Table for buildings
        tableBuilding.setEditable(true);

        TableColumn<Building, Long> idCol =
            new TableColumn<>("id");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(
            new PropertyValueFactory<>("id"));

        TableColumn<Building, String> buildingCol =
            new TableColumn<>("Building Name");
        buildingCol.setMinWidth(100);
        buildingCol.setCellValueFactory(
            new PropertyValueFactory<>("name"));
        buildingCol.setCellFactory(TextFieldTableCell.forTableColumn());
<<<<<<< HEAD
        buildingCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Building, String> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setName(t.getNewValue()));
=======
        buildingCol.setOnEditCommit((TableColumn.CellEditEvent<Building, String> t) ->
            t.getTableView().getItems().get(t.getTablePosition().getRow()).setName(t.getNewValue()));

        TableColumn<Building, LocalTime> openTimeCol =
            new TableColumn<>("Open Time");
        openTimeCol.setMinWidth(100);
        openTimeCol.setCellValueFactory(
            new PropertyValueFactory<>("openTime"));
        openTimeCol.setCellFactory(TextFieldTableCell.forTableColumn(new TimeToStringConvertor()));
        openTimeCol.setOnEditCommit((TableColumn.CellEditEvent<Building, LocalTime> t) ->
            t.getTableView().getItems().get(t.getTablePosition().getRow()).setOpenTime(t.getNewValue()));

        TableColumn<Building, LocalTime> closeTimeCol =
            new TableColumn<>("Close Time");
        closeTimeCol.setMinWidth(100);
        closeTimeCol.setCellValueFactory(
            new PropertyValueFactory<>("closeTime"));
        closeTimeCol.setCellFactory((TextFieldTableCell.forTableColumn(new TimeToStringConvertor())));
        closeTimeCol.setOnEditCommit((TableColumn.CellEditEvent<Building, LocalTime> t) ->
            t.getTableView().getItems().get(t.getTablePosition().getRow()).setCloseTime(t.getNewValue()));
>>>>>>> development

        TableColumn<Building, String> streetNameCol =
            new TableColumn<>("Street Name");
        streetNameCol.setMinWidth(100);
        streetNameCol.setCellValueFactory(
            new PropertyValueFactory<>("streetName"));
        streetNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
<<<<<<< HEAD
        streetNameCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Building, String> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setStreetName(t.getNewValue()));
=======
        streetNameCol.setOnEditCommit((TableColumn.CellEditEvent<Building, String> t) ->
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setStreetName(t.getNewValue()));
>>>>>>> development

        TableColumn<Building, String> streetNumCol =
            new TableColumn<>("Street Number");
        streetNumCol.setMinWidth(100);
        streetNumCol.setCellValueFactory(
            new PropertyValueFactory<>("streetNumber"));
        streetNumCol.setCellFactory(TextFieldTableCell.forTableColumn());
<<<<<<< HEAD
        streetNumCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Building, String> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setStreetNumber(t.getNewValue()));
=======
        streetNumCol.setOnEditCommit((TableColumn.CellEditEvent<Building, String> t) ->
            t.getTableView().getItems().get(t.getTablePosition().getRow()).setStreetNumber(t.getNewValue()));
>>>>>>> development

        TableColumn<Building, String> zipCodeCol =
            new TableColumn<>("Zip Code");
        zipCodeCol.setMinWidth(100);
        zipCodeCol.setCellValueFactory(
            new PropertyValueFactory<>("zipCode"));
        zipCodeCol.setCellFactory(TextFieldTableCell.forTableColumn());
<<<<<<< HEAD
        zipCodeCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Building, String> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setZipCode(t.getNewValue()));
=======
        zipCodeCol.setOnEditCommit((TableColumn.CellEditEvent<Building, String> t) ->
            t.getTableView().getItems().get(t.getTablePosition().getRow()).setZipCode(t.getNewValue()));
>>>>>>> development

        TableColumn<Building, String> cityCol =
            new TableColumn<>("City");
        cityCol.setMinWidth(100);
        cityCol.setCellValueFactory(
            new PropertyValueFactory<>("City"));
        cityCol.setCellFactory(TextFieldTableCell.forTableColumn());
<<<<<<< HEAD
        cityCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Building, String> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setCity(t.getNewValue()));
=======
        cityCol.setOnEditCommit((TableColumn.CellEditEvent<Building, String> t) ->
            t.getTableView().getItems().get(t.getTablePosition().getRow()).setCity(t.getNewValue()));
>>>>>>> development

        ObservableList<Building> buildingData = FXCollections.observableList(BuildingCommunication.getBuildings());
        tableBuilding.setItems(buildingData);
        tableBuilding.getColumns().addAll(idCol, buildingCol, openTimeCol, closeTimeCol, streetNameCol, streetNumCol, zipCodeCol, cityCol);

        //delete button
        deleteButtonBuilding.setOnAction(e -> {
            try {
                deleteBuildingButtonClicked();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //update button
        updateButtonBuilding.setOnAction(e -> {
            try {
                updateBuildingButtonClicked();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox hBoxAddDeleteUpdateBuilding = new HBox(10);
        hBoxAddDeleteUpdateBuilding.setPadding(new Insets(20, 20, 20, 0));
        hBoxAddDeleteUpdateBuilding.getChildren().setAll(deleteButtonBuilding, updateButtonBuilding);

        // adding a building
        BorderPane borderPaneAddBuilding = new BorderPane();
        VBox vBoxAddBuilding = new VBox();

        Text buildingName = new Text("Building Name");
        Text openTime = new Text("Open Time");
        Text closeTime = new Text("Close Time");
        Text streetName = new Text("Street Name");
        Text streetNumber = new Text("Street Number");
        Text zipCode = new Text("Zip Code");
        Text city = new Text("City");

        TextField buildingNameInput = new TextField();
        TextField openTimeInput = new TextField();
        TextField closeTimeInput = new TextField();
        TextField streetNameInput = new TextField();
        TextField streetNumberInput = new TextField();
        TextField zipCodeInput = new TextField();
        TextField cityInput = new TextField();

        Button addButtonBuilding = new Button("Add Building");

<<<<<<< HEAD
        vBoxAddBuilding.getChildren().addAll(buildingName, buildingNameInput, streetName, streetNameInput, streetNumber, streetNumberInput, zipCode, zipCodeInput, city, cityInput, addButtonBuilding);
        vBoxAddBuilding.setPadding(new Insets(0, 10, 10, 10));
        vBoxAddBuilding.setSpacing(10);
        borderPaneAddBuilding.setTop(vBoxAddBuilding);
=======
        vboxAddBuilding.getChildren().addAll(buildingName, buildingNameInput, openTime, openTimeInput, closeTime, closeTimeInput,
            streetName, streetNameInput, streetNumber, streetNumberInput, zipCode, zipCodeInput, city, cityInput, addButtonBuilding);
        vboxAddBuilding.setPadding(new Insets(10, 10, 10, 10));
        vboxAddBuilding.setSpacing(10);
        borderPaneAddBuilding.setTop(vboxAddBuilding);
>>>>>>> development

        addButtonBuilding.setOnAction(e -> {
            String buildingNameInputText = buildingNameInput.getText();
            String openTimeInputText = openTimeInput.getText();
            String closeTimeInputText = closeTimeInput.getText();
            String streetNameInputText = streetNameInput.getText();
            String streetNumberInputText = streetNumberInput.getText();
            String zipCodeInputText = zipCodeInput.getText();
            String cityInputText = cityInput.getText();

            BuildingCommunication.addBuilding(buildingNameInputText, LocalTime.parse(openTimeInputText), LocalTime.parse(closeTimeInputText),
                streetNameInputText, streetNumberInputText, zipCodeInputText, cityInputText);

            buildingNameInput.setText(null);
            openTimeInput.setText(null);
            closeTimeInput.setText(null);
            streetNameInput.setText(null);
            streetNumberInput.setText(null);
            zipCodeInput.setText(null);
            cityInput.setText(null);
<<<<<<< HEAD
        });

        //clearing any previous tableview to avoid multiplication
        tableBuildingTime.getColumns().clear();

        // Table for Time buildings
        tableBuildingTime.setEditable(true);

        TableColumn<OpenTime, Long> idBuildingTimeCol =
                new TableColumn<>("id");
        idBuildingTimeCol.setMinWidth(100);
        idBuildingTimeCol.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        TableColumn<OpenTime, String> buildingNameCol =
                new TableColumn<>("Building Name");
        buildingNameCol.setMinWidth(100);
        buildingNameCol.setCellValueFactory(
                new PropertyValueFactory<>("building"));
        buildingNameCol.setCellFactory(TextFieldTableCell.<OpenTime, String>forTableColumn(new BuildingToStringConvertor()));

        TableColumn<OpenTime, String> dayCol =
                new TableColumn<>("Day");
        dayCol.setMinWidth(100);
        dayCol.setCellValueFactory(
                new PropertyValueFactory<>("day"));
        dayCol.setCellFactory(TextFieldTableCell.forTableColumn());
        dayCol.setOnEditCommit(
                (TableColumn.CellEditEvent<OpenTime, String> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setDay(t.getNewValue()));

        TableColumn<OpenTime, String> openTimeCol =
                new TableColumn<>("Open Time");
        openTimeCol.setMinWidth(100);
        openTimeCol.setCellValueFactory(
                new PropertyValueFactory<>("openTime"));
        openTimeCol.setCellFactory(TextFieldTableCell.<OpenTime, String>forTableColumn((new TimeToStringConverter())));
        openTimeCol.setOnEditCommit(
                (TableColumn.CellEditEvent<OpenTime, String> t) -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setOpenTime(Time.valueOf(t.getNewValue()));
                });

        TableColumn<OpenTime, String> closeTimeCol =
                new TableColumn<>("Close Time");
        closeTimeCol.setMinWidth(100);
        closeTimeCol.setCellValueFactory(
                new PropertyValueFactory<>("closeTime"));
        closeTimeCol.setCellFactory(TextFieldTableCell.<OpenTime, String>forTableColumn((new TimeToStringConverter())));
        closeTimeCol.setOnEditCommit(
                (TableColumn.CellEditEvent<OpenTime, String> t) -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setCloseTime(Time.valueOf(t.getNewValue()));
                });

        ObservableList<OpenTime> buildingTimeData = FXCollections.observableList(OpenTimeCommunication.getOpenTimes());
        tableBuildingTime.setItems(buildingTimeData);
        tableBuildingTime.getColumns().addAll(idBuildingTimeCol, buildingNameCol, dayCol, openTimeCol, closeTimeCol);

        //delete button
        deleteTimeBuilding.setOnAction(e -> {
            try {
                deleteTimeButtonClicked();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //update button
        updateTimeBuilding.setOnAction(e -> {
            try {
                updateTimeButtonClicked();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox hBoxAddDeleteUpdateTime = new HBox(10);
        hBoxAddDeleteUpdateTime.setPadding(new Insets(20, 20, 20, 0));
        hBoxAddDeleteUpdateTime.getChildren().setAll(deleteTimeBuilding, updateTimeBuilding);

        // adding a openTime for each building
        BorderPane borderPaneAddOpenTime = new BorderPane();
        VBox vBoxAddOpenTime = new VBox();

        ObservableList<Building> buildingNames = FXCollections.observableList(BuildingCommunication.getBuildings());
        ArrayList<String> buildingList = new ArrayList<>();

        for (Building name : buildingNames) {
            buildingList.add(name.getName() + ", " + name.getId());
        }
        ObservableList<String> bl = FXCollections.observableArrayList(buildingList);

        Text day = new Text("Day");
        Text openTime = new Text("Open Time");
        Text closeTime = new Text("Close Time");
        Text building = new Text("Building ID");

        TextField dayInput = new TextField();
        TextField openTimeInput = new TextField();
        TextField closeTimeInput = new TextField();
        TextField buildingInput = new TextField();

        ChoiceBox<String> choiceBox = new ChoiceBox<>();

        Button addOpenTime = new Button("Add Open Time");

        choiceBox.setItems(bl);

        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) return;
            String[] string = newValue.split(", ");
            buildingInput.setText(string[1]);
=======
>>>>>>> development
        });

        vBoxAddOpenTime.getChildren().addAll(day, dayInput, openTime, openTimeInput, closeTime, closeTimeInput, building, buildingInput, choiceBox, addOpenTime);
        vBoxAddOpenTime.setPadding(new Insets(0, 10, 10, 10));
        vBoxAddOpenTime.setSpacing(10);
        borderPaneAddOpenTime.setTop(vBoxAddOpenTime);

        addOpenTime.setOnAction(e -> {
            System.out.println((openTimeInput.getText()));

            String day1 = dayInput.getText();
            Time openTime1 = Time.valueOf(openTimeInput.getText());
            Time closeTime1 = Time.valueOf(closeTimeInput.getText());

            OpenTimeCommunication.addOpenTime(day1, openTime1, closeTime1, Long.parseLong(buildingInput.getText()));

            dayInput.setText(null);
            openTimeInput.setText(null);
            closeTimeInput.setText(null);
            buildingInput.setText(null);

            choiceBox.setValue(null);
        });

        // The below things are to load everything to the building TP
        VBox vBoxBuildingAndButtons = new VBox();
        VBox vBoxOpenTimeAndButtons = new VBox();

        HBox hBoxBuildingTP = new HBox();
        hBoxBuildingTP.setSpacing(100);
        HBox hBoxTimeTP  = new HBox();
        hBoxTimeTP.setSpacing(100);

        VBox vBoxMainBuildingTP = new VBox();
        vBoxMainBuildingTP.setPadding(new Insets(20, 20, 20, 20));

        vBoxBuildingAndButtons.getChildren().addAll(tableBuilding, hBoxAddDeleteUpdateBuilding);
        vBoxOpenTimeAndButtons.getChildren().addAll(tableBuildingTime, hBoxAddDeleteUpdateTime);

        hBoxBuildingTP.getChildren().addAll(vBoxBuildingAndButtons, borderPaneAddBuilding);
        hBoxTimeTP.getChildren().addAll(vBoxOpenTimeAndButtons, borderPaneAddOpenTime);
        vBoxMainBuildingTP.getChildren().addAll(hBoxBuildingTP, hBoxTimeTP);

        ScrollPane scroll = new ScrollPane();
        scroll.setContent(vBoxMainBuildingTP);
        buildingTP.setContent(scroll);
    }

    /**
     * The method below is implemented for the rooms table which is loaded on the main accordion element in
     * the admin scene.
     */
    public static void RoomView() {
        //clearing any previous tableview to avoid multiplication
        tableRoom.getColumns().clear();

        // Table for Rooms
        tableRoom.setEditable(true);

        TableColumn<Room, Long> idColRooms =
            new TableColumn<>("id");
        idColRooms.setMinWidth(100);
        idColRooms.setCellValueFactory(
            new PropertyValueFactory<>("id"));

        TableColumn<Room, String> roomCol =
            new TableColumn<>("Room Name");
        roomCol.setMinWidth(100);
        roomCol.setCellValueFactory(
            new PropertyValueFactory<>("name"));
        roomCol.setCellFactory(TextFieldTableCell.forTableColumn());
<<<<<<< HEAD
        roomCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Room, String> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setName(t.getNewValue()));

        TableColumn<Room, Integer> capacityCol =
                new TableColumn<>("Capacity");
=======
        roomCol.setOnEditCommit((TableColumn.CellEditEvent<Room, String> t) ->
            t.getTableView().getItems().get(t.getTablePosition().getRow()).setName(t.getNewValue()));

        TableColumn<Room, Integer> capacityCol =
            new TableColumn<>("capacityField");
>>>>>>> development
        capacityCol.setMinWidth(100);
        capacityCol.setCellValueFactory(
            new PropertyValueFactory<>("capacity"));
        capacityCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
<<<<<<< HEAD
        capacityCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Room, Integer> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setCapacity(t.getNewValue()));
=======
        capacityCol.setOnEditCommit((TableColumn.CellEditEvent<Room, Integer> t) ->
            t.getTableView().getItems().get(t.getTablePosition().getRow()).setCapacity(t.getNewValue()));
>>>>>>> development

        TableColumn<Room, String> buildingNameCol =
            new TableColumn<>("Building Name");
        buildingNameCol.setMinWidth(100);
        buildingNameCol.setCellValueFactory(
            new PropertyValueFactory<>("building"));
        buildingNameCol.setCellFactory(TextFieldTableCell.<Room, String>forTableColumn(new BuildingToStringConvertor()));

        ObservableList<Room> roomData = FXCollections.observableList(RoomCommunication.getRooms());
        tableRoom.setItems(roomData);
        tableRoom.getColumns().addAll(idColRooms, roomCol, capacityCol, buildingNameCol);

        //delete button
        deleteButtonRoom.setOnAction(e -> {
            try {
                deleteRoomButtonClicked();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //update button
        updateButtonRoom.setOnAction(e -> {
            try {
                updateRoomButtonClicked();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // HBox for the buttons under the table
        HBox hBoxAddDeleteUpdateRooms = new HBox(10);
        hBoxAddDeleteUpdateRooms.setPadding(new Insets(20, 20, 20, 0));
        hBoxAddDeleteUpdateRooms.getChildren().setAll(deleteButtonRoom, updateButtonRoom);

        // adding room scene
        ObservableList<Building> buildingNames = FXCollections.observableList(BuildingCommunication.getBuildings());
        ArrayList<String> buildingList = new ArrayList<>();

        for (Building buildingName : buildingNames) {
            buildingList.add(buildingName.getName() + ", " + buildingName.getId());
        }
        ObservableList<String> bl = FXCollections.observableArrayList(buildingList);

        BorderPane borderPaneAddRoom = new BorderPane();
        VBox vBoxAddRoom = new VBox();

        Text roomName = new Text("Room Name");
        Text capacity = new Text("Capacity");
        Text building = new Text("Building Name");

        TextField RoomName = new TextField();
        TextField Capacity = new TextField();
        TextField Building = new TextField();

        ChoiceBox<String> choiceBox = new ChoiceBox<>();

        Button addButton = new Button("Add Room");

        choiceBox.setItems(bl);

        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) return;
            String[] string = newValue.split(", ");
            Building.setText(string[1]);
        });

        vBoxAddRoom.getChildren().addAll(roomName, RoomName, capacity, Capacity, building, Building, choiceBox, addButton);
        vBoxAddRoom.setPadding(new Insets(0, 10, 10, 10));
        vBoxAddRoom.setSpacing(10);
        borderPaneAddRoom.setTop(vBoxAddRoom);

        addButton.setOnAction(e -> {
            String roomName1 = RoomName.getText();
            int capacity1 = Integer.parseInt(Capacity.getText());

            RoomCommunication.addRoom(roomName1, capacity1, Long.parseLong(Building.getText()));

            Building.setText(null);
            RoomName.setText(null);
            Capacity.setText(null);

            choiceBox.setValue(null);
        });

        // This VBox contains the table for the rooms and adding a room
        HBox hBoxRoomTP = new HBox();
        hBoxRoomTP.setSpacing(100);
        VBox vBoxRoomsTP = new VBox();

        hBoxRoomTP.getChildren().addAll(tableRoom, borderPaneAddRoom);
        vBoxRoomsTP.setPadding(new Insets(20, 20, 20, 20));
        vBoxRoomsTP.getChildren().addAll(hBoxRoomTP, hBoxAddDeleteUpdateRooms);

        ScrollPane scrollPaneRooms = new ScrollPane();
        scrollPaneRooms.setContent(vBoxRoomsTP);
        roomsTP.setContent(scrollPaneRooms);
    }

    /**
     * The method below is implemented for the restaurant table which is loaded on the main accordion element in
     * the admin scene.
     */
    public static void RestaurantView() {
        //clearing any previous tableview to avoid multiplication
        tableRestaurant.getColumns().clear();

        // Table for restaurants
        tableRestaurant.setEditable(true);

        TableColumn<Restaurant, Long> idRestaurantCol =
            new TableColumn<>("id");
        idRestaurantCol.setMinWidth(100);
        idRestaurantCol.setCellValueFactory(
            new PropertyValueFactory<>("id"));

        TableColumn<Restaurant, String> restaurantNameCol =
                new TableColumn<>("Restaurant Name");
        restaurantNameCol.setMinWidth(100);
        restaurantNameCol.setCellValueFactory(
            new PropertyValueFactory<>("name"));
        restaurantNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        restaurantNameCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Restaurant, String> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setName(t.getNewValue()));
        restaurantNameCol.setOnEditCommit((TableColumn.CellEditEvent<Restaurant, String> t) ->
            t.getTableView().getItems().get(t.getTablePosition().getRow()).setName(t.getNewValue()));

        TableColumn<Restaurant, Building> buildingNameRestaurantCol =
            new TableColumn<>("Building Name");
        buildingNameRestaurantCol.setMinWidth(100);
        buildingNameRestaurantCol.setCellValueFactory(
            new PropertyValueFactory<>("building"));
        buildingNameRestaurantCol.setCellFactory(TextFieldTableCell.<Restaurant, String>forTableColumn(new BuildingToStringConvertor()));
        buildingNameRestaurantCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Restaurant, Building> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setBuilding(t.getNewValue()));

        TableColumn<Restaurant, Time> timeOpenCol =
                new TableColumn<>("Opening Time");
        timeOpenCol.setMinWidth(100);
        timeOpenCol.setCellValueFactory(
                new PropertyValueFactory<>("timeOpen"));
        timeOpenCol.setCellFactory(TextFieldTableCell.<Restaurant, String>forTableColumn((new TimeToStringConverter())));
        timeOpenCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Restaurant, Time> t) -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setTimeOpen(t.getNewValue());
                });

        TableColumn<Restaurant, Time> timeCloseCol =
                new TableColumn<>("Closing Time");
        timeCloseCol.setMinWidth(100);
        timeCloseCol.setCellValueFactory(
                new PropertyValueFactory<>("timeClose"));
        timeCloseCol.setCellFactory(TextFieldTableCell.<Restaurant, String>forTableColumn((new TimeToStringConverter())));
        timeCloseCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Restaurant, Time> t) -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setTimeClose(t.getNewValue());
                });
        buildingNameRestaurantCol.setOnEditCommit((TableColumn.CellEditEvent<Restaurant, Building> t) ->
            t.getTableView().getItems().get(t.getTablePosition().getRow()).setBuilding(t.getNewValue()));

        TableColumn<Restaurant, String> timeCloseCol =
            new TableColumn<>("Closing Time");
        timeCloseCol.setMinWidth(100);
        timeCloseCol.setCellValueFactory(
            new PropertyValueFactory<>("tClose"));
        timeCloseCol.setCellFactory(TextFieldTableCell.<Restaurant, String>forTableColumn((new TimeToStringConvertor())));

        TableColumn<Restaurant, String> timeOpenCol =
            new TableColumn<>("Opening Time");
        timeOpenCol.setMinWidth(100);
        timeOpenCol.setCellValueFactory(
            new PropertyValueFactory<>("tOpen"));
        timeOpenCol.setCellFactory(TextFieldTableCell.<Restaurant, String>forTableColumn((new TimeToStringConvertor())));

        ObservableList<Restaurant> restaurantData = FXCollections.observableList(RestaurantCommunication.getRestaurants());
        tableRestaurant.setItems(restaurantData);
        tableRestaurant.getColumns().addAll(idRestaurantCol, restaurantNameCol, buildingNameRestaurantCol, timeOpenCol, timeCloseCol);

        //delete button
        deleteButtonRestaurant.setOnAction(e -> {
            try {
                deleteButtonRestaurantClicked();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //update button
        updateButtonRestaurant.setOnAction(e -> {
            try {
                updateButtonRestaurantClicked();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // adding a restaurant
        BorderPane borderPaneAddRestaurant = new BorderPane();
        VBox vBoxAddRestaurant = new VBox();

        ObservableList<Building> buildingNames = FXCollections.observableList(BuildingCommunication.getBuildings());
        ArrayList<String> buildingList = new ArrayList<>();

        for (Building name : buildingNames) {
            buildingList.add(name.getName() + ", " + name.getId());
        }
        ObservableList<String> bl = FXCollections.observableArrayList(buildingList);

        Text restaurantName = new Text("Restaurant Name");
        Text buildingName = new Text("Building Name");
        Text openingTime = new Text("Opening Time");
        Text closingTime = new Text("Closing Time");

        TextField restaurantNameInput = new TextField();
        TextField buildingNameInput = new TextField();
        TextField openingTimeInput = new TextField();
        TextField closingTimeInput = new TextField();

        ChoiceBox<String> choiceBox = new ChoiceBox<>();

        Button addRestaurant = new Button("Add Restaurant");

        choiceBox.setItems(bl);

        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) return;
            String[] string = newValue.split(", ");
            buildingNameInput.setText(string[1]);
        });

        vBoxAddRestaurant.getChildren().addAll(restaurantName, restaurantNameInput, buildingName, buildingNameInput, choiceBox, openingTime, openingTimeInput, closingTime, closingTimeInput, addRestaurant);
        vBoxAddRestaurant.setPadding(new Insets(0, 10, 10, 10));
        vBoxAddRestaurant.setSpacing(10);
        borderPaneAddRestaurant.setTop(vBoxAddRestaurant);

        addRestaurant.setOnAction(e -> {
            String restaurantNameInputText = restaurantNameInput.getText();
            Time openingTimeInputText = Time.valueOf(openingTimeInput.getText());
            Time closingTimeInputText = Time.valueOf(closingTimeInput.getText());

            RestaurantCommunication.addRestaurant(restaurantNameInputText, Long.parseLong(buildingNameInput.getText()), closingTimeInputText, openingTimeInputText);

            restaurantNameInput.setText(null);
            openingTimeInput.setText(null);
            closingTimeInput.setText(null);
            buildingNameInput.setText(null);

            choiceBox.setValue(null);
        });

        // HBox for the buttons under the table
        HBox hBoxAddDeleteUpdateRestaurants = new HBox(10);
        hBoxAddDeleteUpdateRestaurants.setPadding(new Insets(20, 20, 20, 0));
        hBoxAddDeleteUpdateRestaurants.getChildren().setAll(deleteButtonRestaurant, updateButtonRestaurant);

        // This VBox contains the table for the rooms and adding a room
        HBox hBoxRestaurantTP = new HBox();
        hBoxRestaurantTP.setSpacing(100);
        VBox vBoxRestaurantTP = new VBox();

        hBoxRestaurantTP.getChildren().addAll(tableRestaurant, borderPaneAddRestaurant);
        vBoxRestaurantTP.setPadding(new Insets(20, 20, 20, 20));
        vBoxRestaurantTP.getChildren().addAll(hBoxRestaurantTP, hBoxAddDeleteUpdateRestaurants);

        ScrollPane scrollPaneRestaurant = new ScrollPane();
        scrollPaneRestaurant.setContent(vBoxRestaurantTP);
        restaurantsTP.setContent(scrollPaneRestaurant);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}

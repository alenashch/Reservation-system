package nl.tudelft.oopp.demo.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import nl.tudelft.oopp.demo.communication.DishOrderCommunication;
import nl.tudelft.oopp.demo.communication.OrderCommunication;
import nl.tudelft.oopp.demo.entities.Order;
import nl.tudelft.oopp.demo.entities.RestaurantDish;
import nl.tudelft.oopp.demo.entities.RoomReservation;

public class OrderSceneController implements Initializable {

    public ListView basketListView;
    private MainSceneController mainSceneController;
    private  HamburgerMenuSceneController hamburgerMenuSceneController;
    private static ObservableList<String> stringBasketList = FXCollections.observableArrayList();
    private static ArrayList<RestaurantDish> basketList = new ArrayList();
    private static ObservableList<Integer> amountBasketList = FXCollections.observableArrayList();
    private RoomReservation roomReservation;
    @FXML
    private Label totalLabel = new Label();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        basketListView.setItems(stringBasketList);
    }

    /**
     * Adds a dish into the basket.
     * @param restaurantDish A restaurant dish
     */
    public static void addToBasket(RestaurantDish restaurantDish) {
        if (basketList.contains(restaurantDish)) {
            int amount = amountBasketList.get(basketList.indexOf(restaurantDish));
            amount++;
            amountBasketList.set(basketList.indexOf(restaurantDish), amount);
            stringBasketList.set(basketList.indexOf(restaurantDish), amountBasketList.get(basketList.indexOf(restaurantDish)) + "x " + restaurantDish.getDish().getName()
                    + "   " + amountBasketList.get(basketList.indexOf(restaurantDish)) * restaurantDish.getDish().getPrice());
        } else {
            basketList.add(restaurantDish);
            amountBasketList.add(1);
            stringBasketList.add(basketList.indexOf(restaurantDish), amountBasketList.get(basketList.indexOf(restaurantDish)) + "x " + restaurantDish.getDish().getName()
                    + "   " + amountBasketList.get(basketList.indexOf(restaurantDish)) * restaurantDish.getDish().getPrice());
        }
    }

    /**
     * Removes a dish from the basket.
     * @param restaurantDish A restaurant dish
     */
    public static void removeFromBasket(RestaurantDish restaurantDish) {
        if (basketList.contains(restaurantDish)) {
            if (amountBasketList.get(basketList.indexOf(restaurantDish)) > 1) {
                int amount = amountBasketList.get(basketList.indexOf(restaurantDish));
                amount--;
                amountBasketList.set(basketList.indexOf(restaurantDish), amount);
                stringBasketList.set(basketList.indexOf(restaurantDish), amountBasketList.get(basketList.indexOf(restaurantDish)) + "x " + restaurantDish.getDish().getName()
                        + "   " + amountBasketList.get(basketList.indexOf(restaurantDish)) * restaurantDish.getDish().getPrice());
            } else {
                amountBasketList.remove(basketList.indexOf(restaurantDish));
                stringBasketList.remove(basketList.indexOf(restaurantDish));
                basketList.remove(restaurantDish);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("The dish you selected is not currently in your basket");
            alert.show();
        }
    }

    /**
     * Clears the curent state of the basket.
     */
    public static void clearBasket() {
        basketList.clear();
        stringBasketList.clear();
        amountBasketList.clear();
    }

    /**
     * Creates a new order.
     */
    public void handelOrderButton() {
        if (basketList.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You currently have no items in the basket");
            alert.show();
        } else {
            OrderCommunication.addOrder(getRoomReservation());
            List<Order> orders = OrderCommunication.getOrders();
            Order order = new Order(getRoomReservation());
            for (int i = 0; i < orders.size(); i++) {
                if (orders.get(i).getRoomReservation().getId() == getRoomReservation().getId()) {
                    order = orders.get(i);
                }
            }
            for (int i = 0; i < basketList.size(); i++) {
                DishOrderCommunication.addDishOrder(amountBasketList.get(i), basketList.get(i).getId(), order.getId());
                System.out.println("New dishorder place with: ammount:" + amountBasketList.get(i) + ", restaurantdishID:" + basketList.get(i).getId() + ", roomreservaionID: " + order.getId());
            }
            clearBasket();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Your order has been placed");
            alert.show();
        }
    }

    public void refreshTotal() {
        totalLabel.setText("Total: " + calculateTotal());
    }

    /**
     * Calculate total number of items in basket.
     * @return
     */
    public static long calculateTotal() {
        long total = 0;
        for (int i = 0; i < amountBasketList.size(); i++) {
            total += amountBasketList.get(i) * basketList.get(i).getDish().getPrice();
        }
        return total;
    }

    public void setRoomReservation(RoomReservation roomReservation) {
        this.roomReservation = roomReservation;
    }

    public RoomReservation getRoomReservation() {
        return roomReservation;
    }
}
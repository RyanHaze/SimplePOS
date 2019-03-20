package org.dgby.gatorpos.models;

import javafx.beans.property.*;
import javafx.collections.*;

import java.sql.SQLException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.dgby.gatorpos.ConnectionManager;

public class Tab {
    private IntegerProperty id;
    private StringProperty open_date;
    private StringProperty close_date;
    private StringProperty note;

    private StringProperty card_lastfour;
    private StringProperty card_data;
    private IntegerProperty cash;

    private SimpleMapProperty<Integer, Integer> products;

    private static ObservableList<Tab> tabList = FXCollections.observableArrayList();

    public Tab(Integer id, String open_date, String close_date, String note, String card_lastfour, String card_data,
            Integer cash, ObservableMap<Integer, Integer> products) {
        this.id = new SimpleIntegerProperty(id);
        this.open_date = new SimpleStringProperty(open_date);
        this.close_date = new SimpleStringProperty(close_date);
        this.note = new SimpleStringProperty(note);

        this.card_lastfour = new SimpleStringProperty(card_lastfour);
        this.card_data = new SimpleStringProperty(card_data);
        this.cash = new SimpleIntegerProperty(cash);

        this.products = new SimpleMapProperty<Integer, Integer>(products);
    }

    public static void updateTabs() {
        tabList.clear();
        try {
            ConnectionManager.createTable("Tabs", new String[] { "open_date TEXT NOT NULL", "close_date TEXT",
                    "note TEXT", "card_lastfour TEXT", "card_data TEXT", "cash INT" });
            ConnectionManager.createTable("TabItems", new String[] { "tab_id INT", "product_id INT", "count INT" });

            ConnectionManager.executeQuery("SELECT rowid AS id,* FROM Tabs", resultSet -> {
                while (resultSet.next()) {
                    Integer tab_id = resultSet.getInt("id");
                    ObservableMap<Integer, Integer> productMap = FXCollections.observableHashMap();

                    ConnectionManager.executeQuery("SELECT * FROM TabItems WHERE tab_id = " + tab_id, resultSet2 -> {
                        while (resultSet2.next())
                            productMap.put(resultSet2.getInt("product_id"), resultSet2.getInt("count"));
                    });

                    tabList.add(new Tab(resultSet.getInt("id"), resultSet.getString("open_date"),
                            resultSet.getString("close_date"), resultSet.getString("note"),
                            resultSet.getString("card_lastfour"), resultSet.getString("card_data"),
                            resultSet.getInt("cash"), productMap));
                }
            });
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
    }

    public static ObservableList<Tab> getTabs() {
        return tabList;
    }

    private static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static Tab getTab(Integer id) {
        for (Tab tab : tabList) {
            if (tab.getId() == id)
                return tab;
        }
        return null;
    }

    public static Tab openTab(String note) {
        try {
            Integer id = ConnectionManager.insertRow("Tabs", new String[] { "open_date", "note", "cash" },
                    new Object[] { getCurrentDate(), note, 0 });

            ConnectionManager.executeQuery("SELECT rowid AS id,* FROM Tabs WHERE rowid = " + id, resultSet -> {
                while (resultSet.next()) {
                    tabList.add(new Tab(resultSet.getInt("id"), resultSet.getString("open_date"),
                            resultSet.getString("close_date"), resultSet.getString("note"),
                            resultSet.getString("card_lastfour"), resultSet.getString("card_data"),
                            resultSet.getInt("cash"), FXCollections.observableHashMap()));

                }
            });

            return getTab(id);
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
            return null;
        }
    }

    public static void closeTab(Tab tab) {
        try {
            ConnectionManager.executeUpdate(
                    "UPDATE Tabs SET close_date = '" + getCurrentDate() + "' WHERE rowid = " + tab.getId());
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
    }

    public static void deleteTab(Tab tab) {
        try {
            tabList.remove(tab);
            ConnectionManager.executeUpdate("DELETE FROM Tabs WHERE rowid = " + tab.getId());
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
    }

    public static void updateTabProduct(Tab tab, Product product, Integer count) {
        try {
            ConnectionManager.executeUpdate("UPDATE TabItems SET count = " + count + " WHERE tab_id = " + tab.getId()
                    + " AND product_id = " + product.getId());
        } catch (SQLException sqlEx) {
            try {
                ConnectionManager.insertRow("TabItems", new String[] { "tab_id", "product_id", "count" },
                        new Object[] { tab.getId(), product.getId(), count });
            } catch (SQLException sqlEx2) {
                System.out.println(sqlEx2.getMessage());
            }
        }

        tab.products.put(product.getId(), count);
    }

    public static void updateTabCash(Tab tab, Integer cash) {
        try {
            ConnectionManager.executeUpdate("UPDATE Tabs SET cash = " + cash + " WHERE rowid = " + tab.getId());
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }

        tab.setCash(cash);
    }

    public static void updateTabCardInfo(Tab tab, String cardLastFour, String cardData) {
        try {
            ConnectionManager.executeUpdate("UPDATE Tabs SET card_lastfour = '" + cardLastFour + "', card_data = '"
                    + cardData + "' WHERE rowid = " + tab.getId());
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }

        tab.setCardData(cardData);
        tab.setCardLastFour(cardLastFour);
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id.get();
    }

    /**
     * @param id
     *               the id to set
     */
    public void setId(Integer id) {
        this.id.set(id);
    }

    /**
     * @return the open_date
     */
    public String getOpenDate() {
        return open_date.get();
    }

    /**
     * @param openDate
     *                     the open_date to set
     */
    public void setOpenDate(String openDate) {
        this.open_date.set(openDate);
    }

    /**
     * @return the close_date
     */
    public String getCloseDate() {
        return close_date.get();
    }

    /**
     * @param closeDate
     *                      the close_date to set
     */
    public void setCloseDate(String closeDate) {
        this.close_date.set(closeDate);
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note.get();
    }

    /**
     * @param note
     *                 the note to set
     */
    public void setNote(String note) {
        this.note.set(note);
    }

    /**
     * @return the card_lastfour
     */
    public String getCardLastFour() {
        return card_lastfour.get();
    }

    /**
     * @param cardLastFour
     *                         the card_lastfour to set
     */
    public void setCardLastFour(String cardLastFour) {
        this.card_lastfour.set(cardLastFour);
    }

    /**
     * @return the card_data
     */
    public String getCardData() {
        return card_data.get();
    }

    /**
     * @param cardData
     *                     the card_data to set
     */
    public void setCardData(String cardData) {
        this.card_data.set(cardData);
    }

    /**
     * @return the cash
     */
    public Integer getCash() {
        return cash.get();
    }

    /**
     * @param cash
     *                 the cash to set
     */
    public void setCash(Integer cash) {
        this.cash.set(cash);
    }

    @Override
    public String toString() {
        return getNote() + " - " + getCardLastFour();
    }
}

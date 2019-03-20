package org.dgby.gatorpos.models;

import javafx.beans.property.*;
import javafx.collections.*;

import java.sql.SQLException;

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
            ConnectionManager.createTable("Tabs",
                    new String[] { "open_date TEXT NOT NULL", "close_date TEXT", "note TEXT" });

            ConnectionManager.executeQuery("SELECT rowid AS id,* FROM Tabs", resultSet -> {
                while (resultSet.next()) {
                    // TODO: Get products from other table.
                    tabList.add(new Tab(resultSet.getInt("id"), resultSet.getString("open_date"),
                            resultSet.getString("close_date"), resultSet.getString("note"),
                            resultSet.getString("card_lastfour"), resultSet.getString("card_data"),
                            resultSet.getInt("cash"), null));
                }
            });
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
    }

    public static ObservableList<Tab> getTabs() {
        return tabList;
    }

    public static Integer openTab(String open_date, String note) {
        try {
            Integer id = ConnectionManager.insertRow("Tabs", new String[] { "open_date", "note" },
                    new Object[] { open_date, note });

            ConnectionManager.executeQuery("SELECT rowid AS id,* FROM Tabs WHERE rowid = " + id, resultSet -> {
                while (resultSet.next()) {
                    // TODO: Get products from other table.
                    tabList.add(new Tab(resultSet.getInt("id"), resultSet.getString("open_date"),
                            resultSet.getString("close_date"), resultSet.getString("note"),
                            resultSet.getString("card_lastfour"), resultSet.getString("card_data"),
                            resultSet.getInt("cash"), null));
                }
            });

            return id;
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
            return -1;
        }
    }

    public static void closeTab(Integer id) {
        try {
            // TODO: Write SQL for this.
            ConnectionManager.executeQuery("", resultSet -> {
                while (resultSet.next()) {
                    // Lalala
                }
            });

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

    /**
     * @return the id
     */
    public Integer getId() {
        return id.get();
    }

    /**
     * @param id the id to set
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
     * @param openDate the open_date to set
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
     * @param closeDate the close_date to set
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
     * @param note the note to set
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
     * @param cardLastFour the card_lastfour to set
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
     * @param cardData the card_data to set
     */
    public void setCardData(String cardData) {
        this.card_data.set(cardData);
    }
}

package ru.eltex;

public class Sale {
    private String[] items;
    private Double cost;

    public Sale(String[] items, Double cost) {
        this.items = items;
        this.cost = cost;
    }

    public String[] getItems() {
        return items;
    }

    public Double getCost() {
        return cost;
    }

    public void setItems(String[] items) {
        this.items = items;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
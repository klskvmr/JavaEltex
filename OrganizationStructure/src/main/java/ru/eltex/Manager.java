package ru.eltex;

public class Manager {
    private Sales[] sales;

    public Manager(Sales[] sales) {
        this.sales = sales;
    }

    public Sales[] getSales() {
        return sales;
    }

    public void setSales(Sales[] sales) {
        this.sales = sales;
    }
}

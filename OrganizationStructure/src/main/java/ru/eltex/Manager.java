package ru.eltex;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Manager extends User {
    private Sale[] sales;

    public Manager(Integer index, String fio, String number, String email, Sale[] sales) {
        super(index, fio, number, email);
        this.sales = sales;
    }

    public Manager() {
    }

    public Sale[] getSales() {
        return sales;
    }

    public void setSales(Sale[] sales) {
        this.sales = sales;
    }

    private String getSalesInString() {
        String string = "";

        for (int i = 0; i < this.sales.length; i++) {
            String[] items = this.sales[i].getItems();

            for (int j = 0; j < items.length; j++) {
                string += items[j] + "@";
            }

            string += " " + this.sales[i].getCost();

            if (i == this.sales.length - 1) {
                break;
            } else {

                string += ",";
            }
        }
        return string;
    }

    public String toCSV() {
        return this.getId() + ";" + this.getFio() + ";" + this.getNumber() + ";" + this.getEmail() + ";" + this.getSalesInString() + System.lineSeparator();
    }

    public void fromCSV(String stringFromCVS) {
        super.fromCVS(stringFromCVS);

        String[] stringsFromCVSArray = stringFromCVS.split(";"); // массив из всех все данных

        String[] salesArray = stringsFromCVSArray[stringsFromCVSArray.length - 1].split(","); // массив продаж Sales

        Sale[] resultSalesArray = new Sale[salesArray.length]; //

        Integer counter = 0;
        for (String sale : salesArray) {
            String[] saleComponentsArray = sale.split(" "); // массив из составляющих одной продажи

            Double cost = Double.parseDouble(saleComponentsArray[1]);

            String[] itemsArray = saleComponentsArray[0].split("@");

            resultSalesArray[counter] = new Sale(itemsArray, cost);

            counter++;
        }

        this.sales = resultSalesArray;
    }

    @Override
    public String toJSON() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("managers.json"), this);

        return objectMapper.writeValueAsString(this);
    }

    @Override
    public void fromJSON(String string) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Manager manager = objectMapper.readValue(new File("developers.json"), Manager.class);

        this.setId(manager.getId());
        this.setFio(manager.getFio());
        this.setNumber(manager.getNumber());
        this.setEmail(manager.getEmail());
        this.setSales(manager.getSales());
    }
}
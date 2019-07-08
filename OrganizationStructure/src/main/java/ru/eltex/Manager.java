package ru.eltex;

public class Manager extends User {
    private Sale[] sales;

    public Manager(String fio, String number, String email, Sale[] sales) {
        super(fio, number, email);
        this.sales = sales;
    }

    public Sale[] getSales() {
        return sales;
    }

    public void setSales(Sale[] sales) {
        this.sales = sales;
    }

    public String salesToString(Sale[] sales){
        String string = "";

        for (int i = 0; i < sales.length; i++){
            String[] items = sales[i].getItems();

            for(int j = 0; j< items.length; j++){
                string += items[j] + "@";
            }

            string += " " + sales[i].getCost();

            string += ",";
        }
        return string;
    }

    public String toCSV() {
        return this.getFio() + ";" + this.getNumber() + ";" + this.getEmail() + ";" + salesToString(this.getSales()) + System.lineSeparator();
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
}
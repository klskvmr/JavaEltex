package ru.eltex;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codehaus.jackson.map.ObjectMapper;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.File;
import java.io.IOException;

@NoArgsConstructor

@Entity
public class Manager extends User {
    @Id
    @GeneratedValue

    @Getter
    @Setter
    private Sale[] sales;

    public Manager(Integer index, String fio, String number, String email, Sale[] sales) {
        super(index, fio, number, email);
        this.sales = sales;
    }

    public Manager(String fio, String number, String email, Sale[] sales) {
        super(fio, number, email);
        this.sales = sales;
    }

    protected String getSalesInString() {
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

    @Override
    public String toCSV() {
        return this.getId() + ";" + this.getFio() + ";" + this.getNumber() + ";" + this.getEmail() + ";" + this.getSalesInString() + System.lineSeparator();
    }

    @Override
    public void fromCSV(String stringFromCVS) throws TypeException {
        super.fromCVS(stringFromCVS);

        String[] stringsFromCVSArray = stringFromCVS.split(";"); // массив из всех все данных

        if (stringsFromCVSArray.length != 5) {
            throw new TypeException("NOT MANAGER");
        }

        String[] salesArray = stringsFromCVSArray[stringsFromCVSArray.length - 1].split(","); // массив из Sales

        Sale[] resultSalesArray = new Sale[salesArray.length];
        try {
            Integer counter = 0;
            for (String sale : salesArray) {
                String[] saleComponentsArray = sale.split(" "); // массив из составляющих одной продажи

                if (saleComponentsArray.length == 0) {
                    throw new TypeException("NOT MANAGER");
                }

                String[] itemsArray = saleComponentsArray[0].split("@");

                resultSalesArray[counter] = new Sale(itemsArray, Double.parseDouble(saleComponentsArray[1]));


                counter++;
            }

            sales = resultSalesArray;

        } catch (NumberFormatException error) {
            System.out.println(error.getMessage());
            throw new TypeException("NOT MANAGER");
        }
    }

    @Override
    public String toJSON(String filename) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(filename), this);

        return objectMapper.writeValueAsString(this);
    }

    @Override
    public void fromJSON(String string) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Manager manager = objectMapper.readValue(string, Manager.class);

        this.setId(manager.getId());
        this.setFio(manager.getFio());
        this.setNumber(manager.getNumber());
        this.setEmail(manager.getEmail());
        this.setSales(manager.getSales());
    }
}
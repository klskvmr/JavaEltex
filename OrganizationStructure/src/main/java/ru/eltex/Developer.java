package ru.eltex;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Developer extends User {
    private String[] languages;

    public Developer(Integer index, String fio, String number, String email, String[] languages) {
        super(index, fio, number, email);
        this.languages = languages;
    }

    public Developer() {
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    private String getLanguagesInString() {
        String string = "";

        for (String language : this.languages) {
            string += language + " ";
        }
        return string;
    }

    public String toCSV() {
        return this.getId() + ";" + this.getFio() + ";" + this.getNumber() + ";" + this.getEmail() + ";" + this.getLanguagesInString() + System.lineSeparator();
    }

    public void fromCSV(String stringFromCVS) {
        super.fromCVS(stringFromCVS);

        String[] stringsArray = stringFromCVS.split(";");
        String[] languagesArray = stringsArray[stringsArray.length - 1].split(",");

        this.languages = languagesArray;
    }

    public String toJSON() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("developers.json"), this);

        return objectMapper.writeValueAsString(this);
    }

    public void fromJSON(String string) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Developer developer = objectMapper.readValue(new File("developers.json"), Developer.class);

        this.setId(developer.getId());
        this.setFio(developer.getFio());
        this.setNumber(developer.getNumber());
        this.setEmail(developer.getEmail());
        this.setLanguages(developer.getLanguages());
    }
}
package ru.eltex;

public class Developer extends User {
    private String[] languages;

    public Developer(String fio, String number, String email, String[] languages) {
        super(fio, number, email);
        this.languages = languages;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public String languagesToString(String[] languages) {
        String string = "";

        for (String language : languages) {
            string += language + " ";
        }
        return string;
    }

    public String toCSV() {
        return this.getFio() + ";" + this.getNumber() + ";" + this.getEmail() + ";" + this.languagesToString(languages) + System.lineSeparator();
    }

    public void fromCSV(String stringFromCVS) {
        super.fromCVS(stringFromCVS);

        String[] stringsArray = stringFromCVS.split(";");
        String[] languagesArray = stringsArray[stringsArray.length - 1].split(",");

        this.languages = languagesArray;
    }
}

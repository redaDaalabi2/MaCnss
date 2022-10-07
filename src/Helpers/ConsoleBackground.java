package Helpers;


public enum ConsoleBackground {
    RESET("\u001B[0m"),
    BLACK("\u001B[40m"),
    RED("\u001B[41m");

    private final String Value;

    // Constructeur :
    ConsoleBackground(String value) {
        this.Value =value;
    }


    //getters:
    public String getValue() {
        return this.Value;
    }


}

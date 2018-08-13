package enums;

public enum BrowserType {

    CHROME("chrome"), FIREFOX("firefox"), IE("ie"), OPERA("opera");

    String value;

    BrowserType(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}

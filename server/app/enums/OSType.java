package enums;

public enum OSType {

    WINDOWS("windows"), MACOS("macos"), Linux("linux"),IOS("ios"), ANDROID("android");

    String value;

    OSType(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}

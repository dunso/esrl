package enums;

public enum  Role{

    ADMIN("admin"), AUTHOR("author"), EDITOR("editor"), CONTRIBUTOR("contributor");

    String value;

    Role(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}

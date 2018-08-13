package enums;

public enum PostStatus {
    DRAFT("draft"), PUBLISH("publish");

    String value;

    PostStatus(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}

package ro.uaic.info.util;

public enum ResponseMessages {
    CREATED {
        @Override public String toString() {
            return "Successfully uploaded document";
        }
    },
    DELETED {
        @Override public String toString() {
            return "Successfully deleted document";
        }
    },
    UPDATED {
        @Override public String toString() {
            return "Successfully updated file";
        }
    }
}

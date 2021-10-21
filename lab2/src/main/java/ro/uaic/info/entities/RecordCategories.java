package ro.uaic.info.entities;

public enum RecordCategories {
    VERB {
        @Override
        public String toString() {
            return "Verb";
        }
    },
    NOUN {
        @Override
        public String toString() {
            return "Noun";
        }
    }
}

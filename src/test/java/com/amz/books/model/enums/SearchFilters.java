package com.amz.books.model.enums;

public class SearchFilters {


    public enum Format {
        PAPERBACK("Paperback"),
        KINDLE("Kindle Edition"),
        HARDCOVER("Hardcover");

        private final String label;

        Format(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public enum Language {
        ENGLISH("English");

        private final String label;

        Language(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public enum Condition {
        NEW("New"),
        USED("Used");

        private final String label;

        Condition(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

}

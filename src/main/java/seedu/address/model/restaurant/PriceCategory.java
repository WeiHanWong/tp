package seedu.address.model.restaurant;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.util.Pair;

/**
 * Represents the price category of a restaurant.
 */
public enum PriceCategory {
    CHEAP(10, "$"),
    MODERATE(20, "$$"),
    EXPENSIVE(40, "$$$"),
    LUXURY(Integer.MAX_VALUE, "$$$$");

    private final int maxValue;
    private final String symbol;

    /**
     * Constructs a {@code PriceCategory}.
     * 
     * @param maxValue the maximum value of the price category
     * @param symbol   the symbol of the price category
     */
    PriceCategory(int maxValue, String symbol) {
        this.maxValue = maxValue;
        this.symbol = symbol;
    }

    /**
     * Returns the symbol of the price category.
     * 
     * @return the symbol of the price category
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Returns true if the symbol is part of a price category.
     * 
     * @param symbol the symbol to check
     * @return true if the symbol is part of a price category
     */
    public static boolean isSymbol(String symbol) {
        for (PriceCategory category : PriceCategory.values()) {
            if (category.symbol.equals(symbol)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the price category based on the value of the price.
     * 
     * @param value the value of the price
     * @return the price category
     */
    public static String getPriceCategoryString(double value) {
        for (PriceCategory category : PriceCategory.values()) {
            if (value <= category.maxValue) {
                return category.getSymbol();
            }
        }
        throw new IllegalArgumentException("Invalid price value: " + value);
    }

    /**
     * Extracts the price tag from a set of tags.
     * 
     * @param tags the set of tags
     * @return a pair of the price tag and the other tags
     */
    public static Pair<Tag, Set<Tag>> extractPriceTag(Set<Tag> tags) {
        Tag priceTag = null;
        Set<Tag> otherTags = new HashSet<>();

        for (Tag tag : tags) {
            if (isSymbol(tag.tagName) && priceTag == null) {
                priceTag = tag;
            } else if (isSymbol(tag.tagName) && priceTag != null) {
                throw new IllegalArgumentException("Multiple price tags found in the set of tags");
            }
        }
        return new Pair<>(priceTag, otherTags);
    }
}
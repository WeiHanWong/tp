package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RESTAURANTS;

import seedu.address.model.Model;
import seedu.address.model.restaurant.Rating;
import seedu.address.model.restaurant.Restaurant;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Sort all restaurants in the address book to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Sort all restaurants ratings";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        Comparator<Restaurant> ratingComparator = Comparator.comparing(
                restaurant -> {
                    Rating rating = restaurant.getRating();
                    return rating == null ? null : rating.value;
                },
                Comparator.nullsLast(Comparator.reverseOrder())
        );

        model.sortFilteredRestaurantList(ratingComparator);

        return new CommandResult(MESSAGE_SUCCESS);
    }
}

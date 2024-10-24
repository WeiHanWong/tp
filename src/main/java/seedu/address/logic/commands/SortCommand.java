package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RESTAURANTS;

import seedu.address.model.Model;
import seedu.address.model.restaurant.Restaurant;

import java.util.List;

/**
 * Lists all restaurants in the address book to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sort the restaurant name or ratings.\n"
            + "Parameters: PREFIX [ORDER]\n"
            + "Example: " + COMMAND_WORD + " p d";

    public static final String MESSAGE_SUCCESS = "Sort all restaurants";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Restaurant> lastShownList = model.getFilteredRestaurantList();
//        lastShownList.sort();
        model.updateFilteredRestaurantList(PREDICATE_SHOW_ALL_RESTAURANTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

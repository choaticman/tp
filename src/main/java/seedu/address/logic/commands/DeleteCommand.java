package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.internshipapplication.InternshipApplication;

/**
 * Deletes an internship application identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command<InternshipApplication> {

    public static final String COMMAND_WORD = "/d";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the internship application identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_INTERNSHIP_APPLICATION_SUCCESS = "Deleted internship application: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model<InternshipApplication> model) throws CommandException {
        requireNonNull(model);
        List<InternshipApplication> lastShownList = model.getFilteredList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        InternshipApplication internshipApplicationToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteItem(internshipApplicationToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_INTERNSHIP_APPLICATION_SUCCESS,
                Messages.format(internshipApplicationToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}

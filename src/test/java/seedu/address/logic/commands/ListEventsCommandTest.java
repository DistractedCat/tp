package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.MeetingBuilder;

public class ListEventsCommandTest {

    @Test
    public void modelStubWorksAsIntended() {
        ModelStubAcceptingEvent modelStub = new ModelStubAcceptingEvent();

        assertEquals(1, modelStub.getFilteredEventList().size());
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        ModelStubAcceptingEvent modelStub = new ModelStubAcceptingEvent();
        ListEventsCommand listEventCommand = new ListEventsCommand();

        CommandResult commandResult = listEventCommand.execute(modelStub);

        assertEquals(ListEventsCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());

        assertEquals(2, modelStub.getFilteredEventList().size());
    }

    private class ModelStub implements Model {

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook addressBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFullPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getFilteredEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEvent(Event target, Event editedEvent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEvent(Event target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEventList(Predicate<Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEvent(Event toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Set<Name> findInvalidNames(Set<Name> names) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Set<Group> findInvalidGroups(Set<Group> groups) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Set<Group> getEmptyGroups(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeEmptyGroups(Set<Group> emptyGroups) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateGroups() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateAssignedPersons(Person personToEdit, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateAssignedPersons(Person personToDelete) {
            throw new AssertionError("This method should not be called.");
        }
    }



    private class ModelStubAcceptingEvent extends ModelStub {
        private ObservableList<Event> eventList = FXCollections.observableArrayList();
        private final FilteredList<Event> filteredEventList = new FilteredList<>(eventList);

        public ModelStubAcceptingEvent() {
            try {
                reset();
            } catch (ParseException e) {
                throw new AssertionError("reset failed");
            }
        }

        @Override
        public void updateFilteredEventList(Predicate<Event> predicate) {
            filteredEventList.setPredicate(predicate);
        }

        @Override
        public FilteredList<Event> getFilteredEventList() {
            return filteredEventList;
        }


        private void reset() throws ParseException {
            eventList.add(new MeetingBuilder()
                    .withEventName("Meeting 1")
                    .withEventDate("2025-10-10")
                    .build());

            eventList.add(new MeetingBuilder()
                    .withEventName("Meeting 2")
                    .withEventDate("2025-10-10")
                    .build());


            filteredEventList.setPredicate(event -> event.getName().toString().equals("Meeting 1"));

        }

    }
}
package seedu.address.model.event;

import java.util.Comparator;

/**
 * Represents a EventComparator in the address book.
 */
public class EventComparator implements Comparator<Event> {
    /**
     * Compares its two arguments for order.
     * @param event1 First event to be compared.
     * @param event2 Second event to be compared
     * @return a negative integer, zero, or a positive integer based on the LocalDatetime order.
     */
    public int compare(Event event1, Event event2) {
        if (event1.getStartDate().getDate().isBefore(event2.getStartDate().getDate())) {
            return 1;
        } else if (event1.getStartDate().getDate().isAfter(event2.getStartDate().getDate())) {
            return -1;
        } else if (event1.getStartTime().getEventTime().isBefore(event2.getStartTime().getEventTime())) {
            return 1;
        } else if (event1.getStartTime().getEventTime().isAfter(event2.getStartTime().getEventTime())) {
            return -1;
        } else {
            return 0;
        }
    }
}
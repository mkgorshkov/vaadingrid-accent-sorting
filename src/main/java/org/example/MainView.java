package org.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.router.Route;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The main view contains a button and a click listener.
 */
@Route
@PWA(name = "My Application", shortName = "My Application")
public class MainView extends VerticalLayout {

    public MainView() {
//        Button button = new Button("Click me",
//                event -> Notification.show("Clicked!"));
//        add(button);

        List<Person> personList = new ArrayList<>();

        personList.add(new Person(100, "Edward", "Kane", 68,
                "12080 Washington", "127-942-237"));
        personList.add(new Person(101, "Emily", "Buchanan", 38,
                "93849 New York", "201-793-488"));
        personList.add(new Person(102, "Éloi", "Lee", 53,
                "86829 New York", "043-713-538"));
        personList.add(new Person(103, "Anton", "Ross", 37,
                "63521 New York", "150-813-6462"));
        personList.add(new Person(104, "Aaron", "Atkinson", 18,
                "25415 Washington", "321-679-8544"));
        personList.add(new Person(105, "Jack", "Woodward", 28,
                "95632 New York", "187-338-588"));

        Grid<Person> grid = new Grid<>(Person.class);
        grid.setItems(personList);

        grid.removeColumnByKey("id");

        grid.setColumns("firstName", "lastName", "age", "address",
                "phoneNumber");

        add(grid);

        Grid<Person> accent_sortable_grid = new Grid<>(Person.class);
        accent_sortable_grid.setItems(personList);

        accent_sortable_grid.removeColumnByKey("id");

        accent_sortable_grid.setColumns("firstName", "lastName", "age", "address",
                "phoneNumber");

        accent_sortable_grid.getColumnByKey("firstName")
                .setComparator(new Comparator<Person>() {
                    @Override
                    public int compare(Person person, Person person2) {
                        String first = (String) person.getFirstName();
                        String last = (String) person2.getFirstName();

                        first = stripAccents(first);
                        last = stripAccents(last);

                        return first.compareToIgnoreCase(last);
                    }
                });
        add(accent_sortable_grid);
    }

    private static String stripAccents(String s){
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }
}

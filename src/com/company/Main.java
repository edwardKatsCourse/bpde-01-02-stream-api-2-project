package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static Set<Movie> movies = new HashSet<Movie>() {{
        add(new Movie("Avengers: Infinity War", 2018));
        add(new Movie("Deadpool 2", 2018));
        add(new Movie("Aquaman", 2018));
        add(new Movie("Logan", 2017));
        add(new Movie("It", 2017));
        add(new Movie("Blade Runner 2049", 2017));
    }};

    static List<User> users = new ArrayList<>();

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        run();
    }

    static void run() {
        //Before USER
        User user = enter();

        //After USER
        while (true) {
            Integer optionChosen = menu();
            if (!optionChosen.equals(1) && !optionChosen.equals(3)
                    && !optionChosen.equals(4)) {

                System.out.println("You have chosen an invalid option");
                continue;
            }

            if (optionChosen.equals(1)) {
                System.out.println("Enter movie name: ");
                String name = new Scanner(System.in).nextLine();

                System.out.println("Enter movie year: ");
                String stringYear = new Scanner(System.in).nextLine();

                Integer year;
                if (stringYear == null || stringYear.isEmpty()) {
                    year = null;
                } else {
                    year = Integer.parseInt(stringYear);
                }

                //Found movies
                List<Movie> foundMovies = searchMovies(name, year);

                //Printing found movies
                for (int i = 0; i < foundMovies.size(); i++) {

                    System.out.printf("%s. %s - %s%n",
                            i + 1,
                            foundMovies.get(i).getName(),
                            foundMovies.get(i).getYear());
                }

                //Exit option is the number of movies + 1.
                //Out 10 movies, exit 11th option.
                Integer exitOption = foundMovies.size() + 1;
                System.out.println(exitOption + ". Exit");
                Integer chosenOption = new Scanner(System.in).nextInt();
                if (!chosenOption.equals(exitOption)) {
                    Set<Movie> userCollection = user.getMyCollection();
                    if (userCollection == null) {
                        user.setMyCollection(new HashSet<>());
                        userCollection = user.getMyCollection();
                    }
                    userCollection.add(foundMovies.get(chosenOption - 1));
                    user.setMyCollection(userCollection);
                }

            } else if (optionChosen.equals(3)) {
                //Shows My Collection
                user.getMyCollection().forEach(System.out::println);
            } else if (optionChosen.equals(4)) {
                break;
            }
            System.out.println("-------------------");
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
        }
        run();
    }


    static List<Movie> searchMovies(final String name, final Integer year) {
        return movies
                .stream()
                .filter(x -> name == null || name.isEmpty() ? true : x.getName().equals(name))
                .filter(x -> year == null ? true : x.getYear().equals(year))
                .collect(Collectors.toList());
    }

    static Integer menu() {
        System.out.println("1. Search for the movie");
//        System.out.println("2. Add movie to My Collection");
        System.out.println("3. See My Collection");
        System.out.println("4. Exit");

        Integer option = scanner.nextInt();
        return option;
    }

    static User enter() {
        System.out.println("Enter your name");
        String name = scanner.nextLine();
        while (name == null || name.trim().equals("")
                || name.trim().length() < 2) {
            System.out.println("You have to enter a valid name:");
            name = scanner.nextLine();
        }

        String finalName = name;
        User user = users.stream()
                .filter(x -> x.getName().equals(finalName))
                .findAny()
                .orElse(null);
        if (user == null) {
            user = new User(finalName);
            users.add(user);
        }
        Integer myCollectionSize = user.getMyCollection() == null ? 0 : user.getMyCollection().size();
        System.out.printf("Welcome %s! You have %s movies%n", user.getName(), myCollectionSize);
        System.out.println("--------------------------");
        return user;
    }
}



package com.example.filmreviewapp;

import com.example.filmreviewapp.entity.*;
import com.example.filmreviewapp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AddToDatabase implements CommandLineRunner {
    private final ActorRepository actorRepository;
    private final AppUserRepository appUserRepository;
    private final MovieRepository movieRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final DirectorRepository directorRepository;
    private final MovieReviewRepository movieReviewRepository;

    @Override
    public void run(String... args) throws Exception {
        // Suicide Squad, Fast and Furious
        Actor actor1 = new Actor("John", "Cena",
                new SimpleDateFormat("yyyy-MM-dd").parse("1977-04-23"),
                "John Felix Anthony Cena was born on April 23, 1977 in West Newbury, Massachusetts to Carol Cena and John Cena. " +
                        "He is of Italian (father) and French-Canadian and English (mother) descent.");

        // Godfather
        Actor actor2 = new Actor("Marlon", "Brando", new SimpleDateFormat("yyyy-MM-dd").parse("1924-04-03"),
                "Marlon Brando is widely considered the greatest movie actor of all time, " +
                        "rivaled only by the more theatrically oriented Laurence Olivier in terms of esteem.");

        // Godfather
        Actor actor3 = new Actor("Al", "Pacino", new SimpleDateFormat("yyyy-MM-dd").parse("1940-04-25"),
                "Alfredo James \"Al\" 'Pacino established himself as a film actor during one of cinema's most vibrant decades, " +
                        "the 1970s, and has become an enduring and iconic figure in the world of American movies. ");

        // Avatar
        Actor actor4 = new Actor("Zoe", "Saldana", new SimpleDateFormat("yyyy-MM-dd").parse("1978-06-19"),
                "Zoe Saldana was born on June 19, 1978 in Passaic, New Jersey, to Asalia Nazario and Aridio Saldaña." +
                        " Her father was Dominican and her mother is Puerto Rican.");

        // Joker
        Actor actor5 = new Actor("Joaquin", "Phoenix", new SimpleDateFormat("yyyy-MM-dd").parse("1974-10-28"),
                "Joaquin Phoenix was born Joaquin Rafael Bottom in San Juan, Puerto Rico, to Arlyn (Dunetz) and John Bottom, " +
                        "and is the middle child in a brood of five.");

        Actor actor6 = new Actor("Robert", "de Niro", new SimpleDateFormat("yyyy-MM-dd").parse("1943-07-17"),
                "One of the greatest actors of all time, Robert De Niro was born on August 17, 1943 in Manhattan, New York City, " +
                        "to artists Virginia (Admiral) and Robert De Niro Sr. ");

        Actor actor7 = new Actor("Johnny", "Depp", new SimpleDateFormat("yyyy-MM-dd").parse("1963-06-09"),
                "Johnny Depp is perhaps one of the most versatile actors of his day and age in Hollywood. ");

        // Pirates
        Actor actor8 = new Actor("Orlando", "Bloom", new SimpleDateFormat("yyyy-MM-dd").parse("1972-01-18"),
                "Often cast in historical or fantasy-based \"costume epic\" roles (Kingdom of Heaven, " +
                        "The Lord of the Rings, Troy, Pirates of the Caribbean)");

        actorRepository.save(actor1);
        actorRepository.save(actor2);
        actorRepository.save(actor3);
        actorRepository.save(actor4);
        actorRepository.save(actor5);
        actorRepository.save(actor6);
        actorRepository.save(actor7);
        actorRepository.save(actor8);

        Movie movie1 = new Movie("Joker", 2019, 130,
                "A mentally troubled stand-up comedian embarks on a downward spiral that leads to the creation of an iconic villain.");

        Movie movie2 = new Movie(
                "Avatar", 2009, 114,
                "A paraplegic Marine dispatched to the moon Pandora on a unique mission " +
                        "becomes torn between following his orders and protecting the world he feels is his home.");

        Movie movie3 = new Movie("The Godfather", 1972, 175,
                "The aging patriarch of an organized crime dynasty in postwar New York City transfers control" +
                        " of his clandestine empire to his reluctant youngest son.");

        Movie movie4 = new Movie("The Suicide Squad", 2021, 132,
                "Supervillains Harley Quinn, Bloodsport, Peacemaker, " +
                        "and a collection of nutty cons at Belle Reve prison join the super-secret, " +
                        "super-shady Task Force X as they are dropped off at the remote, " +
                        "enemy-infused island of Corto Maltese.");

        Movie movie5 = new Movie("Fast and Furious 9", 2021, 143,
                "Dom and the crew must take on an international terrorist " +
                        "who turns out to be Dom and Mia's estranged brother.");

        // Johnny Depp, Donald Trump
        Movie movie6 = new Movie("The Art of the Deal: The Movie", 2016, 50,
                "Donald Trump has it all. Money, power, respect, and an Eastern European bride." +
                        " But all his success didn't come for nothing. " +
                        "First, he inherited millions of dollars from his rich father.");

        Movie movie7 = new Movie("Pirates of the Caribbean: At World's End", 2007, 169,
                "After Elizabeth, Will, and Captain Barbossa rescue Captain " +
                        "Jack Sparrow from the land of the dead, they must face their foes, " +
                        "Davy Jones and Lord Cutler Beckett.");

        movieRepository.save(movie1);
        movieRepository.save(movie2);
        movieRepository.save(movie3);
        movieRepository.save(movie4);
        movieRepository.save(movie5);
        movieRepository.save(movie6);
        movieRepository.save(movie7);

        // Godfather
        Director director1 = new Director("Francis Ford", "Coppola", new SimpleDateFormat("yyyy-MM-dd").parse("1939-04-07"),
                "Francis Ford Coppola was born in 1939 in Detroit, Michigan, " +
                        "but grew up in a New York suburb in a creative, supportive Italian-American family.");

        // F&F9
        Director director2 = new Director("Justin", "Lin", new SimpleDateFormat("yyyy-MM-dd").parse("1971-10-11"),
                "Justin Lin is a Taiwanese-American film director whose films have grossed $2 billion worldwide.");

        // Avatar
        Director director3 = new Director("James", "Cameron", new SimpleDateFormat("yyyy-MM-dd").parse("1954-08-16"),
                "James Francis Cameron was born on August 16, 1954 in Kapuskasing, Ontario, Canada. " +
                        "He moved to the United States in 1971. The son of an engineer, he majored in physics at California State University");

        // Joker
        Director director4 = new Director("Todd", "Phillips", new SimpleDateFormat("yyyy-MM-dd").parse("1970-12-20"),
                "Todd Phillips is an American filmmaker and actor who got his start by directing the comedy films Road Trip and Old School, " +
                        "the earlier inspired EuroTrip.");

        // Suicide Squad
        Director director5 = new Director("James", "Gunn", new SimpleDateFormat("yyyy-MM-dd").parse("1966-08-05"),
                "James Gunn was born and raised in St. Louis, Missouri, to Leota and James Francis Gunn. " +
                        "He is from a large Catholic family, with Irish and Czech ancestry.");

        Director director6 = new Director("Donald", "Trump", new SimpleDateFormat("yyyy-MM-dd").parse("1969-04-20"),
                "Donald Trump took a small loan of a million dollars.");

        Director director7 = new Director("Gore", "Verbinski", new SimpleDateFormat("yyyy-MM-dd").parse("1974-08-16"),
               "Gore Verbinski, one of American cinema's most inventive directors who was a " +
                       "punk-rock guitarist as a teenager and had to sell his guitar to buy his first camera");

        directorRepository.save(director1);
        directorRepository.save(director2);
        directorRepository.save(director3);
        directorRepository.save(director4);
        directorRepository.save(director5);
        directorRepository.save(director6);
        directorRepository.save(director7);

        // Joker (1)

        actor5.addNewMovie(movie1);
        actor6.addNewMovie(movie1);
        director4.addNewMovie(movie1);

        movie1.addNewDirector(director4);
        movie1.addNewActor(actor5);
        movie1.addNewActor(actor6);


        // Avatar (2)

        actor3.addNewMovie(movie2);
        actor4.addNewMovie(movie2);
        director3.addNewMovie(movie2);

        movie2.addNewDirector(director3);
        movie2.addNewActor(actor3);
        movie2.addNewActor(actor4);

        // Godfather (3)

        actor2.addNewMovie(movie3);
        actor3.addNewMovie(movie3);
        director1.addNewMovie(movie3);

        movie3.addNewDirector(director1);
        movie3.addNewActor(actor2);
        movie3.addNewActor(actor3);

        // Suicide Squad (4)

        director5.addNewMovie(movie4);
        actor1.addNewMovie(movie4);
        actor6.addNewMovie(movie4);

        movie4.addNewDirector(director5);
        movie4.addNewActor(actor1);
        movie4.addNewActor(actor6);

        // Fast & Furious 9 (5)

        director2.addNewMovie(movie5);
        actor1.addNewMovie(movie5);

        movie5.addNewDirector(director2);
        movie5.addNewActor(actor1);

        // Donald Trump's Art of the Deal (6)

        director6.addNewMovie(movie6);
        actor7.addNewMovie(movie6);

        movie6.addNewActor(actor7);
        movie6.addNewDirector(director6);

        // Pirates of the Caribbean (7)

        director7.addNewMovie(movie7);
        actor7.addNewMovie(movie7);
        actor8.addNewMovie(movie7);

        movie7.addNewDirector(director7);
        movie7.addNewActor(actor7);
        movie7.addNewActor(actor8);

        // Total save

        actorRepository.save(actor1);
        actorRepository.save(actor2);
        actorRepository.save(actor3);
        actorRepository.save(actor4);
        actorRepository.save(actor5);
        actorRepository.save(actor6);
        actorRepository.save(actor7);
        actorRepository.save(actor8);

        movieRepository.save(movie1);
        movieRepository.save(movie2);
        movieRepository.save(movie3);
        movieRepository.save(movie4);
        movieRepository.save(movie5);
        movieRepository.save(movie6);
        movieRepository.save(movie7);

        directorRepository.save(director1);
        directorRepository.save(director2);
        directorRepository.save(director3);
        directorRepository.save(director4);
        directorRepository.save(director5);
        directorRepository.save(director6);
        directorRepository.save(director7);

        AppUser user1 = new AppUser();
        user1.setName("Andrzej");
        user1.setSurname("Drwal");
        user1.setEmail("notadmin@admin.com");
        user1.setPassword(passwordEncoder.encode("password"));
        user1.setRole(Role.USER);

        AppUser user2 = new AppUser();
        user2.setName("Jonasz");
        user2.setSurname("Zielony");
        user2.setEmail("test2@test.com");
        user2.setPassword(passwordEncoder.encode("password"));
        user2.setRole(Role.USER);

        AppUser user3 = new AppUser();
        user3.setName("Katarzyna");
        user3.setSurname("Jankowska");
        user3.setEmail("test3@test.com");
        user3.setPassword(passwordEncoder.encode("password"));
        user3.setRole(Role.USER);

        AppUser user4 = new AppUser();
        user4.setName("Joanna");
        user4.setSurname("Panna");
        user4.setEmail("test4@test.com");
        user4.setPassword(passwordEncoder.encode("password"));
        user4.setRole(Role.USER);

        AppUser user5 = new AppUser();
        user5.setName("Daniel");
        user5.setSurname("Koziar");
        user5.setEmail("test5@test.com");
        user5.setPassword(passwordEncoder.encode("password"));
        user5.setRole(Role.USER);

        AppUser admin = new AppUser();
        admin.setName("Marco");
        admin.setSurname("Born");
        admin.setEmail("admin@admin.com");
        admin.setPassword(passwordEncoder.encode("password"));
        admin.setRole(Role.ADMIN);

        appUserRepository.save(admin);
        appUserRepository.save(user1);
        appUserRepository.save(user2);
        appUserRepository.save(user3);
        appUserRepository.save(user4);
        appUserRepository.save(user5);

        MovieReview movieReview1 = new MovieReview(user1, movie1, 5);
        MovieReview movieReview2 = new MovieReview(user1, movie2, 3.5);
        MovieReview movieReview3 = new MovieReview(user1, movie3, 4);
        MovieReview movieReview4 = new MovieReview(user1, movie4, 2);
        MovieReview movieReview5 = new MovieReview(user1, movie5, 5);
        /*MovieReview movieReview6 = new MovieReview(user1, movie6, 2);
        MovieReview movieReview7 = new MovieReview(user1, movie7, 4);
        MovieReview movieReview8 = new MovieReview(admin, movie1, 5);
        MovieReview movieReview9 = new MovieReview(admin, movie2, 1);
        MovieReview movieReview10 = new MovieReview(admin, movie3, 1);
        MovieReview movieReview11 = new MovieReview(admin, movie4, 1);
        MovieReview movieReview12 = new MovieReview(admin, movie5, 4);
        MovieReview movieReview13 = new MovieReview(admin, movie6, 3);
        MovieReview movieReview14 = new MovieReview(admin, movie7, 5);*/

        movieReviewRepository.save(movieReview1);
        movieReviewRepository.save(movieReview2);
        movieReviewRepository.save(movieReview3);
        movieReviewRepository.save(movieReview4);
        movieReviewRepository.save(movieReview5);
        /*movieReviewRepository.save(movieReview6);
        movieReviewRepository.save(movieReview7);
        movieReviewRepository.save(movieReview8);
        movieReviewRepository.save(movieReview9);
        movieReviewRepository.save(movieReview10);
        movieReviewRepository.save(movieReview11);
        movieReviewRepository.save(movieReview12);
        movieReviewRepository.save(movieReview13);
        movieReviewRepository.save(movieReview14);*/

    }
}
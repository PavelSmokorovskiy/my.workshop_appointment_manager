package wam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import wam.model.Appointment;
import wam.model.User;
import wam.model.Workshop;
import wam.repository.AppointmentRepository;
import wam.repository.UserRepository;
import wam.repository.WorkshopRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Configuration
@EnableJpaRepositories
@EnableJpaAuditing
@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger logger = LogManager.getLogger(Application.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkshopRepository workshopRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public static void main(String[] args) {
        logger.info("Start Log4j2 logger");
        SpringApplication.run(Application.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) {

        User user1 = new User("username1"
                , "email1"
                , "city1"
                , "postalCode1"
                , "county1");
        User user2 = new User("username2"
                , "email2"
                , "city2"
                , "postalCode2"
                , "county2");
        userRepository.save(user1);
        userRepository.save(user2);

        Workshop workshop1 = new Workshop("companyName1"
                , "trademark1"
                , "city1"
                , "postalcode1"
                , "country1");
        Workshop workshop2 = new Workshop("companyName2"
                , "trademark2"
                , "city2"
                , "postalcode2"
                , "country2");
        workshopRepository.save(workshop1);
        workshopRepository.save(workshop2);

        Appointment appointment1 = new Appointment(user1
                , workshop1
                , LocalDateTime.now());
        Appointment appointment2 = new Appointment(user2
                , workshop2
                , LocalDateTime.now());
        appointmentRepository.save(appointment1);
        appointmentRepository.save(appointment2);

    }

}

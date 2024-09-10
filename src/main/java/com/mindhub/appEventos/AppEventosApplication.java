package com.mindhub.appEventos;

import com.mindhub.appEventos.models.*;
import com.mindhub.appEventos.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class AppEventosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppEventosApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(CustomerRepository customerRepository, EventRepository eventRepository, CommentRepository commentRepository, EventLocationRepository eventLocationRepository, LocationRepository locationRepository){
		return args -> {

			Customer customer1 = new Customer("santiago","gamarra","santi@gmail.com","123",31,true, Gender.MALE);
			Event event1 = new Event("20","img_demo","exampleName",18);
			Comment comment1 = new Comment("comentario de prueba");
			Location location1 = new Location("locationNombre","imgprueba","locationprueba",300);
			EventLocation eventLocation1 = new EventLocation(LocalDate.now().plusWeeks(3),150);

			customerRepository.save(customer1);
			locationRepository.save(location1);


			CustomerEvent customerEvent1 = new CustomerEvent();
			customer1.addCustomerEvent(customerEvent1);
			eventLocation1.addCustomerEvent(customerEvent1);

			event1.addEventLocation(eventLocation1);
			location1.addEventLocation(eventLocation1);


			customer1.addEvent(event1);
			customer1.addComment(comment1);
			event1.addComment(comment1);


			eventRepository.save(event1);
			commentRepository.save(comment1);
			eventLocationRepository.save(eventLocation1);
		};
	}
}

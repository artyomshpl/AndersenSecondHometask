package com.shep.fourthlecture.services;

import com.shep.fourthlecture.models.Admin;
import com.shep.fourthlecture.models.Client;
import com.shep.fourthlecture.models.Ticket;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class TicketService {
    private List<Ticket> tickets = new ArrayList<>();

    public void saveTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void saveTicket(String concertHall, String eventCode, Long time) {
        saveTicket(new Ticket(concertHall, eventCode, time));
    }

    public void saveTicket(String concertHall, String eventCode, Long time, boolean isPromo, char stadiumSector, double maxBackpackWeight, double price) {
        saveTicket(new Ticket(concertHall, eventCode, time, isPromo, stadiumSector, maxBackpackWeight, price));
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public static void main(String[] args) {
        TicketService ticketService = new TicketService();

        long unixTimestamp = Instant.now().getEpochSecond();
        long unixTimestampOtherTime = Instant.now().plus(Duration.ofDays(1)).plus(Duration.ofHours(2)).getEpochSecond();

        //Saving tickets in different ways (Saving not using JPA)
        ticketService.saveTicket(new Ticket());
        ticketService.saveTicket("Hall A", "123", unixTimestamp);
        ticketService.saveTicket("Hall B", "789", unixTimestampOtherTime);
        ticketService.saveTicket(new Ticket("Hall C", "012", unixTimestampOtherTime));
        ticketService.saveTicket("Hall D", "456", unixTimestampOtherTime, false, 'C', 5.0, 7.0);


        // Print all tickets
        System.out.println("Printing tickets saved in different ways");

        for (Ticket ticket : ticketService.getTickets()) {
            System.out.println(ticket);
        }

        // Demonstrate polymorphism with User, Client, and Admin
        Client client = new Client("1", "Carlson");
        Admin admin = new Admin("2");

        Ticket ticketForChecking = new Ticket("Hall M", "312", unixTimestampOtherTime, true, 'B', 15.0, 25.0);
        System.out.println("\nCreating ticket for testing client and user. Ticket: " + ticketForChecking);
        System.out.println("Printing users with roles. " +
                "Also demonstrating the polymorphism represented in unique functions of users with different roles");
        client.printRole();
        client.getTicket(ticketForChecking);

        admin.printRole();
        admin.checkTicket(ticketForChecking);

        Ticket ticketForSharing = new Ticket("Hall E", "789", unixTimestampOtherTime, true, 'B', 10.0, 20.0);
        System.out.println("\nCreating ticket for testing sharing. Ticket: " + ticketForSharing);

        System.out.println("Printing logs of sharing tickets via number and email. Representing polymorphism");
        ticketForSharing.share("123-456-789");
        ticketForSharing.share("987-654-321","user@gmail.com");

        //Demonstrating overridden methods ".equals" and ".hashCode" inherited from Object class
        Ticket ticket1 = new Ticket("Hall X", "111", unixTimestamp, true, 'A', 8.0, 18.0);
        Ticket ticket2 = new Ticket("Hall X", "111", unixTimestamp, true, 'A', 8.0, 18.0);
        Ticket ticket3 = new Ticket("Hall Y", "333", unixTimestamp, false, 'B', 9.0, 19.0);

        System.out.println("\nTesting hashCode and equals methods");
        System.out.println("ticket1: " + ticket1);
        System.out.println("ticket2: " + ticket2);
        System.out.println("ticket3: " + ticket3);

        System.out.println("ticket1 equals ticket2: " + ticket1.equals(ticket2));
        System.out.println("ticket1 equals ticket3: " + ticket1.equals(ticket3));

        System.out.println("ticket1.hashCode(): " + ticket1.hashCode());
        System.out.println("ticket2.hashCode(): " + ticket2.hashCode());
        System.out.println("ticket3.hashCode(): " + ticket3.hashCode());
    }
}


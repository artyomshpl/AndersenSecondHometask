package com.shep.fourthlecture.models;

import com.shep.fourthlecture.models.base.User;
import com.shep.fourthlecture.utils.DateTimeUtil;

import java.time.LocalDateTime;

public class Admin extends User {
    Ticket ticket;

    public Admin(String id) {
        super(id, "Admin");
    }

    public void checkTicket(Ticket ticket) {
        this.ticket = ticket;

        if(DateTimeUtil.convertUnixTimestampToLocalDateTime(ticket.getTime()).compareTo(LocalDateTime.now()) > 0){
            System.out.println("Ticket is valid");
        } else {
            System.out.println("Ticket is expired");
        }
    }
}
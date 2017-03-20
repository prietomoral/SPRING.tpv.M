package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.TicketDao;
import entities.core.Ticket;

@Controller
public class TicketController {

    private TicketDao ticketDao;

    @Autowired
    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public List<Ticket> findAllTickets() {
        return ticketDao.findAll();
    }

}

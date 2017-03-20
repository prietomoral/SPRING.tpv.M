package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.TicketController;
import entities.core.Ticket;

@RestController
@RequestMapping(Uris.VERSION + Uris.TICKETS)
public class TicketResource {

    TicketController ticketController;

    @Autowired
    public void setTicketController(TicketController ticketController) {
        this.ticketController = ticketController;
    }

    @RequestMapping(value = Uris.TICKETS, method = RequestMethod.GET)
    public List<Ticket> findAllTickets() {
        return ticketController.findAllTickets();
    }
}

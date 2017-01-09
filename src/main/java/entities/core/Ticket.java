package entities.core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import entities.users.User;

@Entity
public class Ticket {

    @Id
    private long id;

    private Calendar created;

    @Enumerated(EnumType.STRING)
    private TicketState ticketState;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Shopping> shoppingList;

    @ManyToOne
    @JoinColumn
    private User user;

    public Ticket() {
    }

    public Ticket(long id, TicketState ticketState) {
        this.id = id;
        created = Calendar.getInstance();
        this.ticketState = ticketState;
        shoppingList = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public TicketState getTicketState() {
        return ticketState;
    }

    public void setTicketState(TicketState ticketState) {
        this.ticketState = ticketState;
    }

    public void addShopping(Shopping shopping) {
        shoppingList.add(shopping);
    }

    public List<Shopping> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(List<Shopping> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Calendar getCreated() {
        return created;
    }

    @Override
    public int hashCode() {
        return (int) id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return id == ((Ticket) obj).id;
    }

    @Override
    public String toString() {
        String createTime = new SimpleDateFormat("HH:00 dd-MMM-yyyy ").format(created.getTime());
        return "Ticket[" + id + ": created=" + createTime + ", ticketState=" + ticketState + ", shoppingList=" + shoppingList + ", userId="
                + user.getId() + "]";
    }

}

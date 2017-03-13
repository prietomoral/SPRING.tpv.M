package wrappers;


public class IdTicketWrapper {
   
    private long id;
    
    public IdTicketWrapper(){
        
    }
    
    public IdTicketWrapper(long id){
        this.id = id;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TicketWrapper [id=" + id + "]";
    }
       
}

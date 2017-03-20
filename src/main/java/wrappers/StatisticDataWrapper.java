package wrappers;

import java.util.Calendar;

public class StatisticDataWrapper {

    Long id;
    
    Calendar inicio;
    
    Calendar fin;

    public StatisticDataWrapper() {

    }

    public StatisticDataWrapper(Long id, Calendar inicio, Calendar fin) {
        super();
        this.id = id;
        this.inicio = inicio;
        this.fin = fin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getInicio() {
        return inicio;
    }

    public void setInicio(Calendar inicio) {
        this.inicio = inicio;
    }

    public Calendar getFin() {
        return fin;
    }

    public void setFin(Calendar fin) {
        this.fin = fin;
    }

    @Override
    public String toString() {
        return "StatisticDataWrapper [id=" + id + ", inicio=" + inicio + ", fin=" + fin + "]";
    }
    
    
    
}

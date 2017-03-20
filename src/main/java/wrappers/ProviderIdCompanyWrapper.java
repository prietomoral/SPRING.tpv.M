package wrappers;

public class ProviderIdCompanyWrapper {
	private int id;

    private String company;

    public ProviderIdCompanyWrapper() {

    }

    public ProviderIdCompanyWrapper(int id, String company) {
        this.id = id;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return id == ((ProviderIdCompanyWrapper) obj).id;
    }

    @Override
    public String toString() {
        return "ProviderWrapper [id=" + id + ", company=" + company + "]";
    }
}

package wrappers;

public class IdentificationVoucherWrapper {
	
	private String identification;

	public IdentificationVoucherWrapper() {
	}
	
	public IdentificationVoucherWrapper(String identification) {
		this.identification = identification;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}


    @Override
    public String toString() {
        return "Identification Voucher [identification=" + identification + "]";
    }
	

}

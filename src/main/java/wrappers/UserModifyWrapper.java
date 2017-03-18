package wrappers;

public class UserModifyWrapper {
	
	private int id;

    private long mobile;

    private String username;
    
    private String dni;
    
    private String address;

    private String email;

    private String password;

    public UserModifyWrapper() {
    }

    public UserModifyWrapper(int id, long mobile, String username, String email, String dni, String address, String password) {
    	this.id = id;
        this.mobile = mobile;
        this.username = username;
        this.email = email;
        this.dni = dni;
        this.address = address;
        this.password = password;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserModifyWrapper [id=" + id + ", mobile=" + mobile + ", username=" + username + ", dni=" + dni
				+ ", address=" + address + ", email=" + email + ", password=" + password + "]";
	}

}

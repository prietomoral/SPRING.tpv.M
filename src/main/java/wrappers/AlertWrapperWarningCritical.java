package wrappers;

public class AlertWrapperWarningCritical {

	private long id;
	private String descripcion;
	private int stock;
	private int warning;
	private int critical;

	public AlertWrapperWarningCritical(long id, String descripcion, int stock, int warning, int critical) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.stock = stock;
		this.warning = warning;
		this.critical = critical;
	}

	public AlertWrapperWarningCritical() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getWarning() {
		return warning;
	}

	public void setWarning(int warning) {
		this.warning = warning;
	}

	public int getCritical() {
		return critical;
	}

	public void setCritical(int critical) {
		this.critical = critical;
	}

}

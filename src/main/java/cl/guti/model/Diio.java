package cl.guti.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="DIIO")
public class Diio {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="DIIO_ID")
    private int id;

    @Column(name="NRO_DIIO")
    private String nroDiio;

    @Column(name="FECHA_INSTALL")
    private String fechaInstall;

    @Column(name="DESCRIPCION")
    private String desc;

    @Column(name="FECHA_NACIMIENTO")
    private String fechaNacimiento;

    //muchos diios pertenecen a un campo
    
    @ManyToOne
    @JoinColumn(name="campo_id")
    private Campo campo;
    
	/*
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "CAMPO_ID", nullable = false)
	 * 
	 * @JsonIgnore private Campo campo;
	 */
    
    
	/*
	 * @ManyToOne(fetch = FetchType.LAZY, optional = true)
	 * 
	 * @JoinColumn(name = "menu_parent", nullable = true)
	 */
    public Diio() {}

	public Diio(int id, String nroDiio, String fechaInstall, String desc, String fechaNacimiento) {
		super();
		this.id = id;
		this.nroDiio = nroDiio;
		this.fechaInstall = fechaInstall;
		this.desc = desc;
		this.fechaNacimiento = fechaNacimiento;
	}
	
	  public int getId() { return id; }
	  
	  public void setId(int id) { this.id = id; }
	 

	public String getNroDiio() {
		return nroDiio;
	}

	public void setNroDiio(String nroDiio) {
		this.nroDiio = nroDiio;
	}

	public String getFechaInstall() {
		return fechaInstall;
	}

	public void setFechaInstall(String fechaInstall) {
		this.fechaInstall = fechaInstall;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Campo getCampo() {
		return campo;
	}

	public void setCampo(Campo campo) {
		this.campo = campo;
	}

    
}

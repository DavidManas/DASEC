package com.dasec.spring.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/* Este código define una entidad JPA llamada "Orden" que se mapea a la tabla "ordenes" en la base de datos. 
 * La tabla tiene cinco columnas: "id" (clave primaria generada automáticamente), "numero", "fechaCreacion", 
 * "fechaRecibida" y "total". La entidad también tiene una relación ManyToOne con la entidad "Usuario" y una 
 * relación OneToMany con la entidad "DetalleOrden", lo que indica que una orden puede tener varios detalles 
 * y que un detalle pertenece a una sola orden. El código incluye getters y setters para todos los campos 
 * y relaciones, así como un constructor vacío y un constructor con todos los campos (excepto las relaciones). 
 * También incluye un método toString que devuelve una representación en forma de cadena de la entidad.*/
@Entity
@Table(name = "ordenes")
public class Orden {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String numero;
	private Date fechaCreacion;
	private Date fechaRecibida;

	private double total;

	@ManyToOne
	private Usuario usuario;

	@OneToMany(mappedBy = "orden")
	private List<DetalleOrden> detalle;

	public Orden() {

	}

	public Orden(Integer id, String numero, Date fechaCreacion, Date fechaRecibida, double total) {
		super();
		this.id = id;
		this.numero = numero;
		this.fechaCreacion = fechaCreacion;
		this.fechaRecibida = fechaRecibida;
		this.total = total;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaRecibida() {
		return fechaRecibida;
	}

	public void setFechaRecibida(Date fechaRecibida) {
		this.fechaRecibida = fechaRecibida;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<DetalleOrden> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetalleOrden> detalle) {
		this.detalle = detalle;
	}

	@Override
	public String toString() {
		return "Orden [id=" + id + ", numero=" + numero + ", fechaCreacion=" + fechaCreacion + ", fechaRecibida="
				+ fechaRecibida + ", total=" + total + "]";
	}

}

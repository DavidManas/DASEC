package com.dasec.spring.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/* Este es un archivo de clase Java que define la entidad `DetalleOrden`. 
 * Esta entidad se utiliza para almacenar información detallada sobre una orden específica, como el 
 * nombre del producto, la cantidad, el precio y el total. Tiene un identificador único `id` generado 
 * automáticamente mediante la anotación `@GeneratedValue`, y tiene varias propiedades que se 
 * establecen mediante sus respectivos métodos `get` y `set`. La entidad también está relacionada 
 * con otras entidades `Orden` y `Producto` mediante las anotaciones `@ManyToOne`. La anotación `@Table` 
 * se utiliza para especificar el nombre de la tabla en la base de datos que se corresponde con 
 * esta entidad.*/
@Entity
@Table(name = "detalles")
public class DetalleOrden {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private double cantidad;
	private double precio;
	private double total;

	@ManyToOne
	private Orden orden;

	@ManyToOne
	private Producto producto;

	public DetalleOrden() {

	}

	public DetalleOrden(Integer id, String nombre, double cantidad, double precio, double total) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
		this.total = total;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Orden getOrden() {
		return orden;
	}

	public void setOrden(Orden orden) {
		this.orden = orden;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "DetalleOrden [id=" + id + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precio=" + precio
				+ ", total=" + total + "]";
	}

}

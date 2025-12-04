package com.cleon.workbook.persistencia;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RESULTADOS") // Nombre de la tabla en la base de datos
public class ResultadoCalculo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1929232398005944315L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double principal;
    private double tasa;
    private int anios;
    private double interesCalculado;
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    // Constructor vacío requerido por JPA
    public ResultadoCalculo() {}

    // Constructor para facilidad de uso
    public ResultadoCalculo(double principal, double tasa, int anios, double interesCalculado) {
        this.principal = principal;
        this.tasa = tasa;
        this.anios = anios;
        this.interesCalculado = interesCalculado;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getPrincipal() {
		return principal;
	}

	public void setPrincipal(double principal) {
		this.principal = principal;
	}

	public double getTasa() {
		return tasa;
	}

	public void setTasa(double tasa) {
		this.tasa = tasa;
	}

	public int getAnios() {
		return anios;
	}

	public void setAnios(int anios) {
		this.anios = anios;
	}

	public double getInteresCalculado() {
		return interesCalculado;
	}

	public void setInteresCalculado(double interesCalculado) {
		this.interesCalculado = interesCalculado;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

}
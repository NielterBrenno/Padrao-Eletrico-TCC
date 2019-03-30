package br.com.unip.padrao.eletrico.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "tb_historico")
public class Historico implements Serializable {
	
	private static final long serialVersionUID = 8718662941687429260L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_historico")
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	private Double gasto;

	private Double voltagem;

	private Double corrente;
	
	@ManyToOne
	@JoinColumn(name = "id_registro", nullable = false)
	private Registro registro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getGasto() {
		return gasto;
	}

	public void setGasto(Double gasto) {
		this.gasto = gasto;
	}

	public Registro getRegistro() {
		return registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

	public Double getVoltagem() {
		return voltagem;
	}

	public void setVoltagem(Double voltagem) {
		this.voltagem = voltagem;
	}
	public Double getCorrente() {
		return corrente;
	}

	public void setCorrente(Double corrente) {
		this.corrente = corrente;
	}
	
	public Double getGastoMedioEmKwh(List<Historico> historicos, int index) {
		final int key = index -1;
		if(key != -1) {
			return historicos.get(index).gasto - historicos.get(key).gasto;	
		}else
			return 0.0;
	}
	
	public Double getValorTotal(List<Historico> historicos, int index) {
		return gasto * 0.23887; 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((corrente == null) ? 0 : corrente.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((gasto == null) ? 0 : gasto.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((registro == null) ? 0 : registro.hashCode());
		result = prime * result + ((voltagem == null) ? 0 : voltagem.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Historico other = (Historico) obj;
		if (corrente == null) {
			if (other.corrente != null)
				return false;
		} else if (!corrente.equals(other.corrente))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (gasto == null) {
			if (other.gasto != null)
				return false;
		} else if (!gasto.equals(other.gasto))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (registro == null) {
			if (other.registro != null)
				return false;
		} else if (!registro.equals(other.registro))
			return false;
		if (voltagem == null) {
			if (other.voltagem != null)
				return false;
		} else if (!voltagem.equals(other.voltagem))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Historico [id=" + id + ", data=" + data + ", gasto=" + gasto + ", voltagem=" + voltagem + ", corrente="
				+ corrente + ", registro=" + registro + "]";
	}
	
	

}

package clasesHibernate;
// Generated 09-feb-2018 19:50:59 by Hibernate Tools 5.2.8.Final

/**
 * MusicaId generated by hbm2java
 */
public class MusicaId implements java.io.Serializable {

	private String propietario;
	private String nombre;
	private String cantante;

	public MusicaId() {
	}

	public MusicaId(String propietario, String nombre, String cantante) {
		this.propietario = propietario;
		this.nombre = nombre;
		this.cantante = cantante;
	}

	public String getPropietario() {
		return this.propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCantante() {
		return this.cantante;
	}

	public void setCantante(String cantante) {
		this.cantante = cantante;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MusicaId))
			return false;
		MusicaId castOther = (MusicaId) other;

		return ((this.getPropietario() == castOther.getPropietario()) || (this.getPropietario() != null
				&& castOther.getPropietario() != null && this.getPropietario().equals(castOther.getPropietario())))
				&& ((this.getNombre() == castOther.getNombre()) || (this.getNombre() != null
						&& castOther.getNombre() != null && this.getNombre().equals(castOther.getNombre())))
				&& ((this.getCantante() == castOther.getCantante()) || (this.getCantante() != null
						&& castOther.getCantante() != null && this.getCantante().equals(castOther.getCantante())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getPropietario() == null ? 0 : this.getPropietario().hashCode());
		result = 37 * result + (getNombre() == null ? 0 : this.getNombre().hashCode());
		result = 37 * result + (getCantante() == null ? 0 : this.getCantante().hashCode());
		return result;
	}

}
package clasesHibernate;
// Generated 09-feb-2018 19:50:59 by Hibernate Tools 5.2.8.Final

/**
 * CalendariosId generated by hbm2java
 */
public class CalendariosId implements java.io.Serializable {

	private String id;
	private String dia;
	private String serie;

	public CalendariosId() {
	}

	public CalendariosId(String id, String dia, String serie) {
		this.id = id;
		this.dia = dia;
		this.serie = serie;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDia() {
		return this.dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getSerie() {
		return this.serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CalendariosId))
			return false;
		CalendariosId castOther = (CalendariosId) other;

		return ((this.getId() == castOther.getId())
				|| (this.getId() != null && castOther.getId() != null && this.getId().equals(castOther.getId())))
				&& ((this.getDia() == castOther.getDia()) || (this.getDia() != null && castOther.getDia() != null
						&& this.getDia().equals(castOther.getDia())))
				&& ((this.getSerie() == castOther.getSerie()) || (this.getSerie() != null
						&& castOther.getSerie() != null && this.getSerie().equals(castOther.getSerie())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result + (getDia() == null ? 0 : this.getDia().hashCode());
		result = 37 * result + (getSerie() == null ? 0 : this.getSerie().hashCode());
		return result;
	}

}

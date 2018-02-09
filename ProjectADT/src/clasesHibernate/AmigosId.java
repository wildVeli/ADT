package clasesHibernate;
// Generated 09-feb-2018 19:50:59 by Hibernate Tools 5.2.8.Final

/**
 * AmigosId generated by hbm2java
 */
public class AmigosId implements java.io.Serializable {

	private String id;
	private String amigo;

	public AmigosId() {
	}

	public AmigosId(String id, String amigo) {
		this.id = id;
		this.amigo = amigo;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAmigo() {
		return this.amigo;
	}

	public void setAmigo(String amigo) {
		this.amigo = amigo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AmigosId))
			return false;
		AmigosId castOther = (AmigosId) other;

		return ((this.getId() == castOther.getId())
				|| (this.getId() != null && castOther.getId() != null && this.getId().equals(castOther.getId())))
				&& ((this.getAmigo() == castOther.getAmigo()) || (this.getAmigo() != null
						&& castOther.getAmigo() != null && this.getAmigo().equals(castOther.getAmigo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result + (getAmigo() == null ? 0 : this.getAmigo().hashCode());
		return result;
	}

}

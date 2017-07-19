package media.modele;

public enum TypeMedia {
	
	Livre(30), CD(15), DVD(15);
	
	private long joursEmpruntables;
	
	private TypeMedia(long joursEmpruntables) {
		this.joursEmpruntables = joursEmpruntables;
	}

	public long getJoursEmpruntables() {
		return joursEmpruntables;
	}
	
}

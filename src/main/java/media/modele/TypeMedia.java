package media.modele;

public enum TypeMedia {
	
	Livre{
		public long getJoursEmpruntables() {
			return 30L;
		}
	}, CD, DVD;

	public long getJoursEmpruntables() {
		return 15L;
	}
	
}

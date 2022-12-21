package Pack01;

public class WeatherDto {
	private int id;
	private int yyyy,mm,dd,placeno,observecd,naturecd;
	private String placenm,naturenm, observedetail;

	public WeatherDto(int id, int yyyy, int mm, int dd, int placeno, String placenm,int naturecd, String naturenm, int observecd, String observedetail) {
		this.id=id;
		this.yyyy = yyyy;
		this.mm = mm;
		this.dd = dd;
		this.placeno = placeno;
		this.placenm = placenm;
		this.naturecd = naturecd;
		this.naturenm = naturenm;
		this.observecd = observecd;
		this.observedetail = observedetail;
	}
	public WeatherDto(int id,int yyyy, int mm, int dd,  int placeno, int naturecd, int observecd) {
		this.id=id;
		this.yyyy = yyyy;
		this.mm = mm;
		this.dd = dd;
		this.placeno = placeno;
		this.naturecd = naturecd;
		this.observecd = observecd;
	}
	public WeatherDto() {}
	@Override
	public String toString() {
		return "WeatherDto [id=" + id + ", yyyy=" + yyyy + ", mm=" + mm + ", dd=" + dd + ", placeno=" + placeno
				+ ", observecd=" + observecd + ", naturecd=" + naturecd + ", placenm=" + placenm + ", naturenm="
				+ naturenm + ", observedetail=" + observedetail + "]";
	}
	public int getObservecd() {
		return observecd;
	}
	public void setObservecd(int observecd) {
		this.observecd = observecd;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setYyyy(int yyyy) {
		this.yyyy=yyyy;
	}
	public void setMm(int mm) {
		this.mm = mm;
	}
	public void setDd(int dd) {
		this.dd=dd;
	}
	public void setPlaceno(int placeno) {
		this.placeno=placeno;
	}

	public void setNaturecd(int naturecd) {
		this.naturecd= naturecd;
	}
	public void setNaturenm(String naturenm) {
		this.naturenm = naturenm;
	}
	public void setplacenm(String placenm) {
		this.placenm = placenm;
	}
	public void setObservedetail(String observedetail) {
		this.observedetail = observedetail;
	}
	public int getId() {
		return id;
	}
	public int getYyyy() {
		return yyyy;
	}
	public int getMm() {
		return mm;
	}
	public int getDd() {
		return dd;
	}
	public int getPlaceno() {
		return placeno;
	}

	public int getNaturecd() {
		return naturecd;
	}
	public String getPlacenm() {
		return placenm;
	}

	public String getNaturenm() {
		return naturenm;
	}

	public String getObservedetail() {
		return observedetail;
	}

}


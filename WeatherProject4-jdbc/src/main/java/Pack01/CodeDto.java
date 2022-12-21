package Pack01;

public class CodeDto {
	private int cd;
	private String cdName;
	public CodeDto(int cd, String cdName) {
		this.cd = cd;
		this.cdName = cdName;
	}
	public CodeDto() {}
	public int getCd() {
		return cd;
	}
	public String getCdName() {
		return cdName;
	}

	public void setCd(int cd) {
		this.cd = cd;
	}
	public void setCdName(String cdName) {
		this.cdName = cdName;
	}
	@Override
	public String toString() {
		return "CodeDto [cd=" + cd + ", cdName=" + cdName + "]";
	}
}

package materials;

public class Disk
{
	public Disk(int pegn,int diskn,int size)
	{
		peg=pegn;
		setSize(size);
		setDisknumber(diskn);
	}
	private int peg;
	private int size;
	private int disknumber;
	public void setPeg(int peg) {
		this.peg = peg;
	}
	public int getPeg() {
		return peg;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getSize() {
		return size;
	}
	public void setDisknumber(int disknumber) {
		this.disknumber = disknumber;
	}
	public int getDisknumber() {
		return disknumber;
	}
}

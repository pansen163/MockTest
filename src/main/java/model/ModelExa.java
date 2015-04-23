package model;

/**
 * Created by pansen on 15/4/24.
 */
public class ModelExa {

	public String id="是id";

	public String name="是name";

	public boolean aBoolean=true;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isaBoolean() {
		return aBoolean;
	}

	public void setaBoolean(boolean aBoolean) {
		this.aBoolean = aBoolean;
	}

	@Override
	public String toString() {
		return "  super="+super.toString()+"  id="+getId()+"  name="+getName();
	}
}

package test;


public class ProcessD  {

	private String name;
	private String key;
	private String id;
	private int version;
	private String deploymentId;
	private String imageResourceName;
	private String description;
	private boolean isSuspended;
	
	
	public String getName() {
		return name;
	}

	public String getKey() {
		return key;
	}

	public String getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public String getImageResourceName() {
		return imageResourceName;
	}

	public String getDescription() {
		return description;
	}

	public boolean isSuspended() {
		return isSuspended;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public void setImageResourceName(String imageResourceName) {
		this.imageResourceName = imageResourceName;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setSuspended(boolean isSuspended) {
		this.isSuspended = isSuspended;
	}

	

}

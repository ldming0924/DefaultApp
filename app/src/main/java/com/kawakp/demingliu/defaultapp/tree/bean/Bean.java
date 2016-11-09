package com.kawakp.demingliu.defaultapp.tree.bean;

public class Bean
{
	@TreeNodeId
	private String id;
	@TreeNodePid
	private String pId;
	@TreeNodeLabel
	private String label;  //层级

	public Bean()
	{
	}

	public Bean(String id, String pId, String label)
	{
		this.id = id;
		this.pId = pId;
		this.label = label;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}

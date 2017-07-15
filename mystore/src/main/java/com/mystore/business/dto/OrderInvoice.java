package com.mystore.business.dto;

import java.util.Date;

public class OrderInvoice  extends BasicDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer id_order;
	
	private String invoicetype;
	
	private String invocetoptype;
	
	private String invoicetop;
	
	private String invoicecontext;
	
	private String status;
	
	private Date createDate;
	
	private Date lastDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_order() {
		return id_order;
	}

	public void setId_order(Integer id_order) {
		this.id_order = id_order;
	}

	public String getInvoicetype() {
		return invoicetype;
	}

	public void setInvoicetype(String invoicetype) {
		this.invoicetype = invoicetype;
	}

	public String getInvoicetop() {
		return invoicetop;
	}

	public void setInvoicetop(String invoicetop) {
		this.invoicetop = invoicetop;
	}

	public String getInvoicecontext() {
		return invoicecontext;
	}

	public void setInvoicecontext(String invoicecontext) {
		this.invoicecontext = invoicecontext;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public String getInvocetoptype() {
		return invocetoptype;
	}

	public void setInvocetoptype(String invocetoptype) {
		this.invocetoptype = invocetoptype;
	}

}

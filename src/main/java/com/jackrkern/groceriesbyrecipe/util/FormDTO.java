package com.jackrkern.groceriesbyrecipe.util;

import java.util.ArrayList;
import java.util.List;

/* @author "Jack Kern"
 * This is a trash object meant for pulling information from forms on the site. */
public class FormDTO
{
	@SuppressWarnings("unused")
	private String field1;
	@SuppressWarnings("unused")
	private String field2;
	@SuppressWarnings("unused")
	private String field3;
	@SuppressWarnings("unused")
	private String field4;
	@SuppressWarnings("unused")
	private String field5;
	private List<String> field;

	/* @param field1 /* @param field2 /* @param field3 /* @param field4 /* @param field5 /* @param field */
	public FormDTO()
	{
		field = new ArrayList<String>();
		field.add("");
		field.add("");
		field.add("");
		field.add("");
		field.add("");
		field.add("");
	}

	public FormDTO(String field1, String field2, String field3, String field4, String field5)
	{
		this();
		field.set(1, field1);
		field.set(2, field2);
		field.set(3, field3);
		field.set(4, field4);
		field.set(5, field5);
	}

	// @return the field1
	public String getField1()
	{ return field.get(1); }

	// @param field1 the field1 to set
	public void setField1(String field1)
	{
		field.set(1, field1);
	}

	// @return the field2
	public String getField2()
	{ return field.get(2); }

	// @param field2 the field2 to set
	public void setField2(String field2)
	{
		field.set(2, field2);
	}

	// @return the field3
	public String getField3()
	{ return field.get(3); }

	// @param field3 the field3 to set
	public void setField3(String field3)
	{
		field.set(3, field3);
	}

	// @return the field4
	public String getField4()
	{ return field.get(4); }

	// @param field4 the field4 to set
	public void setField4(String field4)
	{
		field.set(4, field4);
	}

	// @return the field5
	public String getField5()
	{ return field.get(5); }

	// @param field5 the field5 to set
	public void setField5(String field5)
	{
		field.set(5, field5);
	}

	// @return the field
	public List<String> getField()
	{ return field; }

	// @param field the field to set
	public void setField(List<String> field)
	{ this.field = field; }

	// @return the field by index
	public String getField(int index)
	{
		if (index < 0)
		{
			System.out.println("index is too low");
			return field.get(0);
		} else if (index > field.size())
		{
			System.out.println("index is too high");
			return field.get(field.size() - 1);
		} else
			return field.get(index);
	}

	// @param field the field to set
	public void setField(int index, String element)
	{
		if (index < 0)
		{
			System.out.println("index is too low");
			field.set(0, element);
		} else if (index > field.size())
		{
			System.out.println("index is too high");
			field.set(field.size() - 1, element);
		} else
			field.set(index, element);
	}
}
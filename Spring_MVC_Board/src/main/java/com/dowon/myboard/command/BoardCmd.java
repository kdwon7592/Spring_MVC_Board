package com.dowon.myboard.command;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

public interface BoardCmd<T> {

	public void execute(T model);
	
}

package com.cosylab.vdct;

/**
 * Copyright (c) 2002, Cosylab, Ltd., Control System Laboratory, www.cosylab.com
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer. 
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation 
 * and/or other materials provided with the distribution. 
 * Neither the name of the Cosylab, Ltd., Control System Laboratory nor the names
 * of its contributors may be used to endorse or promote products derived 
 * from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, 
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.io.File;
import java.util.ArrayList;
import java.util.prefs.*;

/**
 * Insert the type's description here.
 * Creation date: (4.2.2001 11:30:32)
 * @author Matej Sekoranja
 */
public class Settings {
	private static Settings instance = null;
	private static String defaultDir = "./";

	// preferences API class
	private Preferences prefs;
	
	// caching (defaults)
	private boolean snapToGrid = true;
	private boolean showGrid = true;
	private boolean navigator = true;
	private boolean grouping = false;
	private boolean globalMacros = false;
	private boolean hierarhicalNames = false;
	private boolean windowsPan = false;
	
/**
 * Settings constructor comment.
 */
protected Settings() {
	
	// user settings
	prefs = Preferences.userNodeForPackage(this.getClass());

	// Console.getInstance().println("o) No settings file loaded. Using defaults...");

	// initialize buffered properties
	snapToGrid = prefs.getBoolean("SnapToGrid", snapToGrid);
	showGrid = prefs.getBoolean("ShowGrid", showGrid);
	navigator = prefs.getBoolean("Navigator", navigator);
	grouping = prefs.getBoolean("Grouping", grouping);
	globalMacros = prefs.getBoolean("GlobalMacros", globalMacros);
	hierarhicalNames = prefs.getBoolean("HierarhicalNames", hierarhicalNames);
	windowsPan = prefs.getBoolean("WindowsPan", windowsPan);

	if (grouping)
	{
		Constants.GROUP_SEPARATOR = (char)prefs.getInt("GroupSeparator", 0);
		if (Constants.GROUP_SEPARATOR=='\0')
			grouping = false;
	}		
}
/**
 * Insert the method's description here.
 * Creation date: (9.12.2001 11:08:15)
 * @return java.lang.String
 */
public static java.lang.String getDefaultDir() {
	return defaultDir;
}

/**
 * Insert the method's description here.
 * Creation date: (27.4.2001 18:48:59)
 * @return int
 */
/*public int getGridSize() {
	return prefs.getInt("GridSize", Constants.GRID_SIZE);
}
/**
 * Insert the method's description here.
 * Creation date: (4.2.2001 11:31:11)
 * @return com.cosylab.vdct.Settings
 */
public static Settings getInstance() {
	if (instance==null) instance = new Settings();
	return instance;
}
/**
 * Insert the method's description here.
 * Creation date: (27.4.2001 18:50:50)
 * @return boolean
 */
public boolean getNavigator() {
	return navigator;
}
/**
 * Insert the method's description here.
 * Creation date: (27.4.2001 18:50:50)
 * @return boolean
 */
public boolean getGrouping() {
	return grouping;
}
/**
 * Insert the method's description here.
 * Creation date: (4.2.2001 12:02:12)
 * @return java.lang.String
 * @param key java.lang.String
 * @param defaultValue java.lang.String
 */
public String getProperty(String key, String defaultValue) {
	return prefs.get(key, defaultValue);
	
}
/**
 * Insert the method's description here.
 * Creation date: (27.4.2001 18:50:50)
 * @return boolean
 */
public boolean getShowGrid() {
	return showGrid;
}
/**
 * Insert the method's description here.
 * Creation date: (27.4.2001 18:50:50)
 * @return boolean
 */
public boolean getSnapToGrid() {
	return snapToGrid;
}
/**
 * Insert the method's description here.
 * Creation date: (27.4.2001 18:50:50)
 * @return boolean
 */
public boolean getStatusbar() {
	return prefs.getBoolean("Statusbar", true);
}
/**
 * Insert the method's description here.
 * Creation date: (27.4.2001 18:50:50)
 * @return boolean
 */
public boolean getToolbar() {
	return prefs.getBoolean("Toolbar", true);
}
/**
 * Insert the method's description here.
 * Creation date: (9.12.2001 11:08:15)
 * @param newDefaultDir java.lang.String
 */
public static void setDefaultDir(java.lang.String newDefaultDir) {
	defaultDir = newDefaultDir;
	if (defaultDir.charAt(defaultDir.length()-1)!=java.io.File.separatorChar)
		defaultDir += java.io.File.separatorChar;
}
/**
 * Insert the method's description here.
 * Creation date: (27.4.2001 18:48:29)
 * @param size int
 */
public void setGridSize(int size) {
	prefs.putInt("GridSize", size);
	sync();
}
/**
 * Insert the method's description here.
 * Creation date: (27.4.2001 18:46:17)
 * @param state boolean
 */
public void setNavigator(boolean state) {
	navigator = state;
	prefs.putBoolean("Navigator", state);
	sync();
}
/**
 * Insert the method's description here.
 * Creation date: (27.4.2001 18:46:17)
 * @param state boolean
 */
public void setGrouping(boolean state) {
	grouping = state;
	prefs.putBoolean("Grouping", state);
	sync();
	
	if (!grouping && Constants.GROUP_SEPARATOR!='\0')
		setGroupSeparator('\0');
}
/**
 * Insert the method's description here.
 * Creation date: (4.2.2001 11:59:58)
 * @param key java.lang.String
 * @param value java.lang.String
 */
public void setProperty(String key, String value) {
	prefs.put(key, value);
	sync();
}
/**
 * Insert the method's description here.
 * Creation date: (27.4.2001 18:46:17)
 * @param state boolean
 */
public void setShowGrid(boolean state) {
	showGrid = state;
	prefs.putBoolean("ShowGrid", state);
	sync();
}
/**
 * Insert the method's description here.
 * Creation date: (27.4.2001 18:46:17)
 * @param state boolean
 */
public void setSnapToGrid(boolean state) {
	snapToGrid = state;
	prefs.putBoolean("SnapToGrid", state);
	sync();
}
/**
 * Insert the method's description here.
 * Creation date: (27.4.2001 18:46:17)
 * @param state boolean
 */
public void setStatusbar(boolean state) {
	prefs.putBoolean("Statusbar", state);
	sync();
}
/**
 * Insert the method's description here.
 * Creation date: (27.4.2001 18:46:17)
 * @param state boolean
 */
public void setToolbar(boolean state) {
	prefs.putBoolean("Toolbar", state);
	sync();
}
/**
 * Insert the method's description here.
 * Creation date: (27.4.2001 18:46:17)
 * @param char group separator character
 */
public void setGroupSeparator(char sep) {
	if (!grouping)
		if (Constants.GROUP_SEPARATOR=='\0')
			return;
		else
			sep = '\0';
	
	Constants.GROUP_SEPARATOR = sep;
	prefs.putInt("GroupSeparator", (int)(Constants.GROUP_SEPARATOR));
	sync();

	if (grouping && Constants.GROUP_SEPARATOR=='\0')
		setGrouping(false);
}
/**
 * Insert the method's description here.
 * Creation date: (4.2.2001 11:32:36)
 * @return boolean
 */
public boolean save() {
	try
	{
		prefs.flush();
		return true;
	}
	catch (BackingStoreException bse)
	{
		Console.getInstance().println("o) Failed to flush VisualDCT settings:");
		Console.getInstance().println(bse);
		return false;
	}
}
/**
 * Insert the method's description here.
 * Creation date: (4.2.2001 11:32:36)
 * @return boolean
 */
public boolean sync() {
	try
	{
		prefs.sync();
		return true;
	}
	catch (BackingStoreException bse)
	{
		Console.getInstance().println("o) Failed to sync VisualDCT settings:");
		Console.getInstance().println(bse);
		return false;
	}
}

/**
 * Insert the method's description here.
 */
public void saveRecentFiles()
{
	final String prefix = "file";

	ArrayList files = VisualDCT.getInstance().getRecentFilesMenu().getFiles();
	int i = 0;
	for (; i<files.size(); i++)
		prefs.put(prefix+String.valueOf(i), ((File)files.get(i)).getAbsolutePath());
		
	// add no files
	for (; i<Constants.MAX_RECENT_FILES; i++)
	{
		String name = prefix+String.valueOf(i); 
		if (prefs.get(name, null)!=null)
		prefs.remove(name);
	}
}

/**
 * Insert the method's description here.
 */
public void loadRecentFiles()
{
	final String prefix = "file";

	for (int i = 0; i<Constants.MAX_RECENT_FILES; i++)
	{
		String name = prefix+String.valueOf(i); 
		String fileName = prefs.get(name, null);
		if (fileName!=null)
		{
			try
			{
				VisualDCT.getInstance().getRecentFilesMenu().addFile(new File(fileName), true);
			}
			catch (Exception ex)
			{
			}
		}
		else
			break;
	}
}

	/**
	 * @return
	 */
	public boolean getGlobalMacros() {
		return globalMacros;
	}

	/**
	 * @param b
	 */
	public void setGlobalMacros(boolean b) {
		globalMacros = b;
		prefs.putBoolean("GlobalMacros", b);
		sync();
	}

	/**
	 * @return
	 */
	public boolean getHierarhicalNames() {
		return hierarhicalNames;
	}

	/**
	 * @param b
	 */
	public void setHierarhicalNames(boolean b) {
		hierarhicalNames = b;
		prefs.putBoolean("HierarhicalNames", b);
		sync();
	}

	/**
	 * @return
	 */
	public boolean getWindowsPan() {
		return windowsPan;
	}

	/**
	 * @param b
	 */
	public void setWindowsPan(boolean b) {
		windowsPan = b;
		prefs.putBoolean("WindowsPan", b);
		sync();
	}

}

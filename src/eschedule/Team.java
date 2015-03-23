package eschedule;

import java.util.*;
import java.awt.image.*;

import com.mongodb.BasicDBObject;

/**
 * Class for storing information about a team.
 * 
 * @author sjaxel
 *
 */
public class Team {
	private String name;
	private String shortname;
	private int spritepos;
	private BufferedImage img;
	
	public Team(String shortname, String name) {
		this.name = name;
		this.shortname = shortname;
	}
	
	public Team() {
	}

	public String getName() {
		return name;
	}

	public String getShortName() {
		return shortname;
	}

	public void setName(String s) {
		name = s;
	}

	public void setShortName(String sname) {
		shortname = sname;
	}

	public void setSpritePos(int i) {
		spritepos = i;
	}
	
	public BufferedImage getSprite() {
		if (img == null) {
			img = SpriteGet.getSprite(spritepos);
			return img;
		} else {
			return img;
		}
	}

	public boolean isValid() {
		if ((name != null) && (shortname != null)) {
			return true;
		}
		return false;
	}

	public String toString() {
		return shortname;
	}
	
	public BasicDBObject getBDO(){
		BasicDBObject doc = new BasicDBObject();
		doc.append("name", name);
		doc.append("shortname", shortname);
		doc.append("spritepos", spritepos);
		return doc;
	}
	
}

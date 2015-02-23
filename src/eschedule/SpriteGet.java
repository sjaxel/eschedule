package eschedule;


import java.awt.image.*;


public abstract class SpriteGet {
	private static BufferedImage img;
	
	public static void imageLoad() {
		if (img == null) {
			img = Tools.loadImage();
			System.out.println("Image loaded");
		}
	}
	
	public static BufferedImage getSprite(int n) {
		int j = ((n-1) % 20);
		int i = ((n-1) / 20);
		BufferedImage sprite = null;
		imageLoad();
		int spriteH = 25;
		int spriteW = img.getWidth()/20;
		sprite = img.getSubimage(j*spriteW, i*spriteH, spriteW, spriteH);
		return sprite;
	}
	
}

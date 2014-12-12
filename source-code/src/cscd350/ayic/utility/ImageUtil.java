package cscd350.ayic.utility;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil
{
	public static BufferedImage getSilhouette(String imageAddress)
	{
		File imageFile = new File(imageAddress);
		BufferedImage silhouette = null;
		try
		{
			silhouette = ImageIO.read(imageFile);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		int x = silhouette.getTileWidth();
		int y = silhouette.getTileHeight();

		for (int r = 0; r < y; r++)
		{
			for (int c = 0; c < x; c++)
			{
				int rgba = silhouette.getRGB(c, r);
				int black = Color.black.getRGB();

				if ((rgba >> 24) != 0x00)
				{
					silhouette.setRGB(c, r, black);
				}
			}

		}

		return silhouette;
	}
}

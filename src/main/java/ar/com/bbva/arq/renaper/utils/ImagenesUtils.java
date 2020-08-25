package ar.com.bbva.arq.renaper.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

import org.apache.axis.utils.ByteArrayOutputStream;

import ar.com.bbva.arq.renaper.services.ServiceException;



/**
 * Clase utilitaria para darle tratamiento a las Imagenes
 * 
 * @author Emiliano Susmano
 */
public class ImagenesUtils {

	public static byte[] cambiarFormatoImagenAJpg(byte[] imgBytes) {
		try {
			BufferedImage image = ImageIO.read(new ByteArrayInputStream(imgBytes));
			BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
			result.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(result, "jpg", os);
			return os.toByteArray();
		} catch (IOException e) {
			throw new ServiceException(HTTPResponseCodesEnum.STATUS_500.getStatusCode(), Constants.SERVER_FAIL_IMG_PROC, null);
		}
	}

	public static byte[] comprimirImagen(byte[] imgBytes) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageWriter writer = (ImageWriter) ImageIO.getImageWritersByFormatName("jpeg").next();

			ImageWriteParam param = writer.getDefaultWriteParam();
			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			param.setCompressionQuality(0.2f);

			writer.setOutput(ImageIO.createImageOutputStream(os));
			writer.write(null, new IIOImage(ImageIO.read(new ByteArrayInputStream(imgBytes)), null, null), param);
			writer.dispose();
			return os.toByteArray();
		} catch (IOException e) {
			throw new ServiceException(HTTPResponseCodesEnum.STATUS_500.getStatusCode(), Constants.SERVER_FAIL_IMG_PROC, null);
		}
	}

}

package ar.com.bbva.arq.renaper.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStream;
import com.itextpdf.text.pdf.PdfWriter;

import ar.com.bbva.arq.renaper.config.Pdf417Configuration;
import ar.com.bbva.arq.renaper.services.ServiceException;

/**
 * 
 *
 */

public class PDFUtils {

	/**
	 * 
	 * @param streamOfPDFFiles
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	
	@Autowired
	Pdf417Configuration pdf417Configuration;

	public static ByteArrayOutputStream concatPDFs(List<InputStream> streamOfPDFFiles)
			throws DocumentException, IOException {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfCopy copy = new PdfCopy(document, out);
		document.open();
		int n;
		for (InputStream in : streamOfPDFFiles) {
			PdfReader reader = new PdfReader(in);
			n = reader.getNumberOfPages();
			for (int page = 0; page < n;) {
				copy.addPage(copy.getImportedPage(reader, ++page));
			}
			copy.freeReader(reader);
			reader.close();
		}
		out.flush();
		document.close();
		out.close();

		return out;

	}

	/**
	 * Transforma un byteArray en base 64 a un pdf en un ImputStream, si es un
	 * imagen, la convierte a pdf.
	 * 
	 * @param archivo
	 * @param contentType
	 * @param maxSize
	 * @return
	 */
	public static byte[] base64ToPDF(byte[] copia, String contentType, String maxSize) throws Exception {

		int maxSizeBytes = (Integer.valueOf(maxSize)) * 1024 * 1024;

		if (copia.length > maxSizeBytes) {
			throw new Exception();
		}

		if (!contentType.contains("pdf")) {
			Document document = new Document();
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			PdfWriter writer;
			try {
				writer = PdfWriter.getInstance(document, byteArrayOutputStream);
				writer.open();
				document.open();
				Image image = Image.getInstance(copia);
				image.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
				image.setAlignment(Element.ALIGN_CENTER);
				document.add(image);
				document.close();
				writer.close();
				copia = byteArrayOutputStream.toByteArray();
			} catch (Exception e) {
				throw new ServiceException(HTTPResponseCodesEnum.STATUS_500.getStatusCode(),
						Constants.SERVER_FAIL_IMG_PROC_FORMAT, null);
			}
		}
		return copia;
	}

	public static byte[] formatearImagenesPDFParaPDF417(byte[] docBytes,String maxImagenes) {
		List<byte[]> listaImagenes = new ArrayList<byte[]>();
		try {
			PdfReader reader = new PdfReader(new ByteArrayInputStream(docBytes));
			BufferedImage image = null;

			int totalHeight = 0;
			int maxWidth = 0;

			// Cargo las Imagenes del pdf y obtengo max Width y total Height
			for (int i = 0; i < reader.getXrefSize(); i++) {
				PdfObject pdfobj = reader.getPdfObject(i);
				if (pdfobj == null || !pdfobj.isStream()) {
					continue;
				}
				PdfStream stream = (PdfStream) pdfobj;
				PdfObject pdfsubtype = stream.get(PdfName.SUBTYPE);

				if (pdfsubtype != null && pdfsubtype.toString().equals(PdfName.IMAGE.toString())) {
					byte[] imgByte = PdfReader.getStreamBytesRaw((PRStream) stream);
					image = ImageIO.read(new ByteArrayInputStream(imgByte));
					totalHeight += image.getHeight();
					maxWidth = (image.getWidth() > maxWidth) ? image.getWidth() : maxWidth;
					listaImagenes.add(ImagenesUtils.cambiarFormatoImagenAJpg(imgByte));
					if (listaImagenes.size() > Integer.valueOf(maxImagenes))
						throw new ServiceException(HTTPResponseCodesEnum.STATUS_500.getStatusCode(),
								Constants.SERVER_FAIL_IMG_PROC_EX, null);
				}
			}

			// Concateno las imagenes
			int heightImgActual = 0;
			BufferedImage result = new BufferedImage(maxWidth, totalHeight, BufferedImage.TYPE_INT_RGB);
			if (!listaImagenes.isEmpty()) {
				for (byte[] img : listaImagenes) {
					image = ImageIO.read(new ByteArrayInputStream(img));
					result.createGraphics().drawImage(image, 0, heightImgActual, Color.WHITE, null);
					heightImgActual += image.getHeight();
				}

				ByteArrayOutputStream os = new ByteArrayOutputStream();
				ImageIO.write(result, "jpg", os);
				return os.toByteArray();
			} else {
				throw new ServiceException(HTTPResponseCodesEnum.STATUS_500.getStatusCode(),
						Constants.SERVER_FAIL_IMG_PROC, null);
			}
		} catch (NullPointerException|IOException e) {
			throw new ServiceException(HTTPResponseCodesEnum.STATUS_500.getStatusCode(), Constants.SERVER_FAIL_IMG_PROC_FORMAT,
					null);
		}
	}

	public static byte[] concatenarAdjuntosComoPDF(List<MultipartFile> files,String maxMegasDni,String maxMegas) {
		List<InputStream> docs = new ArrayList<InputStream>();
		String maxSize = (files.size()> 1) ? maxMegasDni : maxMegas;
		for (MultipartFile adjunto : files) {
			try {
				adjunto.getInputStream();
				byte[] bar=adjunto.getBytes();		
				docs.add(new ByteArrayInputStream(PDFUtils.base64ToPDF(bar,adjunto.getContentType(), maxSize)));
			} catch (Exception e) {
				throw new ServiceException(HTTPResponseCodesEnum.STATUS_500.getStatusCode(),
						Constants.SERVER_FAIL_IMG_PROC_FORMAT, null);
			}
		}

		try {
			return PDFUtils.concatPDFs(docs).toByteArray();
		} catch (Exception e) {
			throw new ServiceException(HTTPResponseCodesEnum.STATUS_500.getStatusCode(), Constants.SERVER_FAIL_IMG_PROC,
					null);
			
		}
	}

}

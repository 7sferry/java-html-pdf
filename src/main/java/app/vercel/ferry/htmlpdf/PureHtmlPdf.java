/************************
 * Made by [MR Ferry™]  *
 * on August 2022       *
 ************************/

package app.vercel.ferry.htmlpdf;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class PureHtmlPdf implements MyHtmlPdf{

	@Override
	public void toPdf() throws Exception{
		float widthCm = 21.59F;
		float widthDpi = cmToDpi(widthCm);
		float heightCm = 13.97F;
		float heightDpi = cmToDpi(heightCm);

		String html = getHtml();
		writePdfFile(html, widthDpi, heightDpi);
	}

	private static String getHtml(){
		TemplateEngine templateEngine = new SpringTemplateEngine();
		ClassLoaderTemplateResolver templateResolver = getTemplateResolver();
		templateEngine.setTemplateResolver(templateResolver);
		return templateEngine.process("Invoice_pure", new Context());
	}

	private float cmToDpi(float input){
		return input / 2.54F * 72;
	}

	private static ClassLoaderTemplateResolver getTemplateResolver(){
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("html/");
		templateResolver.setCacheable(false);
		templateResolver.setSuffix(".html");
		return templateResolver;
	}

	public void writePdfFile(String html, float widthDpi, float heightDpi) throws Exception{
		PageSize pageSize = new PageSize(widthDpi, heightDpi);
		byte[] bytes = buildPdfContentBytes(html, pageSize);
		try(OutputStream out = new FileOutputStream("inv.pdf", false)){
			int off = 0;
			int length = bytes.length;
			out.write(bytes, off, length);
		}
	}

	public byte[] buildPdfContentBytes(String html, PageSize pageSize) throws Exception{
		try(
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				PdfWriter writer = new PdfWriter(byteArrayOutputStream, new WriterProperties());
				PdfDocument pdfDocument = getPdfDocument(writer, pageSize)
		){
			ConverterProperties converterProperties = new ConverterProperties();
			HtmlConverter.convertToPdf(html, pdfDocument, converterProperties);
			return byteArrayOutputStream.toByteArray();
		}
	}

	private PdfDocument getPdfDocument(PdfWriter writer, PageSize pageSize){
		PdfDocument pdfDocument = new PdfDocument(writer);
		pdfDocument.setDefaultPageSize(pageSize);
		return pdfDocument;
	}

}

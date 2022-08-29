package app.vercel.ferry.htmlpdf;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

@SpringBootApplication
public class HtmlpdfApplication implements CommandLineRunner{

	public static void main(String[] args){
		SpringApplication.run(HtmlpdfApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		MyHtmlPdf pdf = new ThymeleafHtmlPdf();
		pdf.toPdf();
	}

}

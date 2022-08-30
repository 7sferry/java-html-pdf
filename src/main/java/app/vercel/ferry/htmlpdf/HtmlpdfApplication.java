package app.vercel.ferry.htmlpdf;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HtmlpdfApplication implements CommandLineRunner{

	public static void main(String[] args){
		SpringApplication.run(HtmlpdfApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		MyHtmlPdf html = new ThymeleafHtmlPdf();
		html.toPdf();
	}

}

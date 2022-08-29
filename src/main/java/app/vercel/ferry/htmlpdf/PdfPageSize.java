/************************
 * Author: [MR FERRY™]  *
 * November 2020        *
 ************************/

package app.vercel.ferry.htmlpdf;

import com.itextpdf.kernel.geom.PageSize;

public class PdfPageSize{
	public static final float DPI = 72;
	public static final PdfPageSize A4 = createPageSizeByInch(8.27F, 11.69F);
	private static final float INC_TO_CM = 2.54F;
	private static final float INC_TO_MM = 25.4F;
	public static final PdfPageSize SURAT_JALAN = createPageSizeByCm(24F, 14F);
	private final float width;
	private final float height;

	private PdfPageSize(float width, float height){
		this.width = width;
		this.height = height;
	}

	public static PdfPageSize createPageSizeByCm(float width, float height){
		return createPageSizeByInch(width / INC_TO_CM, height / INC_TO_CM);
	}

	public static PdfPageSize createPageSizeByMm(float width, float height){
		return createPageSizeByInch(width / INC_TO_MM, height / INC_TO_MM);
	}

	public static PdfPageSize createPageSizeByInch(float width, float height){
		return new PdfPageSize(width * DPI, height * DPI);
	}

	PageSize createPage(){
		return new PageSize(width, height);
	}

}

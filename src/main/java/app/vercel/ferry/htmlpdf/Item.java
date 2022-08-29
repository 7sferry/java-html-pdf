/************************
 * Made by [MR Ferry™]  *
 * on August 2022       *
 ************************/

package app.vercel.ferry.htmlpdf;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Builder
@Value
class Item{
	String name;
	String description;
	BigDecimal rate;
	int qty;
	BigDecimal price;
}

package ch.zhaw.ocr.BitmapParser;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;


public class CharacterParser extends BitmapParserDecorator {

	public CharacterParser(BitmapParser bp) {
		super(bp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ContrastMatrix> parse(BufferedImage image) {
		List<ContrastMatrix> matrices = super.parse(image);
		List<ContrastMatrix> rv = new LinkedList<ContrastMatrix>();

		for (ContrastMatrix m : matrices) {
			if(m.getFunctionalChar() == null){
				m.trim();
	
				int characterStart = -1;
	
				// extract characters
				for (int x = 0; x < m.getWidth(); x++) {
					if(m.isEmptyCol(x)){
						if (characterStart != -1) {
							// column after character is empty => start -> x-1 is a
							// character
							rv.add(m.getSubMatrix(characterStart, 0, x
									- characterStart, m.getHeight()));
							characterStart = -1;
						}
					}else{
						if (characterStart == -1) {
							characterStart = x;
						}
					}
				}
	
				// last charakter
				if (characterStart != -1) {
					rv.add(m.getSubMatrix(characterStart, 0, m.getWidth()
							- characterStart, m.getHeight()));
				}
			}else{
				//functional character => keep it
				rv.add(m);
			}
		}
		return rv;
	}

}

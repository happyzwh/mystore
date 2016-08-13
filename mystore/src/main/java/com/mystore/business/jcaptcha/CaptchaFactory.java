package com.mystore.business.jcaptcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.util.Locale;
import java.util.Random;

import com.octo.captcha.CaptchaException;
import com.octo.captcha.CaptchaQuestionHelper;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.image.ImageCaptcha;
import com.octo.captcha.image.ImageCaptchaFactory;
import com.octo.captcha.image.gimpy.Gimpy;

public class CaptchaFactory extends ImageCaptchaFactory {
	
    
    private static final ThreadLocal<Random> threadLocalRandom = new ThreadLocal<Random>() {
 
        @Override
        protected Random initialValue() {
            return new Random();
        }
 
    };
 
    private static final char[] captchaChars = new char[]{'2', '3', '4', '5', '7', '8', '2', '3', '4', '5', '7', '8',
        'a', 'c', 'd', 'e', 'f', 'g', 'h', 'k', 'm', 'n', 'p', 's', 't', 'v', 'w', 'x', 'y', 'z',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
     
    private static final String[] fonts = new String[]{"Serif", "Sanserif", "Monospace", "Dialog", "DialogInput"};
     
    private static final int[] styles = new int[]{Font.BOLD, Font.PLAIN , Font.ITALIC, Font.ROMAN_BASELINE};
     
    private static final Color bgColor = Color.WHITE;

	private WordGenerator wordGenerator;
	public static final String BUNDLE_QUESTION_KEY = Gimpy.class.getName();
	


	public ImageCaptcha getImageCaptcha() {
		return getImageCaptcha(Locale.getDefault());
	}


	public WordGenerator getWordGenerator() {
		return this.wordGenerator;
	}
	public ImageCaptcha getImageCaptcha(Locale locale) {

		Random random = threadLocalRandom.get();
		 
        String word = "";
        for (int i = 0, length = 4; i < length; i++) {
        	word += captchaChars[random.nextInt(captchaChars.length)];
        }
 
		BufferedImage image = null;
		try {
			image = new BufferedImage(120, 55, BufferedImage.TYPE_INT_RGB);
			drawCaptcha(image, word);
			fast_shear(image);
		} catch (Throwable e) {
			throw new CaptchaException(e);
		}
		ImageCaptcha captcha = new CaptchaGimpy(CaptchaQuestionHelper.getQuestion(locale, BUNDLE_QUESTION_KEY), image, word);

		return captcha;
	}
	
    private static void drawCaptcha(BufferedImage bi, String captcha) {
        Random random = threadLocalRandom.get();
        Graphics2D g = bi.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(bgColor);
        g.fillRect(0, 0, bi.getWidth(), bi.getHeight());
        g.setColor(Color.BLUE);
 
        int length = captcha.length();
        float sx = 12 + random.nextInt(21);
        float sy = 34 + random.nextInt(11);
 
        FontRenderContext frc = g.getFontRenderContext();
        Area area = new Area();
        for (int i = 0; i < length; i++) {
            int t = 36 + random.nextInt(3);
            Font font = new Font(fonts[random.nextInt(fonts.length)], styles[random.nextInt(styles.length)], t);
            GlyphVector glyphVector = font.createGlyphVector(frc, captcha.substring(i, i + 1));
            Shape glyph = glyphVector.getGlyphOutline(0, sx, sy);
            area.add(new Area(glyph));
            double w = glyph.getBounds2D().getMaxX() - glyph.getBounds2D().getMinX();
            sx += w - (w / 5.2);
        }
        g.fill(area);
        g.dispose();
    }
 
    private static void fast_shear(BufferedImage bi) {
        Random random = threadLocalRandom.get();
 
        Graphics2D g = bi.createGraphics();
        int w = bi.getWidth();
        int h = bi.getHeight();
 
        int ox = 10 + random.nextInt(11);
        int oy = 10 + random.nextInt(11);
        double sx = Math.PI * 2 * random.nextDouble() * 100;
        double sy = Math.PI * 2 * random.nextDouble() * 100;
        double rx = 0.5 + random.nextDouble() * 1.0;
        double rt = 1.5 - rx;
        double ry = rt / 3 + random.nextDouble() * rt / 3 * 2;
        double cx = w / rx / Math.PI / 2;
        double cy = h / ry / Math.PI / 2;
 
        for (int i = 0; i < h; i++) {
            double d = (double) (ox >> 1) * Math.sin((double) i / (double) cx + sx);
            g.copyArea(0, i, w, 1, (int) d, 0);
            g.setColor(bgColor);
            g.drawLine((int) d, i, 0, i);
            g.drawLine((int) d + w, i, w, i);
        }
        for (int i = 0; i <= w; i++) {
            double d = (double) (oy >> 1) * Math.sin((double) i / (double) cy + sy);
            g.copyArea(i, 0, 1, h, 0, (int) d);
            g.setColor(bgColor);
            g.drawLine(i, (int) d, i, 0);
            g.drawLine(i, (int) d + h, i, h);
        }
        g.dispose();
    }
}

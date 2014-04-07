package org.zkoss.zul;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import org.zkoss.util.Locales;

public class DoubleboxEnglish extends org.zkoss.zul.Doublebox{

    protected String formatNumber(Object value, String defaultFormat)
    {
        if(value == null)
        {
            return "";
        }
        DecimalFormat df = (DecimalFormat)NumberFormat.getInstance(Locales.getLocale("en_US"));
        //Locales.getCurrent();
        
        if(getRoundingMode() != 6)
        {
            df.setRoundingMode(RoundingMode.valueOf(getRoundingMode()));
        }
        String fmt = getFormat();
        if(fmt == null)
        {
            fmt = defaultFormat;
        }
        if(fmt != null)
        {
            df.applyPattern(fmt);
        }
        
        return df.format(value);
    }
    
	protected Object[] toNumberOnly(String val) {
		if (val == null) return new Object[] {null, null};

		final DecimalFormatSymbols symbols =
			new DecimalFormatSymbols(Locales.getLocale("en_US"));
		final char GROUPING = symbols.getGroupingSeparator(),
			DECIMAL = symbols.getDecimalSeparator(),
			PERCENT = symbols.getPercent(),
			PER_MILL = symbols.getPerMill(), //1/1000
			//not support yet: INFINITY = symbols.getInfinity(), NAN = symbols.getNaN(),
			MINUS = symbols.getMinusSign();
		final String fmt = getFormat();
		StringBuffer sb = null;
		int divscale = 0; //the second element
		boolean minus = false;
		for (int j = 0, len = val.length(); j < len; ++j) {
			final char cc = val.charAt(j);

			boolean ignore = false;
			//We handle percent and (nnn) specially
			if (cc == PERCENT) {
				divscale += 2;
				ignore = true;
			} else if (cc == PER_MILL) {
				divscale += 3;
				ignore = true;
			} else if (cc == '(') {
				minus = true;
				ignore = true;
			} else if (cc == '+') {
				ignore = true;
			}

			//We don't add if cc shall be ignored (not alphanum but in fmt)
			if (!ignore)
				ignore = (cc < '0' || cc > '9')
				&& cc != DECIMAL && cc != MINUS && cc != '+'
				&& (Character.isWhitespace(cc) || cc == GROUPING || cc == ')'
					|| (fmt != null && fmt.indexOf(cc) >= 0));
			if (ignore) {
				if (sb == null)
					sb = new StringBuffer(len).append(val.substring(0, j));
			} else {
				final char c2 = cc == MINUS ? '-':
					cc == DECIMAL ? '.':  cc;
				if (cc != c2) {
					if (sb == null)
						sb = new StringBuffer(len).append(val.substring(0, j));
					sb.append(c2);
				} else if (sb != null) {
					sb.append(c2);
				}
			}
		}
		if (minus) {
			if (sb == null)
				sb = new StringBuffer(val.length() + 1).append(val);
			if (sb.length() > 0) {
				if (sb.charAt(0) == '-') {
					sb.deleteCharAt(0);
				} else {
					sb.insert(0, '-');
				}
			}
		}
		return new Object[] {
			(sb != null ? sb.toString(): val), new Integer(divscale)};
	}
}

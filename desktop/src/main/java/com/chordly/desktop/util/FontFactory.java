package com.chordly.desktop.util;

import javafx.scene.text.Font;
import java.io.InputStream;

public final class FontFactory {
    private static boolean fontLoaded = false;
    private static Font bravuraFont;

    private FontFactory() {}

    public static Font getBravura(int size) {
        if (!fontLoaded) {
            loadFont();
        }
        if (bravuraFont == null || bravuraFont.getFamily().equals("Arial")) {
            return Font.font("Segoe UI Symbol", size);
        }
        return Font.font(bravuraFont.getFamily(), size);
    }

    private static void loadFont() {
        try {
            InputStream is = FontFactory.class.getResourceAsStream("/com/chordly/desktop/font/Bravura.otf");
            if (is == null) {
                Logger.err("Font file not found!");
                bravuraFont = Font.font("Segoe UI Symbol", 12);
            } else {
                bravuraFont = Font.loadFont(is, 12);
                fontLoaded = true;
                Logger.debug("Bravura font loaded");
            }
        } catch (Exception e) {
            System.err.println("✗ Error loading font: " + e.getMessage());
            bravuraFont = Font.font("Segoe UI Symbol", 12);
        }
    }

    public static String getGClef() {
        return "\uD834\uDD1E"; // 𝄞 Treble clef (U+1D11E) - TO DZIAŁA!
    }

    public static String getFClef() {
        return "\uD834\uDD22"; // 𝄢 Bass clef (U+1D122)
    }

    public static String getCClef() {
        return "\uD834\uDD21"; // 𝄡 Alto clef (U+1D121)
    }

    public static String getWholeNote() {
        return "\uD834\uDD5D"; // 𝅝 Whole note (U+1D15D)
    }

    public static String getHalfNote() {
        return "\uD834\uDD5E"; // 𝅗𝅥 Half note (U+1D15E)
    }

    public static String getQuarterNote() {
        return "\uD834\uDD5F"; // 𝅘𝅥 Quarter note (U+1D15F)
    }

    public static String getEighthNote() {
        return "\uD834\uDD60"; // 𝅘𝅥𝅮 Eighth note (U+1D160)
    }

    public static String getSixteenthNote() {
        return "\uD834\uDD61"; // 𝅘𝅥𝅯 Sixteenth note (U+1D161)
    }

    public static String getThirtySecondNote() {
        return "\uD834\uDD62"; // 𝅘𝅥𝅰 Thirty-second note (U+1D162)
    }

    public static String getSixtyFourthNote() {
        return "\uD834\uDD63"; // 𝅘𝅥𝅱 Sixty-fourth note (U+1D163)
    }

    // PROSTE NOTY (Miscellaneous Symbols)
    public static String getQuarterNoteSimple() {
        return "\u2669"; // ♩ Quarter note
    }

    public static String getEighthNoteSimple() {
        return "\u266A"; // ♪ Eighth note
    }

    public static String getBeamedEighthNotes() {
        return "\u266B"; // ♫ Beamed eighth notes
    }

    public static String getBeamedSixteenthNotes() {
        return "\u266C"; // ♬ Beamed sixteenth notes
    }

    // PAUZY - Musical Symbols Block
    public static String getWholeRest() {
        return "\uD834\uDD3B"; // 𝄻 Whole rest (U+1D13B)
    }

    public static String getHalfRest() {
        return "\uD834\uDD3C"; // 𝄼 Half rest (U+1D13C)
    }

    public static String getQuarterRest() {
        return "\uD834\uDD3D"; // 𝄽 Quarter rest (U+1D13D)
    }

    public static String getEighthRest() {
        return "\uD834\uDD3E"; // 𝄾 Eighth rest (U+1D13E)
    }

    public static String getSixteenthRest() {
        return "\uD834\uDD3F"; // 𝄿 Sixteenth rest (U+1D13F)
    }

    public static String getThirtySecondRest() {
        return "\uD834\uDD40"; // 𝅀 Thirty-second rest (U+1D140)
    }

    public static String getSixtyFourthRest() {
        return "\uD834\uDD41"; // 𝅁 Sixty-fourth rest (U+1D141)
    }

    // ZNAKI CHROMATYCZNE - Miscellaneous Symbols
    public static String getSharp() {
        return "\u266F"; // ♯ Sharp (U+266F)
    }

    public static String getFlat() {
        return "\u266D"; // ♭ Flat (U+266D)
    }

    public static String getNatural() {
        return "\u266E"; // ♮ Natural (U+266E)
    }

    public static String getDoubleSharp() {
        return "\uD834\uDD2A"; // 𝄪 Double sharp (U+1D12A)
    }

    public static String getDoubleFlat() {
        return "\uD834\uDD2B"; // 𝄫 Double flat (U+1D12B)
    }

    // KROPKA
    public static String getDot() {
        return "\uD834\uDD6D"; // 𝅭 Dot (U+1D16D)
    }

    // PROSTA KROPKA
    public static String getDotSimple() {
        return ".";
    }

    // METRUM
    public static String getTimeSignatureCommon() {
        return "\uD834\uDD86"; // 𝆆 Common time (U+1D146)
    }

    public static String getTimeSignatureCut() {
        return "\uD834\uDD87"; // 𝆇 Cut time (U+1D147)
    }

    // ALTERNATYWNE METRUM (tekst)
    public static String getTimeSignatureCommonText() {
        return "C";
    }

    public static String getTimeSignatureCutText() {
        return "C|";
    }

    // DYNAMIKA - użyj tekstu
    public enum Dynamic {
        PPP, PP, P, MP, MF, F, FF, FFF, SFZ, FP
    }

    public static String getDynamic(Dynamic dynamic) {
        return switch (dynamic) {
            case PPP -> "ppp";
            case PP -> "pp";
            case P -> "p";
            case MP -> "mp";
            case MF -> "mf";
            case F -> "f";
            case FF -> "ff";
            case FFF -> "fff";
            case SFZ -> "sfz";
            case FP -> "fp";
        };
    }

    // KRESKI TAKTOWE (proste znaki)
    public static String getBarlineSingle() {
        return "|";
    }

    public static String getBarlineDouble() {
        return "\u2016"; // ‖ Double vertical line
    }

    public static String getBarlineFinal() {
        return "\uD834\uDD01"; // 𝄁 Final barline (U+1D101)
    }

    public static String getBarlineRepeatStart() {
        return "\uD834\uDD00"; // 𝄀 Start repeat (U+1D100)
    }

    public static String getBarlineRepeatEnd() {
        return "\uD834\uDD02"; // 𝄂 End repeat (U+1D102)
    }


}
package azelea.codewars;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Decode the Morse code, advanced <4kyu>
 * 
 * @desciption
 *             In this kata you have to write a Morse code decoder for wired
 *             electrical telegraph.
 *             Electric telegraph is operated on a 2-wire line with a key that,
 *             when pressed, connects the wires together, which can be detected
 *             on a remote station. The Morse code encodes every character being
 *             transmitted as a sequence of "dots" (short presses on the key)
 *             and "dashes" (long presses on the key).
 * 
 *             When transmitting the Morse code, the international standard
 *             specifies that:
 * 
 *             "Dot" – is 1 time unit long.
 *             "Dash" – is 3 time units long.
 *             Pause between dots and dashes in a character – is 1 time unit
 *             long.
 *             Pause between characters inside a word – is 3 time units long.
 *             Pause between words – is 7 time units long.
 *             However, the standard does not specify how long that "time unit"
 *             is. And in fact different operators would transmit at different
 *             speed. An amateur person may need a few seconds to transmit a
 *             single character, a skilled professional can transmit 60 words
 *             per minute, and robotic transmitters may go way faster.
 * 
 *             For this kata we assume the message receiving is performed
 *             automatically by the hardware that checks the line periodically,
 *             and if the line is connected (the key at the remote station is
 *             down), 1 is recorded, and if the line is not connected (remote
 *             key is up), 0 is recorded. After the message is fully received,
 *             it gets to you for decoding as a string containing only symbols 0
 *             and 1.
 * 
 *             For example, the message HEY JUDE, that is ···· · −·−− ·−−− ··−
 *             −·· · may be received as follows:
 * 
 *             1100110011001100000011000000111111001100111111001111110000000000000011001111110011111100111111000000110011001111110000001111110011001100000011
 * 
 *             As you may see, this transmission is perfectly accurate according
 *             to the standard, and the hardware sampled the line exactly two
 *             times per "dot".
 * 
 *             That said, your task is to implement two functions:
 * 
 *             Function decodeBits(bits), that should find out the transmission
 *             rate of the message, correctly decode the message to dots .,
 *             dashes - and spaces (one between characters, three between words)
 *             and return those as a string. Note that some extra 0's may
 *             naturally occur at the beginning and the end of a message, make
 *             sure to ignore them. Also if you have trouble discerning if the
 *             particular sequence of 1's is a dot or a dash, assume it's a dot.
 *             2. Function decodeMorse(morseCode), that would take the output of
 *             the previous function and return a human-readable string.
 * 
 *             NOTE: For coding purposes you have to use ASCII characters . and
 *             -, not Unicode characters.
 * 
 *             The Morse code table is preloaded for you (see the solution
 *             setup, to get its identifier in your language).
 * 
 * 
 *             Eg:
 *             morseCodes(".--") //to access the morse translation of ".--"
 *             All the test strings would be valid to the point that they could
 *             be reliably decoded as described above, so you may skip checking
 *             for errors and exceptions, just do your best in figuring out what
 *             the message is!
 * 
 *             Good luck!
 * 
 *             After you master this kata, you may try to Decode the Morse code,
 *             for real.
 * 
 * @link https://www.codewars.com/kata/54b724efac3d5402db00065e
 * @link https://www.codewars.com/kata/54b72c16cd7f5154e9000457
 */
public class MorseCodeDecoder {
    public static String decodeBits(String bits) {
        bits = bits.replaceAll("^0+|0+$", "");
        
        int unit = Integer.MAX_VALUE;
        Matcher matcher = Pattern.compile("0+|1+").matcher(bits);
        while (unit > 1 && matcher.find()) {
            unit = Math.min(unit, matcher.group().length());
        }

        String dot = new String(new char[unit]).replace('\0', '1');
        String dash = new String(new char[unit * 3]).replace('\0', '1');
        String pause = new String(new char[unit]).replace('\0', '0');
        String cpause = new String(new char[unit * 3]).replace('\0', '0');
        String wpause = new String(new char[unit * 7]).replace('\0', '0');

        return bits.replace(dash, "-").replace(dot, ".")
                .replace(wpause, "   ").replace(cpause, " ").replace(pause, "");
    }

    public static String decodeMorse(String morseCode) {
        return Arrays.stream(morseCode.split("   "))
                .map(word -> Arrays.stream(word.split(" ")).map(MorseCode::get)
                        .collect(Collectors.joining()))
                .collect(Collectors.joining(" "));
    }

    private static class MorseCode {
        public static String get(String code) {
            return null;
        }
    }
}
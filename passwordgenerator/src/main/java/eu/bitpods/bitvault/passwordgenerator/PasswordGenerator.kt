package eu.bitpods.bitvault.passwordgenerator

import java.security.SecureRandom

object PasswordGenerator {

    private const val UPPERCASE_CHAR_SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private const val LOWERCASE_CHAR_SET = "abcdefghijklmnopqrstuvwxyz"
    private const val NUMBER_SET = "0123456789"
    private const val SPECIAL_CHARS_SET = "!\"#$%&'()*+,-./:;<=>?@[]^_`{|}~"

    fun generate(
        upperCase: Boolean = true,
        lowerCase: Boolean = true,
        maxLength: Int,
        numbers: Boolean,
        specialCharacters: Boolean,
    ): String {

        val rn = SecureRandom()
        val sb = StringBuilder(maxLength)
        val allChars = buildList {
            if (upperCase) {
                add(UPPERCASE_CHAR_SET.toList().shuffled().take(20))
            }
            if (lowerCase) {
                add(LOWERCASE_CHAR_SET.toList().shuffled().take(20))
            }
            if (numbers) {
                add(NUMBER_SET.toList().shuffled())
                add(NUMBER_SET.toList().shuffled())
            }
            if (specialCharacters) {
                add(SPECIAL_CHARS_SET.toList().shuffled().take(15))
            }
        }.flatten().shuffled()

        // this will fulfill the requirements of atleast one character of a type.
        if (upperCase) {
            sb.append(UPPERCASE_CHAR_SET[rn.nextInt(UPPERCASE_CHAR_SET.length - 1)])
        }
        if (lowerCase) {
            sb.append(LOWERCASE_CHAR_SET[rn.nextInt(LOWERCASE_CHAR_SET.length - 1)])
        }
        if (numbers) {
            sb.append(NUMBER_SET[rn.nextInt(NUMBER_SET.length - 1)])
        }
        if (specialCharacters) {
            sb.append(SPECIAL_CHARS_SET[rn.nextInt(SPECIAL_CHARS_SET.length - 1)])
        }

        // fill the allowed length from different chars now.
        for (i in sb.length until maxLength) {
            sb.append(allChars.random())
        }

        return sb.toString()
            .toList()
            .shuffled()
            .shuffled()
            .joinToString(separator = "")
    }
}
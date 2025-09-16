package eu.bitpods.bitvault.passwordgenerator

import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class PasswordTests {

    @Test
    fun `should generate default`() {
        val password = PasswordGenerator.generate(
            maxLength = 10,
            numbers = true,
            specialCharacters = true
        )
        assertTrue(password.length == 10)
        assertTrue(password.any { it.isUpperCase() })
        assertTrue(password.any { it.isLowerCase() })
        assertTrue(password.any { it.isDigit() })
        assertTrue(password.any { !it.isLetterOrDigit() })
    }

    @Test
    fun `should generate no special characters`() {
        val password = PasswordGenerator.generate(
            maxLength = 10,
            numbers = true,
            specialCharacters = false
        )
        assertTrue(password.length == 10)
        assertTrue(password.any { it.isUpperCase() })
        assertTrue(password.any { it.isLowerCase() })
        assertTrue(password.any { it.isDigit() })
        assertFalse(password.any { !it.isLetterOrDigit() })
    }

    @Test
    fun `should generate no special characters and no numbers`() {
        val password = PasswordGenerator.generate(
            maxLength = 10,
            numbers = false,
            specialCharacters = false
        )
        assertTrue(password.length == 10)
        assertTrue(password.any { it.isUpperCase() })
        assertTrue(password.any { it.isLowerCase() })
        assertFalse(password.any { it.isDigit() })
        assertFalse(password.any { !it.isLetterOrDigit() })
    }
}
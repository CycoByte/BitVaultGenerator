package eu.bitpods.bitvault.passwordgenerator

import junit.framework.TestCase.assertTrue
import org.junit.Test

class PassphraseTests {

    @Test
    fun `should generate 1`() {
        val result = PassphraseGenerator.generate(1, false)
        assertTrue(result.isNotEmpty())
        assertTrue(result.count { it == '-' } == 0)
    }

    @Test
    fun `should generate 2`() {
        val result = PassphraseGenerator.generate(2, false)
        assertTrue(result.isNotEmpty())
        assertTrue(result.count { it == '-' } == 1)
    }

    @Test
    fun `should generate 3`() {
        val result = PassphraseGenerator.generate(3, false)
        assertTrue(result.isNotEmpty())
        assertTrue(result.count { it == '-' } == 2)
    }

    @Test
    fun `should generate 4`() {
        val result = PassphraseGenerator.generate(4, false)
        assertTrue(result.isNotEmpty())
        assertTrue(result.count { it == '-' } == 3)
    }

    @Test
    fun `should generate 5`() {
        val result = PassphraseGenerator.generate(5, false)
        assertTrue(result.isNotEmpty())
        assertTrue(result.count { it == '-' } == 4)
    }

    @Test
    fun `should generate 5 with digits`() {
        val result = PassphraseGenerator.generate(5, true)
        assertTrue(result.isNotEmpty())
        assertTrue(result.count { it == '-' } == 4)
        result.split("-").forEach { word ->
            assertTrue(word.any { it.isDigit() })
        }
    }
}
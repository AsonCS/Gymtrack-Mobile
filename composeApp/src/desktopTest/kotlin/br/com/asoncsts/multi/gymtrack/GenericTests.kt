package br.com.asoncsts.multi.gymtrack

import br.com.asoncsts.multi.gymtrack.extension.deviceLanguage
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import org.junit.Assert
import org.junit.Test
import java.io.FileReader

class GenericTest {

    @Test
    fun `Math 4 operations`() = runBlocking {
        Assert.assertEquals(
            2,
            1 + 1
        )
        Assert.assertEquals(
            0,
            2 - 2
        )
        Assert.assertEquals(
            9,
            3 * 3
        )
        Assert.assertEquals(
            1,
            4 / 4
        )
    }

    @Test
    fun `Locale map test`() = runBlocking {
        val lang = deviceLanguage()
        /*
        Assert.assertEquals(
            DeviceLanguage.Pt,
            lang
        )
        // */
        println(lang)
    }

    @Test
    fun `Print Google Services json`() = runBlocking {
        /*
        FileReader("./src/google-services.json").use {
            val json = Json.decodeFromString<JsonObject>(it.readText())
            println(json)
        }
        // */
    }

}

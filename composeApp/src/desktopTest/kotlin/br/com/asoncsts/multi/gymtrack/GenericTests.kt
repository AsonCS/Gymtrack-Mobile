package br.com.asoncsts.multi.gymtrack

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

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

}

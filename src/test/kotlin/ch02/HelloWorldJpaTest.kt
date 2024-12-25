package ch02

import ch02.entity.Message
import common.use
import org.junit.jupiter.api.assertAll
import javax.persistence.EntityManager
import javax.persistence.Persistence
import kotlin.test.Test
import kotlin.test.assertEquals

internal class HelloWorldJpaTest {

    /**
     * Jpa 기능을 활용해 Hibernate를 사용할경우 persistence.xml 파일처럼 설정이 필요함
     */
    @Test
    fun storeLoadMessage() {
        val result = Persistence.createEntityManagerFactory("ch02-test").use { em: EntityManager ->
            em.transaction.begin()

            // INSERT into message (ID, TEXT) values (1, 'Hello World!')
            val message = Message(id = null, text = "Hello World!")
            em.persist(message)

            em.transaction.commit()

            em.transaction.begin()

            // SELECT * FROM message
            val messages = em.createQuery("SELECT m from Message m", Message::class.java).resultList.toList()

            messages.last().changeMessage("Hello World from JPA!")

            em.transaction.commit()

            em.transaction.begin()
            val messages2 = em.createQuery("SELECT m from Message m", Message::class.java).resultList.toList()
            em.transaction.commit()
            println(messages2)

            assertAll(
                { assertEquals(1, messages.size) },
                { assertEquals("Hello World from JPA!", messages[0].text) }
            )
        }

        assert(result.isSuccess)
    }
}
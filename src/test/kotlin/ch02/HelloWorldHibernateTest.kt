package ch02

import ch02.entity.Message
import org.hibernate.SessionFactory
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.cfg.Configuration
import org.junit.jupiter.api.assertAll
import kotlin.test.Test
import kotlin.test.assertEquals

internal class HelloWorldHibernateTest {

    /**
     * Hibernate를 직접 사용할경우 hibernate.cfg.xml 파일처럼 설정이 필요함
     */
    @Test
    fun storeLoadMessage() {
        createSessionFactory().use { sessionFactory ->
            val session = sessionFactory.openSession()

            session.beginTransaction()

            // INSERT INTO message (id, text) VALUES (1, 'Hello World from Hibernate!')
            val message = Message(null, "Hello World from Hibernate!")
            session.persist(message)

            session.transaction.commit()

            session.beginTransaction()

            val criteriaQuery = session.criteriaBuilder.createQuery(Message::class.java)
            criteriaQuery.from(Message::class.java)

            // SELECT * FROM message m
            val messages = session.createQuery(criteriaQuery).resultList.toList()

            session.transaction.commit()

            assertAll(
                { assertEquals(1, messages.size) },
                { assertEquals("Hello World from Hibernate!", messages.first().text) }
            )
        }
    }

    fun createSessionFactory(): SessionFactory {
        val configuration = Configuration().apply {
            configure().addAnnotatedClass(Message::class.java)
        }

        val serviceRegistry = StandardServiceRegistryBuilder().applySettings(configuration.properties).build()
        return configuration.buildSessionFactory(serviceRegistry)
    }
}
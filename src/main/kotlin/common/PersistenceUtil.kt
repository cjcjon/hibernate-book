package common

import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory

fun <T> EntityManagerFactory.use(f: (entityManager: EntityManager) -> T): Result<T> {
    var entityManager: EntityManager? = null

    return try {
        entityManager = this.createEntityManager()

        Result.success(f(entityManager))
    } catch (e: Exception) {
        Result.failure(e)
    } finally {
        entityManager?.close()
        this.close()
    }
}

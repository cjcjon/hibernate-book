package ch02.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Message(
    id: Long?,
    text: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = id

    @Column(nullable = false)
    var text: String = text
        protected set

    fun changeMessage(message: String) {
        this.text = message
    }

    override fun toString(): String =
        "${this.javaClass.simpleName}($id, $text)"
}
package ru.netology.lesson7

import java.lang.RuntimeException
import java.util.concurrent.atomic.AtomicInteger

/**
 * Description WallService
 * Сервис для хранения и добавления постов
 */

class WallService() {
    //массив постов
    private var posts    = emptyArray<Post>()
    //массив комментариев
    private var comments = emptyArray<Comment>()
    private var commentReports = emptyArray<CommentReport>()
    //генератор уникальных Id
    private var idGenerator = AtomicInteger()
    private fun generateId() : Int  = idGenerator.incrementAndGet()
    /**
     *  функция возвращает количество элементов
     */
    fun count() = posts.size
    /**
     *  функция ищет элемент в массиве или возвращает null
     */
    fun find(predicate: (Post) -> Boolean): Post? = posts.firstOrNull(predicate)

    /**
     * Description функция add добавляет новый пост в список с уникальным id
     * возвращает пост с назначенным id
     */
    fun add(post : Post) = post.copy(id = generateId()).apply {
                                posts += this
                                return this
                            }
    /**
     * Description функция update обновляет содержимое поста (по сути редактирование)
     */
    fun update(post: Post): Boolean
    {
        val (match, rest) = posts.partition { it.id == post.id }
        if (match.isNotEmpty())
        {
            posts = rest.plus(post.copy(id = match.first().id, date = match.first().date)).toTypedArray()
        }
        return match.isNotEmpty()
    }
    /**
     * Добавляет комментарий к записи на стене.
     */
    fun createComment(comment: Comment) {
        find { comment.postId == it.id } ?: throw PostNotFoundException(comment.postId)

        comments += comment
    }

    /**
     * поиск комментария
     */
    private fun findComment(predicate: (Comment) -> Boolean): Comment? = comments.firstOrNull(predicate)
    /**
     * функция пожаловаться на комментарий
     */
    fun reportComment(ownerId : Int, commentId : Int, reason : ReasonDesc)
    {
        findComment { ownerId   == it.fromId } ?: throw CommentNotFoundException(ownerId = ownerId)
        findComment { commentId == it.id }     ?: throw CommentNotFoundException(id = commentId)
        commentReports += CommentReport(ownerId, commentId, reason)
    }
}

/**
 * одна запись о жалобе
 */
data class CommentReport(
    val ownerId   : Int, 
    val commentId : Int,
    val reason    : ReasonDesc
)

/**
 * тип жалобы
 */
enum class ReasonDesc(value : Int, text : String) {
    SPAM             (0, "спам"),
    MINOR_PORN       (1, "детская порнография"),
    EXTREMISM        (2, "экстремизм"),
    VIOLENCE         (3, "насилие"),
    DRUG_PROPAGANDA  (4, "пропаганда наркотиков"),
    ADULT_CONTENT    (5, "материал для взрослых"),
    ABUSE            (6, "оскорбление"),
    APPEAL_TO_SUICIDE(8, "призывы к суициду")
}

/**
 * класс исключение как попытка доступа к несуществующему посту
 */
class PostNotFoundException(id : Int) :
    RuntimeException("Ошибка добавления комментария. Пост с id=$id не найден!")

/**
 * класс исключение как попытка доступа к несуществующему комментарию
 */
class CommentNotFoundException(id : Int = -1, ownerId : Int = -1) :
    RuntimeException(when{
        id      != -1 -> "Комментарий с id=$id не найден!"
        ownerId != -1 -> "Пользователь с id=$ownerId не найден!"
        else         -> ""
    })
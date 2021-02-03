package ru.netology.lesson7

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
        find { comment.postId == it.id } ?: throw PostNotFoundException(
            "Ошибка добавления комментария. Пост с id=$comment.postId не найден!")

        comments += comment
    }
}
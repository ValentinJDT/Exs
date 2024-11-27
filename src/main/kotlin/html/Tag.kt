package html

import java.util.*


typealias Body = Tag.() -> Unit

open class Tag {

    open var body: Body? = null
    open var tag: String? = null
    open var parent: Tag? = null

    private val properties = HashMap<String, String>()

    @Deprecated("Use set(key, value) instead of.")
    var className: String? = null
    val innerTags = ArrayList<Tag>()
    var str: String? = null

    constructor(body: Body?, tag: String?, parent: Tag? = null) {
        this.body = body
        this.tag = tag
        this.parent = parent
        body!!(this)
    }

    operator fun String.unaryPlus(): Boolean = innerTags.add(text(this))

    fun set(key: String, value: String) = properties.put(key, value)

    private fun String.tirets() = replace(Regex("([A-Z])"), " $1").lowercase().replace(" ", "-")

    operator fun String.invoke(body: Body = {}): Tag {
        val tag = Tag(body, this.tirets(), this@Tag)
        innerTags.add(tag)
        return tag
    }

    private fun render(): String = innerTags.joinToString("")

    override fun toString(): String {
        val rendered = render()
        return if (str == null) "<$tag${ if(className != null) " class=\"$className\"" else ""}${properties.map { " ${it.key}=\"${it.value}\"" }.joinToString("")}${if(rendered != "") ">${render()}</$tag>" else "/>"}" else str!!
    }
}

class body(override var parent: Tag? = null, override var body: Body?) : Tag(body, "body", parent)
class html(override var parent: Tag? = null, override var body: Body?) : Tag(body, "html", parent)
private class text(str: String) : Tag({}, "") {
    init {
        super.str = str
    }
}

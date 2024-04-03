package test.target

import test.Test
import test.TestClass
import html.html
import java.time.Instant
import java.time.format.DateTimeFormatter

class HtmlTest: TestClass() {

    @Test
    fun `render title`() {

        val mainTag = html {
            set("lang", "en")

            "head" {
                "meta" {
                    set("charset", "UTF-8")
                }
                "meta" {
                    set("name", "viewport")
                    set("content", "width=device-width, initial-scale=1.0")
                }
                "title" {
                    +DateTimeFormatter.ISO_INSTANT.format(Instant.now())
                }
            }

            "body" {
                "p" {
                    set("class", "suus")

                    +"Lorem ipsum"; "br"()
                    +"dolor"

                    "span"()
                }
                "input" {
                    set("type", "number")
                    set("class", "mqzd qzd uuqzd")
                    set("aria-label", "idqz")
                    set("id", "1234")
                }
            }
        }

        println(mainTag)
    }

}

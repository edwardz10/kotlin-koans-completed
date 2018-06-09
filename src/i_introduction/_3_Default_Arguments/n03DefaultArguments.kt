package i_introduction._3_Default_Arguments

import util.TODO
import util.doc2

fun todoTask3(): Nothing = TODO(
    """
        Task 3.
        Several overloaded 'foo' functions in the class 'JavaCode3' can be replaced with one function in Kotlin.
        Change the declaration of the function 'foo' in a way that makes the code using 'foo' compile.
        You have to add 'foo' parameters and implement its body.
        Uncomment the commented code and make it compile.
    """,
    documentation = doc2(),
    references = { name: String -> JavaCode3().foo(name); foo(name) })

fun foo(name: String, number: Int = 42, toUpperCase: Boolean = false): String =
    if (toUpperCase) name.toUpperCase() + number else name + number


fun task3(): String {
    return (foo("a") + foo("b", 1) + foo("C", 42, true) + foo("d", 2, true))
}
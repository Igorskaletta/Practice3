import Operation.SORT_ASC
import Operation.SORT_DESC
import Operation.SHUFFLE

fun main(){
    val rndInt = generateIntegers(10)
    println("Исходные рандомные числа: $rndInt")
    println("Сортировка по возрастанию: "+eval(rndInt.operate(SORT_ASC)))
    println("Сортировка по убыванию: "+eval(rndInt.operate(SORT_DESC)))
    println("Перемешивание: "+eval(rndInt.operate(SHUFFLE)))

    val rndString = generateStrings(5, 10)
    println("Исходные рандомные строки: $rndString")
    println("Сортировка по возрастанию: "+eval(rndString.operate(SORT_ASC)))
    println("Сортировка по убыванию: "+eval(rndString.operate(SORT_DESC)))
    println("Перемешивание: "+eval(rndString.operate(SHUFFLE)))
}

fun <T: Comparable<T>> List<T>.operate(operation: Operation): Result<List<T>> {
    return if (this.isNotEmpty()) {
        when (operation) {
            SORT_ASC -> Result.Success(SORT_ASC(this))
            SORT_DESC -> Result.Success(SORT_DESC(this))
            SHUFFLE -> Result.Success(SHUFFLE(this))
        }
    }
    else Result.Error("Список пустой");

}

sealed class Result<T> {
    class Success<T>(val data: T) : Result<T>()
    class Error<T>(val errorString: String) : Result<T>()
}

fun <T: Comparable<T>> eval(res: Result<List<T>>): Any = when(res){
    is Result.Success -> res.data
    is Result.Error -> res.errorString
}


enum class Operation{
    SORT_ASC {
        override fun <T : Comparable<T>> invoke(list: List<T>): List<T> = list.sorted()
    },
    SORT_DESC {
        override fun <T : Comparable<T>> invoke(list: List<T>): List<T> = list.sortedDescending()
    },
    SHUFFLE {
        override fun <T : Comparable<T>> invoke(list: List<T>): List<T> = list.shuffled()
    };

    abstract operator fun <T : Comparable<T>> invoke(list: List<T>): List<T>
}

fun generateIntegers(length : Int) : List<Int> = List(length) { java.util.Random().nextInt(100) }

fun generateStrings(stringsLength: Int, length : Int) : List<String> {
    val rnd = mutableListOf<String>()
    val alphabet: List<Char> = ('a'..'z') + ('A'..'Z')
    for (i in 1..length)
        rnd.add(List(stringsLength) { alphabet.random() }.joinToString(""))
    return rnd
}































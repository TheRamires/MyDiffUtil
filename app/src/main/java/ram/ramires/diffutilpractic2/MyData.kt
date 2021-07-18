package ram.ramires.diffutilpractic2
import java.util.*

data class MyData(
    val id: String = Random().nextInt(100000).toString(),
    var name : String ="",
    var weight: String =""
)
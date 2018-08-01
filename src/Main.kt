import image.Image
import spline.Spline
import java.util.*

// generates a sequence of equally spaced values from start to stop
fun linspace(start: Double, stop: Double, num: Int): Sequence<Double> {
    val step = (stop - start) / num
    return generateSequence(start, {it + step}).take(num)
}

fun zeros(num: Int): Sequence<Double> {
    return linspace(0.0, 0.0, num)
}

fun guidelines(size: Int, edge: Double): Sequence<Double> {
    return linspace(edge, 1.0 - edge, size * 10)
}

fun main(args: Array<String>) {
    val size = 800
    val edge = 0.08
    val interpolationPoints = 20 * size
    val step = 0.00000006
    val gamma = 1.5
    var sand = Image(100)

    val pathsNumber = Random().nextInt(96) + 4
    val pathsStartingPoints = linspace(edge, 1.0 - edge, pathsNumber).map { it -> Pair(0, it) }
    val scale = (0..pathsNumber).map { it -> it * step }

    val guideline = guidelines(size, edge)
    guideline.forEach {
        val spline = Spline(it)
        sand.drawPoints(spline.interpolate())
    }

    sand.save()
}
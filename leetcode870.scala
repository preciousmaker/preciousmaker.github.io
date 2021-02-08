package leetcode
//https://leetcode-cn.com/problems/advantage-shuffle/
import scala.util.control.Breaks._

object leetcode870 {
  def main(args: Array[String]): Unit = {
    val A = Array(718967141,189971378,341560426,23521218,339517772)
    val B = Array(967482459,978798455,744530040,3454610,940238504)
    advantageCount(A, B).foreach(println)

  }


  def advantageCount(A: Array[Int], B: Array[Int]): Array[Int] = {

    //将A排序并处理为可变的ArrayBuffer
    val C = A.sorted.toBuffer

    //定义一个空Array，存放结果
    var D: Array[Int] = Array()

    for(i <- 0 until B.length){
      breakable{
        for(j <- 0 until C.length){
          if (B(i) < C(j)) {
            //存放第一个比B(i)大的C(j)
            D = D :+ C(j)

            //在C中去掉这个值，之后不做比较
            C.remove(j)
            break
          }
        }

        //如果没有比B(i)大的C(j)，取C(0)，田忌赛马，这里的C(0)就是下等马
        D = D :+ C(0)

        //同样去掉，之后不做比较
        C.remove(0)
        break
      }
    }

    D

  }
}

package leetcode
//https://leetcode-cn.com/problems/add-binary/

object leetcode67 {
  def main(args: Array[String]): Unit = {
    println(addBinary("111", "11"))
  }

  def addBinary(a: String, b: String): String = {
    var sum = ""
    val aLen = a.length
    val bLen = b.length

    //取到a,b的最大长度
    val maxL = Math.max(aLen, bLen)

    //代表 进位 初始为1
    var carry = 0

    for (i <- 0 to maxL - 1) {

      //从最后一位往前取，相加，类似平时做的加法
      val x = if (aLen > i) a.substring(aLen - i - 1, aLen - i).toInt else 0
      val y = if (bLen > i) b.substring(bLen - i - 1, bLen - i).toInt else 0
      val acc = (x + y + carry).toString

      acc match {
          //x,y,carry均为1，说明需要进位，也就是carry仍为1，进行下一次计算，sum = "1" + sum
        case "3" =>
          carry = 1
          sum = "1" + sum
          //x,y,carry有两个为1，说明需要进位，也就是carry仍为1，进行下一次计算，sum = "0" + sum
        case "2" =>
          carry = 1
          sum = "0" + sum
        //x,y,carry最多有一个为1，说明不需要进位，carry = 0，此时acc可能为0或1，不过无所谓，sum = acc + sum
        case _ =>
          carry = 0
          sum = acc + sum

      }
    }

    //循环结束如果carry为1，说明仍需要进位，那么sum = "1" + sum
    if (carry != 0) sum = "1" + sum

    sum
  }

}

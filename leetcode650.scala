package leetcode
//https://leetcode-cn.com/problems/2-keys-keyboard/

object leetcode650 {
  def main(args: Array[String]): Unit = {
    println(minSteps(26))
  }

  /**
   * 找最大公约数，除之后的结果继续找最大公约数，次数就是最大公约数相加
   * @param n
   * @return
   */
  def minSteps(n: Int): Int = {
    var x = n

    //acc就是记录每个最大公约数相加
    var acc = 0

    //被除数，每次能除尽就加1
    var d = 2

    while (x > 1){
      while (x % d == 0){
        acc += d
        x /= d
      }
      d = d + 1
    }

    acc

  }
}

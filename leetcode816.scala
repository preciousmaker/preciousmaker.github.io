package leetcode


import scala.collection.mutable.ListBuffer
//https://leetcode-cn.com/problems/ambiguous-coordinates/

object leetcode816 {
  def main(args: Array[String]): Unit = {

    println(ambiguousCoordinates("(0123)"))

  }

  def ambiguousCoordinates(S: String): List[String] = {

    //取出参数中的数字
    val A: String = S.replaceAll("[^0-9]","")

    //结果存放在这里
    var D:ListBuffer[String] = ListBuffer()

    var a = ""
    var b = ""
    var x = ""
    var y = ""


    for(i <- 1 until A.length) {
      //存放符合要求的数据，放入List
      val resA: ListBuffer[String] = ListBuffer()
      val resB: ListBuffer[String] = ListBuffer()
      val resX: ListBuffer[String] = ListBuffer()
      val resY: ListBuffer[String] = ListBuffer()


      /**
       * 分为4部分
       * a : 前n个字符
       * b : 除a之外的其他字符
       * x : 在a中插入小数点，从第一个字符开始插入
       * y : 在b中插入小数点，从第一个字符开始插入
       */
      a = A.substring(0, i)
      if (isInt(a)){
        println("a : " + a)
        resA.append(a)
      }


      b = A.substring(i, A.length)
      if (isInt(b)){
        println("b : " + b)
        resB.append(b)
      }


      for (j <- 1 to a.length) {
        x = a.substring(0, j) + "." + a.substring(j, a.length)
        if (isDouble(x)){
          println("x : " + x)
          resX.append(x)
        }

      }

      for (k <- 1 to b.length) {
        y = b.substring(0, k) + "." + b.substring(k, b.length)
        if (isDouble(y)){
          println("y : " + y)
          resY.append(y)
        }

      }


      /**
       * 循环读取List中的数据，做笛卡尔积，放入D中
       * a x 均为第一部分
       * b y 为第二部分
       * 所以组合为(a, b)(a, y)(x, b)(x, y)
       */

      // a b
      // 一次循环中，只有一个a 一个b
      if(resA.nonEmpty && resB.nonEmpty){
        println("a b : " + resA(0), resB(0))
        D += "(" + resA(0) + ", " +  resB(0) + ")"
      }


      // a y
      // 一次循环中，有一个a，很多个y
      if(resA.nonEmpty && resY.nonEmpty){
        for (i <- 0 until resY.size){
          println("a y : " + resA(0), resY(i))
          D += "(" + resA(0) + ", " +  resY(i) + ")"
        }
      }



      // x b
      // 一次循环中，有很多个x，一个b
      if(resX.nonEmpty && resB.nonEmpty){
        for (i <- 0 until resX.size){
          println("x b : " + resX(i), resB(0))
          D += "(" + resX(i) + ", " +  resB(0) + ")"
        }
      }


      // x y
      // 一次循环中，有很多个x，y
      if(resX.nonEmpty && resY.nonEmpty){
        for (i <- 0 until resX.size){
          for (j <- 0 until resY.size){
            println("x y : " + resX(i),resY(j))
            D += "(" + resX(i) + ", " +  resY(j) + ")"
          }
        }
      }

      println("--------")

    }

    D.toList
  }


  /**
   * "00".toInt的结果是0
   * 为了防止出现这种现象，去toInt之后的值再转成字符串，跟s比较
   * 相等则说明s是真正的Int类型的数据
   *
   * @param s
   * @return
   */
  def isInt(s:String): Boolean = {
    s == Some(s.toInt).get.toString
  }


  /**
   *
   * Some(BigDecimal(s)).get.toString
   * BigDecimal("12.")的结果为12.0，所以这类数据需要过滤掉
   *
   * s.substring(s.length - 1,s.length) != "0"
   * 题目要求1.0 0.0 0.00这类的数据需要过滤掉
   * 所以最后一位不能为0
   *
   *
   * @param s
   * @return
   */
  def isDouble(s:String): Boolean ={

    s == Some(BigDecimal(s)).get.toString && s.substring(s.length - 1,s.length) != "0"
  }

}

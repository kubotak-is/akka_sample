package jp.co.kubotak

import akka.actor.{Actor, ActorRef, ActorSystem}
import akka.util.Timeout
import com.typesafe.config.ConfigFactory

import scala.concurrent.duration.Duration

object RemotePay {
  def main(args: Array[String]): Unit = {
    implicit val timeout: Timeout = Timeout(Duration(5000, "ms"))
    val config = ConfigFactory.load("remotePay-application.conf")
    ActorSystem("remotePay", config)
  }
}

case class PayPay(msg: ActorRef)
case class PayPe(msg: ActorRef)

class PayPayActor(call: String) extends Actor {
  override def receive: Receive = {
    case PayPay(msg: ActorRef) =>
      callPayPay(msg)
    case PayPe(msg: ActorRef) =>
      callPayPe(msg)
  }

  def callPayPay(msg: ActorRef): Unit = {
    println(s"${call}")
    Thread.sleep(1000)
    msg ! PayPe(self)
  }

  def callPayPe(msg: ActorRef): Unit = {
    println(s"${call}")
    Thread.sleep(1000)
    msg ! PayPay(self)
  }
}

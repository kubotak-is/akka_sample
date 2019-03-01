package jp.co.kubotak

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.util.Timeout
import com.typesafe.config.ConfigFactory

import scala.concurrent.duration.Duration

object BootPay extends App {
  val config = ConfigFactory.load("application.conf")
  implicit val timeout: Timeout = Timeout(Duration(5000, "ms"))
  implicit val system: ActorSystem = ActorSystem("PayPatSystem", config)

  val paypay = system.actorOf(Props(classOf[PayPayActor], "pay pay"), "paypay")
  val paype = system.actorOf(Props(classOf[PayPayActor], "pay pe"), "paype")

  paypay ! PayPay(paype)

  Thread.sleep(10000)
  system.terminate()
  sys.exit()
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

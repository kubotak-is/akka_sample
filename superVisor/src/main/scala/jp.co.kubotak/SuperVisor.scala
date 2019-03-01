package jp.co.kubotak

import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume}
import akka.actor.{Actor, ActorRef, ActorSystem, OneForOneStrategy, Props, SupervisorStrategy}
import akka.util.Timeout
import scala.concurrent.duration.Duration

object SuperVisor extends App {
  implicit val timeout: Timeout = Timeout(Duration(5000, "ms"))
  implicit val system: ActorSystem = ActorSystem("PayPatSystem")

  val superVisor = system.actorOf(Props(classOf[SuperVisor]))

  for (i <- 0 to 9) {
    superVisor ! i
  }

  Thread.sleep(10000)
  system.terminate()
    sys.exit()
}

class SuperVisor extends Actor {
  override def supervisorStrategy: SupervisorStrategy = OneForOneStrategy() {
    case e: RuntimeException if e.getMessage == "crash" => {
      println("crash")
      Restart
    }
    case _: Exception => Escalate
  }

  val child = context.actorOf(Props(classOf[Child]))
  context.watch(child)

  override def receive: Receive = {
    case x => child ! x
  }
}

class Child extends Actor {
  override def receive: Receive = {
    case _ =>
      if (Math.floor(Math.random * 10).toInt > 5) {
        throw new RuntimeException("crash")
      }
      println("hello")
  }
}
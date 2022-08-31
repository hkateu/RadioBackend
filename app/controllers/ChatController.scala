package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.streams.ActorFlow
import akka.actor.ActorSystem
import akka.actor.Props

import scala.concurrent.ExecutionContext.Implicits.global
import akka.stream.Materializer
import actors.ChatActor
import actors.ChatManager

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class ChatController @Inject()(cc: ControllerComponents)(implicit system: ActorSystem, mat: Materializer) extends AbstractController(cc) {
   val manager =  system.actorOf(Props[ChatManager], "Manager")
   
   def Index = Action{implicit request =>
    Ok(views.html.index())
    }

    def socket = WebSocket.accept[String, String]{request =>
        ActorFlow.actorRef{ out =>
            ChatActor.props(out, manager)
        }
        }
}
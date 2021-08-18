package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
//import models.TaskListInMemoryModel
import models._
import play.api.db.slick.DatabaseConfigProvider
import scala.concurrent.ExecutionContext
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._
import scala.concurrent.Future
import play.filters.csrf._
import play.filters.csrf.CSRF.Token

@Singleton
class RadioController @Inject()(protected val dbConfigProvider: DatabaseConfigProvider, val controllerComponents: ControllerComponents, val addToken: CSRFAddToken, checkToken: CSRFCheck)(implicit ec:ExecutionContext) 
extends BaseController with HasDatabaseConfigProvider[JdbcProfile] {
   
    // private val model = new RadioModel(db)
    private val model = new RadioModel(db)

    def index() = Action { implicit request: Request[AnyContent] =>
        Ok(views.html.index())
    }

     implicit val userDataReads: Reads[UserData] = Json.reads[UserData]

    // def gotData() = Action{ implicit request: Request[AnyContent] =>
    //     request.body.asJson.map{body =>
    //         Json.fromJson[UserData](body) match {
    //             case JsSuccess(ud, path)=> 
    //                 Ok(Json.toJson(Seq("herbert", "kateu")))
    //             case e @ JsError(_) =>  Redirect(routes.Joblist.index())
    //         }
    //         Ok("")
    //         }.getOrElse(Redirect(routes.Joblist.index()))
    // }

    def getToken = addToken(Action { implicit request => 
     val Token(name,value) = CSRF.getToken.get 
     Ok(Json.toJson(value))
      })

    def staticData(name: String) = Action.async{ implicit request =>
         model.getUsers(name).map{userExists =>
            if(userExists){
            Ok(Json.toJson((true)))
         }else{
            Ok(Json.toJson((false)))
         }
         }
    }

    def getflname(email: String) = Action.async{ implicit request =>
         model.getNames(email).map{names =>
            Ok(Json.toJson(names))
         }
    }

    def getRadios() = Action.async{implicit request => 
      model.getRadio().map{rdio =>
         Ok(Json.toJson(rdio))
      }
   }

   def withJsonBody[A](f: A => Future[Result])(implicit request: Request[AnyContent], reads: Reads[A]): Future[Result] = {
      request.body.asJson.map{body =>
         Json.fromJson[A](body) match {
            case JsSuccess(a, path) => f(a)
            case e @ JsError(_) => Future.successful(Ok(Json.toJson("Error at withJsBody")))
         }
      }.getOrElse(Future.successful(Ok(Json.toJson("failed from withJsBody"))))
   }

   def signIn = Action.async { implicit request =>
      withJsonBody[UserData] { ud =>
         model.validateLogin(ud.email, ud.password).map{userExists => 
            if(userExists){
               Ok(Json.toJson(true))
            }else{
               Ok(Json.toJson(false))
            }
            }
      }
   }

   // def signIn = Action { implicit request =>
   //    request.body.asJson.map{body =>
   //          Json.fromJson[UserData](body) match {
   //              case JsSuccess(ud, path)=> 
   //                  Ok(Json.toJson(true))
   //              case e @ JsError(_) =>  
   //                  Ok(Json.toJson(false))
   //          }
   //          }.getOrElse(Ok(Json.toJson("Failed")))
   // }

   def getShow(id: Int) = Action.async{implicit request =>
      model.getShows(id).map{rshows => 
         Ok(Json.toJson(rshows.sortBy(_._3)))
         }
      }

   
}
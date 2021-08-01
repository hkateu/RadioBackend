package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
//import models.TaskListInMemoryModel
import models.RadioModel
import play.api.db.slick.DatabaseConfigProvider
import scala.concurrent.ExecutionContext
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._
import models.UserData
import scala.concurrent.Future

@Singleton
class RadioController @Inject()(protected val dbConfigProvider: DatabaseConfigProvider, val controllerComponents: ControllerComponents)(implicit ec:ExecutionContext) 
extends BaseController with HasDatabaseConfigProvider[JdbcProfile] {
   
    // private val model = new RadioModel(db)
    private val model = new RadioModel(db)

    def index() = Action { implicit request: Request[AnyContent] =>
        Ok(views.html.index())
    }

     implicit val userDataReads: Reads[models.UserData] = Json.reads[UserData]

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

    def staticData(name: String) = Action.async{ implicit request =>
         model.getUsers(name).map{userExists =>
            if(userExists){
            Ok(Json.toJson((true)))
         }else{
            Ok(Json.toJson((false)))
         }
         }
    }

    def getflname(name: String) = Action.async{ implicit request =>
         model.getNames(name).map{names =>
            Ok(Json.toJson(names))
         }
    }

    def getRadios() = Action.async{implicit request => 
      model.getRadio().map{rdio =>
         Ok(Json.toJson(rdio))
      }
   }

   def getShow(id: Int) = Action.async{implicit request =>
      model.getShows(id).map{rshows => 
         Ok(Json.toJson(rshows.sortBy(_._3)))
         }
      }
}
package models

import slick.jdbc.MySQLProfile.api._
import scala.concurrent.ExecutionContext
import models.Tables._
import scala.concurrent.Future


class RadioModel(db: Database)(implicit ec: ExecutionContext){
  
 def getUsers(name: String): Future[Boolean]  = {
  val matches = db.run(Users.filter(UsersRow => UsersRow.username === name).result)
  matches.map(usersRow => usersRow.nonEmpty)
 }

 def getNames(name: String): Future[Seq[(String, String)]] = {
  val matches = for{
      u <- Users if u.username === name
  } yield (u.firstname, u.lastname)
  val myNames = db.run(matches.result)
  myNames.map(userNames => userNames)
 }

 def getRadio(): Future[Seq[(Int, String, String, String, String, String, Option[Int])]] = {
     val matches = for{
         r <- Radio
      } yield (r.radioid, r.station, r.stnid, r.frequency, r.location, r.url, r.likes)
    val myRadios = db.run(matches.result)
    myRadios.map(rdios => rdios)
      }

def getShows(id:Int = 0) : Future[Seq[(Int, String, String, String, Option[Int])]] = {
  val matches = for{
      s <- Shows if s.radioid === id
  } yield (s.showsid, s.shows, s.showtime, s.showdesc, s.likes)
 
  val myShows = db.run(matches.result)
  myShows.map(rshows => rshows)
 }
}
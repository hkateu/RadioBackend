package models

import slick.jdbc.MySQLProfile.api._
import scala.concurrent.ExecutionContext
import models.Tables._
import scala.concurrent.Future


class RadioModel(db: Database)(implicit ec: ExecutionContext){
  
//  def getUsers(name: String): Future[Boolean]  = {
//   val matches = db.run(Users.filter(UsersRow => UsersRow.username === name).result)
//   matches.map(usersRow => usersRow.nonEmpty)
//  }

 def getNames(email: String): Future[Seq[(String, String)]] = {
  val matches = for{
      u <- Users if u.email === email
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

def getShows(id:Int = 0) : Future[Seq[(Int, String, java.sql.Timestamp, String, Option[Int])]] = {
  val matches = for{
      s <- Shows if s.radioid === id
  } yield (s.showsid, s.shows, s.showtime, s.showdesc, s.likes)
 
  val myShows = db.run(matches.result)
  myShows.map(rshows => rshows)
 }

 def validateLogin(email:String, password: String): Future[Boolean] = {
   val matches = db.run(Users.filter(UsersRow => UsersRow.email === email && UsersRow.password === password).result)
  matches.map(userRows => userRows.nonEmpty)
   }

 def checkIfUserExists(email:String): Future[Boolean] = {
   val matches = db.run(Users.filter(UsersRow => UsersRow.email === email).result)
  matches.map(userRows => userRows.nonEmpty)
   }

 def regListener(fName:String, lName:String, email: String, password: String, birthday:java.sql.Date, gender: String): Future[Boolean] = {
 val currTime = new java.sql.Timestamp(System.currentTimeMillis())   
 db.run(Users += UsersRow(-1, password, fName, lName, email, birthday, gender, currTime,currTime ))
    .map(addCount => addCount > 0)
 }

}
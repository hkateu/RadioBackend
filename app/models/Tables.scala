package models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.MySQLProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Favourites.schema ++ Radio.schema ++ Shows.schema ++ Users.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Favourites
   *  @param favid Database column favId SqlType(INT), PrimaryKey
   *  @param shows Database column shows SqlType(TEXT), Default(None)
   *  @param stations Database column stations SqlType(TEXT), Default(None)
   *  @param userid Database column userId SqlType(INT) */
  case class FavouritesRow(favid: Int, shows: Option[String] = None, stations: Option[String] = None, userid: Int)
  /** GetResult implicit for fetching FavouritesRow objects using plain SQL queries */
  implicit def GetResultFavouritesRow(implicit e0: GR[Int], e1: GR[Option[String]]): GR[FavouritesRow] = GR{
    prs => import prs._
    FavouritesRow.tupled((<<[Int], <<?[String], <<?[String], <<[Int]))
  }
  /** Table description of table favourites. Objects of this class serve as prototypes for rows in queries. */
  class Favourites(_tableTag: Tag) extends profile.api.Table[FavouritesRow](_tableTag, Some("radio"), "favourites") {
    def * = (favid, shows, stations, userid) <> (FavouritesRow.tupled, FavouritesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(favid), shows, stations, Rep.Some(userid))).shaped.<>({r=>import r._; _1.map(_=> FavouritesRow.tupled((_1.get, _2, _3, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column favId SqlType(INT), PrimaryKey */
    val favid: Rep[Int] = column[Int]("favId", O.PrimaryKey)
    /** Database column shows SqlType(TEXT), Default(None) */
    val shows: Rep[Option[String]] = column[Option[String]]("shows", O.Default(None))
    /** Database column stations SqlType(TEXT), Default(None) */
    val stations: Rep[Option[String]] = column[Option[String]]("stations", O.Default(None))
    /** Database column userId SqlType(INT) */
    val userid: Rep[Int] = column[Int]("userId")

    /** Foreign key referencing Users (database name fk_user) */
    lazy val usersFk = foreignKey("fk_user", userid, Users)(r => r.userid, onUpdate=ForeignKeyAction.Cascade, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Favourites */
  lazy val Favourites = new TableQuery(tag => new Favourites(tag))

  /** Entity class storing rows of table Radio
   *  @param radioid Database column radioId SqlType(INT), AutoInc, PrimaryKey
   *  @param station Database column station SqlType(VARCHAR), Length(50,true)
   *  @param stnid Database column stnid SqlType(VARCHAR), Length(50,true)
   *  @param frequency Database column frequency SqlType(VARCHAR), Length(50,true)
   *  @param location Database column location SqlType(VARCHAR), Length(50,true)
   *  @param url Database column url SqlType(VARCHAR), Length(255,true)
   *  @param likes Database column likes SqlType(INT), Default(None) */
  case class RadioRow(radioid: Int, station: String, stnid: String, frequency: String, location: String, url: String, likes: Option[Int] = None)
  /** GetResult implicit for fetching RadioRow objects using plain SQL queries */
  implicit def GetResultRadioRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[Int]]): GR[RadioRow] = GR{
    prs => import prs._
    RadioRow.tupled((<<[Int], <<[String], <<[String], <<[String], <<[String], <<[String], <<?[Int]))
  }
  /** Table description of table radio. Objects of this class serve as prototypes for rows in queries. */
  class Radio(_tableTag: Tag) extends profile.api.Table[RadioRow](_tableTag, Some("radio"), "radio") {
    def * = (radioid, station, stnid, frequency, location, url, likes) <> (RadioRow.tupled, RadioRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(radioid), Rep.Some(station), Rep.Some(stnid), Rep.Some(frequency), Rep.Some(location), Rep.Some(url), likes)).shaped.<>({r=>import r._; _1.map(_=> RadioRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column radioId SqlType(INT), AutoInc, PrimaryKey */
    val radioid: Rep[Int] = column[Int]("radioId", O.AutoInc, O.PrimaryKey)
    /** Database column station SqlType(VARCHAR), Length(50,true) */
    val station: Rep[String] = column[String]("station", O.Length(50,varying=true))
    /** Database column stnid SqlType(VARCHAR), Length(50,true) */
    val stnid: Rep[String] = column[String]("stnid", O.Length(50,varying=true))
    /** Database column frequency SqlType(VARCHAR), Length(50,true) */
    val frequency: Rep[String] = column[String]("frequency", O.Length(50,varying=true))
    /** Database column location SqlType(VARCHAR), Length(50,true) */
    val location: Rep[String] = column[String]("location", O.Length(50,varying=true))
    /** Database column url SqlType(VARCHAR), Length(255,true) */
    val url: Rep[String] = column[String]("url", O.Length(255,varying=true))
    /** Database column likes SqlType(INT), Default(None) */
    val likes: Rep[Option[Int]] = column[Option[Int]]("likes", O.Default(None))
  }
  /** Collection-like TableQuery object for table Radio */
  lazy val Radio = new TableQuery(tag => new Radio(tag))

  /** Entity class storing rows of table Shows
   *  @param showsid Database column showsId SqlType(INT), AutoInc, PrimaryKey
   *  @param shows Database column shows SqlType(VARCHAR), Length(255,true)
   *  @param showtime Database column showTime SqlType(DATETIME)
   *  @param likes Database column likes SqlType(INT), Default(None)
   *  @param showdesc Database column showDesc SqlType(TEXT)
   *  @param radioid Database column radioId SqlType(INT) */
  case class ShowsRow(showsid: Int, shows: String, showtime: java.sql.Timestamp, likes: Option[Int] = None, showdesc: String, radioid: Int)
  /** GetResult implicit for fetching ShowsRow objects using plain SQL queries */
  implicit def GetResultShowsRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Option[Int]]): GR[ShowsRow] = GR{
    prs => import prs._
    ShowsRow.tupled((<<[Int], <<[String], <<[java.sql.Timestamp], <<?[Int], <<[String], <<[Int]))
  }
  /** Table description of table shows. Objects of this class serve as prototypes for rows in queries. */
  class Shows(_tableTag: Tag) extends profile.api.Table[ShowsRow](_tableTag, Some("radio"), "shows") {
    def * = (showsid, shows, showtime, likes, showdesc, radioid) <> (ShowsRow.tupled, ShowsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(showsid), Rep.Some(shows), Rep.Some(showtime), likes, Rep.Some(showdesc), Rep.Some(radioid))).shaped.<>({r=>import r._; _1.map(_=> ShowsRow.tupled((_1.get, _2.get, _3.get, _4, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column showsId SqlType(INT), AutoInc, PrimaryKey */
    val showsid: Rep[Int] = column[Int]("showsId", O.AutoInc, O.PrimaryKey)
    /** Database column shows SqlType(VARCHAR), Length(255,true) */
    val shows: Rep[String] = column[String]("shows", O.Length(255,varying=true))
    /** Database column showTime SqlType(DATETIME) */
    val showtime: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("showTime")
    /** Database column likes SqlType(INT), Default(None) */
    val likes: Rep[Option[Int]] = column[Option[Int]]("likes", O.Default(None))
    /** Database column showDesc SqlType(TEXT) */
    val showdesc: Rep[String] = column[String]("showDesc")
    /** Database column radioId SqlType(INT) */
    val radioid: Rep[Int] = column[Int]("radioId")

    /** Foreign key referencing Radio (database name fk_radio) */
    lazy val radioFk = foreignKey("fk_radio", radioid, Radio)(r => r.radioid, onUpdate=ForeignKeyAction.Cascade, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Shows */
  lazy val Shows = new TableQuery(tag => new Shows(tag))

  /** Entity class storing rows of table Users
   *  @param userid Database column userId SqlType(INT), AutoInc, PrimaryKey
   *  @param password Database column password SqlType(VARCHAR), Length(50,true)
   *  @param firstname Database column firstName SqlType(VARCHAR), Length(50,true)
   *  @param lastname Database column lastName SqlType(VARCHAR), Length(50,true)
   *  @param email Database column email SqlType(VARCHAR), Length(255,true)
   *  @param birthday Database column birthday SqlType(DATE)
   *  @param gender Database column gender SqlType(VARCHAR), Length(10,true)
   *  @param createdon Database column createdOn SqlType(TIMESTAMP)
   *  @param lastlogin Database column lastLogIn SqlType(TIMESTAMP) */
  case class UsersRow(userid: Int, password: String, firstname: String, lastname: String, email: String, birthday: java.sql.Date, gender: String, createdon: java.sql.Timestamp, lastlogin: java.sql.Timestamp)
  /** GetResult implicit for fetching UsersRow objects using plain SQL queries */
  implicit def GetResultUsersRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Date], e3: GR[java.sql.Timestamp]): GR[UsersRow] = GR{
    prs => import prs._
    UsersRow.tupled((<<[Int], <<[String], <<[String], <<[String], <<[String], <<[java.sql.Date], <<[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table users. Objects of this class serve as prototypes for rows in queries. */
  class Users(_tableTag: Tag) extends profile.api.Table[UsersRow](_tableTag, Some("radio"), "users") {
    def * = (userid, password, firstname, lastname, email, birthday, gender, createdon, lastlogin) <> (UsersRow.tupled, UsersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(userid), Rep.Some(password), Rep.Some(firstname), Rep.Some(lastname), Rep.Some(email), Rep.Some(birthday), Rep.Some(gender), Rep.Some(createdon), Rep.Some(lastlogin))).shaped.<>({r=>import r._; _1.map(_=> UsersRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column userId SqlType(INT), AutoInc, PrimaryKey */
    val userid: Rep[Int] = column[Int]("userId", O.AutoInc, O.PrimaryKey)
    /** Database column password SqlType(VARCHAR), Length(50,true) */
    val password: Rep[String] = column[String]("password", O.Length(50,varying=true))
    /** Database column firstName SqlType(VARCHAR), Length(50,true) */
    val firstname: Rep[String] = column[String]("firstName", O.Length(50,varying=true))
    /** Database column lastName SqlType(VARCHAR), Length(50,true) */
    val lastname: Rep[String] = column[String]("lastName", O.Length(50,varying=true))
    /** Database column email SqlType(VARCHAR), Length(255,true) */
    val email: Rep[String] = column[String]("email", O.Length(255,varying=true))
    /** Database column birthday SqlType(DATE) */
    val birthday: Rep[java.sql.Date] = column[java.sql.Date]("birthday")
    /** Database column gender SqlType(VARCHAR), Length(10,true) */
    val gender: Rep[String] = column[String]("gender", O.Length(10,varying=true))
    /** Database column createdOn SqlType(TIMESTAMP) */
    val createdon: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("createdOn")
    /** Database column lastLogIn SqlType(TIMESTAMP) */
    val lastlogin: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("lastLogIn")

    /** Uniqueness Index over (email) (database name email) */
    val index1 = index("email", email, unique=true)
  }
  /** Collection-like TableQuery object for table Users */
  lazy val Users = new TableQuery(tag => new Users(tag))
}

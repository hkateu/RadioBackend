package models

case class UserData(email: String, password: String)
case class UInsertData(firstname:String, lastname:String, email: String, password: String, birthday:java.sql.Date, gender: String)
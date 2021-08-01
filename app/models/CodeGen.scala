package models

object CodeGen extends App{
    slick.codegen.SourceCodeGenerator.run(
        "slick.jdbc.MySQLProfile",
        "com.mysql.cj.jdbc.Driver",
        "jdbc:mysql://localhost/radio?user=herb&password=password",
        "/home/hkateu/ScalaWork/Play/slick-project/slickproject/app/",
        "models", None, None, true, false
    )
}
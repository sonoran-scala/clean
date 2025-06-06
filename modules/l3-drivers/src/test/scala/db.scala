package drivers.db

import cats.effect.IO

import java.sql.Connection
import java.sql.DriverManager
import scala.util.chaining.scalaUtilChainingOps

class DbSuite extends drivers.TestSuite:

  given tx: Transactor[IO] with

    private lazy val c: Connection =
      Class.forName("org.h2.Driver")
      val connection: Connection =
        DriverManager.getConnection("jdbc:h2:mem:", "sa", "")
      connection.setAutoCommit(false)
      connection

    override def transact[A](k: Connection => IO[A]): IO[A] =
      k(c)
        .tap(_ => c.commit())
        .recoverWith:
          case e =>
            c.rollback()
            IO.raiseError(e)

  override val counter =
    for _ <- drivers.db.init
    yield drivers.db.counter[IO]

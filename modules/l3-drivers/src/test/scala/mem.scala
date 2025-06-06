package drivers.mem

import cats.effect.IO

class MemSuite extends drivers.TestSuite:

  override val counter =
    drivers.mem.counter[IO]

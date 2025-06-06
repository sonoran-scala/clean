package drivers

import cats.effect.IO
import domain.entities.Count

trait TestSuite extends munit.CatsEffectSuite:

  def counter: IO[usecases.Counter[IO]]

  test("example test that succeeds"):

    for
      svc <- counter
      c1 <- svc.incrementAndGet
      c2 <- svc.incrementAndGet
      c3 <- svc.incrementAndGet
    yield
      assertEquals(c1, Count(1))
      assertEquals(c2, Count(2))
      assertEquals(c3, Count(3))

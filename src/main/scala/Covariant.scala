trait RepositoryCovariant[+T] { // If my function returns an object of type T it can also return an object of subtype of T
  def findAll(): List[T]
  def print(value: T): Unit // value contravariant
}

object CovariantHandler{

  def covariantPrintAll(repo: RepositoryCovariant[Animal]): Unit = {
    repo.findAll().foreach(item => println(item.name))
  }
}

object TestCovariant{

  val dogRepo: RepositoryCovariant[Dog] = new RepositoryCovariant[Dog] {
    def findAll(): List[Dog] = List(Dog("Rex"))
    def save(dog: Dog): Dog = Dog("Rex")
  }

  CovariantHandler.covariantPrintAll(dogRepo)
}
trait RepositoryInvariant[T] { // If my function accesspts a param of type T only objects of type T are accepted, if my function returns an object of type T only objects of type T are returned
  def findAll(): List[T]
  def print(value: T): Unit
}

object InvariantHandler{

  def invariantPrintAll(repo: RepositoryInvariant[Animal]): Unit = { // Animal type
    repo.findAll().foreach(item => println(item.name))
  }

  def print(repo: RepositoryInvariant[Animal], value: Animal): Unit = { // Animal type
    repo.print(value)
  }
}

object TestInvariant{

  val dog = Dog("Dog name")
  val cat = Cat("Cat name")

  val dogRepo: RepositoryInvariant[Dog] = new RepositoryInvariant[Dog] {
    def findAll() = List(Dog("Rex"))
    def save(dog: Dog): Dog = Dog("Rex")
  }

  val animalRepo: RepositoryInvariant[Animal] = new RepositoryInvariant[Animal] {
    def findAll() = List(Dog("Rex"))

    def save(dog: Dog): Dog = Dog("Rex")
  }

  InvariantHandler.invariantPrintAll(dogRepo)      // this error is expected according to documentation
  InvariantHandler.invariantPrintAll(animalRepo)

  InvariantHandler.print(dogRepo, dog)     // use animal
  InvariantHandler.print(animalRepo, dog)
}
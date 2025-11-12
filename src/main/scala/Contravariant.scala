trait RepositoryContravariantOther[-T] { // If my function accepts a param of type T it can be a subtype of type T
  def print(value: T): Unit
  def findAll(): List[T] // List[T] covariant
}

trait RepositoryContravariant[-T] {
  def print(value: T): Unit
}

val animalPrinter: RepositoryContravariant[Animal] = (a: Animal) => println(s"Animal: ${a.name}")
val dogPrinter: RepositoryContravariant[Dog] = (a: Dog) => println(s"Dog: ${a.name}")

object ContravariantHandler{
  def printName[A](consumer: RepositoryContravariant[A], value: A): Unit =
    consumer.print(value)

  def printAnimalName(consumer: RepositoryContravariant[Animal], value: Animal): Unit =
    consumer.print(value)

  def printDogName(consumer: RepositoryContravariant[Dog], value: Dog): Unit =
    consumer.print(value)
}

object TestContravariant{

  val dog = Dog("Dog name")
  val cat = Cat("Cat name")

  ContravariantHandler.printAnimalName(animalPrinter, dog)
  ContravariantHandler.printAnimalName(animalPrinter, cat)
  ContravariantHandler.printAnimalName(dogPrinter, dog)    // use animal printer
  ContravariantHandler.printAnimalName(dogPrinter, cat)    // use animal
  ContravariantHandler.printDogName(animalPrinter, dog)
  ContravariantHandler.printDogName(animalPrinter, cat)    // use dog
  ContravariantHandler.printDogName(animalPrinter, cat)    // use dog
  ContravariantHandler.printDogName(dogPrinter, dog)
  ContravariantHandler.printDogName(dogPrinter, cat)       // use dog
  ContravariantHandler.printName(animalPrinter, dog)
  ContravariantHandler.printName(animalPrinter, cat)
  ContravariantHandler.printName(dogPrinter, dog)
  ContravariantHandler.printName(dogPrinter, cat)          // use dog
}